package mainPackage.mainClasses;

public class Flags {
	// BOJE KARATA
	public static final int SRCE = 1;
	public static final int ZIR = 2;
	public static final int LIST = 3;
	public static final int TIKVA = 4;
	
	// GRAFIKA
	public static final String POZADINA = "PozadinaBelotManja.png";
	public static final String BTN_RESET = "Dugmici/RESET_BUTTON.jpg";
	public static final int BTN_RESET_WIDTH = 150;
	public static final int BTN_RESET_HEIGHT = 50;
	public static final int CARD_WIDTH = 60;
	public static final int CARD_HEIGHT = 120;
	public static final int WINDOW_WIDTH= 850;
	public static final int WINDOW_HEIGHT = 600;
	public static final int CARD_OFFSET_X = 200;
	public static final int CARD_OFFSET_Y = 10;
	public static final int CARD_INCREMENT_OFFSET = 10;
	
	
	//PUTANJE
	public static final String KARTE_PUTANJA = "Karte/";
	public static final String DUGMICI_PUTANJA = "Dugmici/";
	
	
	//CARD SPEC
	public static final int CARD_TOPLEFT_X = 0;
	public static final int CARD_TOPLEFT_Y = WINDOW_HEIGHT - CARD_HEIGHT - CARD_OFFSET_Y;
	
	public static final int CARD_TOPRIGHT_X = CARD_WIDTH;
	public static final int CARD_TOPRIGHT_Y = WINDOW_HEIGHT - CARD_HEIGHT - CARD_OFFSET_Y;
	
	public static final int CARD_BOTTOMLEFT_X = 0;
	public static final int CARD_BOTTOMLEFT_Y = WINDOW_HEIGHT - CARD_OFFSET_Y;
	
	public static final int CARD_BOTTOMRIGHT_X = CARD_WIDTH;
	public static final int CARD_BOTTOMRIGHT_Y = WINDOW_HEIGHT - CARD_OFFSET_Y;
	
	//RESET DUGME SPEC
	public static final int BTN_RESET_TOPLEFT_X = 0;
	public static final int BTN_RESET_TOPLEFT_Y = WINDOW_HEIGHT-BTN_RESET_HEIGHT-10;
	
	public static final int BTN_RESET_BOTTOMLEFT_X = 0;
	public static final int BTN_RESET_BOTTOMLEFT_Y = WINDOW_HEIGHT - 10;
	
	public static final int BTN_RESET_TOPRIGHT_X = BTN_RESET_WIDTH;
	public static final int BTN_RESET_TOPRIGHT_Y = WINDOW_HEIGHT-BTN_RESET_HEIGHT-10; 
	
	public static final int BTN_RESET_BOTTOMRIGHT_X = BTN_RESET_WIDTH;
	public static final int BTN_RESET_BOTTOMRIGTH_Y = WINDOW_HEIGHT - 10;
}
