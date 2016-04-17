package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.stage.Stage;


public class GameOfLifeMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = 	FXMLLoader.load(getClass().getResource("GOLView.fxml"));
			primaryStage.setTitle("Conway's Game of Life");
			// Sets the minimal height and width value
			primaryStage.setMinWidth(600);
			primaryStage.setMinHeight(400);
			Scene GOLgame = new Scene(root);
			// Connects to CSS file
			GOLgame.getStylesheets().add(getClass().getResource("GameboardCSS.css").toExternalForm());
			// Adds a icon to the stage
			primaryStage.getIcons().add(new Image(this.getClass().getResource("GameOfLife.png").toString()));

			primaryStage.setScene(GOLgame);
			primaryStage.show();
			
			// lets us connect the mouse event that is in controller class in some way
	       GOLgame.setOnMouseClicked(GameController.mouseHandlerClicked);
	       GOLgame.setOnMouseDragged(GameController.mouseHandlerDragged);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}	
}