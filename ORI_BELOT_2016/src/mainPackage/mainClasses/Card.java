package mainPackage.mainClasses;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Card {
	private int cardValue;
	private int cardSuit;
	private int cardNumber;
	private int heuristicValue;
	private String cardName;
	private Image cardImage;
	private Image rotatedCardImage;
	
	public Card(String cardName,int cardValue,int cardSuit,int cardNumber,Image cardImage,Image rotated){
		heuristicValue = 0;
		this.cardName = cardName;
		this.cardValue = cardValue;
		this.cardSuit = cardSuit;
		this.cardNumber = cardNumber;
		this.cardImage = cardImage;
		this.rotatedCardImage = rotated;
		this.rotatedCardImage.rotate(90);
	}
	public Image getRotatedCardImage(){
		return rotatedCardImage;
	}
	public int getCardValue(){
		return cardValue;
	}
	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}
	public int getCardSuit() {
		return cardSuit;
	}
	public void setCardSuit(int cardSuit) {
		this.cardSuit = cardSuit;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getHeuristicValue() {
		return heuristicValue;
	}
	public void setHeuristicValue(int heuristicValue) {
		this.heuristicValue = heuristicValue;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String toString(){
		return cardName + " | Value : " + cardValue + "\n ==========================";
	}
	public boolean isAdut(int suit){
		return cardSuit == suit;
	}
	public Image getCardImage(){
		return cardImage;
	}
}
