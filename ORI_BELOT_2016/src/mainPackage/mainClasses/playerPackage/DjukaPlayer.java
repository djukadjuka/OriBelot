package mainPackage.mainClasses.playerPackage;

import java.util.ArrayList;

import mainPackage.mainClasses.AppCore;
import mainPackage.mainClasses.Card;
import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.gameStatePackage.MainState;

public class DjukaPlayer extends Player{
	
	@Override
	public void chooseAdut() {
		// TODO Auto-generated method stub
		
	}
	
	public void chooseAdut(int trenutniPlayer){ // posto su sad svi playeri instanca iste klase
												// salje se ovaj int ali to ce nestati
		int theAdut = 0;
		for(Card c : playerCards){
			if(c.getCardNumber() == 12){
				theAdut = c.getCardSuit();
			}
		}
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
			if(theAdut == 0){
				System.out.println("Player " + (trenutniPlayer - 1) + " has passed");
				if(trenutniPlayer - 1 == 1)
					MainState.player1Choise = "Player " + (trenutniPlayer - 1) + "has passed";
				else if(trenutniPlayer - 1 == 2)
					MainState.player2Choise = "Player " + (trenutniPlayer - 1) + "has passed";
				else
					MainState.player3Choise = "Player " + (trenutniPlayer - 1) + "has passed";
			}else{
				AppCore.adut = theAdut;
				Flags.DEAL_32 = true;
				String sysoString;
				if(theAdut == Flags.SRCE){
					sysoString = "SRCE";
				}else if(theAdut == Flags.LIST){
					sysoString = "LIST";
				}else if(theAdut == Flags.TIKVA){
					sysoString = "TIKVA";
				}else{
					sysoString = "ZIR";
				}
				System.out.println("Player " + (trenutniPlayer - 1) + " javio " + sysoString);
				if(trenutniPlayer - 1 == 1)
					MainState.player1Choise = "Player " + (trenutniPlayer - 1) + " chooses " + sysoString;
				else if(trenutniPlayer - 1 == 2)
					MainState.player2Choise = "Player " + (trenutniPlayer - 1) + " chooses " + sysoString;
				else
					MainState.player3Choise = "Player " + (trenutniPlayer - 1) + " chooses " + sysoString;
			}
			}
		}

	public void playCard() {
		ArrayList<Card> legalCards = null;
		if(MainState.droppedCards.isEmpty()){
			legalCards = playerCards;
		}else{
			legalCards = getLegalCards();
		}
		MainState.droppedCards.put(Flags.COMP_LEFT_ON_PLAY, legalCards.remove(0));
		Card targetCard = MainState.droppedCards.get(Flags.COMP_LEFT_ON_PLAY);
		
		for(int i=0;	i<playerCards.size();	i++){
			if(playerCards.get(i).getCardSuit() == targetCard.getCardSuit() &&
				playerCards.get(i).getCardNumber() == targetCard.getCardNumber()	){
				playerCards.remove(i);
				break;
			}
		}		
		MainState.leftCardNumber--;
		AppCore.getInstance().setNextToPlay(Flags.HUMAN_ON_PLAY);
		if(AppCore.getInstance().getFirstToPlay() == Flags.COMP_LEFT_ON_PLAY){
			AppCore.getInstance().setColorDown(MainState.droppedCards.get(Flags.COMP_LEFT_ON_PLAY).getCardSuit());
		}
	}
}
