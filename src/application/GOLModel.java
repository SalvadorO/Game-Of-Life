package application;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;


public class GOLModel {
	
		
	/**
	 * Method that show a custom inputdialog box to the user.
	 * The dialog takes an x value and a y value.
	 * The values are returned to the calling method.
	 * TODO: consider if method should reside in another class, e.g. GameBoardCanvas
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

	/**
	 * The method takes a pattern in RLE format (e.g 2b2o$2o2b) as a String and interprets the characters 
	 * ('2' followed by a 'b' is 'bb', $ represents new line) and returns the pattern as a complete shape as a String
	 * @param patterntobeparsed
	 * @return parsedpattern
	 * @author hd
	 */

	public String patternParser(String patterntobeparsed){
    	//hardcoded testpattern
		String input = "2b2o2b$2o2b2o$2b2o2b";
    	String parsedpattern = "";
    	
    	String[] is = input.split("\\$");
    	
    	String re = "[0-9]+|[a-zA-Z]";
		Pattern pattern = Pattern.compile(re);
    	Matcher matcher;
		
		for (int i = 0; i < is.length; i++){
			matcher = pattern.matcher(is[i]);
				while (matcher.find()){
					int number = Integer.parseInt(matcher.group());
					matcher.find();
					while (number-- != 0){
						parsedpattern += (matcher.group());
					}
				}
		}
		return parsedpattern;
	}
}

		/** TODO: To be implemented
//	public void nextGeneration(gameboard board) {
			
		/*TODO: Decide if the method should be in the GBCanvas class
		/**
		 * Method counts and returns number of neighbours
		 * to a cell given by provided x and y param
		 * @param int x, int y
		 * @return number of neighbours
		 * @author hd
		 */
//		protected int countNeighbours(int x, int y)	{
//		
//			int neighbours = 0;
//			int cellValue = 0;
//			
//			for (int  i=-1; i<2 ; i++)
//				for (int j=-1;j<2;j++)	{
//					cellValue = gb.getTable()[x+i][y+j];
//					if (!(i==0 && j==0) && (cellValue==1))
//						neighbours++;
//				}
//			return neighbours;
//		}
//			
//			//TODO: add JDOC, Decide if the method should be in the GBCanvas class
//			 protected int cellstatusNextgeneration(int x, int y)	{
//				 int cn = countNeighbours(x,y);
//				 
//				 if (gb.getTable()[x][y]==1){
//					if (survives(cn))
//						return 1;
//					gb.updateCellstatus(x, y, 0);
//					return 0;
//				 }
//				 if (cn==3){
//					 gb.updateCellstatus(x, y, 1);
//					 return 1;
//				 }
//				 return 0;
//			 }	 
//		 
			
			
		/**
		 *TODO: add JDOC, Decide if the method should be in the GBCanvas class

		 * Method implements two rules
		 * 1) cell dies if number of neighbours is less than 2
		 * 2) cell dies if number of neighbours is greater than 3
		 * @param neighBours
		 * @return boolean
		 * @author hd
		 */
//		public boolean survives(int neighbours)	{
//			boolean alive=true;
//			
//			if (( neighbours < 2) || (neighbours> 3))
//					alive = false;
//			return alive;
//		}
		

		/**
		 *TODO: add JDOC, Decide if the method should be in the GBCanvas class

		 * Method checks if current cell is dead or alive
		 * @param x coordinate
		 * @param y coordinate
		 * @return boolean
		 * @author hd
		 */
//		public boolean isAlive(int x, int y)	{
//			if (gb.getTable()[x][y]==1)
//				return true;
//			return false;
//		}
//		}