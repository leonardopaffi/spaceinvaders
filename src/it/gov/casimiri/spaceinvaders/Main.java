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
 *
 * @author 4superscient
 */
public class Main extends Application {
    
    Pane pane;
    ImageView ship;
    Rectangle bullet;
    Rectangle[] enemies = new Rectangle[3];

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 800;

    public static final int ENEMY_EDGE = 20;

    boolean rightEnemy = true;
    boolean bulletIsAlive = false;
    
    Timeline tlB;

    @Override
    public void start(Stage primaryStage) {
        pane = new Pane();

        Image shipV = new Image("it/gov/casimiri/spaceinvaders/resources/ship.png"); //130*115
        ship = new ImageView(shipV);
        
        Duration dI = new Duration(100);
        KeyFrame f = new KeyFrame(dI, e -> movementCore());
        Timeline tl = new Timeline(f);
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
        
        Duration dB = new Duration(100);
        KeyFrame fB = new KeyFrame(dB, e -> movementBullet());
        tlB = new Timeline(fB);
        tlB.setCycleCount(Animation.INDEFINITE);
        
        
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
        } else if (ke.getCode() == KeyCode.A) {
            double x = ship.getX();
            x -= 10;
            ship.setX(x);
        } else if (ke.getCode() == KeyCode.UP) {
            double x = bullet.getY();
            x -= 7.5;
            bullet.setY(x);
        } else if (ke.getCode() == KeyCode.DOWN) {
            double x = bullet.getY();
            x += 7.5;
            bullet.setY(x);
        } else if (ke.getCode() == KeyCode.LEFT) {
            double x = bullet.getX();
            x -= 7.5;
            bullet.setX(x);
        } else if (ke.getCode() == KeyCode.RIGHT) {
            double x = bullet.getX();
            x += 7.5;
            bullet.setX(x);
        } else if (ke.getCode() == KeyCode.SPACE){ //shoot
            bulletIsAlive = true;
            bullet = new Rectangle(300, 300, 10, 50);
            pane.getChildren().add(bullet);
            bullet.setX(ship.getX()+40-5);
            bullet.setY(680-40);
            tlB.play();
        }

        for (int i = 0; i < enemies.length; i++) {
            if ((bullet != null && bullet.getX() < enemies[i].getX() + enemies[i].getWidth()
                    && bullet.getX() + bullet.getWidth() > enemies[i].getX()
                    && bullet.getY() < enemies[i].getY() + enemies[i].getHeight()
                    && bullet.getHeight() + bullet.getY() > enemies[i].getY()) || 
                    bullet.getX() <= 0){
                
                System.out.println("DENIED");
                enemies[i].setWidth(0);
                enemies[i].setHeight(0);
                bullet.setVisible(false);
                bullet.setWidth(0);
                bullet.setHeight(0);
                bulletIsAlive = false;
            }
        }
    }

    public void movementCore() {
        if (rightEnemy) {
            if (enemies[enemies.length - 1].getX() + ENEMY_EDGE >= SCREEN_WIDTH) {
                rightEnemy = false;
            }
            for (int i = 0; i < enemies.length; i++) {
                enemies[i].setX(enemies[i].getX()+10);
            }
        } else {
            if (enemies[0].getX() <= 0) {
                rightEnemy = true;
            }
            for (int i = 0; i < enemies.length; i++) {
                enemies[i].setX(enemies[i].getX()-10);
            }
        }
    }
    
    public void movementBullet() {
        if(bulletIsAlive){
            bullet.setY(bullet.getY()-40);   
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
