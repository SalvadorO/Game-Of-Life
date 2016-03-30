package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class GameOfLifeMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("GOLView.fxml"));
			primaryStage.setTitle("Conway's Game of Life");
			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("GameboardCSS.css").toExternalForm());
			// Adds a icon to the stage
			primaryStage.getIcons().add(new Image(this.getClass().getResource("GameOfLife.png").toString()));
			
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}	
}
