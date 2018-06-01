package Main;

public interface SimpGraphics {
	/*
	 * This needs to be implemented by every group that is making any visuals. We
	 * need to be able to call these methods even if they do different things for
	 * every group. This will be updated throughout the project to include any
	 * additional methods we might require.
	 */

	public boolean isActive(); // Checks whether the implemented class's variable was
							   // evoked/activated. If the variable is false then return
							   // false

	public void inactiveTimer(int seconds); // if a user hasn't pressed a button or touched the screen
											// in a set amount of time then set your chosen
											// variable as false

	public void create(); // Constructs JFrame for your group

	public void destroy(); // Turns of JFrame

	public void rescale(double scale); // This replaces the enlarge and shrink methods. A
										// scale factor will be passed as a parameter. A
										// scale of 2 should make your components twice as
										// large while a 0.5 scale makes it half as large.
}
