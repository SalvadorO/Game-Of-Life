package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
	
	//Moved from Controller
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
		double[][] array = shape.getShapeGlider();
		gc.setFill(Color.BLACK);
		
		for (int i = 0;i<array.length;i++){
			for (int j = 0; j<(array[i].length); j++){
				if (array[i][j]==1)	{
					gc.fillRect((x+size*j), (y+size*i), size, size);
				}
			}
		}
	}

	//Deleted the setters and getter, put in Grid class
		
}