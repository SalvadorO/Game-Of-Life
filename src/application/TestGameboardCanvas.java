package application;

import static org.junit.Assert.*;

import org.junit.Test;

import application.GameboardCanvas;

/**
 * TestGameboardCanvas class
 */
public class TestGameboardCanvas {

	/**
	 * Testing 5 scenarios of the shapeBiggerThan method.
	 * Test instatiates a gameboardcanvas and sets the gridsize of it's grid object
	 * Creates 5 different shapes and calls the shapeBiggerThanGameboard() method to check each of them (result is assigned
	 * to a boolean result variable
	 * Finally uses assertTrue/ assertFalse to test the scenarios
	 * @author hd
	 */
	@Test
	public void testShapeBiggerThanGameboard() {
		
		int gridColumns = 10;
		int gridRows = 10;
		
		GameboardCanvas gb = new GameboardCanvas(20, 20);
		gb.grid.setGrid(gridColumns, gridRows);		
		
//		Grid grid = new Grid(gridColumns,gridRows);
		System.out.println("Columns: "+ gb.grid.getColumns());
		System.out.println("Rows: "+ gb.grid.getRows());
		
		String[] shape1 = new String[]{"x=11","y=11"};
		String[] shape2 = new String[]{"x=9","y=9"};
		String[] shape3 = new String[]{"x=9","y=11"};
		String[] shape4 = new String[]{"x=11","y=9"};
		String[] shape5 = new String[]{"x=10","y=10"};
		
		//Expect true
		boolean result1 = gb.shapeBiggerThanGameboard(shape1);
		//Expect false
		boolean result2 = gb.shapeBiggerThanGameboard(shape2);
		//Expect true
		boolean result3 = gb.shapeBiggerThanGameboard(shape3);
		//Expect true
		boolean result4 = gb.shapeBiggerThanGameboard(shape4);
		//Expect false
		boolean result5 = gb.shapeBiggerThanGameboard(shape5);
		
		assertTrue(result1);
		assertFalse(result2);
		assertTrue(result3);
		assertTrue(result4);
		assertFalse(result5);
	}

}
