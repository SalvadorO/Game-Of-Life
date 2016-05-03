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
import javafx.scene.control.Slider;
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
		public void AboutDialogue() {
			Alert about = new Alert(AlertType.INFORMATION);
			about.setHeaderText(null);
			about.setContentText("John Conway's Game Of Life"+"\n\n"+"The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970"+"\n"+"The 'game' is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves or, for advanced players, by creating patterns with particular properties."+"\n\n"+"Rules"+"\n"+"1. Any live cell with fewer than two live neighbours dies, as if caused by under-population."+"\n"+"2. Any live cell with two or three live neighbours lives on to the next generation."+"\n"+"3. Any live cell with more than three live neighbours dies, as if by over-population."+"\n"+"4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.");
//			ContentText Source: "http://www.conwaylife.com/wiki/Conway's_Game_of_Life"
			about.initStyle(StageStyle.UNDECORATED);
			
			ButtonType buttonTypeCancel = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
			about.getButtonTypes().setAll(buttonTypeCancel);
			
			about.showAndWait();
		}
		
		/**
		 * Advanced dialogue, witch will allow the user to take more controll over the game, and its settings
		 * 
		 */
		protected static void SpeedDialogue() {
			
			Alert speedDialogue = new Alert(null);
			speedDialogue.setTitle("Select Speed");
			speedDialogue.setResizable(false);
			speedDialogue.setHeaderText(null);
			
			Slider SpeedSlider = new Slider();
			Label test = new Label("Test");
			
			GridPane grid = new GridPane();
			grid.add(SpeedSlider, 2, 1);
			grid.add(test, 1, 1);
			
			speedDialogue.getDialogPane().setContent(grid);
//			Label lbl_ChangeSpeed = new Label("Change Speed");
//			Label lbl_GenperSec = new Label("Generations/Sec");
//			Label lbl_CurrentSpeed = new Label ("Current speed is set to: ");
//			TextField InputNewSpeed = new TextField();
//			
//			//Build the user interface
//			GridPane grid = new GridPane();
//			grid.add(lbl_ChangeSpeed, 1, 1);
//			grid.add(InputNewSpeed, 1, 2);
//			grid.add(lbl_GenperSec, 2, 2);
//			grid.add(lbl_CurrentSpeed, 1, 3);
//			speedDialogue.getDialogPane().setContent(grid);
			ButtonType buttonTypeCancel = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
			speedDialogue.getButtonTypes().setAll(buttonTypeCancel);
			
			speedDialogue.showAndWait();
		}
		
		protected static void CellColorDialogue() {
			
			TextInputDialog cellcolordialogue = new TextInputDialog("Change Cell Color");
			cellcolordialogue.setTitle("Change Cell Color");
			
//			Label Speed_lbl = new Label("Speed Value");
//			TextField txt_x = new TextField();
			
			
			cellcolordialogue.showAndWait();
		}
		
		protected static void GridColorDialogue() {
			
			TextInputDialog gridcolordialogue = new TextInputDialog("Change Grid Color");
			gridcolordialogue.setTitle("Change Grid Color");
			
//			Label Speed_lbl = new Label("Speed Value");
//			TextField txt_x = new TextField();
			
			gridcolordialogue.showAndWait();
		}
		
		/**
		 * Stats dialogue.
		 */
		public void StatsDialogue()	{
			Alert stats = new Alert(AlertType.INFORMATION);
			stats.setTitle("Stats");
			stats.setHeaderText(null);
			stats.setResizable(false);
			stats.initStyle(StageStyle.UNDECORATED);
			
//			If the game hasent started yet the stats dialog will add a sentence in the dialog
			if(GameController.CountGen == 0){
				stats.setContentText("Current gridsize is: "+ Grid.gamegrid.length + " x " + Grid.gamegrid[0].length + "\n" + 
			"The game hasn't started yet!");
			}	else	{
				stats.setContentText("Current gridsize is: "+ Grid.gamegrid.length + " x " + Grid.gamegrid[0].length + "\n" + 
						"Number of generations played: " + GameController.CountGen + "\n" + "Speed: " + (Math.round(GameController.timeline.getRate()*100.00)/100.00) + " Generations/Sec");
			};
			  
			ButtonType buttonTypeCancel = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
			stats.getButtonTypes().setAll(buttonTypeCancel);
			
			stats.showAndWait();
		}	
	
	/**
	 * Method that shows a custom inputdialog window to the user.
	 * The dialog takes an x value and a y value.
	 * The values are returned to the calling method.
	 * 
	 * The input values are validated, and only positive integers are accepted
	 *
	 * @author hd
	 * @return An int[] containing the x and the y value, and exit codes if relevant where -1 means cancel, and -2 means validation failed
	 */
	public Optional<int[]> setGridSizeDialogue() {
				
		Dialog<int[]> dialog = new Dialog<int[]>();
		dialog.setTitle("Enter size of grid");
		dialog.setResizable(false);
		Label lbl_x = new Label("X-value:");
		Label lbl_y = new Label("Y-value:");
		TextField txt_x = new TextField();
		TextField txt_y = new TextField();
		
		//Build the user interface
		GridPane grid = new GridPane();
		grid.add(lbl_x, 1, 1);
		grid.add(lbl_y, 2, 1);
		grid.add(txt_x, 1, 2);
		grid.add(txt_y, 2, 2);
		dialog.getDialogPane().setContent(grid);
//		Focuses the X-value when entering dialog
		Platform.runLater(() -> txt_x.requestFocus());
		
		//Instantiates two objects of type ButtonType and assign them to variables, add the variables to the 
		//dialog object 'dialog'
		ButtonType buttonTypeOk = new ButtonType ("Ok", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType ("Cancel", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk, buttonTypeCancel);
		
//		Get the output from the dialog, by implementing the callback interface
		dialog.setResultConverter(
				new Callback<ButtonType, int[]>() {
					@Override	
					public int[] call(ButtonType ok) {

						
						if (ok==buttonTypeOk){
							String x_value = txt_x.getText();
							String y_value = txt_y.getText();
							//Validate that both x and y value is a number and positive (variable valuesOk set to true if they are)
							boolean valuesNumericAndPositive = 
									( (StringUtils.isNumeric(x_value)) && (Integer.parseInt(x_value) > 0) ) && 
									( (StringUtils.isNumeric(y_value)) && (Integer.parseInt(y_value) > 0) );
							//Validate that x equals y, i.e the grid is supposed to be a square
							boolean valuesOk = 
									( valuesNumericAndPositive && ( (Integer.parseInt(x_value)) == (Integer.parseInt(y_value)) ) ) ; 
	
							//For testing
							System.out.println("Verdier ok: " + valuesOk);
							//End for testing
															
							//Return an array with the x and y values if validation is ok 
							if (valuesOk){
								return new int[]{Integer.parseInt(x_value), Integer.parseInt(y_value)};
							}
							else {
								//-2 as return value indicates validations failed
								return new int[]{-2};
							}
						}
					
						else {
							//-1 as return value indicates Cancel was pressed
							return new int[]{-1};
					}
				}
						
			    });
				
		Optional<int[]> result = dialog.showAndWait();
		
		if ( result.isPresent() ) {
			return result;
		}
		
		else {
			return null;
		}
		
		

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}
}
