package test;

import java.util.Random;

import javafx.scene.image.Image;

public class Notes extends Sprite {
	public final static int Notes_WIDTH = 80;
	public final static Image Notes_IMAGE = new Image("images/Notes Asset  Animated (3).gif",Notes.Notes_WIDTH,Notes.Notes_WIDTH,false,false);
	public static final int MAX_Notes_SPEED = 5;
	private int speed;
	private boolean catched;
	private boolean reachedEnd;


	public Notes(int x, int y) {
		super(x, y);
		this.catched = false;
		this.reachedEnd = false;
		this.loadImage(Notes.Notes_IMAGE);
		//random speed of falling Notes
		Random r = new Random();
		this.speed = r.nextInt(Notes.MAX_Notes_SPEED) + 1;
	}

	void moveNotes(){
		this.y += this.speed;
	}

	void catchNotes(){
		this.catched = true;
	}

	void makeDisappearNotes(){
		this.reachedEnd = true;
	}

	public boolean isCatchedNotes(){
		return this.catched;
	}

	public boolean reachedEndNotes(){
		return this.reachedEnd;
	}
}
