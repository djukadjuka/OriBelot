package mainPackage.mainClasses.playerPackage;

import java.util.ArrayList;

import mainPackage.mainClasses.AppCore;
import mainPackage.mainClasses.Card;
import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.gameStatePackage.MainState;

public class DusicPlayer implements Player {
	
	private ArrayList<Card> playerCards = new ArrayList<Card>();
	
	public void dealCardToPlayer(Card card){
		if(playerCards.size() < 8)
			playerCards.add(card);
	}
	public void runThrough(){
		for(int i=0;	i<8;	i++){
			if(playerCards.get(i).getCardSuit() == AppCore.adut){
				if(playerCards.get(i).getCardNumber() == 12){
					playerCards.get(i).setCardValue(20);
				}else if(playerCards.get(i).getCardNumber() == 9){
					playerCards.get(i).setCardValue(14);
				}
			}
		}
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
	public boolean imaBoju(){
		for(Card card : playerCards){
			if(card.getCardSuit() == MainState.droppedCards.get(AppCore.getInstance().getFirstToPlay()).getCardSuit()){
				return true;
			}
		}
		return false;
	}
	
	public boolean imaAduta(){
		for(Card card : playerCards){
			if(card.getCardSuit() == AppCore.adut){
				return true;
			}
		}
		return false;
	}
	
	public boolean imaJacegAduta(){
		Card najjaciAdut = null;
		for(Card c : MainState.droppedCards.values()){
			if(c.getCardSuit() == AppCore.adut){
				if(najjaciAdut == null){
					najjaciAdut = c;
				}else if(najjaciAdut.getCardValue() < c.getCardValue()){
					najjaciAdut = c;
				}
			}
		}
		
		for(Card c : playerCards){
			if(c.getCardSuit() == AppCore.adut && c.getCardValue() > najjaciAdut.getCardValue())
				return true;
		}
		
		return false;
	}

	public ArrayList<Card> dajJaceAdute(){
		ArrayList<Card> ret = new ArrayList<Card>();
		Card najjaciAdut = null;
		for(Card c : MainState.droppedCards.values()){
			if(c.getCardSuit() == AppCore.adut){
				if(najjaciAdut == null){
					najjaciAdut = c;
				}else if(najjaciAdut.getCardValue() < c.getCardValue()){
					najjaciAdut = c;
				}
			}
		}
		for(Card c : playerCards){
			if(c.getCardSuit() == AppCore.adut && c.getCardValue() > najjaciAdut.getCardValue()){
				ret.add(c);
			}
		}	
		return ret;
	}
	
	public ArrayList<Card> dajSveAdute(){
		ArrayList<Card> ret = new ArrayList<Card>();
		for(Card c : playerCards){
			if(c.getCardSuit() == AppCore.adut){
				ret.add(c);
			}
		}
		return ret;
	}

	public boolean seceno(){
		for(Card card : MainState.droppedCards.values()){
			if(card.getCardSuit() == AppCore.adut){
				return true;
			}
		}		
		return false;
	}
	
	public ArrayList<Card> dajSveUBoji(int boja){
		ArrayList<Card> ret = new ArrayList<Card>();
		for(Card card : playerCards ){
			if(card.getCardSuit() == boja){
				ret.add(card);
			}
		}
		return ret;
	}
	
	public boolean imaJacuBoju(int boja){
		Card najjaciCard = null;
		for(Card c : MainState.droppedCards.values()){
			if(c.getCardSuit() == boja){
				if(najjaciCard == null){
					najjaciCard = c;
				}else if(najjaciCard.getCardValue() < c.getCardValue()){
					najjaciCard = c;
				}
			}
		}
		
		for(Card c : playerCards){
			if(c.getCardSuit() == boja && c.getCardValue() > najjaciCard.getCardValue())
				return true;
		}
		
		return false;
	}
	
	public ArrayList<Card> dajSveJaceUBoji(int boja){
		ArrayList<Card> ret = new ArrayList<Card>();
		Card najjacaUBojiNaPolju = null;
		for(Card c : MainState.droppedCards.values()){
			if(najjacaUBojiNaPolju == null){
				if(c.getCardSuit() == boja){
					najjacaUBojiNaPolju = c;
				}
			}else if(najjacaUBojiNaPolju.getCardValue() < c.getCardValue() && c.getCardSuit() == boja){
				najjacaUBojiNaPolju = c;
			}
		}
		for(Card c : playerCards){
			if(c.getCardValue() > najjacaUBojiNaPolju.getCardValue() && c.getCardSuit() == boja){
				ret.add(c);
				System.out.println(c);
			}
		}
		return ret;
	}
	
	public ArrayList<Card> getLegalCards(){
		Card firstCard = MainState.droppedCards.get(AppCore.getInstance().getFirstToPlay());
		System.out.println(firstCard);
		System.out.println("\nBabiscese : ");
		if(firstCard.getCardSuit() == AppCore.adut){			// adut
			System.out.print("-Adut dole");
			if(imaAduta()){										//ima aduta
				System.out.print("-ima aduta");
				if(imaJacegAduta()){							//ima jaceg aduta
					System.out.print("-ima jaceg");
					return dajJaceAdute();
				}else{											//nema jaceg aduta
					System.out.print("-nema jaceg");
					return dajSveAdute();
				}
			}else{												//nema aduta
				System.out.print("-nema aduta");
				return playerCards;
			}
		}else{							//nije adut
			System.out.print("-Prva nije adut");
			if(seceno()){			//seceno
				System.out.print("-neko seko");
				if(imaBoju()){									//ima boju
					System.out.print("-ima boju");
					return dajSveUBoji(firstCard.getCardSuit());
				}else{											//nema boju
					System.out.print("-nema boju");
					if(imaAduta()){								//ima aduta
						System.out.print("-ima aduta");
						if(imaJacegAduta()){
							System.out.print("-ima jaceg aduta");
							return dajJaceAdute();
						}else{
							System.out.print("-nema jaceg aduta");
							return dajSveAdute();
						}
					}else{										//nema aduta
						System.out.print("-mane aduta");
						return playerCards;
					}
				}
			}else{												//neseceno
				System.out.print("-niko seko");
				if(imaBoju()){
					System.out.print("-ima boju");
					if(imaJacuBoju(firstCard.getCardSuit())){
						System.out.print("-ima jacu boju");
						return dajSveJaceUBoji(firstCard.getCardSuit());
					}else{
						System.out.print("-nema jacu boju");
						return dajSveUBoji(firstCard.getCardSuit());
					}
				}else{
					System.out.print("-nema boju");
					if(imaAduta()){
						System.out.print("-ima aduta");
						return dajSveAdute();
					}else{
						System.out.print("-mane aduta");
						return playerCards;
					}
				}
			}
		}
	}
	
	@Override
	public void playCard() {
		ArrayList<Card> legalCards = null;
		if(MainState.droppedCards.isEmpty()){
			legalCards = playerCards;
		}else{
			legalCards = getLegalCards();
		}
		MainState.droppedCards.put(Flags.COMP_TOP_ON_PLAY, legalCards.remove(0));
		Card targetCard = MainState.droppedCards.get(Flags.COMP_TOP_ON_PLAY);
		
		for(int i=0;	i<playerCards.size();	i++){
			if(playerCards.get(i).getCardSuit() == targetCard.getCardSuit() &&
				playerCards.get(i).getCardNumber() == targetCard.getCardNumber()	){
				playerCards.remove(i);
				break;
			}
		}		
		MainState.topCardNumber--;
		
		/*if(AppCore.getInstance().getLastToPlay() == Flags.COMP_TOP_ON_PLAY){
			Flags.ONE_CIRCLE_PHASE = false;
		}else{
			AppCore.getInstance().setNextToPlay(Flags.COMP_LEFT_ON_PLAY);
		}*/
		AppCore.getInstance().setNextToPlay(Flags.COMP_LEFT_ON_PLAY);
		if(AppCore.getInstance().getFirstToPlay() == Flags.COMP_TOP_ON_PLAY){
			AppCore.getInstance().setColorDown(MainState.droppedCards.get(Flags.COMP_TOP_ON_PLAY).getCardSuit());
		}
	}

}
