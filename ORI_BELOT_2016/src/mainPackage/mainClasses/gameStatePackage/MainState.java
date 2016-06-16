package mainPackage.mainClasses.gameStatePackage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import mainPackage.mainClasses.AppCore;
import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.supportPackage.SecondCounter;
import net.java.games.input.Mouse;

public class MainState extends BasicGameState{
	//STVARI
	private int gameStateID;
	
	//SLIKE
	private Image backgroundImage;
	private Image resetButton;
	
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
		resetButton = new Image(Flags.BTN_RESET);
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
		if(resetClicked){
			g.drawString("\n\n\nReset clicked", 10, 10);
		}else{
			g.drawString("\n\n\nReset not clicked", 10, 10);
		}
		if(cardClicked){
			g.drawString("\n\n\n\nCard Clicked : YES", 10, 10);
		}else{
			g.drawString("\n\n\n\nCard Clicked : NO", 10, 10);
		}
		drawCardsTest(g);
		drawButtons(g);
	}
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int DELTA) throws SlickException {
		secondCounter.calculateSec(DELTA);
		
		Input inp = gc.getInput();
		
		/**Nastimaj misa*/
		configureMouseXY(inp);
		doReset(inp);
		doCardClickCheck(inp);
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
	private void drawCardsTest(Graphics g){
		int currentOffset = 0;
		for(int i=0;	i<8;	i++){
			g.drawImage(AppCore.getInstance().getCards().get(i).getCardImage(), 
						Flags.CARD_OFFSET_X + currentOffset, 
						Flags.WINDOW_HEIGHT - Flags.CARD_HEIGHT - Flags.CARD_OFFSET_Y);
			currentOffset += Flags.CARD_WIDTH + Flags.CARD_INCREMENT_OFFSET;
		}
	}
	private void drawButtons(Graphics g){
		g.drawImage(resetButton,Flags.BTN_RESET_TOPLEFT_X,Flags.BTN_RESET_TOPLEFT_Y);
	}
	
	private boolean resetClicked = false;
	
	private void doReset(Input inp){
		if(inp.isMousePressed(0)){
			if(   MOUSE_X < Flags.BTN_RESET_TOPRIGHT_X 
			   && MOUSE_X < Flags.BTN_RESET_BOTTOMRIGHT_X
			   && MOUSE_X > Flags.BTN_RESET_TOPLEFT_X
			   && MOUSE_X > Flags.BTN_RESET_BOTTOMLEFT_X
			   
			   && MOUSE_Y < Flags.BTN_RESET_BOTTOMRIGTH_Y
			   && MOUSE_Y < Flags.BTN_RESET_BOTTOMLEFT_Y
			   && MOUSE_Y > Flags.BTN_RESET_TOPLEFT_Y
			   && MOUSE_Y > Flags.BTN_RESET_TOPRIGHT_Y){
				resetClicked = true;
			}else{
				resetClicked = false;
			}
			int offset = 200;
			for(int i=0;	i<8;	i++){
				if(   MOUSE_X > (Flags.CARD_TOPLEFT_X + offset)
				   && MOUSE_X > (Flags.CARD_BOTTOMLEFT_X + offset)
				   && MOUSE_X < (Flags.CARD_TOPRIGHT_X + offset)
				   && MOUSE_X < (Flags.CARD_BOTTOMRIGHT_X + offset)
				   
				   && MOUSE_Y > (Flags.CARD_TOPLEFT_Y)
				   && MOUSE_Y > (Flags.CARD_TOPRIGHT_Y)
				   && MOUSE_Y < (Flags.CARD_BOTTOMLEFT_Y)
				   && MOUSE_Y < (Flags.CARD_BOTTOMRIGHT_Y)){
					cardClicked = true;
					break;
				}else{
					cardClicked = false;
				}
				offset += Flags.CARD_INCREMENT_OFFSET + Flags.CARD_WIDTH;
			}
		}
	}
	private void doCardClickCheck(Input inp){
		if(inp.isMousePressed(0)){
			int offset = 200;
			for(int i=0;	i<8;	i++){
				if(   MOUSE_X > (Flags.CARD_TOPLEFT_X + offset)
				   && MOUSE_X > (Flags.CARD_BOTTOMLEFT_X + offset)
				   && MOUSE_X < (Flags.CARD_TOPRIGHT_X + offset)
				   && MOUSE_X < (Flags.CARD_BOTTOMRIGHT_X + offset)
				   
				   && MOUSE_Y > (Flags.CARD_TOPLEFT_Y)
				   && MOUSE_Y > (Flags.CARD_TOPRIGHT_Y)
				   && MOUSE_Y < (Flags.CARD_BOTTOMLEFT_Y)
				   && MOUSE_Y < (Flags.CARD_BOTTOMRIGHT_Y)){
					cardClicked = true;
					break;
				}else{
				}
				offset += Flags.CARD_INCREMENT_OFFSET + Flags.CARD_WIDTH;
			}
		}
	}
	private boolean cardClicked = false;
	
	private void doMouseClick(Input inp){
		if(inp.isMousePressed(0)){
			int offset = 200;
			for(int i=0;	i<8;	i++){///da li je karta pritisnuta
				if(   MOUSE_X > (Flags.CARD_TOPLEFT_X + offset)
				   && MOUSE_X > (Flags.CARD_BOTTOMLEFT_X + offset)
				   && MOUSE_X < (Flags.CARD_TOPRIGHT_X + offset)
				   && MOUSE_X < (Flags.CARD_BOTTOMRIGHT_X + offset)
				   
				   && MOUSE_Y > (Flags.CARD_TOPLEFT_Y)
				   && MOUSE_Y > (Flags.CARD_TOPRIGHT_Y)
				   && MOUSE_Y < (Flags.CARD_BOTTOMLEFT_Y)
				   && MOUSE_Y < (Flags.CARD_BOTTOMRIGHT_Y)){
					cardClicked = true;
					break;
				}else{
					cardClicked = false;
				}
				offset += Flags.CARD_INCREMENT_OFFSET + Flags.CARD_WIDTH;
			}///kraj da li je karta pritisnuta
			
			if(   MOUSE_X < Flags.BTN_RESET_TOPRIGHT_X 
			   && MOUSE_X < Flags.BTN_RESET_BOTTOMRIGHT_X
			   && MOUSE_X > Flags.BTN_RESET_TOPLEFT_X
			   && MOUSE_X > Flags.BTN_RESET_BOTTOMLEFT_X
					   
					   && MOUSE_Y < Flags.BTN_RESET_BOTTOMRIGTH_Y
					   && MOUSE_Y < Flags.BTN_RESET_BOTTOMLEFT_Y
					   && MOUSE_Y > Flags.BTN_RESET_TOPLEFT_Y
					   && MOUSE_Y > Flags.BTN_RESET_TOPRIGHT_Y){
						resetClicked = true;
					}else{
						resetClicked = false;
					}
		}
	}
}
