package application;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*
 * Holds shapes, currently two shapes for testing purposes 
 * May be extended to set properties
 * May be extended to hold a collection of shapes
 *  */
public class Shape {


	double[][] typeA = new double[][] 	{
		{0,0,0,0},
		{0,1,1,0},
		{1,0,0,1},
		{0,1,1,0}
		};
		
	double[][] typeB = new double[][] 	{
		{1,0,0,0,1},
		{0,1,0,1,0},
		{0,0,1,0,0},
		{0,1,0,1,0},
		{1,0,0,0,1}
		};
		
	public double[][] getShapeA(){
		return typeA;
	}
	
	public double[][] getShapeB(){
		return typeB;
	}
	
}
