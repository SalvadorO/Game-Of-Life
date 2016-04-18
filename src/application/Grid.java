package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Grid {
	
	
	
	
	public static int[][] gamegrid;
	static int cellSize = 10;
	
	
	public static int testCellSize ;
	public static int testCellSize2 ;

	
	
	
	public Grid(int x, int y){
		gamegrid = new int[x][y];
		
		
	}
	
	/**
	 * Method defines the grid size based on received x and y values
	 * @param x
	 * @param y
	 * @author hd
	 */
	public void setGrid(int x, int y){
		gamegrid = new int[x][y];
	}
	
	/**
	 * Method returns current gamegrid
	 * @return int[][] gamegrid
	 * @auth hd
	 */
	public int[][] getGrid(){
		return gamegrid;
	}
	
	/**
	 * Method counts and returns number of neighbours
	 * to a cell given by provided x and y param
	 * TODO: not tested after moved here
	 * @param int x, int y
	 * @return number of neighbours
	 * @author hd
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
	 * Method sets cell value based on received x and y parameter
	 * @param x
	 * @param y
	 * @param value
	 * @author hd
	 */
	public void setCellstatus(int x, int y, int value)	{
		gamegrid[x][y]=value;
	}
	
	public int getCellstatus(int x, int y){
		return gamegrid[x][y];
	}
	
	/**
	 * Method implements two rules
	 * 1) cell dies if number of neighbours is less than 2
	 * 2) cell dies if number of neighbours is greater than 3
	 * @param neighBours
	 * @return boolean
	 * @author hd
	 */
	public boolean survives(int neighbours){
		boolean alive=true;
		if (( neighbours < 2) || (neighbours> 3))
			alive = false;
		return alive;
	}
	
		
	/**
	 * A toString method to get a 1D representation of the table.
	 * @author hd
	 */
	@Override
	public String toString() {
		String o="";
		for (int i=0;i<gamegrid.length;i++)
			for (int j=0;j<gamegrid[i].length;j++)
				o+=gamegrid[i][j];
		return o;
	}
	
	public int [][] nextGeneration(int [][] array2, GraphicsContext gc){
		
		int [][] currentGen = array2;
		int size = cellSize;
		
		
			for (int x = 0; x < currentGen.length; x++){
	    	   for (int y = 0; y < currentGen[0].length; y++){
	    		   
	    		
	    	   }
	    	 
	       }
		
	    	 
	       
		
	    	   
	   
	   
	    	   
		int [][] nextGen = new int [gamegrid.length][gamegrid.length];
		
		//  Loop for counting neighbors
		for ( int r = 0; r < gamegrid.length; r++ ) {
            int above, below; // rows considered above and below row number r 
            int left, right;  // columns considered left and right of column c
            above = r > 0 ? r-1 : gamegrid.length-1;
            below = r < gamegrid.length-1 ? r+1 : 0;
            for ( int c = 0; c < gamegrid.length; c++ ) {
                left =  c > 0 ? c-1 : gamegrid.length-1;
                right = c < gamegrid.length-1 ? c+1 : 0;
                int n = 0; // number of alive cells in the 8 neighboring cells
                if (currentGen[above][left]==1)
                    n++;
                if (currentGen[above][c]==1)
                    n++;
                if (currentGen[above][right]==1)
                    n++;
                if (currentGen[r][left]==1)
                    n++;
                if (currentGen[r][right]==1)
                    n++;
                if (currentGen[below][left]==1)
                    n++;
                if (currentGen[below][c]==1)
                    n++;
                if (currentGen[below][right]==1)
                    n++;
                if (n == 3 || ((currentGen[r][c] ==1 )&& (n == 2)))
                    nextGen[r][c] = 1;
                else
                    nextGen[r][c] = 0;
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
	 * 
	 * @param gc
	 * @author hd
	 * @return 
	 */
	
	
	


	
	
	
	public void oneGen(GraphicsContext gc, Canvas img){
		
		
			
		gamegrid = nextGeneration(gamegrid, gc);
		draw(gc,img);
		
	}
	
	public static int[][] draw(GraphicsContext gc, Canvas canvas){
		
	
		testCellSize = (int) (canvas.getHeight()/gamegrid[0].length);
		
	

		

		int[][] array = gamegrid;
		gc.setFill(Color.GREY);
		gc.setStroke(Color.BLACK);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		for (int i = 0;i<array.length;i++){
			for (int j = 0; j<(array[i].length); j++){
				
				
	
				if (array[i][j]==1)	{
					gc.setFill(Color.WHITE);
					gc.fillRect(i*testCellSize, j*testCellSize, testCellSize, testCellSize);
				}
				
				gc.strokeRect(i*testCellSize, j*testCellSize, testCellSize, testCellSize);
			}
		}
	
		return array;
	}
	
	
	public static void updateGameGrid (int x, int y, GraphicsContext gc){
		for (int i = 0;i<gamegrid.length;i++){
			for (int j = 0; j<(gamegrid[i].length); j++){
				if (x == i && y == j){
					
					gamegrid[i][j] = 1;
					gc.setFill(Color.WHITE);
					gc.fillRect(i*testCellSize, j*testCellSize, testCellSize, testCellSize);
				}
	
				}
			}
		
	}
}
