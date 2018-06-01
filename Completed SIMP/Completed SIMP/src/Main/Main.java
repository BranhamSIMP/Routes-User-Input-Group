package Main;

import java.io.IOException;

public class Main
{
	public static void main(String[] args)
	{
		// Start with screensaver
		// On click, turn off screensaver and go to the board
		// After a period of inacitivity, return to screensaver
		//
		boolean active = false;
		FastGameTimer timer;
		//
		IdleScreen r = new IdleScreen();
		try
		{
			r.startidlescreen(2, .5);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		while (true)
		{
			if (!active)
			{
				active = true;
				Map m = new Map();
				m.create();
				System.out.print("\n-\n-\nNew Map Created\n-\n-\n\n");
				//
				while (m.isActive())
				{
					if (!m.isActive())
						break;
					System.out.print("");
				}
				//
				m.destroy();
				active = false;
				r = new IdleScreen();
				try
				{
					r.startidlescreen(2, .5);
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//
		}
	}
}