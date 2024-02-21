package menu;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class BackgroundManager {

	private Group root;
    private AnimationTimer creditsAnimation;
    private List<ImageView> backgroundImages;
    private List<Node> rollingCreditsNodes = new ArrayList<>();
    private int currentImageIndex = 0;
    private boolean movingUp = true;

    private double cloudX = 100; // Initial X position of the cloud
    private double cloudSpeed = 1; // Speed at which the cloud moves
	private ButtonsFunctions buttonsFunctions;
	private BackgroundManager backgroundManager;
	private Stage primaryStage;


    static int WINDOW_HEIGHT = 608;
    static int WINDOW_WIDTH = 1000;

    public BackgroundManager(Group root, Stage stage) {
        this.root = root;
		this.buttonsFunctions = new ButtonsFunctions(root, this, primaryStage);
        this.backgroundImages = new ArrayList<>();
        
        
        initializeBackground();
        initializeClouds();
        setupLinearGradient();
        logoDisplay();
    }

    private void initializeBackground() {
        createBackgroundImage("assetsBackground/OBLE.png", 500, 50, 450, 0.50);
        createBackgroundImage("assetsBackground/MAINLIB.png", 1000, -210, 250, 0.60);
        createBackgroundImage("assetsBackground/FPark.png", 1000, -210, 250, 0.60);

        // Adding the first image to the group
        root.getChildren().add(backgroundImages.get(currentImageIndex));

        // Set up AnimationTimer to move up, down, then switch images
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double speed = 1.0; // Adjust the speed of movement
                ImageView currentImage = backgroundImages.get(currentImageIndex);

                // Move the image up or down based on the direction
                if (movingUp) {
                    currentImage.setLayoutY(currentImage.getLayoutY() - speed);
                    if (currentImage.getLayoutY() <= 250) {
                        movingUp = false;
                    }
                } else {
                    currentImage.setLayoutY(currentImage.getLayoutY() + speed);
                    if (currentImage.getLayoutY() >= 1000) {
                        movingUp = true;
                        switchBackgroundImage();
                    }
                }
            }
        };
        animationTimer.start();
    }

    private void initializeClouds() {
        // Cloud  BIG
        Image cloudImage = new Image("assetsBackground/CLOUDS.png");
        ImageView cloudImageView = createCloudImageView(cloudImage, 600, -400, 100, 0.75);

        // Cloud SMALL
        Image cloudImage1 = new Image("assetsBackground/CLOUDS1.png");
        ImageView cloudImageView1 = createCloudImageView(cloudImage1, 250, -300, 250, 0.85);

        // Add the cloud images to the root group
        root.getChildren().addAll(cloudImageView, cloudImageView1);

        // Starting the animation of clouds
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                cloudX += cloudSpeed;
                if (cloudX > WINDOW_WIDTH) {
                    cloudX = -100; // status quo position of clouds
                }

                // updating both cloud positions to create animation
                cloudImageView.setTranslateX(cloudX);
                cloudImageView1.setTranslateX(cloudX);
            }
        };
        animationTimer.start();
    }

    private void setupLinearGradient() {
        // gradientFill Scene
        LinearGradient gradient = new LinearGradient(
                0, 0, // Start X, Y
                0, 1, // End X, Y
                true, // Proportional
                CycleMethod.NO_CYCLE, // Cycle method
                new Stop[]{
                        new Stop(0, Color.web("#59C1FF")), // Start color
                        new Stop(1, Color.web("#D3F0FA")) // End color
                }
        );
        root.getScene().setFill(gradient);
    }
    
    private void logoDisplay() {
    	Image logo = new Image("assetsBackground/LOGO.png");
		ImageView imageViewlogo = new ImageView(logo);
		imageViewlogo.setPreserveRatio(true);
		imageViewlogo.setFitWidth(450);
		imageViewlogo.setLayoutX(75);
		imageViewlogo.setLayoutY(50);
		root.getChildren().add(imageViewlogo);
    }

    private ImageView createCloudImageView(Image image, double fitWidth, double layoutX, double layoutY, double opacity) {
        ImageView cloudImageView = new ImageView(image);
        cloudImageView.setPreserveRatio(true);
        cloudImageView.setFitWidth(fitWidth);
        cloudImageView.setLayoutX(layoutX);
        cloudImageView.setLayoutY(layoutY);
        cloudImageView.setOpacity(opacity);
        return cloudImageView;
    }

    private void createBackgroundImage(String imagePath, double fitWidth, double setX, double setY, double opacity) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(fitWidth);
        imageView.setLayoutX(setX);
        imageView.setLayoutY(setY);
        imageView.setOpacity(opacity);
        backgroundImages.add(imageView);
    }

    private void switchBackgroundImage() {
        // removing the current image
        root.getChildren().remove(backgroundImages.get(currentImageIndex));

        // incrementing from the array list of images
        currentImageIndex = (currentImageIndex + 1) % backgroundImages.size();

        // adding the image to the root
        root.getChildren().add(backgroundImages.get(currentImageIndex));
    }
    
    public void showRollingCredits(){
    	
    	Rectangle creditsBackground = new Rectangle(WINDOW_HEIGHT, WINDOW_WIDTH, Paint.valueOf("rgba(0, 0, 0, 0.8)")); // Adjust the color and opacity
      root.getChildren().add(creditsBackground);

      // Add rolling credits text
      Text creditsText = new Text("Credits \n Geastin Castillo \n Ethan Elizondo \n Ryan Galaban \n		\n Sources \n Piskel \n Google \n"
      		+ "StackoverFlow \n Reddit \n \n Acknowledgements to \n CMSC Seniors \n  \n In fulfillment \n for CMSC-22 \n \n \n Lecture: Mylah Anacleto \n Lab: Carl Angcana");
      
      // Setting Font
      
      Font customFont = Font.loadFont(getClass().getResourceAsStream("/assetsBackground/Pixeboy.ttf"), 50);
      
      creditsText.setFont(customFont);
      creditsText.setFill(Color.WHITE);
      creditsText.setTextAlignment(TextAlignment.CENTER);
      
   // Calculate center position
      double centerX = (WINDOW_HEIGHT - creditsText.getLayoutBounds().getWidth()) / 2;

      creditsText.setLayoutX(centerX);
      creditsText.setLayoutY(1000);
      
      // Add creditsText to the root group
      root.getChildren().add(creditsText);

      // Start rolling credits animation
      AnimationTimer creditsAnimation = new AnimationTimer() {
          double yPosition = creditsText.getLayoutY();

          @Override
          public void handle(long now) {
              yPosition -= 0.5; // Adjust the speed of credits
              creditsText.setLayoutY(yPosition);

              if (yPosition < -1100) {
                  // Reset the credits when it goes off the screen
                  yPosition = 1100;
              }
          }
      };
      creditsAnimation.start();
      
      Node exitCreditButton = buttonsFunctions.createExitCreditButton("assetsBackground/EXIT_BUTTON.png", "assetsBackground/EXIT_BUTTON_PRESSED.png", 200, 400, -65);
      rollingCreditsNodes.add(exitCreditButton);
      rollingCreditsNodes.add(creditsBackground);
      rollingCreditsNodes.add(creditsText);
      
    }
    
    public void stopRollingCredits() {
        // Stop the rolling credits animation
        if (creditsAnimation != null) {
            creditsAnimation.stop();
        }
        
        
     // Remove only the rolling credits nodes from the scene
        root.getChildren().removeAll(rollingCreditsNodes);
        rollingCreditsNodes.clear();
    }
    
}
