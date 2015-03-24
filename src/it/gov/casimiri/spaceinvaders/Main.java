package it.gov.casimiri.spaceinvaders;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author 4°B 2014/2015 R.Casimiri, Gualdo Tadino (PG)
 */
public class Main extends Application {
	Bullet bulletc;
    Pane pane = new Pane();
    Image shipV = new Image("it/gov/casimiri/spaceinvaders/resources/ship.png");
    ImageView ship = new ImageView(shipV);; 
    Rectangle[] enemies = new Rectangle[3];

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 800;

    public static final int ENEMY_EDGE = 20;
    
    int contBullets = 0;

    boolean rightEnemy = true;
    boolean bulletIsAlive = false;
    
    //Timeline tlB;

    @Override
    public void start(Stage primaryStage) {

        
        
        Duration dI = new Duration(25);
        KeyFrame f = new KeyFrame(dI, e -> movementCore());
        Timeline tl = new Timeline(f);
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
        
        ship.setPreserveRatio(true);
        ship.setFitWidth(80);
        ship.setX(100);
        ship.setY(680);

        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Rectangle(i * 50, 50, ENEMY_EDGE, ENEMY_EDGE);
            pane.getChildren().add(enemies[i]);
        }

        pane.getChildren().add(ship);

        Scene scene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.setOnKeyPressed(e -> keyboardManage(e));

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void keyboardManage(KeyEvent ke) {
        if (ke.getCode() == KeyCode.D) {
            double x = ship.getX();
            x += 10;
            ship.setX(x);
        } else if (ke.getCode() == KeyCode.SPACE){ //shoot
            //bulletIsAlive = true;
            //bullet = new Rectangle(300, 300, 10, 50);
        	bulletc = new Bullet(10, 50, ship.getX());
            pane.getChildren().add(bulletc.r);
            //bullet.setX(ship.getX()+40-5);
            //bullet.setY(680-40);
            contBullets++;
            //tlB.play();
        }

      
    }

    public void movementCore() {
        if (rightEnemy) {
            if (enemies[enemies.length - 1].getX() + ENEMY_EDGE >= SCREEN_WIDTH) {
                rightEnemy = false;
            }
            for (int i = 0; i < enemies.length; i++) {
                enemies[i].setX(enemies[i].getX()+5);
            }
        } else {
            if (enemies[0].getX() <= 0) {
                rightEnemy = true;
            }
            for (int i = 0; i < enemies.length; i++) {
                enemies[i].setX(enemies[i].getX()-5);
            }
        }
    }
    
   

    public static void main(String[] args) {
        launch(args);
    }
}
