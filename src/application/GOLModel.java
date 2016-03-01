package application;

import java.util.Optional;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class GOLModel {
	
	/**
	 * 
	 * @param gc
	 * @author hd
	 */
	public void draw(GraphicsContext gc){
		int x = 100;
		int y = 100;
		int size = 10;

		Shape shape = new Shape();
		double[][] array = shape.getShapeA();
		gc.setFill(Color.BLACK);
		
		for (int i = 0;i<array.length;i++){
			for (int j = 0; j<(array[i].length); j++){
				if (array[i][j]==1)	{
					gc.fillRect((x+size*j), (y+size*i), size, size);
				}
			}
		}
	}
	
	/**
	 * Method that show an input dialog box to the user.
	 * The dialog takes an x value and a y value.
	 * the values are returned to the calling method.
	 * 
	 * @return String
	 */
	public String setGridSizeDialogue()	{
		String returnValue=null;
		
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Enter size of grid");
		dialog.setResizable(true);
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
		
		ButtonType buttonTypeOk = new ButtonType ("Ok", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType ("Cancel", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk, buttonTypeCancel);
		
		dialog.setResultConverter(new Callback<ButtonType, String>() {
			@Override
			public String call(ButtonType b) {
				if (b==buttonTypeOk){
					return new String(txt_x.getText());
				}
				return null;
			}
	    });
		
		Optional<String> result = dialog.showAndWait();
		if ( result.isPresent()){
			returnValue = result.get();
		}
		
		return returnValue;
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
		
	}



	


