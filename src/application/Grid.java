package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class Grid.
 */
class Grid {
	
	/** The gamegrid. */
	protected static int[][] gamegrid;
	
	/** The cell size. */
	protected static int cellSize ;
	
	/**
	 * Instantiates a new grid.
	 *
	 * @param x the x
	 * @param y the y
	 */
	protected Grid(int x, int y){
		gamegrid = new int[x][y];	
	}
	
	/**
	 * Method defines the grid size based on received x and y values.
	 *
	 * @author hd
	 * @param x the x
	 * @param y the y
	 */
	protected void setGrid(int x, int y){
		gamegrid = new int[x][y];
	}
	
	/**
	 * Method returns number of rows in gamegrid.
	 *
	 * @author hd
	 * @return int rows
	 */
	protected int getRows(){
		return gamegrid.length;
	}
	
	/**
	 * Method returns number of columns in gamegrid.
	 *
	 * @author hd
	 * @return int columns
	 */
	protected int getColumns(){
		return gamegrid[0].length;
	}
	
	/**
	 * Method returns current gamegrid.
	 *
	 * @return int[][] gamegrid
	 * @auth hd
	 */
	protected int[][] getGrid(){
		return gamegrid;
	}
	
	/**
	 * Method counts and returns number of neighbours
	 * to a cell given by provided x and y param
	 * TODO: not tested after moved here.
	 *
	 * @author hd
	 * @param x the x
	 * @param y the y
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
	
	/**
	 * Method sets cell value based on received x and y parameter.
	 *
	 * @author hd
	 * @param x the x
	 * @param y the y
	 * @param value the value
	 */
	public void setCellstatus(int x, int y, int value)	{
		gamegrid[x][y]=value;
	}
	
	/**
	 * Method gets cell value based on received x and y parameter.
	 *
	 * @param x the x
	 * @param y the y
	 * @return int value
	 */
	public int getCellstatus(int x, int y){
		return gamegrid[x][y];
	}
	
	/**
	 * Method implements two rules
	 * 1) cell dies if number of neighbours is less than 2
	 * 2) cell dies if number of neighbours is greater than 3.
	 *
	 * @author hd
	 * @param neighbours the neighbours
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
	 *
	 * @author hd
	 * @return the string
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
	 * Next generation.
	 *
	 * @param array2 the array2
	 * @param gc the gc
	 * @return the int[][]
	 */
	public int [][] nextGeneration(int [][] array2, GraphicsContext gc){
		
		int [][] currentGen = array2;
		int size = cellSize;
		
		
			for (int x = 0; x < currentGen.length; x++){
	    	   for (int y = 0; y < currentGen[0].length; y++){
	    		   
	    		
	    	   }
	    	 
	       }
		
	    	 
	       
		
	    	   
	   
	   
	    	   
		int [][] nextGen = new int [gamegrid.length][gamegrid.length];
		
		// This loop is for counting neighbors
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
    		   
    		   int[][] array3 = nextGen;
      			gc.setFill(Color.WHITE);
      			
      			for (int i = 0;i<array3.length;i++){
       				for (int j = 0; j<(array3[i].length); j++){
       					if (array3[i][j]==1)	{
       						
    		   
    
    	
    		   
    		   gc.fillRect((x+size*j), (y+size*i), size/currentGen.length, size/currentGen.length);
    	   }
       					
       }
    }
    	   }}
       
       return nextGen;
       
      	}

	/**
	 * One gen.
	 *
	 * @author hd
	 * @param gc the gc
	 * @param img the img
	 */
	
	
	


	
	
	
	public void oneGen(GraphicsContext gc, Canvas img){
		
		
			
		gamegrid = nextGeneration(gamegrid, gc);
		draw(gc,img);
		
	}
	
	/**
	 * Draw.
	 *
	 * @param gc the gc
	 * @param canvas the canvas
	 * @return the int[][]
	 */
	public static int[][] draw(GraphicsContext gc, Canvas canvas){
		
	


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
	 * Update game grid.
	 *
	 * @param x the x
	 * @param y the y
	 * @param gc the gc
	 */
	public static void updateGameGrid (int x, int y, GraphicsContext gc){
		for (int i = 0;i<gamegrid.length;i++){
			for (int j = 0; j<(gamegrid[i].length); j++){
				if (x == i && y == j){
					
					gamegrid[i][j] = 1;
					gc.setFill(Color.WHITE);
					gc.fillRect(i*cellSize, j*cellSize, cellSize*0.9, cellSize *0.9);
				}
	
				}
			}
		
	}
}
