package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Grid {
	private int[][] gamegrid;
	
	public Grid(int x, int y){
		gamegrid = new int[x][y];
	}
	
	/**
	 * Method defines the grid size based on received x and y values
	 * @param x
	 * @param y
	 * @author hd
	 */
	public void setGrid(int x, int y){
		gamegrid = new int[x][y];
	}
	
	/**
	 * 
	 * @return int[][] gamegrid
	 * @auth
	 */
	public int[][] getGrid(){
		return gamegrid;
	}
	
	/**
	 * Method sets cell value based on received x and y parameter
	 * @param x
	 * @param y
	 * @param value
	 * @author hd
	 */
	public void setCellstatus(int x, int y, int value)	{
		gamegrid[x][y]=value;
	}
	
	public int getCellstatus(int x, int y){
		return gamegrid[x][y];
	}
	
	/**
	 * A toString method to get a 1D representation of the table.
	 * @author hd
	 */
	@Override
	public String toString() {
		String o="";
		for (int i=0;i<gamegrid.length;i++)
			for (int j=0;j<gamegrid[i].length;j++)
				o+=gamegrid[i][j];
		return o;
	}
	
}
