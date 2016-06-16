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
import mainPackage.mainClasses.supportPackage.PressableRectangle;
import mainPackage.mainClasses.supportPackage.SecondCounter;

public class MainState extends BasicGameState{
	//STVARI
	private int gameStateID;
	private PressableRectangle resetButton;
	private PressableRectangle showResultButton;			//dugme prikazuje globalni rezultat i 
	private PressableRectangle pickAdutButton;
	
	//SLIKE
	private Image backgroundImage;
	
	//STATICKE
	public static SecondCounter secondCounter;
	public static int MOUSE_X;
	public static int MOUSE_Y;
	
	public MainState(int id) throws SlickException{
		gameStateID = id;
		secondCounter = new SecondCounter();
	}
	/**
	 * Poziva se slicno kao konstruktor. Prvo se pozove konstruktor, onda se inicajlizuje
	 * stanje koristeci kontejner. <br/>Prvi parametar te ne interesuje, drugi argument je sam main -> ono sto pokrece igru.
	 * */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		AppCore.getInstance().shuffleCards();
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
		
		pickAdutButton = new PressableRectangle(new Point(Flags.BTN_PICK_ADUT_TOPLEFT_X,Flags.BTN_PICK_ADUT_TOPLEFT_Y), 
				                                new Point(Flags.BTN_PICK_ADUT_TOPRIGHT_X,Flags.BTN_PICK_ADUT_TOPRIGHT_Y), 
				                                new Point(Flags.BTN_PICK_ADUT_BOTTOMLEFT_X,Flags.BTN_PICK_ADUT_BOTTOMLEFT_Y), 
				                                new Point(Flags.BTN_PICK_ADUT_BOTTOMRIGHT_X,Flags.BTN_PICK_ADUT_BOTTOMRIGHT_Y), 
				                                new Image(Flags.BTN_PICK_ADUT_IMAGE));
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
	}
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int DELTA) throws SlickException {
		secondCounter.calculateSec(DELTA);
		
		Input inp = gc.getInput();
		
		/**Nastimaj misa*/
		configureMouseXY(inp);
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
	private void drawInterface(Graphics g){
		g.drawImage(resetButton.getImage(), resetButton.getTopLeft().getX(), resetButton.getTopLeft().getY());
		g.drawImage(showResultButton.getImage(), showResultButton.getTopLeft().getX(), showResultButton.getTopLeft().getY());
		g.drawImage(pickAdutButton.getImage(), pickAdutButton.getTopLeft().getX(), pickAdutButton.getTopLeft().getY());
	}
}
