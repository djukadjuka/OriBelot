package mainPackage.mainClasses.playerPackage;

import java.util.ArrayList;

import mainPackage.mainClasses.Card;

public class HumanPlayer {
	private ArrayList<Card> playerCards;
	
	public HumanPlayer(){
		playerCards = new ArrayList<>();
	}
	public void dealCardToPlayer(Card card){
		playerCards.add(card);
	}
}
