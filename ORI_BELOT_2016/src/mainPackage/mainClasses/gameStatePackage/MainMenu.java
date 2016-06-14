package mainPackage.mainClasses.gameStatePackage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import mainPackage.MainClass;
import mainPackage.mainClasses.AppCore;

public class MainMenu extends BasicGameState{
	private int gameStateID;
	
	public MainMenu(int id){
		gameStateID = id;
	}
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		AppCore.getInstance().shuffleCards();
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(new Image("SveKarte/PozadinaBelotManja.png"),0,0);
		g.drawImage(AppCore.getInstance().getCards().get(5).getCardImage(),50,50);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return gameStateID;
		
	}

}
