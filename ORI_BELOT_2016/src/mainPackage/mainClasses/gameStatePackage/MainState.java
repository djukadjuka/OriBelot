package mainPackage.mainClasses.gameStatePackage;

import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.Iterator;
import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.sun.glass.ui.Window;

import mainPackage.mainClasses.AppCore;
import mainPackage.mainClasses.Card;
import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.supportPackage.PickAdutDialog;
import mainPackage.mainClasses.supportPackage.PressableRectangle;
import mainPackage.mainClasses.supportPackage.SecondCounter;

public class MainState extends BasicGameState{
	//STVARI
	private int gameStateID;
	private PressableRectangle resetButton;
	private PressableRectangle showResultButton;			//dugme prikazuje globalni rezultat i 
	private PickAdutDialog pickAdutDialog;
	
	private Image listAdut;
	private Image zirAdut;
	private Image tikvaAdut;
	private Image srceAdut;
	private Image noAdut;
	
	//SLIKE
	private Image backgroundImage;
	
	//STATICKE
	public static HashMap<Integer, Card> droppedCards = new HashMap<>();
	public static SecondCounter secondCounter;
	public static int MOUSE_X;
	public static int MOUSE_Y;
	public static int leftCardNumber=0,topCardNumber=0,rightCardNumber=0;
	
	public static String player1Choise;
	public static String player2Choise;
	public static String player3Choise;
	
	private UnicodeFont ufont = null;
	
	public MainState(int id) throws SlickException{
		gameStateID = id;
		secondCounter = new SecondCounter();
	}
	
	/**
	 * Poziva se slicno kao konstruktor. Prvo se pozove konstruktor, onda se inicajlizuje
	 * stanje koristeci kontejner. <br/>Prvi parametar te ne interesuje, drugi argument je sam main -> ono sto pokrece igru.
	 * */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = new Image(Flags.POZADINA);
		showResultButton = new PressableRectangle(new Point(Flags.BTN_SHOW_RES_TOPLEFT_X,Flags.BTN_SHOW_RES_TOPLEFT_Y),
				 new Point(Flags.BTN_SHOW_RES_TOPRIGHT_X,Flags.BTN_SHOW_RES_TOPRIGHT_Y),
				 new Point(Flags.BTN_SHOW_RES_BOTTOMLEFT_X,Flags.BTN_SHOW_RES_BOTTOMLEFT_Y),
				 new Point(Flags.BTN_SHOW_RES_BOTTOMRIGHT_X,Flags.BTN_SHOW_RES_BOTTOMRIGHT_X),
				 new Image(Flags.BTN_SHOW_RES_IMAGE));

		resetButton = new PressableRectangle(new Point(Flags.BTN_RESET_TOPLEFT_X,Flags.BTN_RESET_TOPLEFT_Y),
				 new Point(Flags.BTN_RESET_TOPRIGHT_X,Flags.BTN_RESET_TOPRIGHT_Y),
				 new Point(Flags.BTN_RESET_BOTTOMLEFT_X,Flags.BTN_RESET_BOTTOMLEFT_Y),
				 new Point(Flags.BTN_RESET_BOTTOMRIGHT_X,Flags.BTN_RESET_BOTTOMRIGHT_X),
				 new Image(Flags.BTN_RESET_IMAGE));
		pickAdutDialog = new PickAdutDialog();
		listAdut = new Image(Flags.CHOSEN_LIST_ADUT);
		zirAdut = new Image(Flags.CHOSEN_ZIR_ADUT);
		tikvaAdut = new Image(Flags.CHOSEN_TIKVA_ADUT);
		srceAdut = new Image(Flags.CHOSEN_SRCE_ADUT);
		noAdut = new Image(Flags.CHOSEN_NO_ADUT);
		
