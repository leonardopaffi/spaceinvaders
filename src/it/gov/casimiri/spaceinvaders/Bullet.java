package it.gov.casimiri.spaceinvaders;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Bullet {
	boolean bulletIsAlive = true;
	Rectangle r = null;
	
	public Bullet (double x, double y, double pos, Rectangle v[], Pane p){
		Timeline tlB;
		r = new Rectangle (x, y);
		p.getChildren().add(r);
        r.setX(pos+40-5);
        r.setY(680-40);
        Duration dB = new Duration(5);
        KeyFrame fB = new KeyFrame(dB, e -> {
        	if(r != null){
            	r.setY(r.getY()-5);
            	collisioncheck(v);
        	}
        });
        tlB = new Timeline(fB);
        tlB.setCycleCount(Animation.INDEFINITE);
        tlB.play();
	}
	
	private boolean collisioncheck (Rectangle enemies[]){
    	for (int i = 0; i < enemies.length; i++) {
    		if (r != null && enemies[i] != null){
    			if ((r != null && r.getX() < enemies[i].getX() + enemies[i].getWidth()
    			&& r.getX() + r.getWidth() > enemies[i].getX()
    		    && r.getY() < enemies[i].getY() + enemies[i].getHeight()
    		    && r.getHeight() + r.getY() > enemies[i].getY()))
    			{
    				System.out.println("DENIED");
        			enemies[i].setWidth(0);
        			enemies[i].setHeight(0);
        			enemies[i].setVisible(false);
        			enemies[i] = null;
        			r.setVisible(false);
        			r.setWidth(0);
        			r.setHeight(0);
        			r= null;
        			bulletIsAlive = false;
    			}
    		}
    	}
    	if(r!=null){
    		if(r.getY()<0){
    			r.setVisible(false);
    			r.setWidth(0);
    			r.setHeight(0);
    			r= null;
    		}
    	}
    	return bulletIsAlive;
    }
}
