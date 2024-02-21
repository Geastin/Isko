package test;

import java.util.Random;

import javafx.scene.image.Image;

public class Attend extends Sprite {
	public final static int Attend_WIDTH = 100;
	public final static Image Attend_IMAGE = new Image("images/Attendance Asset Animated.gif",Attend.Attend_WIDTH,Attend.Attend_WIDTH,false,false);
	public static final int MAX_Attend_SPEED = 5;
	private int speed;
	private boolean catched;
	private boolean reachedEnd;


	public Attend(int x, int y) {
		super(x, y);
		this.catched = false;
		this.reachedEnd = false;
		this.loadImage(Attend.Attend_IMAGE);
		//random speed of falling Attend
		Random r = new Random();
		this.speed = r.nextInt(Attend.MAX_Attend_SPEED) + 1;
	}

	void moveAttend(){
		this.y += this.speed;
	}

	void catchAttend(){
		this.catched = true;
	}

	void makeDisappearAttend(){
		this.reachedEnd = true;
	}

	public boolean isCatchedAttend(){
		return this.catched;
	}

	public boolean reachedEndAttend(){
		return this.reachedEnd;
	}
}
