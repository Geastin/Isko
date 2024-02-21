package menu;

import menu.GameStage_1;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import java.io.File;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
		playAudio("assetsBackground/Main.wav");
		GameStage_1 gamestage = new GameStage_1();
		gamestage.setStage(primaryStage);}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	   public static void playAudio(String filePath) {
	        try {
	            File audioFile = new File(filePath);
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

	            Clip clip = AudioSystem.getClip();
	            clip.open(audioInputStream);

	            clip.start();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
