import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

public class Circle extends Shape implements Serializable{
	protected int r;

	public Circle(Point p1, Point p2, Color colour, int shapeType){
		super(p1, p2, colour, shapeType);
	}

	@Override
	public void draw(Graphics2D g) {
		int r = (int) Math.round(p1.distance(p2));
		g.setColor(colour);
		AffineTransform saveAT = g.getTransform();
		g.drawOval(p1.x - r, p1.y - r, 2 * r, 2 * r);
		g.setTransform(saveAT);
	}
}
