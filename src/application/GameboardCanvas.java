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
		grid = new Grid(10,10);
		
	}
	
	public int[][] getGrid() {
		return grid.getGrid();
	}
	
	/**
	 * Method gets gridsize as parameters, then calls the class' gridobject' setGrid method.
	 * @param x
	 * @param y
	 */
	public void setGridSize(int x, int y){
		grid.setGrid(x, y);
		
	}
	
	public String getGridAsString(){
		return grid.toString();
	}
	
}