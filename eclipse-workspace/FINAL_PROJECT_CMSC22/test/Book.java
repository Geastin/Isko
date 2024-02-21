package test;

import java.util.Random;

import javafx.scene.image.Image;

public class Book extends Sprite {
	public final static int Book_WIDTH = 120;
	public final static Image Book_IMAGE = new Image("images/Textbook Asset Animated Final.gif",Book.Book_WIDTH,Book.Book_WIDTH,false,false);
	public static final int MAX_Book_SPEED = 5;
	private int speed;
	private boolean catched;
	private boolean reachedEnd;


	public Book(int x, int y) {
		super(x, y);
		this.catched = false;
		this.reachedEnd = false;
		this.loadImage(Book.Book_IMAGE);
		//random speed of falling Book
		Random r = new Random();
		this.speed = r.nextInt(Book.MAX_Book_SPEED) + 5;
	}

	void moveBook(){
		this.y += this.speed;
	}

	void catchBook(){
		this.catched = true;
	}

	void makeDisappearBook(){
		this.reachedEnd = true;
	}

	public boolean isCatchedBook(){
		return this.catched;
	}

	public boolean reachedEndBook(){
		return this.reachedEnd;
	}
}
