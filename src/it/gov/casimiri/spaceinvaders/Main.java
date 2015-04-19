package it.gov.casimiri.spaceinvaders;
import java.net.URL;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author 4°B 2014/2015 R.Casimiri, Gualdo Tadino (PG)
 */

public class Main extends Application {
	Bullet bulletc;
	MediaPlayer mp;
    Pane pane = new Pane();
    Image shipV = new Image("it/gov/casimiri/spaceinvaders/resources/ship.png");
    ImageView ship = new ImageView(shipV);; 
    Rectangle[] enemies = new Rectangle[12];
    Rectangle pointer = new Rectangle();
    String statusMP;

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 800;

    public static final int ENEMY_EDGE = 20;
    public static final int ENEMY_ROW = 3;
    public static final int ENEMY_COLUMN = 4;
    
    public static final int SPEED = 3;

    boolean rightEnemy = true;
    boolean bulletIsAlive = false;

    @Override
    public void start(Stage primaryStage) {
    	URL resource = getClass().getResource("resources/attacco.mp3");
    	Media media = new Media(resource.toString());
    	mp = new MediaPlayer(media);
    	
        ship.setPreserveRatio(true);
        ship.setFitWidth(80);
        ship.setX(100);
        ship.setY(680);
        
        for (int j = 0; j < ENEMY_ROW; j++) {
			for (int i = 0; i < ENEMY_COLUMN; i++) {
        		enemies[j*ENEMY_COLUMN+i] = new Rectangle(i*50, j*50, ENEMY_EDGE, ENEMY_EDGE);
        		pane.getChildren().add(enemies[j*4+i]);
        		if (i==ENEMY_COLUMN-1 && j==0){
        			pointer.setWidth(ENEMY_EDGE);
        			pointer.setHeight(ENEMY_EDGE);
        			pointer.setFill(Color.TRANSPARENT);
        			pointer.setX(enemies[i].getX() + ENEMY_EDGE);
        		}
        	}
        }
        
        pane.getChildren().add(ship);
        
        Duration dI = new Duration(25);
        KeyFrame f = new KeyFrame(dI, e -> movementCore());
        Timeline tl = new Timeline(f);
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
        Scene scene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.setOnKeyPressed(e -> keyboardManage(e));
        
        mp.setCycleCount(MediaPlayer.INDEFINITE);
        mp.play();

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void keyboardManage(KeyEvent ke) {
        if (ke.getCode() == KeyCode.D) {
            double x = ship.getX();
            x += 10;
            ship.setX(x);
        } else if (ke.getCode() == KeyCode.A) {
            double x = ship.getX();
            x -= 10;
            ship.setX(x);
        } else if (ke.getCode() == KeyCode.SPACE){ //shoot
        	bulletc = new Bullet(10, 50, ship.getX(), enemies, pane);
        }
    }

    public void movementCore() {
        if (rightEnemy) {
			if (pointer.getX() + ENEMY_EDGE >= SCREEN_WIDTH) {
                rightEnemy = false;
                for (int i = 0; i < enemies.length; i++) {
                	if(enemies[i]!=null){
    					enemies[i].setY(enemies[i].getY()+50);
                	}
    			}
			}
        	for (int i = 0; i < enemies.length; i++) {
        		if(enemies[i]!=null){
					enemies[i].setX(enemies[i].getX()+SPEED);
        		}
			}
        	pointer.setX(pointer.getX()+ SPEED);
        } else {
            if (pointer.getX() - ((ENEMY_EDGE*ENEMY_COLUMN+50)/2*(ENEMY_COLUMN-1)) <= 0) {
                rightEnemy = true;
                for (int i = 0; i < enemies.length; i++) {
                	if(enemies[i]!=null){
    					enemies[i].setY(enemies[i].getY()+50);
                	}
    			}
            }
            for (int i = 0; i < enemies.length; i++) {
            	if(enemies[i]!=null){
            		enemies[i].setX(enemies[i].getX()-SPEED);
            	}
            }
            pointer.setX(pointer.getX()- SPEED);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
