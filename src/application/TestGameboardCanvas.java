package application;

import static org.junit.Assert.*;

import org.junit.Test;

import application.GameboardCanvas;
import application.Grid;

/**
 * The Class TestGameboardCanvas.
 */
public class TestGameboardCanvas {

	/**
	 * Testing 4 scenarios of the shapeBiggerThan method.
	 *
	 * @author hd
	 */
	@Test
	public void testShapeBiggerThanGameboard() {
		
		GameboardCanvas gb = new GameboardCanvas(20, 20);
				
		int gridColumns = 10;
		int gridRows = 10;
				
		Grid grid = new Grid(gridColumns,gridRows);
		System.out.println("Kolonner: "+ grid.getColumns());
		
		String[] headerelements1 = new String[]{"x=11","y=11"};
		String[] headerelements2 = new String[]{"x=9","y=9"};
		String[] headerelements3 = new String[]{"x=9","y=11"};
		String[] headerelements4 = new String[]{"x=11","y=9"};
		
		//Expect true
		boolean result1 = gb.shapeBiggerThanGameboard(headerelements1);
		//Expect false
		boolean result2 = gb.shapeBiggerThanGameboard(headerelements2);
		//Expect true
		boolean result3 = gb.shapeBiggerThanGameboard(headerelements3);
		//Expect true
		boolean result4 = gb.shapeBiggerThanGameboard(headerelements4);
		
		assertTrue(result1);
		assertFalse(result2);
		assertTrue(result3);
		assertTrue(result4);
		
	}

}
