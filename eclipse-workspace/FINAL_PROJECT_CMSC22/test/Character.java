package test;

import javafx.scene.image.Image;

public class Character extends Sprite {
	private final static int CHAR_HEIGHT = 84;
	private final static int CHAR_WIDTH = 60;
	public final static Image CHARACTER_IMAGE = new Image("images/Character_Asset_Walking_R.gif",Character.CHAR_WIDTH,Character.CHAR_HEIGHT,false,false);
	private int score;

	public Character(int x, int y) {
		super(x, y);
		this.score = 0;
		this.loadImage(Character.CHARACTER_IMAGE);
	}

	public void move() {
		/*
		 *TODO: 		Only change the x and y position of the ship if the current x,y position
		 *				is within the gamestage width and height so that the ship won't exit the screen
		 */
		if(this.x<0){
			this.x = 0;
		}if(this.x>(GameStage.WINDOW_WIDTH-50)) {
			this.x = GameStage.WINDOW_WIDTH-50;
		}else {
			this.x += this.dx;
			this.y += this.dy;
		}
	}

	void setScore(int s){
		// set scoring
	}

}
