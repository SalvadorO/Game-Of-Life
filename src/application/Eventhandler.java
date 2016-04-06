package application;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Eventhandler{
	
	Stage stage = (Stage) root.getScene().getWindow();
	
	scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
	    public void handle(MouseEvent mouseEvent) {
	        System.out.println("mouse click detected! " + mouseEvent.getSource());
	    }
	});
	
	
	
}