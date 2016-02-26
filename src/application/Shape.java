package application;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*
 * Holds shapes, currently one shape for testing purposes 
 * May be extended to set properties
 *  */
public class Shape {


	double[][] typeA = new double[][] 	{
		{0,0,0,0},
		{0,1,1,0},
		{1,0,0,1},
		{0,1,1,0}
		};
		
	public double[][] getShape(){
		return typeA;
	}
	
}
