package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;


public class Main extends Application {
	ImageView ship = new ImageView();
	@Override
	public void start(Stage primaryStage) {
		Image mainChLoad = new Image("/application/res/ship.png", true); //loads ship
		ship.setImage(mainChLoad);  //show ship
		Pane pane = new Pane(); 
		pane.getChildren().add(ship); //adds the ship to Pane
		Scene scene = new Scene(pane, 600, 600);
		scene.setOnKeyPressed(e-> keys(e)); //keyboard event
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void keys(KeyEvent ke){
		if(ke.getCode() == KeyCode.D){
			double x = ship.getX()+10;
			ship.setX(x);
		}
		if(ke.getCode() == KeyCode.A){
			double x = ship.getX()-10;
			ship.setX(x);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
