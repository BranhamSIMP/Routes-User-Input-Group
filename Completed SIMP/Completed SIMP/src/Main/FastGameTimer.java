package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/*
   Displays the current time once every second.
 */
public class FastGameTimer
{
	private Timer t;
	private double timeRemaining;
	private double startingTime;
	private final int DELAY = 5; // milliseconds between timer ticks
	
	public FastGameTimer(double d)
	{
		timeRemaining = d;
		startingTime = d;
		class CurrentTime implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				timeRemaining -= .005;
				if (timeRemaining <= 0)
				{
					t.stop();
				}
			}
		}
		CurrentTime listener = new CurrentTime();
		t = new Timer(DELAY, listener);
		t.start();
	}
	
	/**
	 * 
	 * @return The number of seconds remaining
	 */
	public double getTimeRemaining()
	{
		return timeRemaining;
	}
	
	public double getStartingTime()
	{
		return startingTime;
	}
}
