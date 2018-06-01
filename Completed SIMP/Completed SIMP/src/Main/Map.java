package Main;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
//
import javax.swing.JFrame;
import javax.swing.WindowConstants;
// 
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
// https://medium.com/programmers-blockchain/importing-gson-into-eclipse-ec8cf678ad52

public class Map implements SimpGraphics
{
	private static final String MAP_FILENAME = "BetterMapOfBranham3.png";
	private static final int FRAME_HEIGHT = 920;
	private static final int FRAME_WIDTH = 1165; // 729, 469
	private ArrayList<MapElement> elements;
	private JFrame frame;
	//
	FastGameTimer timer;
	int idleTime = 5;
	
	public Map()
	{
		elements = new ArrayList<MapElement>();
		try
		{
			loadFromJson("locations.json");
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Locations file not found");
		}
	}
	
	private void loadFromJson(String fileName) throws FileNotFoundException
	{
		File f = new File(fileName);
		Scanner s = new Scanner(f);
		s.useDelimiter("\\A");
		String result = s.hasNext() ? s.next() : "";
		s.close();
		JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
		for (JsonElement wing : jsonObject.get("wings").getAsJsonArray())
		{
			elements.add(parseWing(wing.getAsJsonObject()));
		}
		for (JsonElement room : jsonObject.get("rooms").getAsJsonArray())
		{
			elements.add(parseClassroom(this, room.getAsJsonObject()));
		}
	}
	
	private Point arrayToPoint(JsonArray arr)
	{
		return new Point((int) (FRAME_WIDTH * arr.get(0).getAsDouble()),
				(int) (FRAME_HEIGHT * arr.get(1).getAsDouble()));
	}
	
	private Classroom parseClassroom(SimpGraphics parent, JsonObject c)
	{
		ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
		for (JsonElement rect : c.get("buttons").getAsJsonArray())
		{
			JsonObject r = rect.getAsJsonObject();
			Point upperLeft = arrayToPoint(r.get("upperLeft").getAsJsonArray());
			Point lowerRight = arrayToPoint(r.get("lowerRight").getAsJsonArray());
			rects.add(new Rectangle(upperLeft.x, upperLeft.y, lowerRight.x - upperLeft.x,
					lowerRight.y - upperLeft.y));
		}
		return new Classroom(parent, rects, c.get("description").getAsString());
	}
	
	private Wing parseWing(JsonObject w)
	{
		ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
		ArrayList<Classroom> rooms = new ArrayList<Classroom>();
		for (JsonElement rect : w.get("buttons").getAsJsonArray())
		{
			JsonObject r = rect.getAsJsonObject();
			Point upperLeft = arrayToPoint(r.get("upperLeft").getAsJsonArray());
			Point lowerRight = arrayToPoint(r.get("lowerRight").getAsJsonArray());
			rects.add(new Rectangle(upperLeft.x, upperLeft.y, lowerRight.x - upperLeft.x,
					lowerRight.y - upperLeft.y));
		}
		Wing result = new Wing(this, rects, w.get("description").getAsString());
		for (JsonElement room : w.get("rooms").getAsJsonArray())
		{
			rooms.add(parseClassroom(result, room.getAsJsonObject()));
		}
		result.addRooms(rooms);
		return result;
	}
	
	private class MapListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
		}
		
		@Override
		public void mousePressed(MouseEvent e)
		{
			// TODO Auto-generated method stub
			for (MapElement m : elements)
			{
				if (m.contains(e.getPoint()))
				{
					System.out.println(m.toString());
				}
			}
			timer = new FastGameTimer(idleTime);
		}
		
		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub
		}
		
		@Override
		public void mouseEntered(MouseEvent e)
		{
			// TODO Auto-generated method stub
		}
		
		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub
		}
	}
	
	@Override
	public void create()
	{
		// TODO Auto-generated method stub
		frame = new JFrame("Tester");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		ImagePanel IP = new ImagePanel(MAP_FILENAME);
		frame.add(IP);
		frame.setVisible(true);
		MouseListener listener = new MapListener();
		IP.addMouseListener(listener);
		timer = new FastGameTimer(idleTime);
	}
	
	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		frame.dispose();
	}
	
	@Override
	public boolean isActive()
	{
		// TODO Auto-generated method stub
		if (timer.getTimeRemaining() <= 0)
			return false;
		else
			return true;
	}
	
	@Override
	public void inactiveTimer(int seconds)
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void rescale(double scale)
	{
		// TODO Auto-generated method stub
	}
}
