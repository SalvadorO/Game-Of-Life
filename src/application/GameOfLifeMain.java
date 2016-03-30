package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class GameOfLifeMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("GOLView.fxml"));
			primaryStage.setTitle("Conway's Game of Life");
			Scene scene = new Scene(root);
			primaryStage.setMinWidth(600);
			primaryStage.setMinHeight(400);
			scene.getStylesheets().add(getClass().getResource("GameboardCSS.css").toExternalForm());
			// Adds a icon to the stage
			primaryStage.getIcons().add(new Image(this.getClass().getResource("GameOfLife.png").toString()));
			
			primaryStage.setScene(scene);
			primaryStage.show();

	    	scene.setOnMousePressed(new EventHandler<MouseEvent>() {
	    	        public void handle(MouseEvent event) {
	    	            System.out.println("You pressed:\nX: "+event.getX()+"\nY: "+event.getY());
		    	        }
		    	    });
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}	
}
