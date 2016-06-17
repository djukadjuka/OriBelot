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
import mainPackage.mainClasses.Card;
import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.playerPackage.HumanPlayer;
import mainPackage.mainClasses.supportPackage.PickAdutDialog;
import mainPackage.mainClasses.supportPackage.PressableRectangle;
import mainPackage.mainClasses.supportPackage.SecondCounter;
import net.java.games.input.Mouse;

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
	
	boolean sec = false;
	boolean sec2 = false;
	
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
		secondCounter.calculateSec(DELTA);
		
		Input inp = gc.getInput();
		
		if(sec2){
			sec = false;
		}
		
		if(sec){
			AppCore.getInstance().chooseAdut();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			for(int i=24; i < 32; i++){
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
			
			AppCore.getInstance().declarations();
			System.out.println("!");
			sec2 = true; 
		}
		
		if(AppCore.getInstance().getHumanPlayer().getCardNumber() == 0 && // pocinje nova mesnja
				AppCore.getInstance().getPlayer1().getCardNumber() == 0 &&
				AppCore.getInstance().getPlayer2().getCardNumber() == 0 &&
				AppCore.getInstance().getPlayer3().getCardNumber() == 0)
		{
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
			sec = true;
			AppCore.getInstance().getHumanPlayer().sortCards();
		}
	}
	
	/**
	 * Vraca id od ovog sranja. <br/>Potrebno u mainu da bi mogli da se prebacujemo iz stanja u stanje.
	 * */
	@Override
	public int getID() {
		return gameStateID;
	}
	/**
	 * Metoda koja konfigure koordinate misa.<br/>
	 * Napisao posebno jer ce u update da se desavaju sokovi.<br/>
	 * Pa cisto da izgleda lepse.
	 * */
	private void configureMouseXY(Input inp){
		MOUSE_X = inp.getMouseX();
		MOUSE_Y = inp.getMouseY();
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
		
	}
	private void drawHumanPickAdut(boolean humanOnAdut,Graphics g){
		pickAdutDialog.drawDialog(g, true);
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
