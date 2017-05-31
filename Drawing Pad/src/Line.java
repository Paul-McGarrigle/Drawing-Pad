import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

public class Line extends Shape implements Serializable{

	public Line(Point p1, Point p2, Color colour, int shapeType){
		super(p1, p2, colour, shapeType);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(colour);
		AffineTransform saveAT = g.getTransform();
		g.drawLine(p1.x, p1.y, p2.x, p2.y);
		g.setTransform(saveAT);
	}


}
