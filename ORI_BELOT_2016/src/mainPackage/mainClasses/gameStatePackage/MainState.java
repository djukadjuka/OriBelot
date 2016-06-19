package mainPackage.mainClasses.gameStatePackage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import mainPackage.mainClasses.AppCore;
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
	public static SecondCounter secondCounter;
	public static int MOUSE_X;
	public static int MOUSE_Y;
	public static int leftCardNumber=0,topCardNumber=0,rightCardNumber=0;
	
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
	}
	
	/**
	 * Poziva se svaki put kad treba da se prikaze nesto na ekranu.
	 * */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//g.drawImage(backgroundImage,0,0);	//Prikaz backgrounda -> zakomentarisao da brze potera igru. vratiti na krajnjim testovima.
		g.drawString("Proslo sekundi : " 
					+ secondCounter.getSeconds() 
					+ "\nProslo milisekundi : " 
					+ secondCounter.getMilliseconds()
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
		
//		
		if(Flags.HUMAN_TO_CHOOSE){
			if(inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				pickAdutDialog.humanChosesAdut(MOUSE_X, MOUSE_Y);		//HUMAN_TO_CHOSE i DEAL_32 se postavlja unutar metoda :
																		//humanChosesAdut -> xClicked -> x.isPressed
			}
		}
//		if(Flags.DEAL_32){
//			try{
//				Thread.sleep(300);
//			}catch(InterruptedException e){
//				e.printStackTrace();
//			}
//			for(int i=24;i<32;	i++){
//				if(i%4 == 0){
//					AppCore.getInstance().getHumanPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
//				} else if(i%4 == 1){
//					AppCore.getInstance().getPlayer1().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
//					rightCardNumber += 1;
//				} else if(i%4 == 2){
//					AppCore.getInstance().getPlayer2().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
//					topCardNumber += 1;
//				} else{
//					AppCore.getInstance().getPlayer3().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
//					leftCardNumber += 1;
//				}
//			}
//			AppCore.getInstance().getHumanPlayer().sortCards();
//			AppCore.getInstance().declarations();
//		}

		//
		if(Flags.DEAL_24){
			AppCore.getInstance().shuffleCards();
			
			for(int i=0; i < 24; i++){
				if(i%4 == 0){
					AppCore.getInstance().getHumanPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
				} else if(i%4 == 1){
					AppCore.getInstance().getPlayer1().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					rightCardNumber += 1;
				} else if(i%4 == 2){
					AppCore.getInstance().getPlayer2().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					topCardNumber += 1;
				} else{
					AppCore.getInstance().getPlayer3().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
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
			AppCore.getInstance().getPlayer1().chooseAdut(2);
		}
//		if(Flags.HUMAN_TO_CHOOSE){
//			if(inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
//				pickAdutDialog.humanChosesAdut(MOUSE_X, MOUSE_Y);		//HUMAN_TO_CHOSE i DEAL_32 se postavlja unutar metoda :
//																		//humanChosesAdut -> xClicked -> x.isPressed
//			}
//		}
		
		if(Flags.PLAYER2_TO_CHOOSE){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			AppCore.getInstance().getPlayer2().chooseAdut(3);
		}
		
		if(Flags.PLAYER3_TO_CHOOSE){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			AppCore.getInstance().getPlayer3().chooseAdut(4);
		}
		
		if(Flags.DEAL_32){
			for(int i=24;i<32;	i++){
				if(i%4 == 0){
					AppCore.getInstance().getHumanPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
				} else if(i%4 == 1){
					AppCore.getInstance().getPlayer1().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					rightCardNumber += 1;
				} else if(i%4 == 2){
					AppCore.getInstance().getPlayer2().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					topCardNumber += 1;
				} else{
					AppCore.getInstance().getPlayer3().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					leftCardNumber += 1;
				}
			}
			AppCore.getInstance().getHumanPlayer().sortCards();
		}
		
		flowControl();
	}
	
	private void flowControl(){
		
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
			Flags.PLAYER3_TO_CHOOSE = false;
			Flags.DECLARATIONS = true;
		}
		
		else if(Flags.PLAYER3_TO_CHOOSE){
			Flags.PLAYER3_TO_CHOOSE = false;
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
//		for(int i=0;	i<8;	i++){
//			g.drawImage(AppCore.getInstance().getCards().get(i).getCardImage(), 
//						Flags.CARD_OFFSET_X + currentOffset, 
//						Flags.WINDOW_HEIGHT - Flags.CARD_HEIGHT - Flags.CARD_OFFSET_Y);
//			currentOffset += Flags.CARD_WIDTH + Flags.CARD_INCREMENT_OFFSET;
//		}
//		HumanPlayer hm = new HumanPlayer();
//		for(int i=0; i < 8; i++){
//			hm.dealCardToPlayer(AppCore.getInstance().getCards().get(i));
//		}
//		hm.sortCards();
//		
//		for(int i=0; i < 8; i++){
//			g.drawImage(hm.getCardAt(i).getCardImage(), 
//					Flags.CARD_OFFSET_X + currentOffset, 
//					Flags.WINDOW_HEIGHT - Flags.CARD_HEIGHT - Flags.CARD_OFFSET_Y);
//			currentOffset += Flags.CARD_WIDTH + Flags.CARD_INCREMENT_OFFSET;
//		}
		
		for (int i = 0; i < AppCore.getInstance().getHumanPlayer().getCardNumber(); i++) {
			g.drawImage(AppCore.getInstance().getHumanPlayer().getCardAt(i).getCardImage(), 
					Flags.CARD_OFFSET_X + currentOffset, 
					Flags.WINDOW_HEIGHT - Flags.CARD_HEIGHT - Flags.CARD_OFFSET_Y);
			currentOffset += Flags.CARD_WIDTH + Flags.CARD_INCREMENT_OFFSET;
		}
	}

	private void drawInterface(Graphics g) throws SlickException{
		g.drawImage(resetButton.getImage(), resetButton.getTopLeft().getX(), resetButton.getTopLeft().getY());
		g.drawImage(showResultButton.getImage(), showResultButton.getTopLeft().getX(), showResultButton.getTopLeft().getY());
		drawLeftCards(g);
		drawRightCards(g);
		drawTopCards(g);
		changeAdutCorner(g);
		
		if(Flags.HUMAN_TO_CHOOSE){
			drawHumanPickAdut(true, g);
		}
		
		if(Flags.PLAYER1_TO_CHOOSE){
			drawPlayersChoises(g);
		}
	}
	
	private void drawPlayersChoises(Graphics g){
		g.drawString("LUUUUUUUD", 40, 40);
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