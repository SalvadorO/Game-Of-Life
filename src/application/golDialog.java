package application;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * The Class golDialog.
 */
class golDialog extends Application	{

		/**
		 * Method that show a Alertbox to the user that explains how the game work.
		 * 
		 * @author Lars 
		 */
		protected void AboutDialogue() {
			Alert about = new Alert(AlertType.INFORMATION);
			about.setHeaderText(null);
			about.setContentText("John Conway's Game Of Life"+"\n\n"+"The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970"+"\n"+"The 'game' is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves or, for advanced players, by creating patterns with particular properties."+"\n\n"+"Rules"+"\n"+"1. Any live cell with fewer than two live neighbours dies, as if caused by under-population."+"\n"+"2. Any live cell with two or three live neighbours lives on to the next generation."+"\n"+"3. Any live cell with more than three live neighbours dies, as if by over-population."+"\n"+"4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.");
			//ContentText Source: "http://www.conwaylife.com/wiki/Conway's_Game_of_Life"
			about.initStyle(StageStyle.UNDECORATED);
			
			ButtonType buttonTypeCancel = new ButtonType("Thanks!", ButtonData.CANCEL_CLOSE);
			about.getButtonTypes().setAll(buttonTypeCancel);
			
			about.showAndWait();
		}
		
		/**
		 * Will allow the user to change the color of the cells
		 * TODO: To be implemented
		 * Not finished
		 * 
		 * @author Lars 
		 */
//		protected static void CellColorDialogue() {
//			
//			List<String> Color = new ArrayList<>();
//			Color.add("Blue");
//			Color.add("Red");
//			Color.add("Green");
//			Color.add("White");
//			
//			ChoiceDialog<String> ColorDialog = new ChoiceDialog<>("",Color);
//			ColorDialog.setTitle("Select the color of your choice");
//			ColorDialog.setHeaderText(null);
//			ColorDialog.setContentText("Choose your color carefully:");
//			
//			Optional<String> newColor = ColorDialog.showAndWait();
//			if(newColor.isPresent()){
//				Grid.cellcolor.equals(newColor);
//				newColor.equals(Grid.cellcolor);
//				Grid.cellcolor. = newColor.get();
//				
//				System.out.println(Grid.cellcolor.equals(newColor));
//				System.out.println("newColor: " + newColor.get());
//				System.out.println("cellcolor: " + Grid.cellcolor);
//			}
//		}		
		
				
		/**
		 * TODO: To be implemented
		 * Will change the grid's color on user request.
		 * Not finished
		 * 
		 * @author Lars 
		 */
//		protected void GridColorDialogue() {
//			
//			TextInputDialog gridcolordialogue = new TextInputDialog("Change Grid Color");
//			gridcolordialogue.setTitle("Change Grid Color");
//			
//			gridcolordialogue.showAndWait();
//		}
		
		/**
		 * This will show a stats dialogue which contains some information about the game
		 * 
		 * @author Lars , hd contributed by rewriting calls to avoid using static references
		 */
		protected void StatsDialogue(Grid grid)	{
			Alert stats = new Alert(AlertType.INFORMATION);
			stats.setTitle("Stats");
			stats.setHeaderText(null);
			stats.initStyle(StageStyle.UNDECORATED);
			
//			If the game hasen't started yet the stats dialog will add a sentence in the dialog
			if(grid.getCountgen() == 0){
				stats.setContentText("Current gridsize is: "+ grid.getColumns() + " x " + grid.getRows() + "\n" + 
			"The game hasn't started yet!" + "\n" + "The game will start with " + (Math.round(grid.getTimeline().getRate()*100.00) / 100.00) + " Generations/ Sec");
			}	else	{
				stats.setContentText("Current gridsize is: "+ grid.getColumns() + " x " + grid.getRows() + "\n" + 
						"Number of generations played: " + grid.getCountgen() + "\n" + "Speed: " + (Math.round(grid.getTimeline().getRate()*100.00) / 100.00) + " Generations/ Sec");
			};
			  
			ButtonType buttonTypeCancel = new ButtonType("Cool!", ButtonData.CANCEL_CLOSE);
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
	 * @author Hans Dragnes, Lars
	 * @return An int[] containing the x and the y value, and exit codes if relevant where -1 means cancel, and -2 means validation failed
	 */
	protected Optional<int[]> setGridSizeDialogue() {
				
		Dialog<int[]> dialog = new Dialog<int[]>();
		dialog.setTitle("Enter size of grid");
		dialog.setResizable(false);
		Label lbl_x = new Label("X-value:");
		Label lbl_y = new Label("Y-value:");
		TextField txt_x = new TextField();
		TextField txt_y = new TextField();
		
//		Build the user interface and placing the TextFields and labels
		GridPane grid = new GridPane();
		grid.add(lbl_x, 1, 1);
		grid.add(lbl_y, 2, 1);
		grid.add(txt_x, 1, 2);
		grid.add(txt_y, 2, 2);
		dialog.getDialogPane().setContent(grid);
//		Set focus on the X-value when entering dialog
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
//		Show the dialog, and wait for user to interact		
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
