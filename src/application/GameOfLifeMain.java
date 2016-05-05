package application;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.stage.Stage;

/**
 * The Class GameOfLifeMain.
 */
public class GameOfLifeMain extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = 	FXMLLoader.load(getClass().getResource("GOLView.fxml"));
			primaryStage.setTitle("Conway's Game of Life");
			// Sets the minimal height and width value
			primaryStage.setMinWidth(716);
			primaryStage.setMinHeight(799);
			primaryStage.setResizable(true);
			Scene GOLgame = new Scene(root);
			// Connects to CSS file
			GOLgame.getStylesheets().add(getClass().getResource("GameboardCSS.css").toExternalForm());
			// Adds a icon to the stage
			primaryStage.getIcons().add(new Image(this.getClass().getResource("GameOfLife.png").toString()));

			primaryStage.setScene(GOLgame);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}	
}