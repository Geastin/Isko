package test;

import java.util.Random;

import javafx.scene.image.Image;

public class Coffee extends Sprite {
	public final static int Coffee_WIDTH = 100;
	public final static Image Coffee_IMAGE = new Image("images/Coffee Asset.gif",Coffee.Coffee_WIDTH,Coffee.Coffee_WIDTH,false,false);
	public static final int MAX_Coffee_SPEED = 5;
	private int speed;
	private boolean catched;
	private boolean reachedEnd;


	public Coffee(int x, int y) {
		super(x, y);
		this.catched = false;
		this.reachedEnd = false;
		this.loadImage(Coffee.Coffee_IMAGE);
		//random speed of falling Coffee
		Random r = new Random();
		this.speed = r.nextInt(Coffee.MAX_Coffee_SPEED) + 1;
	}

	void moveCoffee(){
		this.y += this.speed;
	}

	void catchCoffee(){
		this.catched = true;
	}

	void makeDisappearCoffee(){
		this.reachedEnd = true;
	}

	public boolean isCatchedCoffee(){
		return this.catched;
	}

	public boolean reachedEndCoffee(){
		return this.reachedEnd;
	}
}
