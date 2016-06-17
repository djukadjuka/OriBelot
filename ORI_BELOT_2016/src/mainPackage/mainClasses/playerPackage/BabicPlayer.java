package mainPackage.mainClasses.playerPackage;

import java.util.ArrayList;

import mainPackage.mainClasses.Card;

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
			
		if(flag1 == false){
			for(int i = 0; i < playerCards.size(); i++){
				Card c = playerCards.get(i);
				c.setPlayable(true);
			}
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