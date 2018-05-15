public interface SimpGraphics
{
	/*
	 * This needs to be implemented by every group that is making any visuals. We
	 * need to be able to call these methods even if they do different things for
	 * every group. This will be updated throughout the project to include any
	 * additional methods we might require.
	 */
	public void create(); // Constructs JFrame for your group
	
	public void destroy(); // Turns of JFrame
	
	public void enlarge(); // Makes JFrame and any components that are appropriate larger
	
	public void shrink(); // Makes JFrame and any components that are appropriate smaller
}
