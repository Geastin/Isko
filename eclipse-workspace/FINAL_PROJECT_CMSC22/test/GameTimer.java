package test;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import test.GameStage;

import test.BackgroundManager;


public class GameTimer extends AnimationTimer{
	//STAGE DECLARATION
	private Scene scene;
	private String msg;
	private Group root;
	private GraphicsContext gc;
	public final Image bg = new Image("images/Gradient.png",608,950,false,false);
	
	//ASSETS DECLARATION
	private Character myCharacter;
	//GOOD ASSETS
	private ArrayList<Book> book;
	private ArrayList<Notes> notes;
	private ArrayList<Coffee> coffee;
	private ArrayList<Attend> attend;
	//BAD ASSETS
	private ArrayList<Object> objects;
	private ArrayList<Cntrl> cntrl;
	private ArrayList<Twt> twt;
	private ArrayList<TkTk> tktk;
	
	//TIMER DECLARATION
	private long time;
	private long startDrop;
	private long startFall;
	private long startDown;
	private long startSouth;
	private long startSinking;
	private long startSlipping;
	private long startDipping;
	private long startDecline;
	
	//MAX NUMBER DECLARATION
	public static final int MAX_NUM_BOOK = 1;
	public static final int MAX_NUM_NOTES = 1;
	public static final int MAX_NUM_COFFEE = 1;
	public static final int MAX_NUM_ATTEND = 2;
	
	public static final int MAX_NUM_OBJECTS = 1;
	public static final int MAX_NUM_CNTRL = 1;
	public static final int MAX_NUM_TWT = 2;
	public static final int MAX_NUM_TKTK = 1;

	private int score = 0;
	private BackgroundManager backgroundManager;
	
	GameTimer(GraphicsContext gc, Scene scene){
		//STAGE
		this.gc = gc;
		this.scene = scene;
		
		this.myCharacter = new Character(200, 890);
		
		//GOOD ASSETS
		this.book = new ArrayList<Book>();
		this.notes = new ArrayList<Notes>();
		this.coffee = new ArrayList<Coffee>();
		this.attend = new ArrayList<Attend>();

		//BAD ASSETS
		this.objects = new ArrayList<Object>();
		this.cntrl = new ArrayList<Cntrl>();
		this.twt = new ArrayList<Twt>();
		this.tktk = new ArrayList<TkTk>();
		
		//GOOD ASSET DROPS
		this.dropBook(GameTimer.MAX_NUM_BOOK);
		this.dropNotes(GameTimer.MAX_NUM_NOTES);
		this.dropCoffee(GameTimer.MAX_NUM_COFFEE);
		this.dropAttend(GameTimer.MAX_NUM_ATTEND);
		
		//BAD ASSET DROPS
		this.dropObjects(GameTimer.MAX_NUM_OBJECTS);
		this.dropCntrl(GameTimer.MAX_NUM_CNTRL);
		this.dropTwt(GameTimer.MAX_NUM_TWT);
		this.dropTkTk(GameTimer.MAX_NUM_TKTK);
		
		//TIMERS
		this.time = System.nanoTime(); //countdown timer
		this.startDrop = System.nanoTime(); //alcohol timer
		this.startFall = System.nanoTime(); //controller timer
		this.startDown = System.nanoTime(); //twitter timer
		this.startSouth = System.nanoTime(); //tiktok timer
		this.startSinking = System.nanoTime(); //book timer
		this.startSlipping = System.nanoTime(); //notes timer
		this.startDipping = System.nanoTime(); //coffee timer
		this.startDecline = System.nanoTime(); //attendance timer

		this.handleKeyPressEvent();
	}

