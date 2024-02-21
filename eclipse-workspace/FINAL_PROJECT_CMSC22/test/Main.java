/*
 * @authors: Geastin, Ryan, Tan
 * @date: 11-30-2023 to 12-21-2023
 * @section: CMSC 22 W4L
 * @code description: PROJECT_GAME_IS-SCORE-LAR-NG-BAYAN
 */
package test;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public static final String Main = null;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		GameStage gamestage = new GameStage();
		gamestage.setStage(primaryStage);
	}
}