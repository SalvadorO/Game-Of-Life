package application;

import java.util.Optional;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;

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
			
	public void setGridSizeDialogue()	{
		TextInputDialog textInput = new TextInputDialog("Enter grid size");
		textInput.setTitle("Enter some text");
	    textInput.setHeaderText("Text Input");
	    textInput.setContentText("Please enter something:");
	    Optional<String> result = textInput.showAndWait();
	    if (result.isPresent())	{
	         Alert alert = new Alert(AlertType.INFORMATION);
	         alert.setContentText("You entered: " + result.get());
	         alert.showAndWait();
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
		
	}



	


