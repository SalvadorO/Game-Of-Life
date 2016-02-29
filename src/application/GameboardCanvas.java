package application;

/**
 * Constructed by creating a Grid object
 * Provides the possibility to set and get the grid, 
 * 
 * @author hd
 *
 */
public class GameboardCanvas {
	Grid grid;
	
	public GameboardCanvas(){
		grid = new Grid(4,4);
		
	}
	
	public int[][] getGrid() {
		return grid.getGrid();
	}
	
	public String getGridAsString(){
		return grid.toString();
	}
	
}
	

