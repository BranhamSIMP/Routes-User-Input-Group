package Main;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Wing extends MapElement implements SimpGraphics {
	private ArrayList<Classroom> rooms;
	private String description;
	
	public Wing(SimpGraphics parent, Rectangle rect, String description) {
		super(parent, rect);
		this.description = description;
	}
	
	public Wing(SimpGraphics parent, ArrayList<Rectangle> rectList, String description) {
		super(parent, rectList);
		this.description = description;
	}
	
	public void addRooms(ArrayList<Classroom> rooms) {
		this.rooms = rooms;
	}
	
	public String toString() {
		String result = description + "\n\nRooms:\n";
		for (Classroom r : rooms) {
			result += r.toString() + "\n";
		}
		return result;
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void inactiveTimer(int seconds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rescale(double scale) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
