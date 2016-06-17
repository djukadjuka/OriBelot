package mainPackage.mainClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import mainPackage.mainClasses.playerPackage.BabicPlayer;
import mainPackage.mainClasses.playerPackage.HumanPlayer;
import mainPackage.mainClasses.supportPackage.Result;

public class AppCore {
	public static int adut = 0;
	private static AppCore instance;
	
	private ArrayList<Card> cards;
	private ArrayList<Result> shuffleResults;
	private Result fullResult;
	private Result roundsWon;
	private int nextToPlay = 1;
	private HumanPlayer hm = new HumanPlayer();
	private BabicPlayer p1 = new BabicPlayer();
	private BabicPlayer p2 = new BabicPlayer();
	private BabicPlayer p3 = new BabicPlayer();
	
	private AppCore(){
		init();
	}
	private void init(){
		fullResult = new Result();
		shuffleResults = new ArrayList<Result>();
		roundsWon = new Result();
		cards = new ArrayList<Card>();
	}
	public static AppCore getInstance(){
		if(instance == null){
			instance = new AppCore();
		}
		return instance;
	}
	public void shuffleCards(){
		long seed = System.nanoTime();	// za random seed
		cards.clear();					// da se pobiju sve karte
		/** lista mora biti prazna pre ovoga inace sokovi*/
		try {
			initCards();				// napuni listu sa svim kartama
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
		Collections.shuffle(cards, new Random(seed));	//promesaj sve karte
	}
	public void initCards() throws SlickException{
		cards.add(new Card("Srce 7",0,Flags.SRCE,7,new Image("Karte/SRCE_7.jpg"),new Image("Karte/SRCE_7.jpg")));
		cards.add(new Card("Srce 8",0,Flags.SRCE,8,new Image("Karte/SRCE_8.jpg"),new Image("Karte/SRCE_8.jpg")));
		cards.add(new Card("Srce 9",0,Flags.SRCE,9,new Image("Karte/SRCE_9.jpg"),new Image("Karte/SRCE_9.jpg")));
		cards.add(new Card("Srce 10",10,Flags.SRCE,10,new Image("Karte/SRCE_10.jpg"),new Image("Karte/SRCE_10.jpg")));
		cards.add(new Card("Srce J",2,Flags.SRCE,12,new Image("Karte/SRCE_J.jpg"),new Image("Karte/SRCE_J.jpg")));
		cards.add(new Card("Srce Q",3,Flags.SRCE,13,new Image("Karte/SRCE_Q.jpg"),new Image("Karte/SRCE_Q.jpg")));
		cards.add(new Card("Srce K",4,Flags.SRCE,14,new Image("Karte/SRCE_K.jpg"),new Image("Karte/SRCE_K.jpg")));
		cards.add(new Card("Srce A",11,Flags.SRCE,15,new Image("Karte/SRCE_A.jpg"),new Image("Karte/SRCE_A.jpg")));
		
		cards.add(new Card("List 7",0,Flags.LIST,7,new Image("Karte/LIST_7.jpg"),new Image("Karte/LIST_7.jpg")));
		cards.add(new Card("List 8",0,Flags.LIST,8,new Image("Karte/LIST_8.jpg"),new Image("Karte/LIST_8.jpg")));
		cards.add(new Card("List 9",0,Flags.LIST,9,new Image("Karte/LIST_9.jpg"),new Image("Karte/LIST_9.jpg")));
		cards.add(new Card("List 10",10,Flags.LIST,10,new Image("Karte/LIST_10.jpg"),new Image("Karte/LIST_10.jpg")));
		cards.add(new Card("List J",2,Flags.LIST,12,new Image("Karte/LIST_J.jpg"),new Image("Karte/LIST_J.jpg")));
		cards.add(new Card("List Q",3,Flags.LIST,13,new Image("Karte/LIST_Q.jpg"),new Image("Karte/LIST_Q.jpg")));
		cards.add(new Card("List K",4,Flags.LIST,14,new Image("Karte/LIST_K.jpg"),new Image("Karte/LIST_K.jpg")));
		cards.add(new Card("List A",11,Flags.LIST,15,new Image("Karte/LIST_A.jpg"),new Image("Karte/LIST_A.jpg")));
		
		cards.add(new Card("Zir 7",0,Flags.ZIR,7,new Image("Karte/ZIR_7.jpg"),new Image("Karte/ZIR_7.jpg")));
		cards.add(new Card("Zir 8",0,Flags.ZIR,8,new Image("Karte/ZIR_8.jpg"),new Image("Karte/ZIR_8.jpg")));
		cards.add(new Card("Zir 9",0,Flags.ZIR,9,new Image("Karte/ZIR_9.jpg"),new Image("Karte/ZIR_9.jpg")));
		cards.add(new Card("Zir 10",10,Flags.ZIR,10,new Image("Karte/ZIR_10.jpg"),new Image("Karte/ZIR_10.jpg")));
		cards.add(new Card("Zir J",2,Flags.ZIR,12,new Image("Karte/ZIR_J.jpg"),new Image("Karte/ZIR_J.jpg")));
		cards.add(new Card("Zir Q",3,Flags.ZIR,13,new Image("Karte/ZIR_Q.jpg"),new Image("Karte/ZIR_Q.jpg")));
		cards.add(new Card("Zir K",4,Flags.ZIR,14,new Image("Karte/ZIR_K.jpg"),new Image("Karte/ZIR_K.jpg")));
		cards.add(new Card("Zir A",11,Flags.ZIR,15,new Image("Karte/ZIR_A.jpg"),new Image("Karte/ZIR_A.jpg")));
		
		cards.add(new Card("Tikva 7",0,Flags.TIKVA,7,new Image("Karte/TIKVA_7.jpg"),new Image("Karte/TIKVA_7.jpg")));
		cards.add(new Card("Tikva 8",0,Flags.TIKVA,8,new Image("Karte/TIKVA_8.jpg"),new Image("Karte/TIKVA_8.jpg")));
		cards.add(new Card("Tikva 9",0,Flags.TIKVA,9,new Image("Karte/TIKVA_9.jpg"),new Image("Karte/TIKVA_9.jpg")));
		cards.add(new Card("Tikva 10",10,Flags.TIKVA,10,new Image("Karte/TIKVA_10.jpg"),new Image("Karte/TIKVA_10.jpg")));
		cards.add(new Card("Tikva J",2,Flags.TIKVA,12,new Image("Karte/TIKVA_J.jpg"),new Image("Karte/TIKVA_J.jpg")));
		cards.add(new Card("Tikva Q",3,Flags.TIKVA,13,new Image("Karte/TIKVA_Q.jpg"),new Image("Karte/TIKVA_Q.jpg")));
		cards.add(new Card("Tikva K",4,Flags.TIKVA,14,new Image("Karte/TIKVA_K.jpg"),new Image("Karte/TIKVA_K.jpg")));
		cards.add(new Card("Tikva A",11,Flags.TIKVA,15,new Image("Karte/TIKVA_A.jpg"),new Image("Karte/TIKVA_A.jpg")));
	}
	public void setAdut(int adut){
		this.adut = adut;
		for(Card karta : cards){
			
			if(adut == karta.getCardSuit()){
				if(karta.getCardNumber() == 9){
					karta.setCardValue(14);
				}else if(karta.getCardNumber() == 12){
					karta.setCardValue(20);
				}
			}
		}
	}
	
	public void startGame() throws InterruptedException{
		while(true){
			
			shuffleCards();
			
			Thread.sleep(1000);
			for(int i=0; i < 24; i++){
				if(i%4 == 0){
					hm.dealCardToPlayer(cards.get(i));
				} else if(i%4 == 1){
					p1.dealCardToPlayer(cards.get(i));
				} else if(i%4 == 2){
					p2.dealCardToPlayer(cards.get(i));
				} else{
					p3.dealCardToPlayer(cards.get(i));
				}
			}
			
			Thread.sleep(1000);
			chooseAdut();
			
			for(int i=24; i < 32; i++){
				if(i%4 == 0){
					hm.dealCardToPlayer(cards.get(i));
				} else if(i%4 == 1){
					p1.dealCardToPlayer(cards.get(i));
				} else if(i%4 == 2){
					p2.dealCardToPlayer(cards.get(i));
				} else{
					p3.dealCardToPlayer( cards.get(i));
				}
			}
			
			Thread.sleep(1000);
			declarations();
			
			for(int k=0; k < 8; k++) {
				for(int i=0; i < 4; i++){
					
					switch (nextToPlay) {
					case 1: // human
						hm.playCard();
						nextToPlay = 2;
						break;
					case 2:
						p1.playCard();
						nextToPlay = 3;
						break;
					case 3:
						p2.playCard();
						nextToPlay = 4;
						break;
					case 4:
						p3.playCard();
						nextToPlay = 1;
						break;
					default:
						break;
					}
					
				}
			}
			
//			hm.clearCards();
//			p1.clearCards();
//			p2.clearCards();
//			p3.clearCards();
		}
	}
	
	public void chooseAdut(){
	}
	
	public HumanPlayer getHumanPlayer(){
		return hm;
	}
	
	public BabicPlayer getPlayer1(){
		return p1;
	}
	
	public BabicPlayer getPlayer2(){
		return p2;
	}
	
	public BabicPlayer getPlayer3(){
		return p3;
	}
	
	public void declarations(){
		
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
}
