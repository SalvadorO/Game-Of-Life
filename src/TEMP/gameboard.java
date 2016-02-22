package TEMP;

import java.util.Arrays;

/**
 * Initialized with a table representing the gameboard
 * Provides the possibility to set and get the grid, 
 * Provides a toString method to get a 1D representation of the table.
 * @author hd
 *
 */
public class gameboard {
	int [][] table;
	
	public gameboard(int[][] t){
		table = t;
	}
	
	public int[][] getTable() {
		return table;
	}

	public void setTable(int[][] table) {
		this.table = table;
	}
	
	public void updateCellstatus(int x, int y, int value)	{
		table[x][y]=value;
	}

	@Override
	public String toString() {
		String o="";
		for (int i=0;i<table.length;i++)
			for (int j=0;j<table[i].length;j++)
				o+=table[i][j];
		return o;
	}
	
	
	
}
