package Main;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class MapElement {
	private ArrayList<Rectangle> boxes;
	private MapElement parent;
	
	public MapElement(SimpGraphics parent, Point corner, int width, int height) {
		boxes = new ArrayList<Rectangle>();
		boxes.add(new Rectangle(corner.x, corner.y, width, height));
	}
	
	public MapElement(SimpGraphics parent, Rectangle rect) {
		boxes = new ArrayList<Rectangle>();
		boxes.add(rect);
	}
	
	public MapElement(SimpGraphics parent, ArrayList<Rectangle> rectList) {
		boxes = new ArrayList<Rectangle>(rectList);
	}
	
	public boolean contains(Point p) {
		for (Rectangle box: boxes) {
			if (box.contains(p)) {
				return true;
			}
		}
		return false;
	}
	
	protected ArrayList<Rectangle> getBounds() {
		return boxes;
	}
	
	protected MapElement getParent() {
		return parent;
	}
}
