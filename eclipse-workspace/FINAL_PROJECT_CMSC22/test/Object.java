package test;

import java.util.Random;

import javafx.scene.image.Image;

public class Object extends Sprite {
	public final static int OBJECT_WIDTH = 150;
	public final static Image OBJECT_IMAGE = new Image("images/alco.gif",Object.OBJECT_WIDTH,Object.OBJECT_WIDTH,false,false);
	public static final int MAX_OBJECT_SPEED = 5;
	private int speed;
	private boolean catched;
	private boolean reachedEnd;


	public Object(int x, int y) {
		super(x, y);
		this.catched = false;
		this.reachedEnd = false;
		this.loadImage(Object.OBJECT_IMAGE);
		//random speed of falling object
		Random r = new Random();
		this.speed = r.nextInt(Object.MAX_OBJECT_SPEED) + 5;
	}

	void move(){
		this.y += this.speed;
	}

	void catchObject(){
		this.catched = true;
	}

	void makeDisappear(){
		this.reachedEnd = true;
	}

	public boolean isCatched(){
		return this.catched;
	}

	public boolean reachedEnd(){
		return this.reachedEnd;
	}
}
