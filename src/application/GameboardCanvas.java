package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Constructed by creating a Grid object (grid) and keep this as a class variable
 * Provides methods to;
 * - parse patterns loaded from e.g. a file and update the grid.
 * - a validator that may be called to check that a pattern is within grid boundaries 
 * @author Hans Dragnes
 */
class GameboardCanvas {
	
//	Grid object, holding the grid array
	protected Grid grid;
		
	//Constructor, instantiates a new grid og Grid type
	protected GameboardCanvas(int columns, int rows){
		grid = new Grid(columns, rows);
	}
	
	/**
	 * The method takes a pattern in RLE format (e.g 2b2o$2o2b) as a String and interprets the characters 
	 * ('2' followed by a 'b' is 'bb', $ represents new line) and sets the gamegrid array accordingly.
	 * Note; 'b' indicates live cell, while 'o' indicates dead cell
	 * @author Hans Dragnes with help from Internet on the actual parsing
	 * @param The pattern to be parsed as a String
	 * @return void
	 */
	protected void parsePattern(String pattern){
		
//		Split the pattern (at each occorrance of $ which indicates new line) and store each element in an 
//		array 
    	String[] inputstring = pattern.split("\\$");
//    	Define a pattern (regex) to match against (look for digits and upper/lower case letters)
    	String re = "[0-9]+|[a-zA-Z]";
		Pattern p = Pattern.compile(re);
    	Matcher matcher;
		
  		int x_counter = 0;
		
		//Loop through inputstring array
		for (int i = 0; i < inputstring.length; i++){
			x_counter = 0;
//			Look for match with pattern
			matcher = p.matcher(inputstring[i]);
				while (matcher.find()){
//					If match is a number, it indicates the following value should be repeated that number of times 
//					(count down until zero)
					if (Character.isDigit(matcher.group().charAt(0))){
						int number = Integer.parseInt(matcher.group());
						matcher.find();
						while (number-- != 0){
							if (matcher.group().equals("b")){
//							Use Grid's setCellstatus method. "b" means dead cell i.e. value set to 0
								grid.setCellstatus( x_counter++,i, 0);
							}
//							Use Grid's setCellstatus method. "o" means dead cell, i.e. value set to 1
							if (matcher.group().equals("o")){
								grid.setCellstatus( x_counter++,i, 1);
							}
						}
					}
//					Same as above, but no leading digit, hence update cell according to value, only
					else {
						if (matcher.group().equals("b"))	{
							grid.setCellstatus(x_counter++,i, 0);
						}
						if (matcher.group().equals("o"))	{
							grid.setCellstatus(x_counter++,i, 1);
						}
					}
				}
		}
	}

		
	/**
	 * Method takes the shape size as integer, compares with the current gamegrid size
	 * and returns true if any exceeds.
	 * returns false otherwise.
	 * TODO: Consider creating a Model class to hold this method
	 * @author Hans Dragnes
	 * @param String[] headerelements
	 * 
	 * @return boolean
	 */
	protected boolean shapeWithinGamegridBoundaries(int shapeColumns, int shapeRows){
		
    	int gridRows = grid.getRows();
		int gridColumns = grid.getColumns();
		
//		Both rows and columns value of shape must be smaller than corresponding grid
		if ((shapeRows <= gridRows) && (shapeColumns <= gridColumns))
			return true;
		else 
			return false;
			
	}
	
	/**			
	 * Method counts and returns number of neighbours
	 * to a cell given by provided x and y coordinates.
	 * Note; Not in use
	 * @author Hans Dragnes
	 * @param int x
	 * @param int y
	 * @return number of neighbours
	 */
//		protected int countNeighbours(int x, int y)	{
//		
//			int neighbours = 0;
//			int cellValue = 0;
//			int[][] tempgrid = grid.getGrid();	
//			for (int  i=-1; i<2 ; i++)
//				for (int j=-1;j<2;j++)	{
//					cellValue = tempgrid()[x+i][y+j];
//					if (!(i==0 && j==0) && (cellValue==1))
//						neighbours++;
//				}
//			return neighbours;
//		}
					
		/**
		 * Method implements two rules
		 * 1) cell dies if number of neighbours is less than 2
		 * 2) cell dies if number of neighbours is greater than 3
		 * Note; not in use
		 * @param in neighbours
		 * @return boolean 
		 * @author hd
		 */
//		protected boolean survives(int neighbours)	{
//			boolean alive=true;
//			
//			if (( neighbours < 2) || (neighbours> 3))
//					alive = false;
//			return alive;
//		}
	
}