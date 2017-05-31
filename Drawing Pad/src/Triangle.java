import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

public class Triangle extends Shape implements Serializable{
	protected int count;

	public Triangle(Point p1, Point p2, Point p3, Color colour, int shapeType, int count){
		super(p1, p2, p3, colour, shapeType);
		this.count = count;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(colour);
		AffineTransform saveAT = g.getTransform();
		g.drawLine(p1.x, p1.y, p2.x, p2.y);
		g.drawLine(p2.x, p2.y, p3.x, p3.y);
		g.drawLine(p1.x, p1.y, p3.x, p3.y);
		g.setTransform(saveAT);
	}

}
