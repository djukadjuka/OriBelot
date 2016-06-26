package mainPackage.mainClasses.searchPackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import mainPackage.mainClasses.AppCore;
import mainPackage.mainClasses.Card;
import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.gameStatePackage.MainState;

public class FinalStateTry {
	
	private FinalStateTry parent;
	private int roundsWon,roundsPlayed;
	private int whoPlays,totalPlayedCards,wonFlag,startPlayer;
	private Card targetCard;
	private ArrayList<FinalStateTry> children = null;
	private ArrayList<Card> removeCards;
	
	public int getRoundsPlayed(){
		return roundsPlayed;
	}
	
	public int getRoundsWon(){
		return roundsWon;
	}
	
	public FinalStateTry(int player,Card tc,ArrayList<Card> removeUs,FinalStateTry parent,int startPlayer){
		roundsWon = 0;	roundsPlayed = 0;
		this.parent = parent;
		this.startPlayer = startPlayer;
		if(player>4)
			whoPlays = 1;
		else
			whoPlays = player;
		
		targetCard = tc;
		removeCards = removeUs;
	}
	
	public void makeNextStates(){
		children = new ArrayList<FinalStateTry>();
		ArrayList<Card> cardsToKill = new ArrayList<Card>();
		for(Card c : removeCards){
			cardsToKill.add(c);
		}
		for(Card c : MainState.deadCards.values()){
			cardsToKill.add(c);
		}
		for(Card c : MainState.droppedCards.values()){
			cardsToKill.add(c);
		}
		FinalSimPlayer sim = new FinalSimPlayer(cardsToKill);
		
		ArrayList<Card> legalCards = new ArrayList<Card>();
		legalCards = sim.getLegalCards();
		
		/*System.out.println("");
		System.out.println("NEXT STATES FOR : " + MainState.droppedCards);
		for(Card c : legalCards){
			System.out.print(c + " | ");
		}
		System.out.println("");*/
		for(int i=0;	i<legalCards.size();	i++){
			children.add(new FinalStateTry(whoPlays+1, legalCards.get(i), removeCards, this,startPlayer));
		}
	}
	
	public void simulate(){
		MainState.droppedCards.put(whoPlays, targetCard);
		if(MainState.droppedCards.size() == 4){
			//System.out.println(MainState.droppedCards);
			calculateWin();
			propagateBack(wonFlag);
		}else{
			makeNextStates();
			for(int i=0;	i<children.size();	i++){
				children.get(i).simulate();
			}
		}
		MainState.droppedCards.remove(whoPlays);
	}
	
	public void propagateBack(int won){
		if(parent == null){
			roundsPlayed++;
			roundsWon += won;
		}else{
			parent.propagateBack(won);
		}
	}
	
	private void calculateWin(){
		Card winCard = MainState.droppedCards.get(1);
		int winIndex = 1;
		Iterator iter = MainState.droppedCards.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Entry)iter.next();
			Card tempCard = (Card) entry.getValue();
			int idx = (int) entry.getKey();
			
			if(tempCard.getCardSuit() == winCard.getCardSuit()){
				if(tempCard.getCardValue() > winCard.getCardValue()){
					winCard = tempCard;
					winIndex = idx;
				}
			}else if(tempCard.getCardSuit() == AppCore.adut && winCard.getCardSuit() != AppCore.adut){
				winCard = tempCard;
				winIndex = idx;
			}
		}
		
		if(startPlayer == Flags.COMP_TOP_ON_PLAY){
			if(winIndex == Flags.COMP_TOP_ON_PLAY || winIndex == Flags.HUMAN_ON_PLAY){
				wonFlag = 1;
			}else{
				wonFlag = 0;
			}
		}else{
			if(winIndex == Flags.COMP_LEFT_ON_PLAY || winIndex == Flags.COMP_RIGHT_ON_PLAY){
				wonFlag = 1;
			}else{
				wonFlag = 0;
			}
		}
	}
}
