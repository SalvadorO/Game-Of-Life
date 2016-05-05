package application;

import static org.junit.Assert.*;

import org.junit.Test;

import application.GameboardCanvas;

/**
 * TestGameboardCanvas class
 */
class TestGameboardCanvas {

	/**
	 * Testing 5 scenarios of the shapeBiggerThan method.
	 * Test instatiates a gameboardcanvas and sets the gridsize of it's grid object
	 * Creates 5 different shapes and calls the shapeBiggerThanGameboard() method to check each of them (result is assigned
	 * to a boolean result variable
	 * Finally uses assertTrue/ assertFalse to test the scenarios
	 * @author hd
	 */
	@Test
	protected void test_shapeWithinGamegridBoundaries() {
		
		int gridColumns = 10;
		int gridRows = 10;
		
		GameboardCanvas gb = new GameboardCanvas(20, 20);
		gb.grid.setGrid(gridColumns, gridRows);		
		
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

	/**
	 * Test case for the parsePattern() method.
	 * A testpattern is run through the method, which updates the grid.
	 * Uses the Grid's toString method to get a 1D representation of the grid
	 * and compare this to the testpattern
	 * @author hd
	 */
	@Test
	protected void test_parsePattern(){
		
//		RLE representation of a glider
		String testpattern1 = "bo$2bo$3o!";
//		1D representation of a glider
		String expectedresult1 = "010001111";
		
		String testpattern2 = "o2bo$o2bo$o2bo$o2bo!";
		String expectedresult2 = "1001100110011001";
		
		
//		Instatiate a GameboardCanvas object (size 3 by 3)
		GameboardCanvas gbc = new GameboardCanvas(3, 3);
		
//		Test 1
//		Run the parsePttern() method, which parse and updates the grid
		gbc.parsePattern(testpattern1);
		
//		Compare the updated grid to the expected result
//		Expect true
		assertEquals(expectedresult1, gbc.grid.toString());
		
//		Test 2
//		Set new gridsize and run next pattern
		gbc.grid.setGrid(4, 4);
		gbc.parsePattern(testpattern2);
		
//		Compare updated grid to expected result
//		Expect true
		assertEquals(expectedresult2, gbc.grid.toString());
		
	}
}
