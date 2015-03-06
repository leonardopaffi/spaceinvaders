package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	ImageView imgv = new ImageView();
	Image mainCh = new Image();
	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		pane.getChildren().add(mainCh)
		Stage stage = new Stage(pane);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
