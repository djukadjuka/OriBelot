package mainPackage.mainClasses.supportPackage;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

import mainPackage.mainClasses.AppCore;
import mainPackage.mainClasses.Flags;

public class PickAdutDialog {
	private PressableRectangle pickAdutButton;
	private PressableRectangle pickListButton;
	private PressableRectangle pickZirButton;
	private PressableRectangle pickSrceButton;
	private PressableRectangle pickTikvaButton;
	private PressableRectangle pickPassButton;
	private PressableRectangle pickPassMussButton;
	private boolean playerOnMuss;
	public PickAdutDialog() throws SlickException{
		pickAdutButton = new PressableRectangle(new Point(Flags.BTN_PICK_ADUT_TOPLEFT_X,Flags.BTN_PICK_ADUT_TOPLEFT_Y), 
                new Point(Flags.BTN_PICK_ADUT_TOPRIGHT_X,Flags.BTN_PICK_ADUT_TOPRIGHT_Y), 
                new Point(Flags.BTN_PICK_ADUT_BOTTOMLEFT_X,Flags.BTN_PICK_ADUT_BOTTOMLEFT_Y), 
                new Point(Flags.BTN_PICK_ADUT_BOTTOMRIGHT_X,Flags.BTN_PICK_ADUT_BOTTOMRIGHT_Y), 
                new Image(Flags.BTN_PICK_ADUT_IMAGE));
		pickListButton = new PressableRectangle(new Point(Flags.BTN_LIST_TOPLEFT_X,Flags.BTN_LIST_TOPLEFT_Y),
				new Point(Flags.BTN_LIST_TOPRIGHT_X,Flags.BTN_LIST_TOPRIGHT_Y),
				new Point(Flags.BTN_LIST_BOTTOMLEFT_X,Flags.BTN_LIST_BOTTOMLEFT_Y),
				new Point(Flags.BTN_LIST_BOTTOMRIGHT_X,Flags.BTN_LIST_BOTTOMRIGHT_Y),
				new Image(Flags.BTN_LIST_ICON));
		pickZirButton = new PressableRectangle(new Point(Flags.BTN_ZIR_TOPLEFT_X,Flags.BTN_ZIR_TOPLEFT_Y),
				new Point(Flags.BTN_ZIR_TOPRIGHT_X,Flags.BTN_ZIR_TOPRIGHT_Y),
				new Point(Flags.BTN_ZIR_BOTTOMLEFT_X,Flags.BTN_ZIR_BOTTOMLEFT_Y),
				new Point(Flags.BTN_ZIR_BOTTOMRIGHT_X,Flags.BTN_ZIR_BOTTOMRIGHT_Y),
				new Image(Flags.BTN_ZIR_ICON));
		pickTikvaButton = new PressableRectangle(new Point(Flags.BTN_TIKVA_TOPLEFT_X,Flags.BTN_TIKVA_TOPLEFT_Y),
				new Point(Flags.BTN_TIKVA_TOPRIGHT_X,Flags.BTN_TIKVA_TOPRIGHT_Y),
				new Point(Flags.BTN_TIKVA_BOTTOMLEFT_X,Flags.BTN_TIKVA_BOTTOMLEFT_Y),
				new Point(Flags.BTN_TIKVA_BOTTOMRIGHT_X,Flags.BTN_TIKVA_BOTTOMRIGHT_Y),
				new Image(Flags.BTN_TIKVA_ICON));
		pickSrceButton = new PressableRectangle(new Point(Flags.BTN_SRCE_TOPLEFT_X,Flags.BTN_SRCE_TOPLEFT_Y),
				new Point(Flags.BTN_SRCE_TOPRIGHT_X,Flags.BTN_SRCE_TOPRIGHT_Y),
				new Point(Flags.BTN_SRCE_BOTTOMLEFT_X,Flags.BTN_SRCE_BOTTOMLEFT_Y),
				new Point(Flags.BTN_SRCE_BOTTOMRIGHT_X,Flags.BTN_SRCE_BOTTOMRIGHT_Y),
				new Image(Flags.BTN_SRCE_ICON));
		pickPassButton = new PressableRectangle(new Point(Flags.BTN_PASS_TOPLEFT_X,Flags.BTN_PASS_TOPLEFT_Y),
				new Point(Flags.BTN_PASS_TOPRIGHT_X,Flags.BTN_PASS_TOPRIGHT_Y),
				new Point(Flags.BTN_PASS_BOTTOMLEFT_X,Flags.BTN_PASS_BOTTOMLEFT_Y),
				new Point(Flags.BTN_PASS_BOTTOMRIGHT_X,Flags.BTN_PASS_BOTTOMRIGHT_Y),
				new Image(Flags.BTN_PASS_ICON));
		pickPassMussButton = new PressableRectangle(new Point(Flags.BTN_PASS_MUS_TOPLEFT_X,Flags.BTN_PASS_MUS_TOPLEFT_Y),
				new Point(Flags.BTN_PASS_MUS_TOPRIGHT_X,Flags.BTN_PASS_MUS_TOPRIGHT_Y),
				new Point(Flags.BTN_PASS_MUS_BOTTOMLEFT_X,Flags.BTN_PASS_MUS_BOTTOMLEFT_Y),
				new Point(Flags.BTN_PASS_MUS_BOTTOMRIGHT_X,Flags.BTN_PASS_MUS_BOTTOMRIGHT_Y),
				new Image(Flags.BTN_PASS_MUS_ICON));	
	}
	public void drawDialog(Graphics g,boolean onMuss){
		playerOnMuss = onMuss;
		if(onMuss){
			pickAdutButton.drawThis(g);
			pickZirButton.drawThis(g);
			pickListButton.drawThis(g);
			pickSrceButton.drawThis(g);
			pickTikvaButton.drawThis(g);
			pickPassMussButton.drawThis(g);
		}else{
			pickAdutButton.drawThis(g);
			pickZirButton.drawThis(g);
			pickListButton.drawThis(g);
			pickSrceButton.drawThis(g);
			pickTikvaButton.drawThis(g);
			pickPassButton.drawThis(g);
		}
	}
	public boolean zirClicked(int MOUSE_X,int MOUSE_Y){
		return pickZirButton.isPressed(MOUSE_X, MOUSE_Y);
	}
	public boolean srceClicked(int MOUSE_X,int MOUSE_Y){
		return pickSrceButton.isPressed(MOUSE_X, MOUSE_Y);
	}
	public boolean listClicked(int MOUSE_X,int MOUSE_Y){
		return pickListButton.isPressed(MOUSE_X, MOUSE_Y);
	}
	public boolean tikvaClicked(int MOUSE_X,int MOUSE_Y){
		return pickTikvaButton.isPressed(MOUSE_X, MOUSE_Y);
	}
	public boolean passClicked(int MOUSE_X,int MOUSE_Y){
		return pickPassButton.isPressed(MOUSE_X, MOUSE_Y);
	}
	public boolean humanChosesAdut(int MOUSE_X,int MOUSE_Y){
		if(zirClicked(MOUSE_X, MOUSE_Y)){
			AppCore.adut = Flags.ZIR;
			return true;
		}else if(listClicked(MOUSE_X, MOUSE_Y)){
			AppCore.adut = Flags.LIST;
			return true;
		}else if(srceClicked(MOUSE_X,MOUSE_Y)){
			AppCore.adut = Flags.SRCE;
			return true;
		}else if(tikvaClicked(MOUSE_X, MOUSE_Y)){
			AppCore.adut = Flags.TIKVA;
			return true;
		}else if(!playerOnMuss && passClicked(MOUSE_X, MOUSE_Y)){
			AppCore.adut = 0;
			return true;
		}
		return false;
	}
}
