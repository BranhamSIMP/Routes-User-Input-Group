package Main;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Classroom extends MapElement implements SimpGraphics {
	private String description;
	
	public Classroom(SimpGraphics parent, Rectangle box, String description) {
		super(parent, box);
		this.description = description;
		// TODO Auto-generated constructor stub
	}
	
	public Classroom(SimpGraphics parent, ArrayList<Rectangle> boxes, String description) {
		super(parent, boxes);
		this.description = description;
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return description;
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
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rescale(double scale) {
		// TODO Auto-generated method stub
		
	}
}
