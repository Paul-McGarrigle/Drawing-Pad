import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

public class Square extends Shape implements Serializable{

	public Square(Point p1, Point p2, Color colour, int shapeType){
		super(p1, p2, colour, shapeType);
	}

	@Override
	public void draw(Graphics2D g) {
		int r = (int) Math.round(p1.distance(p2));
		g.setColor(colour);
		AffineTransform saveAT = g.getTransform();
		g.rotate(Math.toRadians(r), p1.x, p1.y);
		g.drawRect(p1.x, p1.y, r, r);
		g.setTransform(saveAT);
	}

}
