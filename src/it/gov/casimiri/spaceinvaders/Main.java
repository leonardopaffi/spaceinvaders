package it.gov.casimiri.spaceinvaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author 4B 2014/2015 R.Casimiri, Gualdo Tadino (PG)
 */
public class Main extends Application {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 800;

    public static final int ENEMY_EDGE = 40;
    public static final int ENEMY_ROW = 7;
    public static final int ENEMY_COLUMN = 7;

    public static final int SPEED = 3;

    public int record = 0;

    Bullet bulletc;
    MediaPlayer mp;
    Pane pane = new Pane();
    Image backV = new Image("it/gov/casimiri/spaceinvaders/resources/back.jpg");
    ImageView back = new ImageView(backV);
    Image shipV = new Image("it/gov/casimiri/spaceinvaders/resources/ship.png");
    ImageView ship = new ImageView(shipV);
    Image enemiesV = new Image(
            "it/gov/casimiri/spaceinvaders/resources/invasore1.gif");
    Image enemiesVI = new Image(
            "it/gov/casimiri/spaceinvaders/resources/invasore2.gif");

    static Rectangle pointer = new Rectangle();
    String statusMP;
    ImageView[] enemies = new ImageView[ENEMY_COLUMN * ENEMY_ROW];

    public int MOV = 0;

    boolean rightEnemy = true;
    boolean bulletIsAlive = false;
    boolean newLevel = true;

    int score = 0;
    int updateTime = 28;
    Text punt = new Text("Score: " + score);

    String sRecord = "";

    public void again() {
        for (int j = 0; j < ENEMY_ROW; j++) {
            for (int i = 0; i < ENEMY_COLUMN; i++) {
                enemies[j * ENEMY_COLUMN + i] = new ImageView(enemiesV);
                enemies[j * ENEMY_COLUMN + i].setPreserveRatio(true);
                enemies[j * ENEMY_COLUMN + i].setX(i * 50);
                enemies[j * ENEMY_COLUMN + i].setY(j * 50);
                enemies[j * ENEMY_COLUMN + i].setFitWidth(ENEMY_EDGE);
                pane.getChildren().add(enemies[j * ENEMY_COLUMN + i]);
                if (i == ENEMY_COLUMN - 1 && j == 0) {
                    pointer.setWidth(ENEMY_EDGE);
                    pointer.setHeight(ENEMY_EDGE);
                    pointer.setFill(Color.TRANSPARENT);
                    pointer.setX(enemies[i].getX() + ENEMY_EDGE);
                }
            }
        }
        updateTime-=3;
    }

    @Override
    public void start(Stage primaryStage) {

        URL resource = getClass().getResource("resources/attacco.mp3");
        Media media = new Media(resource.toString());
        mp = new MediaPlayer(media);

        ship.setPreserveRatio(true);
        ship.setFitWidth(80);
        ship.setX(100);
        ship.setY(680);

        pane.getChildren().add(back);
        pane.getChildren().add(ship);
        pane.getChildren().add(punt);

        punt.setFont(Font.font("Verdana", 20));
        punt.setFill(Color.YELLOW);

        again();

        back.setX(0);
        back.setY(0);

        punt.setX(10);
        punt.setY(20);

        Duration dI = new Duration(updateTime);
        KeyFrame f = new KeyFrame(dI, e -> movementCore());
        Timeline tl = new Timeline(f);
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
        Scene scene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.setOnKeyPressed(e -> keyboardManage(e));

        mp.setCycleCount(MediaPlayer.INDEFINITE);
        mp.play();

        primaryStage.setTitle("SpaceInvaders");
        primaryStage.setScene(scene);
        primaryStage.show();

        String k = System.getProperty("user.home");
        String n = k + File.separator + "si.txt";
        System.out.println(n);

        try {
            FileReader fr = new FileReader(n);
            BufferedReader br = new BufferedReader(fr);
            sRecord = br.readLine();
            record = Integer.parseInt(sRecord);
            System.out.println("" + record);
            br.close();
            fr.close();
        } catch (Exception e) {
            record = 0;
        }
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
        } else if (ke.getCode() == KeyCode.SPACE) { // shoot
            bulletc = new Bullet(10, 50, ship.getX(), enemies, pane);
        }
    }

    public void movementCore() {
        if (rightEnemy) { //check if the enemy is going toward right
            if (pointer.getX() + ENEMY_EDGE >= SCREEN_WIDTH) { //check collision on right edge
                rightEnemy = false;
                for (int i = 0; i < enemies.length; i++) {
                    if (enemies[i] != null) {
                        enemies[i].setY(enemies[i].getY() + 50);
                    }
                }
            }
            for (int i = 0; i < enemies.length; i++) {
                if (enemies[i] != null) {
                    enemies[i].setX(enemies[i].getX() + SPEED); //move the enemy 
                }
            }
            pointer.setX(pointer.getX() + SPEED); //move the pointer
        } else {
            if (pointer.getX() - ((ENEMY_EDGE * (ENEMY_COLUMN + 2))) <= 0) {
                rightEnemy = true;
                for (int i = 0; i < enemies.length; i++) {
                    if (enemies[i] != null) {
                        enemies[i].setY(enemies[i].getY() + 50);
                    }
                }
            }
            for (int i = 0; i < enemies.length; i++) {
                if (enemies[i] != null) {
                    enemies[i].setX(enemies[i].getX() - SPEED);
                }
            }
            pointer.setX(pointer.getX() - SPEED);
        }
        MOV++; //animation block 
        if (MOV == 20) { //first frame
            for (int j = 0; j < enemies.length; j++) {
                if (enemies[j] != null) {
                    enemies[j].setImage(enemiesVI);
                }
            }
        } else if (MOV == 40) { //second frame
            for (int j = 0; j < enemies.length; j++) {
                if (enemies[j] != null) {
                    enemies[j].setImage(enemiesV);
                }
            }
            MOV = 0;
        }
        if (bulletc != null) {
            score += bulletc.getScore();
            punt.setText("Score: " + score);
        }
        if (score % 50 * 49 == 0 && score >= 50 * 49) {
            if (newLevel) {
                again();
                newLevel = false;
            }
        }
        if (score % 50 * 49 > 0 && score > 50 * 49) {
            newLevel = true;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}