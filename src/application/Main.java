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
	ImageView bullet = new ImageView();

	@Override
	public void start(Stage primaryStage) {
		Image mainChLoad = new Image("/application/res/ship.png", true); // load
																			// ship
		Image bulletLoad = new Image("/application/res/bullet.png", true); // load
																			// bullet
		ship.setImage(mainChLoad); // show ship //176x147px
		Pane pane = new Pane();
		pane.getChildren().add(ship); // add the ship to Pane
		Scene scene = new Scene(pane, 600, 600);

		ship.setX(100);
		ship.setY(550);
		ship.setPreserveRatio(true);
		ship.setFitWidth(50);

		bullet.setVisible(false); // set bullet as invisible

		scene.setOnKeyPressed(e -> keys(e)); // keyboard event
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void keys(KeyEvent ke) {
		if (ke.getCode() == KeyCode.D) {
			double x = ship.getX() + 7.5;
			ship.setX(x);
		}
		if (ke.getCode() == KeyCode.A) {
			double x = ship.getX() - 7.5;
			ship.setX(x);
		}
		if (ke.getCode() == KeyCode.SPACE) {
			/* NEED ANIMATION
			bullet.setVisible(true);
			for (int i = 600; i >= 0; i += 5) {
				bullet.setY(600 - i);
				bullet.setX(ship.getX()+25);
			}
			bullet.setVisible(false);
			*/
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
