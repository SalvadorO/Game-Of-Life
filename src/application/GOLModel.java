package application;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class GOLModel {
	
	private final boolean INITIAL_VALUE=false;
	private boolean cellValue;
	private GameboardCanvas gb;	
	
	GOLModel()	{
		reset();
	}
	
	//TODO: add JDOC
	public void reset()	{
		cellValue=INITIAL_VALUE;
	}
	
	
	
	/**
	 * Method counts and returns number of neighbours
	 * to a cell given by provided x and y param
	 * @param int x, int y
	 * @return number of neighbours
	 * @author hd
	 */
	protected int countNeighbours(int x, int y)	{
	
		int neighbours = 0;
		int cellValue = 0;
		
		for (int  i=-1; i<2 ; i++)
			for (int j=-1;j<2;j++)	{
				cellValue = gb.getTable()[x+i][y+j];
				if (!(i==0 && j==0) && (cellValue==1))
					neighbours++;
			}
		return neighbours;
	}
	
	//TODO: add JDOC
	 protected int cellstatusNextgeneration(int x, int y)	{
		 int cn = countNeighbours(x,y);
		 
		 if (gb.getTable()[x][y]==1){
			if (survives(cn))
				return 1;
			gb.updateCellstatus(x, y, 0);
			return 0;
		 }
		 if (cn==3){
			 gb.updateCellstatus(x, y, 1);
			 return 1;
		 }
		 return 0;
	 }	 
 
	
/**
 * Method implements two rules
 * 1) cell dies if number of neighbours is less than 2
 * 2) cell dies if number of neighbours is greater than 3
 * @param neighBours
 * @return boolean
 *  
 */
public boolean survives(int neighbours)	{
	boolean alive=true;
	
	if (( neighbours < 2) || (neighbours> 3))
			alive = false;
	return alive;
}


/**
 * Method checks if current cell is dead or alive
 * @param x coordinate
 * @param y coordinate
 * @return boolean
 */
public boolean isAlive(int x, int y)	{
	if (gb.getTable()[x][y]==1)
		return true;
	return false;
}
}

	


