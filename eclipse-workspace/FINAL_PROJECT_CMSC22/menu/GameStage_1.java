package menu;

import javafx.scene.paint.Color;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;


//import application.GameTImer;
import javafx.scene.Group;

import javafx.scene.Scene;
import javafx.scene.shape.Circle;

import javafx.stage.Stage;



public class GameStage_1 {
	private Scene scene;
	private Stage stage;
	
	//Group layout/container is a component which applies no special layout to its children. All child components (nodes) are positioned at 0,0
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	
	
	//private GameTimer GameTimer;
	static int WINDOW_HEIGHT = 608;
	static int WINDOW_WIDTH = 1000;
	
	//assets
	private Circle circle;
	Image oble;
	Image logo;
	Image mainlib;
	Image fpark;
	Image startbutton;
    private BackgroundManager backgroundManager;
    private ButtonsFunctions buttonsFunctions;
    

	
	
	public GameStage_1() {
		this.root = new Group();
	    this.circle = new Circle(40, 40, 40, Color.WHITE);
	    this.root.getChildren().add(circle);
	    this.scene = new Scene(root, this.WINDOW_HEIGHT, this.WINDOW_WIDTH);

	    // for BACKGROUND
	    this.backgroundManager = new BackgroundManager(root, stage);
        this.buttonsFunctions = new ButtonsFunctions(root, backgroundManager, stage);

        	//rendering the background
	    this.canvas = new Canvas(this.WINDOW_WIDTH, this.WINDOW_HEIGHT);
	    this.gc = canvas.getGraphicsContext2D();

	    

	    Stage primaryStage = new Stage();
		// for button
	    buttonsFunctions.createStartButton("assetsBackground/START_BUTTON.png", "assetsBackground/START_BUTTON_PRESSED.png", 300, 150, 490, primaryStage ); //
	    buttonsFunctions.createCreditButton("assetsBackground/CREDIT_BUTTON.png", "assetsBackground/CREDIT_BUTTON_PRESSED.png", 300, 150, 578);
	    buttonsFunctions.createExitButton("assetsBackground/EXIT_BUTTON.png", "assetsBackground/EXIT_BUTTON_PRESSED.png", 300, 150, 666);
	    
	    
	   
	}

	public void setStage(Stage primaryStage) {
 			
			this.stage = primaryStage;
			this.addComponents();
			
			//set stage elements here
			this.root.getChildren().add(canvas);
			
			this.stage.setTitle("I-score-lar ng Bayan");
			
			//this.GameTimer.start();
			this.stage.setScene(this.scene);
			this.stage.setResizable(false);
			this.stage.show();
			
			}
	
	private void addComponents() {
		// this is where assets will go
		
		
	}
}
