package menu;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import test.Main;



public class ButtonsFunctions {
    private Group root;
    private BackgroundManager backgroundManager;
	private Node exitCreditButton;
	private Stage stage;

    public ButtonsFunctions(Group root, BackgroundManager backgroundManager, Stage Stage) {
        this.root = root;
        this.backgroundManager = backgroundManager;
        this.stage = Stage;
        
    }
    
    public Node createStartButton(String imagePath, String imagePathClicked, double fitWidth, double setX, double setY, Stage primaryStage) {
        return createButton(imagePath, imagePathClicked, fitWidth, setX, setY, () -> {
            // Add functionality for Start button click
            System.out.println("Start button clicked");
            Main mainApp = new Main();
            try {
				mainApp.start(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        });
    }

    public Node createCreditButton(String imagePath, String imagePathClicked, double fitWidth, double setX, double setY) {
        return createButton(imagePath, imagePathClicked, fitWidth, setX, setY, () -> {
            // Add functionality for Credit button click
            System.out.println("Credit button clicked");
            // Add the rolling credits functionality here
            backgroundManager.showRollingCredits();
        });
    }

    public Node createExitButton(String imagePath, String imagePathClicked, double fitWidth, double setX, double setY) {
        return createButton(imagePath, imagePathClicked, fitWidth, setX, setY, () -> {
            // Add functionality for Exit button click
            System.out.println("Exit button clicked");
            System.out.println("Goodbye!");
            System.exit(0);
        });
    }
    
    public Node createExitCreditButton(String imagePath, String imagePathClicked, double fitWidth, double setX, double setY) {
        return createButton(imagePath, imagePathClicked, fitWidth, setX, setY, () -> {
            // Add functionality for Exit button click
        	 System.out.println("Exit Credits button clicked");
                 backgroundManager.stopRollingCredits(); // Stop the rolling credit
        });

    }


    public Node createButton(String imagePath, String imagePathClicked, double fitWidth, double setX, double setY, Runnable onClick) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(fitWidth);
        imageView.setLayoutX(setX);
        imageView.setLayoutY(setY);

        Image imageClicked = new Image(imagePathClicked);
        ImageView imageViewClicked = new ImageView(imageClicked);
        imageViewClicked.setPreserveRatio(true);
        imageViewClicked.setFitWidth(fitWidth);
        imageViewClicked.setLayoutX(setX);
        imageViewClicked.setLayoutY(setY);
        imageViewClicked.setVisible(false);

        // Add event handling here
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Change the image when the button is pressed
                imageView.setVisible(false);
                imageViewClicked.setVisible(true);

                // Run the provided onClick action
                onClick.run();
            }
        });

        imageViewClicked.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imageView.setVisible(true);
                imageViewClicked.setVisible(false);
            }
        });

        // Create a group to hold both button states
        Group groupButtons = new Group();
        groupButtons.getChildren().addAll(imageView, imageViewClicked);

        // Add the group to the root
        root.getChildren().addAll(imageView, imageViewClicked);

        return groupButtons;
    }

}
