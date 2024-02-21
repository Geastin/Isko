package test;

import java.util.Random;

import javafx.scene.image.Image;

public class Twt extends Sprite {
	public final static int Twt_WIDTH = 110;
	public final static Image Twt_IMAGE = new Image("images/Twitter Asset Animated.gif",Twt.Twt_WIDTH,Twt.Twt_WIDTH,false,false);
	public static final int MAX_Twt_SPEED = 5;
	private int speed;
	private boolean catched;
	private boolean reachedEnd;


	public Twt(int x, int y) {
		super(x, y);
		this.catched = false;
		this.reachedEnd = false;
		this.loadImage(Twt.Twt_IMAGE);
		//random speed of falling Twt
		Random r = new Random();
		this.speed = r.nextInt(Twt.MAX_Twt_SPEED) + 1;
	}

	void moveTwt(){
		this.y += this.speed;
	}

	void catchTwt(){
		this.catched = true;
	}

	void makeDisappearTwt(){
		this.reachedEnd = true;
	}

	public boolean isCatchedTwt(){
		return this.catched;
	}

	public boolean reachedEndTwt(){
		return this.reachedEnd;
	}
}
