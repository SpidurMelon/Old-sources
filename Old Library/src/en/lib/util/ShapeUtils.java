package en.lib.util;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.util.ArrayList;

public class ShapeUtils {
	public static ArrayList<Point> getCoords(Shape s, AffineTransform at) {
		ArrayList<Point> result = new ArrayList<Point>();
		PathIterator pi = s.getPathIterator(at);
		double[] coords = new double[6];
		while(!pi.isDone()) {
			pi.currentSegment(coords);
			result.add(new Point((int)coords[0], (int)coords[1]));
			pi.next();
		}
		return result;
	}
	
	public static int getMaxY(Shape s, AffineTransform at) {
		ArrayList<Point> coords = getCoords(s, at);
		int result = coords.get(0).y;
		for (Point p:coords) {
			if (p.y > result) {
				result = p.y;
			}
		}
		return result;
	}
	
}
