package application;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * The Class golDialog.
 */
public class golDialog extends Application	{

		/**
		 * Method that show a Alertbox to the user that explains how the game work.
		 */
		protected static void AboutDialogue() {
			Alert about = new Alert(AlertType.INFORMATION);
			about.setHeaderText(null);
			about.setContentText("John Conway's Game Of Life"+"\n\n"+"The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970"+"\n"+"The 'game' is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves or, for advanced players, by creating patterns with particular properties."+"\n\n"+"Rules"+"\n"+"1. Any live cell with fewer than two live neighbours dies, as if caused by under-population."+"\n"+"2. Any live cell with two or three live neighbours lives on to the next generation."+"\n"+"3. Any live cell with more than three live neighbours dies, as if by over-population."+"\n"+"4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.");
			about.initStyle(StageStyle.UNDECORATED);
			
			ButtonType buttonTypeCancel = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
			about.getButtonTypes().setAll(buttonTypeCancel);
			
			about.showAndWait();
		}
		
		/**
		 * Advanced dialogue, witch will allow the user to take more controll over the game, and its settings
		 */
		// Advanced Dialogue
		protected  static void AdvancedDialogue() {
			Alert advanced = new Alert(AlertType.NONE);
			advanced.setTitle("Advanced");
			advanced.setHeaderText(null);
			advanced.setContentText("Advanced Menu is now enabled! \n\n Good luck!");
			advanced.setResizable(true);
			
			advanced.showAndWait();
		}
		
		/**
		 * Stats dialogue.
		 */
		protected static void StatsDialogue()	{
			Alert stats = new Alert(AlertType.NONE);
			stats.setTitle("Stats");
			stats.setHeaderText(null);
			stats.setResizable(false);

			stats.setContentText("Current gridsize is: "+ Grid.gamegrid.length + " x " + Grid.gamegrid[0].length + "\n" + "Number of generations: ");
			//cellSpace.generations

						
//			stats.setContentText("Here will stats about the game be \n\n\n Ticks in the game: \n How many cell's are alive: \n And much more!");
			stats.initStyle(StageStyle.UNDECORATED);
			
			ButtonType buttonTypeCancel = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
			stats.getButtonTypes().setAll(buttonTypeCancel);
			
			stats.showAndWait();
		}	
	
	/**
	 * Method that shows a custom inputdialog window to the user.
	 * The dialog takes an x value and a y value.
	 * The values are returned to the calling method.
	 * 
	 * TODO: add validation of x and y input
	 *
	 * @author hd
	 * @return String
	 */
	public Optional<int[]> setGridSizeDialogue() {
		Optional<int[]>returnValue = null;
		
		Dialog<int[]> dialog = new Dialog<int[]>();
		dialog.setTitle("Enter size of grid");
		dialog.setResizable(false);
		Label CurrentGrid = new Label ("Current gridsize is: "+ Grid.gamegrid.length + " x " + Grid.gamegrid[0].length);
		Label lbl_x = new Label("X-value:");
		Label lbl_y = new Label("Y-value:");
		TextField txt_x = new TextField();
		TextField txt_y = new TextField();
		
		GridPane grid = new GridPane();
		
		grid.add(lbl_x, 1, 1);
		grid.add(lbl_y, 2, 1);
		grid.add(txt_x, 1, 2);
		grid.add(txt_y, 2, 2);
		grid.add(CurrentGrid, 1, 3);
		dialog.getDialogPane().setContent(grid);
//		Focuses the X-value when you enter the stage
		Platform.runLater(() -> txt_x.requestFocus());
		
		ButtonType buttonTypeOk = new ButtonType ("Ok", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType ("Cancel", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk, buttonTypeCancel);
		
		dialog.setResultConverter(new Callback<ButtonType, int[]>() {
			@Override	
			public int[] call(ButtonType ok) {
				
				String x_value = txt_x.getText();
				String y_value = txt_y.getText();
				
				//Validate that both x and y value is a number (parameter valuesOk set to true if they are
				boolean valuesOk = (StringUtils.isNumeric(x_value) && StringUtils.isNumeric(y_value));
							
				if (valuesOk) {
					if (ok==buttonTypeOk){
						//Return an array with the x and y values 
						return new int[]{Integer.parseInt(x_value), Integer.parseInt(y_value)};
					}
					else {
						//Return null, if cancel is pressed
						return new int[]{-1};
					}
				}
				else { 
					System.out.println("Feil input");
					return null;
				}
					
			}
	    });
		
		Optional<int[]> result = dialog.showAndWait();
		if ( result.isPresent()){
			return result;
		}
		
		return returnValue;
	}

//	protected static void SaveFileDialogueName()	{
//		FileChooser fileChooser1 = new FileChooser();
//		fileChooser1.setTitle("Save Image");
//		System.out.println(pic.getId());
//		File file = fileChooser1.showSaveDialog(stage);
//		System.out.println(file);
//	}
	
	protected static void SaveFileDialogueConfirmed(){
		Alert savefileconfirmed = new Alert(AlertType.NONE);
		savefileconfirmed.setHeaderText(null);
		savefileconfirmed.setContentText("Your file is now saved!"+"\n");
		savefileconfirmed.setResizable(false);
		
		savefileconfirmed.initStyle(StageStyle.UNDECORATED);
		
		ButtonType buttonTypeOkei = new ButtonType("Good!", ButtonData.CANCEL_CLOSE);
		savefileconfirmed.getButtonTypes().setAll(buttonTypeOkei);
		
		savefileconfirmed.showAndWait();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}
}
