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
	public void test_shapeWithinGamegridBoundaries() {
		
		int gridColumns = 10;
		int gridRows = 10;
		
		GameboardCanvas gb = new GameboardCanvas(20, 20);
		gb.grid.setGrid(gridColumns, gridRows);		
		
//		Grid grid = new Grid(gridColumns,gridRows);
		System.out.println("Columns: "+ gb.grid.getColumns());
		System.out.println("Rows: "+ gb.grid.getRows());
		
		//Expect false
		boolean result1 = gb.shapeWithinGamegridBoundaries(11, 11);
		//Expect true
		boolean result2 = gb.shapeWithinGamegridBoundaries(9, 9);
		//Expect false
		boolean result3 = gb.shapeWithinGamegridBoundaries(9, 11);
		//Expect false
		boolean result4 = gb.shapeWithinGamegridBoundaries(11, 9);
		//Expect true
		boolean result5 = gb.shapeWithinGamegridBoundaries(10, 10);
		
		assertFalse(result1);
		assertTrue(result2);
		assertFalse(result3);
		assertFalse(result4);
		assertTrue(result5);
	}

}
