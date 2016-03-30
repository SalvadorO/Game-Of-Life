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
	 * Method counts and returns number of neighbours
	 * to a cell given by provided x and y param
	 * TODO: not tested after moved here
	 * @param int x, int y
	 * @return number of neighbours
	 * @author hd
	 */
	public int countNeighbours(int x, int y)	{
	
		int neighbours = 0;
		int cellValue = 0;
		
		for (int  i=-1; i<2 ; i++)
			for (int j=-1;j<2;j++)	{
				cellValue = gamegrid[x+i][y+j];
				if (!(i==0 && j==0) && (cellValue==1))
					neighbours++;
			}
		return neighbours;
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
	
	/**
	 * Method implements two rules
	 * 1) cell dies if number of neighbours is less than 2
	 * 2) cell dies if number of neighbours is greater than 3
	 * @param neighBours
	 * @return boolean
	 * @author hd
	 */
	public boolean survives(int neighbours){
		boolean alive=true;
		if (( neighbours < 2) || (neighbours> 3))
			alive = false;
		return alive;
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