		try {
		   Font font = new Font("Serif", Font.BOLD, 45);
		   ufont = new UnicodeFont(font, font.getSize(), font.isBold(), font.isItalic());
		   ufont.addAsciiGlyphs();
		   ufont.addGlyphs(Flags.WINDOW_WIDTH/2 - 115, Flags.WINDOW_HEIGHT/2);
		   ufont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		   ufont.loadGlyphs();
		} catch (SlickException e) {
		   e.printStackTrace();
		}
	}
	
	/**
	 * Poziva se svaki put kad treba da se prikaze nesto na ekranu.
	 * */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//g.drawImage(backgroundImage,0,0);	//Prikaz backgrounda -> zakomentarisao da brze potera igru. vratiti na krajnjim testovima.
		g.drawString("team 1 : " + AppCore.team1Score 
				    +"\nteam 2 : " + AppCore.team2Score
					+"" 
					+ "\nMis je na : X(" 
					+ MOUSE_X 
					+")| Y("
					+MOUSE_Y+")", 10, 10);
		drawInterface(g);
		drawCards(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int DELTA) throws SlickException {
		Input inp = gc.getInput();	//uzmi sav trenutni input
		calibrateMouse(inp);		//namesti koordinate misa
		
		if(Flags.ONE_CIRCLE_PHASE){
			switch(AppCore.getInstance().getNextToPlay()){
				case Flags.HUMAN_ON_PLAY:{
					if(inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
						dropSelectedCard();
					}
					break;
				}
				case Flags.COMP_RIGHT_ON_PLAY :{
					AppCore.getInstance().getBabicPlayer().playCard();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
				case Flags.COMP_TOP_ON_PLAY:{
					AppCore.getInstance().getDusicPlayer().playCard();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
				case Flags.COMP_LEFT_ON_PLAY:{
					AppCore.getInstance().getDjukaPlayer().playCard();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		
		if(Flags.HUMAN_TO_CHOOSE){
			if(inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				pickAdutDialog.humanChosesAdut(MOUSE_X, MOUSE_Y);		//HUMAN_TO_CHOSE i DEAL_32 se postavlja unutar metoda :
																		//humanChosesAdut -> xClicked -> x.isPressed
			}
		}

		if(Flags.DEAL_24){
			AppCore.getInstance().shuffleCards();
			
			for(int i=0; i < 24; i++){
				if(i%4 == 0){
					AppCore.getInstance().getHumanPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
				} else if(i%4 == 1){
					AppCore.getInstance().getBabicPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					rightCardNumber += 1;
				} else if(i%4 == 2){
					AppCore.getInstance().getDusicPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					topCardNumber += 1;
				} else{
					AppCore.getInstance().getDjukaPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					leftCardNumber += 1;
				}
			}
			AppCore.getInstance().getHumanPlayer().sortCards();
		}
		
		if(Flags.PLAYER1_TO_CHOOSE){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			AppCore.getInstance().getBabicPlayer().chooseAdut(2);
		}
		
		if(Flags.PLAYER2_TO_CHOOSE){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			AppCore.getInstance().getDusicPlayer().chooseAdut(3);
		}
		
		if(Flags.PLAYER3_TO_CHOOSE){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			AppCore.getInstance().getDjukaPlayer().chooseAdut(4);
		}
		
		if(Flags.DEAL_32){
			for(int i=24;i<32;	i++){
				if(i%4 == 0){
					AppCore.getInstance().getHumanPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
				} else if(i%4 == 1){
					AppCore.getInstance().getBabicPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					rightCardNumber += 1;
				} else if(i%4 == 2){
					AppCore.getInstance().getDusicPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					topCardNumber += 1;
				} else{
					AppCore.getInstance().getDjukaPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					leftCardNumber += 1;
				}
			}
			for (int i = 0; i < AppCore.getInstance().getBabicPlayer().getCardNumber(); i++) {
				System.out.print(AppCore.getInstance().getBabicPlayer().getCardByIndex(i) + "||");
			}
			System.out.println();
			for (int i = 0; i < AppCore.getInstance().getDusicPlayer().getCardNumber(); i++) {
				System.out.print(AppCore.getInstance().getDusicPlayer().getCardByIndex(i) + "||");
			}
			System.out.println();
			for (int i = 0; i < AppCore.getInstance().getDjukaPlayer().getCardNumber(); i++) {
				System.out.print(AppCore.getInstance().getDjukaPlayer().getCardByIndex(i) + "||");
			}
			AppCore.getInstance().getHumanPlayer().sortCards();
		}
		
		if(Flags.DECLARATIONS){
			
		}
		
		flowControl();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void flowControl(){
		
		if(Flags.DIALOG_PRESTEP){
			Flags.DIALOG_PRESTEP = false;
			Flags.PLAYER1_TO_CHOOSE = true;
			return;
		}
		
		if(Flags.CALCULATE_DECK_RESULT){
			
		}
		
		if(Flags.CALCULATE_CIRCLE_RESULT){
			Flags.CALCULATE_CIRCLE_RESULT = false;
			if(AppCore.getInstance().getBabicPlayer().getCardNumber() == 0 &&
				   AppCore.getInstance().getDjukaPlayer().getCardNumber() == 0 &&
				   AppCore.getInstance().getDusicPlayer().getCardNumber() == 0 &&
				   AppCore.getInstance().getHumanPlayer().getCardNumber() == 0
				){
					Flags.CALCULATE_DECK_RESULT = true;
					if(Flags.HUMAN_ON_PLAY == AppCore.getInstance().getFirstToPlay() || Flags.COMP_TOP_ON_PLAY == AppCore.getInstance().getFirstToPlay()){
						AppCore.team1Score += 10;
					}else{
						AppCore.team2Score += 10;
					}
			}else{
				Flags.ONE_CIRCLE_PHASE = true;
			}
		}
		if(Flags.CLEAR_CARDS_ON_TABLE_PHASE){
			Flags.CLEAR_CARDS_ON_TABLE_PHASE = false;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Flags.CALCULATE_CIRCLE_RESULT = true;
			droppedCards.clear();
		}
		
		if(Flags.CONFIG_FIRST_NO_DEAL){
			AppCore.getInstance().PRVIIGRAC();
			Flags.CLEAR_CARDS_ON_TABLE_PHASE = true;
			Flags.CONFIG_FIRST_NO_DEAL = false;
		}
		
		if(Flags.ONE_CIRCLE_PHASE){
			if(droppedCards.size()==4){
				Flags.ONE_CIRCLE_PHASE = false;
				Flags.CONFIG_FIRST_NO_DEAL = true;
			}
		}
		
		if(Flags.PLAY_PHASE_CONFIG_FIRST){
			int ftp = 0;
			int ltp = 0;
			if(Flags.ON_DEAL+1 == 5){
				ftp = 1;
				ltp = 4;
			}else{
				ftp = Flags.ON_DEAL+1;
				ltp = Flags.ON_DEAL;
			}
			AppCore.getInstance().setFirstToPlay(ftp);
			AppCore.getInstance().setNextToPlay(ftp);
			AppCore.getInstance().setLastToPlay(ltp);
			Flags.PLAY_PHASE_CONFIG_FIRST = false;
			Flags.ONE_CIRCLE_PHASE = true;
		}
		
		if(Flags.DECLARATIONS){
			Flags.DECLARATIONS = false;
			Flags.PLAY_PHASE_CONFIG_FIRST = true;
		}
		
		if(Flags.DECLARATION_PRESTEP) {
			Flags.DECLARATION_PRESTEP = false;
			Flags.DECLARATIONS = true;
		}
		
		if(Flags.DEAL_24){					//ako si podelio 24 karte, ne mozes vise 24 karte da delis i human moze da bira aduta (za sad..)
			Flags.DEAL_24 = false;
			
			if(AppCore.getInstance().getFirstToPlay() == 1)
				Flags.HUMAN_TO_CHOOSE = true;
			else if(AppCore.getInstance().getFirstToPlay() == 2)
				Flags.PLAYER1_TO_CHOOSE = true;
			else if(AppCore.getInstance().getFirstToPlay() == 3)
				Flags.PLAYER2_TO_CHOOSE = true;
			else
				Flags.PLAYER3_TO_CHOOSE = true;
		}
		
		else if(Flags.DEAL_32){					// ako si podelio 32 karte, ne mozes vise da delis 32 karte
			Flags.DEAL_32 = false;
			
			Flags.PLAYER1_TO_CHOOSE = false;
			Flags.PLAYER2_TO_CHOOSE = false;
			Flags.PLAYER3_TO_CHOOSE = false;
			Flags.HUMAN_TO_CHOOSE = false;
			
			Flags.HUMAN_TO_DROP_CARD = true;
			Flags.DECLARATION_PRESTEP = true;
			AppCore.getInstance().runThroughCards();
		}
		
		else if(Flags.PLAYER3_TO_CHOOSE){
			Flags.PLAYER3_TO_CHOOSE = false;
			if(AppCore.adut == 0)
				Flags.HUMAN_TO_CHOOSE = true;
		}
		
		else if(Flags.PLAYER2_TO_CHOOSE){
			Flags.PLAYER2_TO_CHOOSE = false;
			if(AppCore.adut == 0)
				Flags.PLAYER3_TO_CHOOSE = true;
		}
		
		else if(Flags.PLAYER1_TO_CHOOSE){
			Flags.PLAYER1_TO_CHOOSE = false;
			if(AppCore.adut == 0) {
				Flags.PLAYER2_TO_CHOOSE = true;
			}
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	private void calibrateMouse(Input in){
		MOUSE_X = in.getMouseX();
		MOUSE_Y = in.getMouseY();
	}
	
	/**
	 * Vraca id od ovog sranja. <br/>Potrebno u mainu da bi mogli da se prebacujemo iz stanja u stanje.
	 * */
	@Override
	public int getID() {
		return gameStateID;
	}

	private void drawCards(Graphics g) throws SlickException{
		int currentOffset = 0;
		
		for (int i = 0; i < AppCore.getInstance().getHumanPlayer().getCardNumber(); i++) {
			g.drawImage(AppCore.getInstance().getHumanPlayer().getCardAt(i).getCardImage(), 
					Flags.CARD_OFFSET_X + currentOffset, 
					Flags.WINDOW_HEIGHT - Flags.CARD_HEIGHT - Flags.CARD_OFFSET_Y);
			currentOffset += Flags.CARD_WIDTH + Flags.CARD_INCREMENT_OFFSET;
		}
	}
	
	private void drawDownCards(Graphics g){
		if(!droppedCards.isEmpty()){
			if(droppedCards.containsKey(Flags.HUMAN_ON_PLAY)){
				droppedCards.get(Flags.HUMAN_ON_PLAY).getCardImage().draw(Flags.WINDOW_WIDTH/2 - Flags.CARD_WIDTH/2
																		 ,Flags.WINDOW_HEIGHT/2 + Flags.CARD_HEIGHT/4);
			}
			if(droppedCards.containsKey(Flags.COMP_LEFT_ON_PLAY)){
				droppedCards.get(Flags.COMP_LEFT_ON_PLAY).getRotatedCardImage().draw(Flags.WINDOW_WIDTH/2 - 2*Flags.CARD_WIDTH
																		 ,Flags.WINDOW_HEIGHT/2 - Flags.CARD_HEIGHT/2);
			}
			if(droppedCards.containsKey(Flags.COMP_TOP_ON_PLAY)){
				droppedCards.get(Flags.COMP_TOP_ON_PLAY).getCardImage().draw(Flags.WINDOW_WIDTH/2 - Flags.CARD_WIDTH/2
																		 ,Flags.WINDOW_HEIGHT/2 - 4*Flags.CARD_HEIGHT/3 + 10);
			}
			if(droppedCards.containsKey(Flags.COMP_RIGHT_ON_PLAY)){
				droppedCards.get(Flags.COMP_RIGHT_ON_PLAY).getRotatedCardImage().draw(Flags.WINDOW_WIDTH/2 + Flags.CARD_WIDTH
																		 ,Flags.WINDOW_HEIGHT/2 - Flags.CARD_HEIGHT/2);
			}
		}
	}
	
	private void dropSelectedCard(){
		int currentOffset = 0;
		for(int i = 0; i < AppCore.getInstance().getHumanPlayer().getCardNumber();i++){
			PressableRectangle cardRec = new PressableRectangle(new Point(Flags.CARD_OFFSET_X+currentOffset,Flags.WINDOW_HEIGHT - Flags.CARD_HEIGHT - Flags.CARD_OFFSET_Y ), 
					new Point(Flags.CARD_OFFSET_X + currentOffset + Flags.CARD_WIDTH,Flags.WINDOW_HEIGHT - Flags.CARD_HEIGHT - Flags.CARD_OFFSET_Y ),
					new Point(Flags.CARD_OFFSET_X + currentOffset, Flags.WINDOW_HEIGHT), new Point(Flags.CARD_OFFSET_X + currentOffset + Flags.CARD_WIDTH, Flags.WINDOW_HEIGHT), AppCore.getInstance().getHumanPlayer().getCardAt(i).getCardImage());
			currentOffset += Flags.CARD_WIDTH + Flags.CARD_INCREMENT_OFFSET;
			if(cardRec.isPressed(MOUSE_X, MOUSE_Y)){
				AppCore.getInstance().getHumanPlayer().playCard(i);
				return;
			}
		}
		
	}
	
	

	private void drawInterface(Graphics g) throws SlickException{
		g.drawImage(resetButton.getImage(), resetButton.getTopLeft().getX(), resetButton.getTopLeft().getY());
		g.drawImage(showResultButton.getImage(), showResultButton.getTopLeft().getX(), showResultButton.getTopLeft().getY());
		drawLeftCards(g);
		drawRightCards(g);
		drawTopCards(g);
		changeAdutCorner(g);
		drawDownCards(g);
		if(Flags.HUMAN_TO_CHOOSE){
			drawHumanPickAdut(true, g);
		}
		
		if(Flags.PLAYER1_TO_CHOOSE){
			drawPlayersPicking(g,1);
		}
		
		if(Flags.PLAYER2_TO_CHOOSE){
			drawPlayersPicking(g, 2);
		}
		
		if(Flags.PLAYER3_TO_CHOOSE){
			drawPlayersPicking(g, 3);
		}
		
		if(Flags.DECLARATIONS || Flags.DECLARATION_PRESTEP){
			drawPlayerChooseAdut(g);
		}
	}
	
	private void drawPlayerChooseAdut(Graphics g){
		// PROMENITI DA PISE KO JE JAVIO
		String adut = "";
		
		if(AppCore.adut == Flags.SRCE)
			adut = "harts";
		else if(AppCore.adut == Flags.TIKVA)
			adut = "pumpkins";
		else if(AppCore.adut == Flags.ZIR)
			adut = "clubs";
		else
			adut = "spades";
		
		ufont.drawString(Flags.WINDOW_WIDTH/2-160, Flags.WINDOW_HEIGHT/2 - 40, "Adut is " + adut);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void drawPlayersPicking(Graphics g,int player){
		ufont.drawString(Flags.WINDOW_WIDTH/2 - 220, Flags.WINDOW_HEIGHT/2 - 40, "Player" + player + " to choose adut");
	}
	
	private void drawHumanPickAdut(boolean humanOnAdut,Graphics g){
		pickAdutDialog.drawDialog(g);
	}
	
	private void changeAdutCorner(Graphics g){
		int gdeX = Flags.WINDOW_WIDTH - Flags.CHOSEN_ADUT_ICON_WIDTH;
		int gdeY = Flags.WINDOW_HEIGHT - Flags.CHOSEN_ADUT_ICON_HEIGHT;
		switch(AppCore.adut){
			case 0:	{
				g.drawImage(noAdut, gdeX, gdeY);
				break;
			}
			case Flags.LIST: {
				g.drawImage(listAdut, gdeX, gdeY);
				break;
			}
			case Flags.SRCE: {
				g.drawImage(srceAdut, gdeX, gdeY);
				break;
			}
			case Flags.TIKVA: {
				g.drawImage(tikvaAdut, gdeX, gdeY);
				break;
			}
			case Flags.ZIR: {
				g.drawImage(zirAdut, gdeX, gdeY);
				break;
			}
		}
	}
	
	private void drawLeftCards(Graphics g) throws SlickException{
		if(leftCardNumber == 8){
			g.drawImage(new Image(Flags.CARD_BACK_8_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(leftCardNumber == 7){
			g.drawImage(new Image(Flags.CARD_BACK_7_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(leftCardNumber == 6){
			g.drawImage(new Image(Flags.CARD_BACK_6_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(leftCardNumber == 5){
			g.drawImage(new Image(Flags.CARD_BACK_5_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(leftCardNumber == 4){
			g.drawImage(new Image(Flags.CARD_BACK_4_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(leftCardNumber == 3){
			g.drawImage(new Image(Flags.CARD_BACK_3_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(leftCardNumber == 2){
			g.drawImage(new Image(Flags.CARD_BACK_2_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(leftCardNumber == 1){
			g.drawImage(new Image(Flags.CARD_BACK_1_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}
	}
	
	private void drawRightCards(Graphics g) throws SlickException{
		if(rightCardNumber == 8){
			g.drawImage(new Image(Flags.CARD_BACK_8_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(rightCardNumber == 7){
			g.drawImage(new Image(Flags.CARD_BACK_7_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(rightCardNumber == 6){
			g.drawImage(new Image(Flags.CARD_BACK_6_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(rightCardNumber == 5){
			g.drawImage(new Image(Flags.CARD_BACK_5_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(rightCardNumber == 4){
			g.drawImage(new Image(Flags.CARD_BACK_4_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(rightCardNumber == 3){
			g.drawImage(new Image(Flags.CARD_BACK_3_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(rightCardNumber == 2){
			g.drawImage(new Image(Flags.CARD_BACK_2_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(rightCardNumber == 1){
			g.drawImage(new Image(Flags.CARD_BACK_1_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}
	}
	
	private void drawTopCards(Graphics g) throws SlickException{
		if(topCardNumber == 8){
			g.drawImage(new Image(Flags.CARD_BACK_8_H), Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}else if(topCardNumber == 7){
			g.drawImage(new Image(Flags.CARD_BACK_7_H), Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}else if(topCardNumber == 6){
			g.drawImage(new Image(Flags.CARD_BACK_6_H), Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}else if(topCardNumber == 5){
			g.drawImage(new Image(Flags.CARD_BACK_5_H), Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}else if(topCardNumber == 4){
			g.drawImage(new Image(Flags.CARD_BACK_4_H), Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}else if(topCardNumber == 3){
			g.drawImage(new Image(Flags.CARD_BACK_3_H),Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}else if(topCardNumber == 2){
			g.drawImage(new Image(Flags.CARD_BACK_2_H),Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}else if(topCardNumber == 1){
			g.drawImage(new Image(Flags.CARD_BACK_1_H),Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}
	}
}