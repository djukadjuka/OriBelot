package mainPackage.mainClasses.supportPackage;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

/**
 * OVO JE DUGME AAAA
 * */
public class PressableRectangle {
	private Image rectImage;
	private Point topLeft;
	private Point topRight;
	private Point bottomLeft;
	private Point bottomRight;
	
	public Image getImage(){
		return rectImage;
	}
	public void setImage(Image image){
		this.rectImage = image;
	}
	public PressableRectangle(Point topLeft,Point topRight,Point bottomLeft,Point bottomRight,Image theImage){
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
		this.rectImage = theImage;
	}
	
	public Point getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(Point topLeft) {
		this.topLeft = topLeft;
	}

	public Point getTopRight() {
		return topRight;
	}

	public void setTopRight(Point topRight) {
		this.topRight = topRight;
	}

	public Point getBottomLeft() {
		return bottomLeft;
	}

	public void setBottomLeft(Point bottomLeft) {
		this.bottomLeft = bottomLeft;
	}

	public Point getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(Point bottomRight) {
		this.bottomRight = bottomRight;
	}

	public boolean isPressed(int MOUSE_X,int MOUSE_Y){
		System.out.println(MOUSE_X + " " + MOUSE_Y);
		System.out.println(topLeft.getX() + " " + topRight.getX() + "\n" + bottomLeft.getX() + " " + bottomRight.getX());
		System.out.println("================================================");
		if(    MOUSE_X < topRight.getX()
			&& MOUSE_X < bottomRight.getX()
			&& MOUSE_X > topLeft.getX()
		    && MOUSE_X > bottomLeft.getX()
		    
		    && MOUSE_Y < bottomRight.getY()
		    && MOUSE_Y < bottomLeft.getY()
		    && MOUSE_Y > topLeft.getY()
		    && MOUSE_Y > topRight.getY()){
			System.out.println("Pressed");
			return true;
		}
		return false;
	}
	public void drawThis(Graphics g){
		g.drawImage(getImage(), getTopLeft().getX(), getTopLeft().getY());
	}
}