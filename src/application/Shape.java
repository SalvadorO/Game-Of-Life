package application;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*
 * Cell object, with defined size and colour
 * May be developed to support user selected color and form
 */
public class Shape extends Canvas	{

	public Shape()	{
		super(10,10);
		GraphicsContext gc = super.getGraphicsContext2D();
		gc.setFill(Color.BLUE);
		gc.fillOval(0,0,10,10);
	}
}
