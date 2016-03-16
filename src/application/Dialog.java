package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class Dialog {

	// About Dialogue
		protected static  void AboutDialogue() {
			Alert about = new Alert(AlertType.INFORMATION);
			about.setTitle("About");
			about.setHeaderText(null);
			about.setContentText("John Conway's Game Of Life"+"\n\n"+"The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970"+"\n"+"The 'game' is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves or, for advanced players, by creating patterns with particular properties."+"\n\n"+"Rules"+"\n"+"1. Any live cell with fewer than two live neighbours dies, as if caused by under-population."+"\n"+"2. Any live cell with two or three live neighbours lives on to the next generation."+"\n"+"3. Any live cell with more than three live neighbours dies, as if by over-population."+"\n"+"4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.");
			about.setResizable(true);
			about.initStyle(StageStyle.UNDECORATED);
			
			ButtonType buttonTypeCancel = new ButtonType("I understand", ButtonData.CANCEL_CLOSE);
			about.getButtonTypes().setAll(buttonTypeCancel);
			
			about.showAndWait();
		}
		// Advanced Dialogue
		protected  static void AdvancedDialogue() {
			Alert advanced = new Alert(AlertType.INFORMATION);
			advanced.setTitle("Advanced");
			advanced.setHeaderText(null);
			advanced.setContentText("Advanced Menu is now enabled! \n\n Good luck!");
			advanced.setResizable(true);
			
			ButtonType buttonTypeCancel = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
			advanced.getButtonTypes().setAll(buttonTypeCancel);
			
			advanced.showAndWait();
		}
		// Stats Dialogue
		protected static void StatsDialogue()	{
			Alert stats = new Alert(AlertType.INFORMATION);
			stats.setTitle("Stats");
			stats.setHeaderText(null);
			stats.setResizable(false);
			stats.setContentText("Here will stats about the game be \n\n\n Ticks in the game: \n How many cell's are alive: \n And much more!");
			stats.initStyle(StageStyle.UNDECORATED);
			
			ButtonType buttonTypeCancel = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
			stats.getButtonTypes().setAll(buttonTypeCancel);
			
			stats.showAndWait();
		}	
	
		// Shapes Dialogue
		protected static  void ShapesDialogue()	{
			List<String> choices = new ArrayList<>();
			choices.add("1");
			choices.add("2");
			
			ChoiceDialog<String> dialog = new ChoiceDialog<>(null,choices);
			dialog.setTitle("Select your shape");
			dialog.setHeaderText(null);
			dialog.setContentText("Choose your shape");
			
			Optional<String> result = dialog.showAndWait();
			if(result.isPresent()){
				System.out.println("You picked " + result.get());
			}
		}
	/**
	 * Method that show a custom inputdialog box to the user.
	 * The dialog takes an x value and a y value.
	 * The values are returned to the calling method.
	 * TODO: consider if method should reside in another class, e.g. GameBoardCanvas
	 * TODO: validate x and y
	 * @return 
	 * @return String
	 */
	public Optional<int[]> setGridSizeDialogue()	{
		Optional<int[]>returnValue=null;
		
		Dialog<int[]> dialog = new Dialog<>();
		dialog.setTitle("Enter size of grid");
		dialog.setResizable(false);
		Label lbl_x = new Label("X-value:");
		Label lbl_y = new Label("Y-value:");
		TextField txt_x = new TextField();
		TextField txt_y = new TextField();
		
		GridPane grid = new GridPane();
		grid.add(lbl_x, 1, 1);
		grid.add(lbl_y, 2, 1);
		grid.add(txt_x, 1, 2);
		grid.add(txt_y, 2, 2);
		
		dialog.getDialogPane().setContent(grid);
//		Focuses the X-value when you enter the stage
		Platform.runLater(() -> txt_x.requestFocus());
		
		ButtonType buttonTypeOk = new ButtonType ("Ok", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType ("Cancel", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeCancel, buttonTypeOk);
		
		dialog.setResultConverter(new Callback<ButtonType, int[]>() {
			@Override	
			public int[] call(ButtonType b) {
				if (b==buttonTypeOk){
					return new int[]{Integer.parseInt(txt_x.getText()), Integer.parseInt(txt_y.getText())};
				}
				return null;
			}
	    });
		
		Optional<int[]> result = dialog.showAndWait();
		if ( result.isPresent()){
			return result;
		}
		
		return returnValue;
	}
}
