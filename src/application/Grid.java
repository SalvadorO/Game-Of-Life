package application;

import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

/**
 * The Grid class.
 * Hold the gamegrid and offers various methods to operate on it.
 */
class Grid {
	
	// Declarations
	private int[][] gamegrid;
	
	private int cellSize ;
	
//	Variable holding the number of generations played
	private int countgen;
	
	public static Color cellcolor = Color.WHITE;
	private Color gridcolor = Color.GREY;
		
	private Timeline timeline = new Timeline();
	
	/**
	 * Constructor; Instantiates a new grid.
	 * Resets the generationcounter variable
	 * @author hd
	 * @param int columns
	 * @param int rows
	 */
	protected Grid(int columns, int rows){
		gamegrid = new int[columns][rows];	
		countgen = 0;
	}
	
	 /**
     * 
     * @return
     * @author Lars 
     */
    protected Timeline getTimeline() {
		return timeline;
	}
    
    /**
     * 
     * @param timeline
     * @author Lars 
     */
	protected void setTimeline(Timeline tl) {
		timeline = tl;
	}
	
	/**
	 * Method defines the grid size based on received x and y values and pint the gamegrid array to a new array object.
	 *
	 * @author hd
	 * @param x
	 * @param y
	 */
	protected void setGrid(int columns, int rows){
		gamegrid = new int[columns][rows];
	}
	
	protected void incrementCountgen() {
		countgen++;
	}
	
	protected int getCountgen() {
		return countgen;
	}
	
	protected void resetCountgen() {
		countgen = 0;
	}
	
	
	/**
	 * Method returns number of rows in gamegrid.
	 *
	 * @author hd
	 * @return int rows
	 */
	protected int getColumns(){
		return gamegrid.length;
	}
	
	/**
	 * Method returns number of columns in gamegrid.
	 *
	 * @author hd
	 * @return int columns
	 */
	protected int getRows(){
		return gamegrid[0].length;
	}
	
	/**
	 * Method returns current gamegrid.
	 *
	 * @author hd
	 * @return int[][] gamegrid
	 */
	protected int[][] getGrid(){
		return gamegrid;
	}
	
	/**
	 * The method returns the value of the private cellsize variable.
	 * 
	 * @author hd
	 * @return int cellsize
	 */
	protected int getCellSize() {
		return cellSize;
	}
	
	
	/**
	 * Method counts and returns number of neighbours
	 * to a cell given by provided x and y param.
	 * Not in use
	 * 
	 * TODO: er den ikke i bruk kan vi ikke like s�godt fjerne den?
	 * 
	 * @author hd
	 * @param x
	 * @param y
	 * @return number of neighbours
	 */
	protected int countNeighbours(int x, int y)	{
	
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
	
	
	protected boolean onGrid(int x, int y) {
		if ((x+1) <= getColumns() && (y+1) <= getRows())
			return true;
		else
			return false;
	}
	
	/**
	 * Method sets cell value based on received x and y parameter.
	 *
	 * @author Hans Dragnes
	 * @param int x
	 * @param int y
	 * @param int value (1 means live, 0 means dead)
	 */
	protected void setCellstatus(int x, int y, int value)	{
		if (onGrid(x, y))
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
	protected int getCellstatus(int x, int y) {
		if (onGrid(x, y))
			return gamegrid[x][y];
		else
			return -1;
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
	protected boolean survives(int neighbours){
		boolean alive=true;
		if (( neighbours < 2) || (neighbours> 3))
			alive = false;
		return alive;
	}
	
	/**
	 * A toString method to get a 1D representation of the gamegrid array.
	 * Useful when implemeting tests.
	 * 
	 * @author hd
	 * @return string
	 */
	@Override
	public String toString() {
		String o="";
		for (int i=0;i<gamegrid.length;i++)
			for (int j=0;j<gamegrid[i].length;j++)
				o+=gamegrid[j][i];
		return o;
	}
	
	/**
	 * Next generation method is used to calculate the next state of a cell according to the game rules.
	 *
	 * @param int[][] array2
	 * @param GraphicsContext gc
	 * @return the next generation
	 * @author Salvador
	 */
	protected int [][] nextGeneration(int [][] array2, GraphicsContext gc){
		
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
       incrementCountgen();
       return nextGen;
      	}

	/**
	 * OneGen method is used to only calculate the next generation when next button is clicked.
	 *
	 * @param GraphicsContext gc
	 * @param Canvas img
	 * @author Salvador
	 */
	protected void oneGen(GraphicsContext gc, Canvas img){
		gamegrid = nextGeneration(gamegrid, gc);
		draw(gc,img);
	}
	
	/**
	 * Draw method is used to draw the gameboard.
	 *
	 * @param gc GraphicsContext
	 * @param Canvas canvas
	 * @author Salvador
	 * @return the int[][] array
	 */
	protected int[][] draw(GraphicsContext gc, Canvas canvas){

		cellSize = (int) (canvas.getHeight()/gamegrid.length);

		int[][] array = gamegrid;
		gc.setFill(Color.GREY);
		gc.setStroke(Color.BLACK);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		for (int i = 0;i<array.length;i++){
			for (int j = 0; j<(array[i].length); j++){
				
				if (array[i][j]==1)	{
					gc.setFill(cellcolor);
					gc.fillRect(i*cellSize, j*cellSize, cellSize, cellSize);
				}
				
				gc.strokeRect(i*cellSize, j*cellSize, cellSize, cellSize);
			}
		}
	
		return array;
}
	
	/**

	 * MarkCell & MarkCell2 methods is used to change the value of cells that are being marked by the mouse, 
	 * it also visualizes where we are drawing cells by changing the color of the cells 

	 *
	 * @param int x
	 * @param int y
	 * @author Salvador, Hans
	 * @param GraphicsContext gc
	 */
	protected void markCell (int x, int y, GraphicsContext gc){
	
		if (getCellstatus(x, y)==0) {
			setCellstatus(x, y, 1);
			gc.setFill(cellcolor);
			gc.fillRect(x*getCellSize(), y*getCellSize(), getCellSize(), getCellSize());
			gc.strokeRect(x*cellSize, y*cellSize, cellSize, cellSize);
		}
		else if (getCellstatus(x, y)==1){
			
			setCellstatus(x, y, 0);
			gc.setFill(gridcolor);
			gc.fillRect(x*getCellSize(), y*getCellSize(), getCellSize(), getCellSize());
			gc.strokeRect(x*cellSize, y*cellSize, cellSize, cellSize);
		}
		

	}
	protected void markCell2 (int x, int y, GraphicsContext gc){
		
			if (onGrid(x, y)) {
				setCellstatus(x, y, 1);
				gc.setFill(cellcolor);
				gc.fillRect(x*getCellSize(), y*getCellSize(), getCellSize(), getCellSize());
				gc.strokeRect(x*cellSize, y*cellSize, cellSize, cellSize);
			}
	}
}