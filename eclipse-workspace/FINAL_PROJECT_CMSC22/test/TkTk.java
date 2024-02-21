package test;

import java.util.Random;

import javafx.scene.image.Image;

public class TkTk extends Sprite {
	public final static int TkTk_WIDTH = 110;
	public final static Image TkTk_IMAGE = new Image("images/Tiktok Asset.gif",TkTk.TkTk_WIDTH,TkTk.TkTk_WIDTH,false,false);
	public static final int MAX_TkTk_SPEED = 5;
	private int speed;
	private boolean catched;
	private boolean reachedEnd;


	public TkTk(int x, int y) {
		super(x, y);
		this.catched = false;
		this.reachedEnd = false;
		this.loadImage(TkTk.TkTk_IMAGE);
		//random speed of falling TkTk
		Random r = new Random();
		this.speed = r.nextInt(TkTk.MAX_TkTk_SPEED) + 1;
	}

	void moveTkTk(){
		this.y += this.speed;
	}

	void catchTkTk(){
		this.catched = true;
	}

	void makeDisappearTkTk(){
		this.reachedEnd = true;
	}

	public boolean isCatchedTkTk(){
		return this.catched;
	}

	public boolean reachedEndTkTk(){
		return this.reachedEnd;
	}
}
