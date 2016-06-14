package mainPackage.mainClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import mainPackage.mainClasses.supportPackage.Result;

public class AppCore {
	public static int adut = 0;
	private static AppCore instance;
	
	private ArrayList<Card> cards;
	private ArrayList<Result> shuffleResults;
	private Result fullResult;
	private Result roundsWon;
	
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
		cards.add(new Card("Srce 7",0,Flags.SRCE,7,new Image("SveKarte/Srce/Srce_7.jpg")));
		cards.add(new Card("Srce 8",0,Flags.SRCE,8,new Image("SveKarte/Srce/Srce_8.jpg")));
		cards.add(new Card("Srce 9",0,Flags.SRCE,9,new Image("SveKarte/Srce/Srce_9.jpg")));
		cards.add(new Card("Srce 10",10,Flags.SRCE,10,new Image("SveKarte/Srce/Srce_10.jpg")));
		cards.add(new Card("Srce J",2,Flags.SRCE,12,new Image("SveKarte/Srce/Srce_J.jpg")));
		cards.add(new Card("Srce Q",3,Flags.SRCE,13,new Image("SveKarte/Srce/Srce_Q.jpg")));
		cards.add(new Card("Srce K",4,Flags.SRCE,14,new Image("SveKarte/Srce/Srce_K.jpg")));
		cards.add(new Card("Srce A",11,Flags.SRCE,11,new Image("SveKarte/Srce/Srce_A.jpg")));
		
		cards.add(new Card("List 7",0,Flags.LIST,7,new Image("SveKarte/List/List_7.jpg")));
		cards.add(new Card("List 8",0,Flags.LIST,8,new Image("SveKarte/List/List_8.jpg")));
		cards.add(new Card("List 9",0,Flags.LIST,9,new Image("SveKarte/List/List_9.jpg")));
		cards.add(new Card("List 10",10,Flags.LIST,10,new Image("SveKarte/List/List_10.jpg")));
		cards.add(new Card("List J",2,Flags.LIST,12,new Image("SveKarte/List/List_J.jpg")));
		cards.add(new Card("List Q",3,Flags.LIST,13,new Image("SveKarte/List/List_Q.jpg")));
		cards.add(new Card("List K",4,Flags.LIST,14,new Image("SveKarte/List/List_K.jpg")));
		cards.add(new Card("List A",11,Flags.LIST,11,new Image("SveKarte/List/List_A.jpg")));
		
		cards.add(new Card("Zir 7",0,Flags.ZIR,7,new Image("SveKarte/Zir/Zir_7.jpg")));
		cards.add(new Card("Zir 8",0,Flags.ZIR,8,new Image("SveKarte/Zir/Zir_8.jpg")));
		cards.add(new Card("Zir 9",0,Flags.ZIR,9,new Image("SveKarte/Zir/Zir_9.jpg")));
		cards.add(new Card("Zir 10",10,Flags.ZIR,10,new Image("SveKarte/Zir/Zir_10.jpg")));
		cards.add(new Card("Zir J",2,Flags.ZIR,12,new Image("SveKarte/Zir/Zir_J.jpg")));
		cards.add(new Card("Zir Q",3,Flags.ZIR,13,new Image("SveKarte/Zir/Zir_Q.jpg")));
		cards.add(new Card("Zir K",4,Flags.ZIR,14,new Image("SveKarte/Zir/Zir_K.jpg")));
		cards.add(new Card("Zir A",11,Flags.ZIR,11,new Image("SveKarte/Zir/Zir_A.jpg")));
		
		cards.add(new Card("Tikva 7",0,Flags.TIKVA,7,new Image("SveKarte/Tikva/Tikva_7.jpg")));
		cards.add(new Card("Tikva 8",0,Flags.TIKVA,8,new Image("SveKarte/Tikva/Tikva_8.jpg")));
		cards.add(new Card("Tikva 9",0,Flags.TIKVA,9,new Image("SveKarte/Tikva/Tikva_9.jpg")));
		cards.add(new Card("Tikva 10",10,Flags.TIKVA,10,new Image("SveKarte/Tikva/Tikva_10.jpg")));
		cards.add(new Card("Tikva J",2,Flags.TIKVA,12,new Image("SveKarte/Tikva/Tikva_J.jpg")));
		cards.add(new Card("Tikva Q",3,Flags.TIKVA,13,new Image("SveKarte/Tikva/Tikva_Q.jpg")));
		cards.add(new Card("Tikva K",4,Flags.TIKVA,14,new Image("SveKarte/Tikva/Tikva_K.jpg")));
		cards.add(new Card("Tikva A",11,Flags.TIKVA,11,new Image("SveKarte/Tikva/Tikva_A.jpg")));
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
	public void startGame(){
		while(true){
			shuffleCards();
			/*setAdut(Flags.SRCE);
			for(Card karta : cards){
				System.out.println(karta);
			}
			System.out.println("********************************************");
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
	public ArrayList<Card> getCards(){
		return cards;
	}
}
