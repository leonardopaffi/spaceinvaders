package it.gov.casimiri.spaceinvaders;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Bullet {
	Main main = new Main();
	boolean bulletIsAlive = true;
	Rectangle r = new Rectangle (0,0);
	public Bullet (){
		
	}
	
	public Bullet (double x, double y, double pos){
		Timeline tlB;
		r = new Rectangle (x, y);
		main.pane.getChildren().add(r);
        r.setX(pos+40-5);
        r.setY(680-40);
        Duration dB = new Duration(25);
        KeyFrame fB = new KeyFrame(dB, e -> {
            	r.setY(r.getY()-5);
            	collisioncheck();
        });
        tlB = new Timeline(fB);
        tlB.setCycleCount(Animation.INDEFINITE);
        tlB.play();
	}
	
	 public boolean collisioncheck (){
	    	for (int i = 0; i < main.enemies.length; i++) {
	        if ((r != null && r.getX() < main.enemies[i].getX() + main.enemies[i].getWidth()
	                && r.getX() + r.getWidth() > main.enemies[i].getX()
	                && r.getY() < main.enemies[i].getY() + main.enemies[i].getHeight()
	                && r.getHeight() + r.getY() > main.enemies[i].getY())){
	            
	            System.out.println("DENIED");
	            main.enemies[i].setWidth(0);
	            main.enemies[i].setHeight(0);
	            r.setVisible(false);
	            r.setWidth(0);
	            r.setHeight(0);
	            bulletIsAlive = false;
	        }
	    }
	    	return bulletIsAlive;
	    }
}
