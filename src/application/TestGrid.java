package application;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * TestGrid class
 * @author hd
 */
class TestGrid {

	GameboardCanvas gbc = new GameboardCanvas(10, 10);
	
	@Test
	protected void test_GridGetters() {
		
//		Test 1
		assertTrue(gbc.grid.getColumns() == 10);
		assertTrue(gbc.grid.getRows() == 10);
		
//		Test 2
		gbc.grid.setGrid(5, 5);
		assertFalse(gbc.grid.getColumns() == 10);
		assertFalse(gbc.grid.getRows() == 10);
		
	}
	
	@Test
	protected void test_setCellStatus() {
		
		gbc.grid.setGrid(5, 5);
		
//		Test 1
		gbc.grid.setCellstatus(0, 0, 1);
		gbc.grid.setCellstatus(4, 4, 1);
		String expectedresult = "1000000000000000000000001";
		assertEquals(expectedresult, gbc.grid.toString());
		
//		Test 2
		gbc.grid.setCellstatus(4, 4, 0);
		assertFalse(expectedresult.equals(gbc.grid.toString()));
	}

}
