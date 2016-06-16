package mainPackage.mainClasses;

public class Flags {
	// BOJE KARATA
	public static final int SRCE = 1;
	public static final int ZIR = 2;
	public static final int LIST = 3;
	public static final int TIKVA = 4;
	
	// GRAFIKA
	public static final String POZADINA = "PozadinaBelotManja.png";
	public static final String BTN_RESET_IMAGE = "Dugmici/RESET_BUTTON.jpg";
	public static final String BTN_SHOW_RES_IMAGE = "Dugmici/SHOW_RESULT_BUTTON.jpg";
	public static final String BTN_PICK_ADUT_IMAGE = "Dugmici/PICK_ADUT.jpg";
	public static final String LIST_ICON = "Ikonice/LIST_IKONA.jpg";
	public static final String SRCE_ICON = "Ikonice/SRCE_IKONA.jpg";
	public static final String ZIR_ICON = "Ikonice/ZIR_IKONA.jpg";
	
	public static final int BTN_PICK_ADUT_WIDTH = 300;
	public static final int BTN_PICK_ADUT_HEIGHT = 160;
	public static final int BTN_SHOW_RES_WIDTH = 150;
	public static final int BTN_SHOW_RES_HEIGHT = 50;
	public static final int BTN_RESET_WIDTH = 150;
	public static final int BTN_RESET_HEIGHT = 50;
	public static final int CARD_WIDTH = 60;
	public static final int CARD_HEIGHT = 120;
	public static final int WINDOW_WIDTH= 850;
	public static final int WINDOW_HEIGHT = 600;
	public static final int CARD_OFFSET_X = 200;
	public static final int BTN_OFFSET = 10;
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
	
	//SHOW RES SPEC
	public static final int BTN_SHOW_RES_TOPLEFT_X = 0;
	public static final int BTN_SHOW_RES_TOPLEFT_Y = WINDOW_HEIGHT - 2*BTN_OFFSET - BTN_RESET_HEIGHT - BTN_SHOW_RES_HEIGHT;
	
	public static final int BTN_SHOW_RES_TOPRIGHT_X = BTN_SHOW_RES_WIDTH;
	public static final int BTN_SHOW_RES_TOPRIGHT_Y = WINDOW_HEIGHT - 2*BTN_OFFSET - BTN_RESET_HEIGHT - BTN_SHOW_RES_HEIGHT;
	
	public static final int BTN_SHOW_RES_BOTTOMLEFT_X = 0;
	public static final int BTN_SHOW_RES_BOTTOMLEFT_Y = WINDOW_HEIGHT - 2*BTN_OFFSET;
	
	public static final int BTN_SHOW_RES_BOTTOMRIGHT_X = BTN_SHOW_RES_WIDTH;
	public static final int BTN_SHOW_RES_BOTTOMRIGHT_Y = WINDOW_HEIGHT - 2*BTN_OFFSET;
	
	//PICK ADUT SPEC
	
	public static final int BTN_PICK_ADUT_TOPLEFT_X = WINDOW_WIDTH/2 - BTN_PICK_ADUT_WIDTH/2;
	public static final int BTN_PICK_ADUT_TOPLEFT_Y = WINDOW_HEIGHT/2 - BTN_PICK_ADUT_HEIGHT/2;
	
	public static final int BTN_PICK_ADUT_TOPRIGHT_X = WINDOW_WIDTH/2 + BTN_PICK_ADUT_WIDTH/2;
	public static final int BTN_PICK_ADUT_TOPRIGHT_Y = WINDOW_HEIGHT/2 - BTN_PICK_ADUT_HEIGHT/2;
	
	public static final int BTN_PICK_ADUT_BOTTOMLEFT_X = WINDOW_WIDTH/2 - BTN_PICK_ADUT_WIDTH/2;
	public static final int BTN_PICK_ADUT_BOTTOMLEFT_Y = WINDOW_HEIGHT/2 + BTN_PICK_ADUT_HEIGHT/2;
	
	public static final int BTN_PICK_ADUT_BOTTOMRIGHT_X = WINDOW_WIDTH/2 + BTN_PICK_ADUT_WIDTH/2;
	public static final int BTN_PICK_ADUT_BOTTOMRIGHT_Y = WINDOW_HEIGHT/2 + BTN_PICK_ADUT_HEIGHT/2;
}
