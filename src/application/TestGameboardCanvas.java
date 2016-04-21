package application;

import static org.junit.Assert.*;

import org.junit.Test;

import application.GameboardCanvas;
import application.Grid;

// TODO: Auto-generated Javadoc
/**
 * The Class TestGameboardCanvas.
 */
public class TestGameboardCanvas {

	/**
	 * Testing 4 scenarioes of the shapeBiggerThan method.
	 *
	 * @author hd
	 */
	@Test
	public void testShapeBiggerThanGameboard() {
		
		GameboardCanvas gb = new GameboardCanvas();
				
		int shapeColumns, shapeRows;
		int gridColumns = 10;
		int gridRows = 10;
				
		Grid grid = new Grid(gridColumns,gridRows);
		
		//Expect true
		boolean result1 = gb.shapeBiggerThanGameboard(11, 11);
		//Expect false
		boolean result2 = gb.shapeBiggerThanGameboard(9, 9);
		//Expect true
		boolean result3 = gb.shapeBiggerThanGameboard(9, 11);
		//Expect true
		boolean result4 = gb.shapeBiggerThanGameboard(11, 9);
		
		assertTrue(result1);
		assertFalse(result2);
		assertTrue(result3);
		assertTrue(result4);
		
	}

}
