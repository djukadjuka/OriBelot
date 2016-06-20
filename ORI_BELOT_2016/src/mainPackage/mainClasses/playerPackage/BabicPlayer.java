package mainPackage.mainClasses.playerPackage;

import java.util.ArrayList;

import mainPackage.mainClasses.AppCore;
import mainPackage.mainClasses.Card;
import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.gameStatePackage.MainState;

public class BabicPlayer implements Player{

	private ArrayList<Card> playerCards = new ArrayList<Card>();
	
	public void dealCardToPlayer(Card card){
		if(playerCards.size() < 8)
			playerCards.add(card);
	}
	
	public Card getCardFromPlayer(Card card){
		for(int i = 0; i < playerCards.size(); i++){
			if(playerCards.get(i) == card){
				return playerCards.get(i);
			}
		}
		return null;
	}
	
	public Card getCardByIndex(int i){
		return playerCards.get(i);
	}
	
	public void dropCard(Card card){
		playerCards.remove(card);
	}
	
	public void dropCardIndex(int i){
		playerCards.remove(i);
	}
	
	
	// izvuci u interface
	public void chooseAdut(int trenutniPlayer){ // posto su sad svi playeri instanca iste klase
												// salje se ovaj int ali to ce nestati
		if(AppCore.getInstance().getLastToPlay() == trenutniPlayer) {
			AppCore.adut = Flags.SRCE;
			Flags.DEAL_32 = true;
			System.out.println("Player " + (trenutniPlayer - 1) + " javio srce");
			if(trenutniPlayer - 1 == 1)
				MainState.player1Choise = "Player " + (trenutniPlayer - 1) + " chooses harts";
			else if(trenutniPlayer - 1 == 2)
				MainState.player2Choise = "Player " + (trenutniPlayer - 1) + " chooses harts";
			else
				MainState.player3Choise = "Player " + (trenutniPlayer - 1) + " chooses harts";
		} else{
			System.out.println("Player " + (trenutniPlayer - 1) + " has passed");
			
			if(trenutniPlayer - 1 == 1)
				MainState.player1Choise = "Player " + (trenutniPlayer - 1) + "has passed";
			else if(trenutniPlayer - 1 == 2)
				MainState.player2Choise = "Player " + (trenutniPlayer - 1) + "has passed";
			else
				MainState.player3Choise = "Player " + (trenutniPlayer - 1) + "has passed";
		}
		
		Flags.PLAYER1_CHOISE = true;
	}
	
	public void makePlayableCards(Card card,int AdutColor){
		boolean flag = false;
		for(int i = 0; i < playerCards.size(); i++){
			Card c = playerCards.get(i);
			if(card.getCardSuit() == c.getCardSuit()){
				c.setPlayable(true);
				flag = true;
			}
		}
		boolean flag1 = false;
		
		if(flag == false){
			for(int i = 0; i < playerCards.size(); i++){
				Card c = playerCards.get(i);
				if(c.isAdut(AdutColor)){
					c.setPlayable(true);
					flag1 = true;
				}
			}
		}	
		if(flag1 == false){
			for(int i = 0; i < playerCards.size(); i++){
				Card c = playerCards.get(i);
				c.setPlayable(true);
			}
		}
	}
	
	public int getCardNumber(){
		return playerCards.size();
	}

	public void clearCards(){
		playerCards.clear();
	}
	
	@Override
	public void playCard() {
		// TODO Auto-generated method stub
		
	}

}