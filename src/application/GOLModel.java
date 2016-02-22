package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GOLModel {
	
	private final boolean INITIAL_VALUE=false;
	private boolean cellValue;

		
	GOLModel()	{
		reset();
		
	}
	
	public void reset()	{
		cellValue=INITIAL_VALUE;
	}
	
	public void setGrid(GridPane grd_Gameboard)	{
		
		if (cellValue)	{
			grd_Gameboard.setGridLinesVisible(true);	
			cellValue=false;
		}
		else	{
			grd_Gameboard.setGridLinesVisible(false);
			cellValue=true;
		}
	}
	
	public void setCell(GridPane grd_Gameboard){
		
		Shape shape = new Shape();
				
		grd_Gameboard.add(shape, 1, 3);
		
	}

	
}

