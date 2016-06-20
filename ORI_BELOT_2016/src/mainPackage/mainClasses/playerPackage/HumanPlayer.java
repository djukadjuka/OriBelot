package mainPackage.mainClasses.playerPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import mainPackage.mainClasses.Card;
import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.gameStatePackage.MainState;

public class HumanPlayer {
	private ArrayList<Card> playerCards;
	
	public HumanPlayer(){
		playerCards = new ArrayList<>();
	}
	
	public void dealCardToPlayer(Card card){
		playerCards.add(card);
	}
	
	public void sortCards(){
		Collections.sort(playerCards, new Comparator<Card>() {
			@Override
			public int compare(Card o1, Card o2) {
				return o1.getCardSuit() - o2.getCardSuit();
			}
		});
		
		int srceInd = 0;
		int tikvaInd,listInd,zirInd;
		tikvaInd = listInd = zirInd = -1;
		
		if(playerCards.get(0).getCardSuit() == Flags.TIKVA)
			tikvaInd = 0;
		else if((playerCards.get(0).getCardSuit() == Flags.LIST)) {
			tikvaInd = 0;
			listInd = 0;
		}
		else if(playerCards.get(0).getCardSuit() == Flags.ZIR){
			tikvaInd = 0;
			listInd = 0;
			zirInd = 0;
		}
		
		for(int i=0; i < playerCards.size(); i++){
			if((playerCards.get(i).getCardSuit()) == Flags.TIKVA){
				tikvaInd = i;
				if(i == playerCards.size()-1)
					zirInd = playerCards.size();
				break;
			}
		}
		for(int i=0; i < playerCards.size(); i++){
			if((playerCards.get(i).getCardSuit()) == Flags.LIST){
				listInd = i;
				if(i == playerCards.size()-1)
					zirInd = playerCards.size();
				break;
			}
		}
		for(int i=0; i < playerCards.size(); i++){
			if((playerCards.get(i).getCardSuit()) == Flags.ZIR){
				zirInd = i;
				break;
			}
		}
		
		if(tikvaInd == -1)
			tikvaInd = srceInd;
		if(listInd == -1)
			listInd = tikvaInd;
		if(zirInd == -1)
			zirInd = listInd;
		
		Collections.sort(playerCards.subList(0, tikvaInd),new Compare());
		Collections.sort(playerCards.subList(tikvaInd, listInd),new Compare());
		Collections.sort(playerCards.subList(listInd, zirInd),new Compare());
		Collections.sort(playerCards.subList(zirInd, playerCards.size()),new Compare());
	}
	
	public int getCardNumber(){
		return playerCards.size();
	}
	
	public void playCard(int i){
		MainState.droppedCards.put(Flags.HUMAN_ON_PLAY, this.playerCards.remove(i));
		Flags.HUMAN_TO_DROP_CARD = false;
		Flags.PLAYER1_TO_DROP_CARD = true;
	}
	
	public void clearCards(){
		playerCards.clear();
	}
	
	public Card getCardAt(int i){
		return playerCards.get(i);
	}

	private class Compare implements Comparator<Card>{

		@Override
		public int compare(Card o1, Card o2) {
			// TODO Auto-generated method stub
			return o1.getCardNumber() - o2.getCardNumber();
		}
		
	}
}
