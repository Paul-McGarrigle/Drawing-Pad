import java.awt.*;
import java.io.Serializable;

public abstract class Shape implements Serializable{
	protected int shapeType;
	protected Color colour;
	protected Point p1,p2,p3,p4;
	
	//Shape Constructor accepts the coordinates, color and type of each 2 point shape
	public Shape(Point p1, Point p2, Color colour, int shapeType){
		this.p1 = p1;
		this.p2 = p2;
		this.colour = colour;
		this.shapeType = shapeType;
	}

	//Shape Constructor accepts the coordinates, color and type of each 3 point shape
	public Shape(Point p1, Point p2, Point p3, Color colour, int shapeType){
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.colour = colour;
		this.shapeType = shapeType;
	}

	//Draw Method
	public abstract void draw(Graphics2D g);
}
