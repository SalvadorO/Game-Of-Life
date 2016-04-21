package application;

/**
 * The Class Shape.
 */
public class Shape {

		/** The type a. */
		int[][] typeA = new int[][]{
		{0,0,0,0},
		{0,1,1,0},
		{1,0,0,1},
		{0,1,1,0}
		};
		
		/** The type b. */
		int[][] typeB = new int[][]{
		{1,0,0,0,1},
		{0,1,0,1,0},
		{0,0,1,0,0},
		{0,1,0,1,0},
		{1,0,0,0,1}
		};
		
		/** The type infinity. */
		int[][] typeInfinity = new int[][] {
		{0,0,0,0,0},
		{0,0,0,0,0},
		{0,1,1,1,0},
		{0,0,0,0,0},
		{0,0,0,0,0}
		};
		
		/** The type glider. */
		int[][] typeGlider = new int[][] 	{
		{0,0,0,0,0},
		{0,0,1,0,0},
		{0,0,0,1,0},
		{0,1,1,1,0},
		{0,0,0,0,0}
		};
		
		/** The type glider gun. */
		int[][] typeGliderGun = new int[][] {
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
		{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
		{1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}	
		};
		
		/** The test. */
		int[][] test = new int[][]{
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,1,0,0,0,0,0},
		{0,0,1,0,1,0,0,0,0,0},
		{0,0,0,1,1,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0}
		};
		
	/**
	 * Gets the shape a.
	 *
	 * @return the shape a
	 */
	public  int[][] getShapeA(){
		return typeA;
	}
	
	/**
	 * Gets the shape b.
	 *
	 * @return the shape b
	 */
	public int[][] getShapeB(){
		return typeB;
	}
	
	/**
	 * Gets the shape infinity.
	 *
	 * @return the shape infinity
	 */
	public int[][] getShapeInfinity(){
		return typeInfinity;
	}
	
	/**
	 * Gets the shape glider.
	 *
	 * @return the shape glider
	 */
	public int[][] getShapeGlider(){
		return typeGlider;
	}
	
	/**
	 * Gets the shape glider gun.
	 *
	 * @return the shape glider gun
	 */
	public int[][] getShapeGliderGun(){
		return typeGliderGun;
	}
	
	/**
	 * Gets the test.
	 *
	 * @return the test
	 */
	public int[][] gettest(){
		return test;
	}
}