	@Override
	public void handle(long currentNanoTime) {
		this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.gc.drawImage(this.bg, 0, 0);
		long currentSec = TimeUnit.NANOSECONDS.toSeconds(currentNanoTime);
		
		//RENDER CHARACTER
		this.myCharacter.render(this.gc);
		this.myCharacter.move();
		
		this.showScore();
		
		//RENDER GOOD ASSETS
		this.renderBook();
		this.renderNotes();
		this.renderCoffee();
		this.renderAttend();
		
		//RENDER BAD ASSETS
		this.renderObjects();
		this.renderCntrl();
		this.renderTwt();
		this.renderTkTk();
		
		//DROP RATES
		this.dropMoreBook(currentSec);
		this.dropMoreNotes(currentSec);
		this.dropMoreCoffee(currentSec);
		this.dropMoreAttend(currentSec);
		
		this.dropMoreObjects(currentSec);
		this.dropMoreCntrl(currentSec);
		this.dropMoreTwt(currentSec);
		this.dropMoreTkTk(currentSec);
		
		//MOVE ASSETS
		this.moveBook();
		this.moveNotes();
		this.moveCoffee();
		this.moveAttend();
		
		this.moveObjects();
		this.moveCntrl();
		this.moveTwt();
		this.moveTkTk();
		
		//PLAYER CATCH ASSETS
		this.catchBook();
		this.catchNotes();
		this.catchCoffee();
		this.catchAttend();
		
		this.catchObject();
		this.catchCntrl();
		this.catchTwt();
		this.catchTkTk();
		
		//REMOVE BAD ASSET
		this.disappearBook();
		this.disappearNotes();
		this.disappearCoffee();
		this.disappearAttend();
		
		this.disappear();
		this.disappearCntrl();
		this.disappearTwt();
		this.disappearTkTk();
		
	}
	//SHOW SCORE
	private void showScore() {
		this.gc.fillText("SCORE:" + Integer.toString(score), GameStage.WINDOW_WIDTH * 0.05, GameStage.WINDOW_HEIGHT * 0.05);
		Font theFont = Font.font("Impact",FontWeight.BOLD,40);
		this.gc.setFont(theFont);
		}

	//RENDER GOOD ASSETS
	private void renderBook() {
		for (Book b : this.book){
			b.render(this.gc);
		}
	}
	private void renderNotes() {
		for (Notes no : this.notes){
			no.render(this.gc);
		}
	}
	private void renderCoffee() {
		for (Coffee co : this.coffee){
			co.render(this.gc);
		}
	}
	private void renderAttend() {
		for (Attend a : this.attend){
			a.render(this.gc);
		}
	}
	//RENDER BAD ASSETS
	private void renderObjects() {
		for (Object o : this.objects){
			o.render(this.gc);
		}
	}
	private void renderCntrl() {
		for (Cntrl c : this.cntrl){
			c.render(this.gc);
		}
	}
	private void renderTwt() {
		for (Twt t : this.twt){
			t.render(this.gc);
		}
	}
	private void renderTkTk() {
		for (TkTk tk : this.tktk){
			tk.render(this.gc);
		}
	}

