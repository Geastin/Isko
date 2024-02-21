package test;

import test.GameTimer;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class GameStage {

	private Scene scene;
	private Stage stage;
	private Group root;
	static final int WINDOW_HEIGHT = 1000;
	static final int WINDOW_WIDTH = 608;
	private GameTimer gametimer;
	private Rectangle rectangle;
	private GraphicsContext gc;
	private Canvas canvas;
	public final Image bg = new Image("images/Gradient.png",1000,608,false,false);

	public GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.gametimer = new GameTimer(this.gc,this.scene);
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		this.root.getChildren().add(canvas);

		this.stage.setTitle("Mini Shooting Game");
		this.stage.setScene(this.scene);

		//invoke the start method of the animation timer
		this.gametimer.start();
		this.stage.show();
	}
}
