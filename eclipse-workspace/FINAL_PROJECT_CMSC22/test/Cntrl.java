package test;

import java.util.Random;

import javafx.scene.image.Image;

public class Cntrl extends Sprite {
	public final static int Cntrl_WIDTH = 120;
	public final static Image Cntrl_IMAGE = new Image("images/controller.gif",Cntrl.Cntrl_WIDTH,Cntrl.Cntrl_WIDTH,false,false);
	public static final int MAX_Cntrl_SPEED = 5;
	private int speed;
	private boolean catched;
	private boolean reachedEnd;


	public Cntrl(int x, int y) {
		super(x, y);
		this.catched = false;
		this.reachedEnd = false;
		this.loadImage(Cntrl.Cntrl_IMAGE);
		//random speed of falling Cntrl
		Random r = new Random();
		this.speed = r.nextInt(Cntrl.MAX_Cntrl_SPEED) + 1;
	}

	void moveCntrl(){
		this.y += this.speed;
	}

	void catchCntrl(){
		this.catched = true;
	}

	void makeDisappearCntrl(){
		this.reachedEnd = true;
	}

	public boolean isCatchedCntrl(){
		return this.catched;
	}

	public boolean reachedEndCntrl(){
		return this.reachedEnd;
	}
}