	//DROP GOOD ASSETS
	private void dropBook(int num){
		Random r = new Random();
		for(int i=0;i<num;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH);
			int y = 0;
			this.book.add(new Book(x,y));
		}
	}
	private void dropNotes(int num){
		Random r = new Random();
		for(int i=0;i<num;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH);
			int y = 0;
			this.notes.add(new Notes(x,y));
		}
	}
	private void dropCoffee(int num){
		Random r = new Random();
		for(int i=0;i<num;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH);
			int y = 0;
			this.coffee.add(new Coffee(x,y));
		}
	}
	private void dropAttend(int num){
		Random r = new Random();
		for(int i=0;i<num;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH);
			int y = 0;
			this.attend.add(new Attend(x,y));
		}
	}
	
	//DROP BAD ASSETS
	private void dropObjects(int num){
		Random r = new Random();
		for(int i=0;i<num;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH);
			int y = 0;
			this.objects.add(new Object(x,y));
		}
	}
	private void dropCntrl(int num){
		Random r = new Random();
		for(int i=0;i<num;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH);
			int y = 0;
			this.cntrl.add(new Cntrl(x,y));
		}
	}
	private void dropTwt(int num){
		Random r = new Random();
		for(int i=0;i<num;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH);
			int y = 0;
			this.twt.add(new Twt(x,y));
		}
	}
	private void dropTkTk(int num){
		Random r = new Random();
		for(int i=0;i<num;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH);
			int y = 0;
			this.tktk.add(new TkTk(x,y));
		}
	}

	//DROP RATES
	private void dropMoreBook(long currentSec){
		long startSec = TimeUnit.NANOSECONDS.toSeconds(this.startSinking);
		if((currentSec - startSec) == 6){
			this.dropBook(GameTimer.MAX_NUM_BOOK); //drop 3 more objects after 5 seconds
			this.startSinking = System.nanoTime(); //timer update
		}
	}
	private void dropMoreNotes(long currentSec){
		long startSec = TimeUnit.NANOSECONDS.toSeconds(this.startSlipping);
		if((currentSec - startSec) == 5){
			this.dropNotes(GameTimer.MAX_NUM_NOTES); //drop 3 more objects after 5 seconds
			this.startSlipping = System.nanoTime(); //timer update
		}
	}
	private void dropMoreCoffee(long currentSec){
		long startSec = TimeUnit.NANOSECONDS.toSeconds(this.startDipping);
		if((currentSec - startSec) == 4){
			this.dropCoffee(GameTimer.MAX_NUM_COFFEE); //drop 3 more objects after 5 seconds
			this.startDipping = System.nanoTime(); //timer update
		}
	}
	private void dropMoreAttend(long currentSec){
		long startSec = TimeUnit.NANOSECONDS.toSeconds(this.startDecline);
		if((currentSec - startSec) == 3){
			this.dropAttend(GameTimer.MAX_NUM_ATTEND); //drop 3 more objects after 5 seconds
			this.startDecline = System.nanoTime(); //timer update
		}
	}
	private void dropMoreObjects(long currentSec){
		long startSec = TimeUnit.NANOSECONDS.toSeconds(this.startDrop);
		if((currentSec - startSec) == 6){
			this.dropObjects(GameTimer.MAX_NUM_OBJECTS); //drop 3 more objects after 5 seconds
			this.startDrop = System.nanoTime(); //timer update
		}
	}
	private void dropMoreCntrl(long currentSec){
		long startSec = TimeUnit.NANOSECONDS.toSeconds(this.startFall);
		if((currentSec - startSec) == 5){
			this.dropCntrl(GameTimer.MAX_NUM_CNTRL); //drop 3 more objects after 5 seconds
			this.startFall = System.nanoTime(); //timer update
		}
	}
	private void dropMoreTwt(long currentSec){
		long startSec = TimeUnit.NANOSECONDS.toSeconds(this.startDown);
		if((currentSec - startSec) == 3){
			this.dropTwt(GameTimer.MAX_NUM_TWT); //drop 3 more objects after 5 seconds
			this.startDown = System.nanoTime(); //timer update
		}
	}
	private void dropMoreTkTk(long currentSec){
		long startSec = TimeUnit.NANOSECONDS.toSeconds(this.startSouth);
		if((currentSec - startSec) == 4){
			this.dropTkTk(GameTimer.MAX_NUM_TKTK); //drop 3 more objects after 5 seconds
			this.startSouth = System.nanoTime(); //timer update
		}
	}


	//ASSET REMOVE CONDITION
	private void moveBook(){
		for(int i = 0; i < this.book.size(); i++){
			Book b = this.book.get(i);
			if(b.isCatchedBook() || b.reachedEndBook()) {
				this.book.remove(i);
			} else{
				b.moveBook();
			}
		}
	}
	private void moveNotes(){
		for(int i = 0; i < this.notes.size(); i++){
			Notes no = this.notes.get(i);
			if(no.isCatchedNotes() || no.reachedEndNotes()) {
				this.notes.remove(i);
			} else{
				no.moveNotes();
			}
		}
	}
	private void moveCoffee(){
		for(int i = 0; i < this.coffee.size(); i++){
			Coffee co = this.coffee.get(i);
			if(co.isCatchedCoffee() || co.reachedEndCoffee()) {
				this.coffee.remove(i);
			} else{
				co.moveCoffee();
			}
		}
	}
	private void moveAttend(){
		for(int i = 0; i < this.attend.size(); i++){
			Attend a = this.attend.get(i);
			if(a.isCatchedAttend() || a.reachedEndAttend()) {
				this.attend.remove(i);
			} else{
				a.moveAttend();
			}
		}
	}
	
	private void moveObjects(){
		for(int i = 0; i < this.objects.size(); i++){
			Object o = this.objects.get(i);
			if(o.isCatched() || o.reachedEnd()) {
				this.objects.remove(i);
			} else{
				o.move();
			}
		}
	}
	private void moveCntrl(){
		for(int i = 0; i < this.cntrl.size(); i++){
			Cntrl c = this.cntrl.get(i);
			if(c.isCatchedCntrl() || c.reachedEndCntrl()) {
				this.cntrl.remove(i);
			} else{
				c.moveCntrl();
			}
		}
	}
	private void moveTwt(){
		for(int i = 0; i < this.twt.size(); i++){
			Twt t = this.twt.get(i);
			if(t.isCatchedTwt() || t.reachedEndTwt()) {
				this.twt.remove(i);
			} else{
				t.moveTwt();
			}
		}
	}
	private void moveTkTk(){
		for(int i = 0; i < this.tktk.size(); i++){
			TkTk tk = this.tktk.get(i);
			if(tk.isCatchedTkTk() || tk.reachedEndTkTk()) {
				this.tktk.remove(i);
			} else{
				tk.moveTkTk();
			}
		}
	}
	
	//ASSET CATCH CONDITION
	private void catchBook(){
		for(int i = 0; i < this.book.size(); i++){
			Book b = this.book.get(i);
			if(b.collidesWith(this.myCharacter)){
				b.catchBook();
				b.setVisible(false);
				increaseScore(10);
                updateScoreDisplay();
                checkWinCondition();
			}
		}
	}
	private void catchNotes(){
		for(int i = 0; i < this.notes.size(); i++){
			Notes no = this.notes.get(i);
			if(no.collidesWith(this.myCharacter)){
				no.catchNotes();
				no.setVisible(false);
				increaseScore(5);
                updateScoreDisplay();
                checkWinCondition();
			}
		}
	}
	private void catchCoffee(){
		for(int i = 0; i < this.coffee.size(); i++){
			Coffee co = this.coffee.get(i);
			if(co.collidesWith(this.myCharacter)){
				co.catchCoffee();
				co.setVisible(false);
				increaseScore(5);
                updateScoreDisplay();
                checkWinCondition();
			}
		}
	}
	private void catchAttend(){
		for(int i = 0; i < this.attend.size(); i++){
			Attend a = this.attend.get(i);
			if(a.collidesWith(this.myCharacter)){
				a.catchAttend();
				a.setVisible(false);
				increaseScore(1);
                updateScoreDisplay();
                checkWinCondition();
			}
		}
	}
	
	private void catchObject(){
		for(int i = 0; i < this.objects.size(); i++){
			Object o = this.objects.get(i);
			if(o.collidesWith(this.myCharacter)){
				o.catchObject();
				o.setVisible(false);
				increaseScore(-10);
                updateScoreDisplay();
                checkWinCondition();
			}
		}
	}
	private void catchCntrl(){
		for(int i = 0; i < this.cntrl.size(); i++){
			Cntrl c = this.cntrl.get(i);
			if(c.collidesWith(this.myCharacter)){
				c.catchCntrl();
				c.setVisible(false);
				increaseScore(-5);
                updateScoreDisplay();
                checkWinCondition();
			}
		}
	}
	private void catchTwt(){
		for(int i = 0; i < this.twt.size(); i++){
			Twt t = this.twt.get(i);
			if(t.collidesWith(this.myCharacter)){
				t.catchTwt();
				t.setVisible(false);
				increaseScore(-2);
                updateScoreDisplay();
                checkWinCondition();
			}
		}
	}
	private void catchTkTk(){
		for(int i = 0; i < this.tktk.size(); i++){
			TkTk tk = this.tktk.get(i);
			if(tk.collidesWith(this.myCharacter)){
				tk.catchTkTk();
				tk.setVisible(false);
				increaseScore(-5);
                updateScoreDisplay();
                checkWinCondition();
			}
		}
	}

	// DESPAWN ASSETS
	private void disappearBook(){
		for(int i = 0; i < this.book.size(); i++){
			Book b = this.book.get(i);
			if(b.y > (GameStage.WINDOW_HEIGHT)){
				b.reachedEndBook();
				b.setVisible(false);
			}
		}
	}
	private void disappearNotes(){
		for(int i = 0; i < this.notes.size(); i++){
			Notes no = this.notes.get(i);
			if(no.y > (GameStage.WINDOW_HEIGHT)){
				no.reachedEndNotes();
				no.setVisible(false);
			}
		}
	}
	private void disappearCoffee(){
		for(int i = 0; i < this.coffee.size(); i++){
			Coffee co = this.coffee.get(i);
			if(co.y > (GameStage.WINDOW_HEIGHT)){
				co.reachedEndCoffee();
				co.setVisible(false);
			}
		}
	}
	private void disappearAttend(){
		for(int i = 0; i < this.attend.size(); i++){
			Attend a = this.attend.get(i);
			if(a.y > (GameStage.WINDOW_HEIGHT)){
				a.reachedEndAttend();
				a.setVisible(false);
			}
		}
	}
	private void disappear(){
		for(int i = 0; i < this.objects.size(); i++){
			Object o = this.objects.get(i);
			if(o.y > (GameStage.WINDOW_HEIGHT)){
				o.reachedEnd();
				o.setVisible(false);
			}
		}
	}
	private void disappearCntrl(){
		for(int i = 0; i < this.cntrl.size(); i++){
			Cntrl c = this.cntrl.get(i);
			if(c.y > (GameStage.WINDOW_HEIGHT)){
				c.reachedEndCntrl();
				c.setVisible(false);
			}
		}
	}
	private void disappearTwt(){
		for(int i = 0; i < this.twt.size(); i++){
			Twt t = this.twt.get(i);
			if(t.y > (GameStage.WINDOW_HEIGHT)){
				t.reachedEndTwt();
				t.setVisible(false);
			}
		}
	}
	private void disappearTkTk(){
		for(int i = 0; i < this.tktk.size(); i++){
			TkTk tk = this.tktk.get(i);
			if(tk.y > (GameStage.WINDOW_HEIGHT)){
				tk.reachedEndTkTk();
				tk.setVisible(false);
			}
		}
	}

	//KEY PRESS EVENTS
	public void handleKeyPressEvent() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode code = e.getCode();
				System.out.println(code);
				moveChar(code);
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode code = e.getCode();
				System.out.println(code);
				stopChar(code);
			}
		});
	}

	//MOVE CHARACTER
	private void moveChar(KeyCode code){
		if(code==KeyCode.A || code == KeyCode.LEFT) this.myCharacter.setDX(-5);
		if(code == KeyCode.D || code == KeyCode.RIGHT) this.myCharacter.setDX(5);
	}

	//STOP CHARACTER
	private void stopChar(KeyCode ke){
		this.myCharacter.setDX(0);
		this.myCharacter.setDY(0);
	}
	
	private void increaseScore(int points) {
        score += points;
        //System.out.println("Score: " + score);

    }

    private void updateScoreDisplay() {
        System.out.println("Score: " + score); // Output to console, replace with your actual update logic
    }

    private void checkWinCondition() {
        if (score >= 100) {
            stop(); // Stop the game
            System.out.println("You Win!");
            // insert YOU WIN Screen
            //backgroundManager.showWinCondition();

        }

        if (score < 0) {
            stop(); // Stop the game
            System.out.println("You Lose!");
            // insert YOU LOSE Screen
            //backgroundManager.showLoseCondition();
        }
    }
}













