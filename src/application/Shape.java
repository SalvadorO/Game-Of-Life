package application;

public class Shape {

	int[][] typeA = new int[][]{
		{0,0,0,0},
		{0,1,1,0},
		{1,0,0,1},
		{0,1,1,0}
		};
		
		int[][] typeB = new int[][]{
		{1,0,0,0,1},
		{0,1,0,1,0},
		{0,0,1,0,0},
		{0,1,0,1,0},
		{1,0,0,0,1}
		};
		
		int[][] typeInfinity = new int[][] {
		{0,0,0,0,0},
		{0,0,0,0,0},
		{0,1,1,1,0},
		{0,0,0,0,0},
		{0,0,0,0,0}
		};
		
		int[][] typeGlider = new int[][] 	{
		{0,0,0,0,0},
		{0,0,1,0,0},
		{0,0,0,1,0},
		{0,1,1,1,0},
		{0,0,0,0,0}
		};
		
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
		
	public  int[][] getShapeA(){
		return typeA;
	}
	
	public int[][] getShapeB(){
		return typeB;
	}
	
	public int[][] getShapeInfinity(){
		return typeInfinity;
	}
	
	public int[][] getShapeGlider(){
		return typeGlider;
	}
	
	public int[][] getShapeGliderGun(){
		return typeGliderGun;
	}
}
