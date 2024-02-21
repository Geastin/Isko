package test;


import javafx.scene.Group;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class BackgroundManager {
    private Group root;
    static int WINDOW_HEIGHT = 608;
    static int WINDOW_WIDTH = 1000;
    
    public BackgroundManager(Group root) {
        this.root = root;
      
    }

    public void showWinCondition(){
        
        Rectangle creditsBackground = new Rectangle(WINDOW_HEIGHT, WINDOW_WIDTH, Paint.valueOf("rgba(0, 0, 0, 0.8)")); // Adjust the color and opacity
      root.getChildren().add(creditsBackground);

      // Add rolling credits text
      Text winText = new Text("YOU WIN!");
      
      // Setting Font
      
      Font customFont = Font.loadFont(getClass().getResourceAsStream("/images/Pixeboy.ttf"), 150);
      
      winText.setFont(customFont);
      winText.setFill(Color.WHITE);
      winText.setTextAlignment(TextAlignment.CENTER);
      
   // Calculate center position
      double centerX = (WINDOW_HEIGHT - winText.getLayoutBounds().getWidth()) / 2;
      double centerY = (WINDOW_WIDTH - winText.getLayoutBounds().getHeight()) / 2;

      winText.setLayoutX(centerX);
      winText.setLayoutY(centerY);
      
      // Add creditsText to the root group
      root.getChildren().add(winText);
    }
public void showLoseCondition(){
        
        Rectangle creditsBackground = new Rectangle(WINDOW_HEIGHT, WINDOW_WIDTH, Paint.valueOf("rgba(0, 0, 0, 0.8)")); // Adjust the color and opacity
      root.getChildren().add(creditsBackground);

      // Add rolling credits text
      Text loseText = new Text("YOU LOSE!");
      
      // Setting Font
      Font customFont = Font.loadFont(getClass().getResourceAsStream("/assetsBackground/Pixeboy.ttf"), 150);
      
      loseText.setFont(customFont);
      loseText.setFill(Color.WHITE);
      loseText.setTextAlignment(TextAlignment.CENTER);
      
   // Calculate center position
      double centerX = (WINDOW_HEIGHT - loseText.getLayoutBounds().getWidth()) / 2;
      double centerY = (WINDOW_WIDTH - loseText.getLayoutBounds().getHeight()) / 2;

      loseText.setLayoutX(centerX);
      loseText.setLayoutY(centerY);
      
      // Add creditsText to the root group
      root.getChildren().add(loseText);
    }
    
}
