package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Class Grid.
 */
class Grid {
	
// The gamegrid array
	protected static int[][] gamegrid;
	
	/** The cell size. */
	private int cellSize ;
	
	/**
	 * Instantiates a new grid.
	 *
	 * @param int columns
	 * @param int rows
	 */
	public Grid(int columns, int rows){
		gamegrid = new int[columns][rows];	
	}
	
	/**
	 * Method defines the grid size based on received x and y values and pint the gamegrid array to a new array object.
	 *
	 * @author hd
	 * @param x
	 * @param y
	 */
	public void setGrid(int columns, int rows){
		gamegrid = new int[columns][rows];
	}
	
	/**
	 * Method returns number of rows in gamegrid.
	 *
	 * @author hd
	 * @return int rows
	 */
	public int getColumns(){
		return gamegrid.length;
	}
	
	/**
	 * Method returns number of columns in gamegrid.
	 *
	 * @author hd
	 * @return int columns
	 */
	public int getRows(){
		return gamegrid[0].length;
	}
	
	/**
	 * Method returns current gamegrid.
	 *
	 * @author hd
	 * @return int[][] gamegrid
	 */
	public int[][] getGrid(){
		return gamegrid;
	}
	
	/**
	 * The method returns the value of the private cellsize variable
	 * @author hd
	 * @return int cellsize
	 */
	public int getCellSize() {
		return cellSize;
	}
	
	
	/**
	 * Method counts and returns number of neighbours
	 * to a cell given by provided x and y param.
	 * Not in use
	 * TODO: not tested after moved to this class.
	 *
	 * @author hd
	 * @param x
	 * @param y
	 * @return number of neighbours
	 */
	public int countNeighbours(int x, int y)	{
	
		int neighbours = 0;
		int cellValue = 0;
		
		for (int  i=-1; i<2 ; i++)
			for (int j=-1;j<2;j++)	{
				cellValue = gamegrid[x+i][y+j];
				if (!(i==0 && j==0) && (cellValue==1))
					neighbours++;
			}
		return neighbours;
	}
	
	/**
	 * Method sets cell value based on received x and y parameter.
	 *
	 * @author hd
	 * @param int x
	 * @param int y
	 * @param int value
	 */
	public void setCellstatus(int x, int y, int value)	{
		gamegrid[x][y]=value;
	}
	
	/**
	 * Method gets cell value based on received x and y parameter.
	 * 
	 * @author hd
	 * @param int x
	 * @param int y
	 * @return int
	 */
	public int getCellstatus(int x, int y){
		return gamegrid[x][y];
	}
	
	/**
	 * Method implements two rules
	 * Note: method not in use
	 * 1) cell dies if number of neighbours is less than 2
	 * 2) cell dies if number of neighbours is greater than 3.
	 *
	 * @author hd
	 * @param int neighbours
	 * @return boolean
	 */
	public boolean survives(int neighbours){
		boolean alive=true;
		if (( neighbours < 2) || (neighbours> 3))
			alive = false;
		return alive;
	}
	
		
	/**
	 * A toString method to get a 1D representation of the gamegrid array.
	 * Useful when implemeting tests
	 * @author hd
	 * @return string
	 */
	@Override
	public String toString() {
		String o="";
		for (int i=0;i<gamegrid.length;i++)
			for (int j=0;j<gamegrid[i].length;j++)
				o+=gamegrid[i][j];
		return o;
	}
	
	/**
	 * Next generation method is used to calculate the next state of a cell according to the game rules.
	 *
	 * @param int[][] array2
	 * @param GraphicsContext gc
	 * @return the next generation
	 */
	public int [][] nextGeneration(int [][] array2, GraphicsContext gc){
		
		int [][] currentGen = array2;
		
		
		
			for (int x = 0; x < currentGen.length; x++){
	    	   for (int y = 0; y < currentGen[0].length; y++){
	    	   }
	     }
		   
		int [][] nextGen = new int [gamegrid.length][gamegrid.length];
		
		
		for ( int rows = 0; rows < gamegrid.length; rows++ ) {
            int over, under;  
            int left, right; 
            over = rows > 0 ? rows-1 : gamegrid.length-1;
            under = rows < gamegrid.length-1 ? rows+1 : 0;
            for ( int cols = 0; cols < gamegrid.length; cols++ ) {
                left =  cols > 0 ? cols-1 : gamegrid.length-1;
                right = cols < gamegrid.length-1 ? cols+1 : 0;
                int n = 0; 
                if (currentGen[over][left]==1)
                    n++;
                if (currentGen[over][cols]==1)
                    n++;
                if (currentGen[over][right]==1)
                    n++;
                if (currentGen[rows][left]==1)
                    n++;
                if (currentGen[rows][right]==1)
                    n++;
                if (currentGen[under][left]==1)
                    n++;
                if (currentGen[under][cols]==1)
                    n++;
                if (currentGen[under][right]==1)
                    n++;
                if (n == 3 || ((currentGen[rows][cols] ==1 )&& (n == 2)))
                    nextGen[rows][cols] = 1;
                else
                    nextGen[rows][cols] = 0;
            }
        }
        currentGen = nextGen;
        
       for (int x = 0; x < currentGen.length; x++){
    	   for (int y = 0; y < currentGen[0].length; y++){
    		   
   
    	   }}
       
       return nextGen;
       
      	}

	/**
	 * OneGen method is used to only calculate the next generation when next button is clicked
	 *
	 * @author
	 * @param GraphicsContext gc
	 * @param Canvas img
	 */
	public void oneGen(GraphicsContext gc, Canvas img){
		gamegrid = nextGeneration(gamegrid, gc);
		draw(gc,img);
	}
	
	/**
	 * Draw method is used to draw the gameboard.
	 *
	 * @param gc GraphicsContext
	 * @param Canvas canvas
	 * @return the int[][] array
	 */
	public int[][] draw(GraphicsContext gc, Canvas canvas){

		cellSize = (int) (canvas.getHeight()/gamegrid.length);

		int[][] array = gamegrid;
		gc.setFill(Color.GREY);
		gc.setStroke(Color.BLACK);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		for (int i = 0;i<array.length;i++){
			for (int j = 0; j<(array[i].length); j++){
				
				if (array[i][j]==1)	{
					gc.setFill(Color.WHITE);
					gc.fillRect(i*cellSize, j*cellSize, cellSize*0.9, cellSize*0.9);
				}
				
				gc.strokeRect(i*cellSize, j*cellSize, cellSize, cellSize);
			}
		}
	
		return array;
	}
	
	
	/**
	 * DrawOnGameGrid method is used to visually draw cells on the gamebaord.
	 *
	 * @param int x
	 * @param int y
	 * @param GraphicsContext gc
	 */
	public void DrawOnGameGrid (int x, int y, GraphicsContext gc){
		for (int i = 0;i<gamegrid.length;i++){
			for (int j = 0; j<(gamegrid[i].length); j++){
				if (x == i && y == j){
					
					gamegrid[i][j] = 1;
					gc.setFill(Color.WHITE);
					gc.fillRect(i*getCellSize(), j*getCellSize(), getCellSize()*0.9, getCellSize()*0.9);
				}
	
				}
			}
		
	}
}
