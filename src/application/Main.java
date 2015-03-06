package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		Image mainChLoad = new Image("application/res/ship.png");
		ImageView ship = new ImageView(mainChLoad);
		Pane pane = new Pane();
		pane.getChildren().add(ship);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
