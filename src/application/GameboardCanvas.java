package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;

/**
 * Constructed by creating a Grid object
 * Provides methods to set and get the grid
 * Provides methods to set and get a specific cell
 * TODO: consider adding a register holding living cells
 * @author hd
 *
 */
public class GameboardCanvas {
	Grid grid;
	
	public GameboardCanvas(){

		grid = new Grid(50,50);
		
		

	}
	
	/**
	 * The method takes a pattern in RLE format (e.g 2b2o$2o2b) as a String and interprets the characters 
	 * ('2' followed by a 'b' is 'bb', $ represents new line) and sets the gamegrid array.
	 * @param patterntobeparsed
	 * @return void
	 * @author hd
	 */

	public void parsePattern(String pattern){
		
    	String[] inputstring = pattern.split("\\$");
    	String re = "[0-9]+|[a-zA-Z]";
		Pattern p = Pattern.compile(re);
    	Matcher matcher;
		
  		int x_counter = 0;
		
		//loop antall ganger som det er linjer i is
		for (int i = 0; i < inputstring.length; i++){
			x_counter = 0;
			matcher = p.matcher(inputstring[i]);
				while (matcher.find()){
					if (Character.isDigit(matcher.group().charAt(0))){
						int number = Integer.parseInt(matcher.group());
						matcher.find();
						while (number-- != 0){

							//For testing
							if (matcher.group().equals("b")){
								grid.setCellstatus( x_counter++,i, 0);
							}
							//End for testing

							if (matcher.group().equals("o")){
								grid.setCellstatus( x_counter++,i, 1);
							}
						}
					}
					else {
						//For testing
						if (matcher.group().equals("b"))	{
							grid.setCellstatus(x_counter++,i, 0);
						}
						//End for testing
						if (matcher.group().equals("o"))	{
							grid.setCellstatus(x_counter++,i, 1);
						}
					}
				}
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
//			//TODO: add JDOC
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
		public boolean survives(int neighbours)	{
			boolean alive=true;
			
			if (( neighbours < 2) || (neighbours> 3))
					alive = false;
			return alive;
		}
		
		
		/**
		 * Method takes the size (x and y values) of and returns true if any exceeds size of gamegrid,
		 * returns false otherwise
		 * @param shapeColumns
		 * @param shapeRows
		 * @return boolean
		 * @author hd
		 */
		public boolean shapeBiggerThanGameboard(int shapeColumns, int shapeRows){
			
			int rows = grid.getRows();
			int columns = grid.getColumns();
			
			if ((shapeRows > rows) || (shapeColumns > columns))
				return true;
			else 
				return false;
				
		}

}