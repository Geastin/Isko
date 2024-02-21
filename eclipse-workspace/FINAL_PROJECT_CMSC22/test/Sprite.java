package test;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	protected Image img;
	protected int x, y, dx, dy;
	protected boolean visible;
	protected double width;
	protected double height;

	public Sprite(int xPos, int yPos){
		this.x = xPos;
		this.y = yPos;
		this.visible = true;
	}

	protected void loadImage(Image img){
		try{
			this.img = img;
	        this.setSize();
		} catch(Exception e){}
	}

	void render(GraphicsContext gc){
		gc.drawImage(this.img, this.x, this.y);

    }

	private void setSize(){
		this.width = this.img.getWidth();
	    this.height = this.img.getHeight();
	}

	public boolean collidesWith(Sprite rect2)	{
		Rectangle2D rectangle1 = this.getBounds();
		Rectangle2D rectangle2 = rect2.getBounds();

		return rectangle1.intersects(rectangle2);
	}

	private Rectangle2D getBounds() {
		return new Rectangle2D(this.x, this.y, this.width, this.height);
	}

	Image getImage(){
		return this.img;
	}
	//getters
	public int getX() {
    	return this.x;
	}

	public int getY() {
    	return this.y;
	}

	public boolean getVisible(){
		return visible;
	}
	public boolean isVisible(){
		if(visible) return true;
		return false;
	}

	public void setDX(int dx){
		this.dx = dx;
	}

	public void setDY(int dy){
		this.dy = dy;
	}

	public void setWidth(double val){
		this.width = val;
	}

	public void setHeight(double val){
		this.height = val;
	}

	public void setVisible(boolean value){
		this.visible = value;
	}

}
