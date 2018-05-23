package Pathfinder;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class PixelTraverse {
	private NodeMap nm;
	public Node node_goal;
	public Node node_start;
	
	public PixelTraverse(BufferedImage f, int roadcolor, int startx, int starty, int goalx, int goaly) throws IOException {
		nm = new NodeMap(f, roadcolor);
		node_goal = new Node(null, goalx, goaly, 1 );
		node_start=new Node(node_goal,startx, starty, 1);
		
	}
	
	
	public ArrayList<Point> startpixeltraverse(){
	ArrayList<Point> SolutionPathList = new ArrayList<Point>();

	// Create a node containing the goal state node_goal
	// Create a node containing the start state node_start
	

	// Create OPEN and CLOSED SortedSets
	TreeSet<Node> OPEN = new TreeSet<Node>();
	TreeSet<Node> CLOSED = new TreeSet<Node>();
	Iterator<Node> opennode=OPEN.iterator();
	Iterator<Node> closednode=OPEN.iterator();
	// Put node_start on the OPEN list
	OPEN.add(node_start);

	
	// while the OPEN list is not empty
	Key:while(OPEN.size()>0)
	{

	 //Get the node off the open list
	 //with the lowest f and call it node_current
	 Node node_current = OPEN.first();
	 OPEN.remove(OPEN.first());

	
	 
		
	 //if node_current is the same state as node_goal we
	 //have found the solution;
	 //break from the while loop;
	 if (node_current.isMatch (node_goal))
	 {
	   node_goal.setParent(node_current.getParent());
	   break;
	 }
//	 System.out.println(node_current.getX()+", "+node_current.getY());
	 //Generate each state node_successor that can come after node_current
	 ArrayList<Node> successors = node_current.GetSuccessors(nm, node_goal);
	 
	 
	 //for each node_successor or node_current
	 for(Node node_successor:successors)
	 {
	
			
			
	   //Set the cost of node_successor to be the cost of node_current plus
	   //the cost to get to node_successor from node_current
	   //--> already set while we were getting successors

	   //find node_successor on the OPEN list
		 
	   
	   //if node_successor is on the OPEN list but the existing one is as good
	   //or better then discard this successor and continue
		 Node n = node_successor.pull(opennode);
		 
		 
		   if(n!=null) {
			   
			   
			   if(node_successor.compareTo(n)>0) {
				   continue;
				   
			   }	
			   }
	   //find node_successor on the CLOSED list
	  

	   //if node_successor is on the CLOSED list
	   //but the existing one is as good
	   //or better then discard this successor and continue;
	   Node na = node_successor.pull(closednode);
	   if(na!=null) {
		   if(node_successor.compareTo(na)>0) {
			   continue;
			   
		   }	
		   
	   }
	   
	   
	   //Remove occurences of node_successor from OPEN and CLOSED
	   if(n !=null) {
	  OPEN.remove(n);
	   }
	   if(na != null) {
	  CLOSED.remove(na);

	   }
	   //Set the parent of node_successor to node_current;
	   //--> already set while we were getting successors

	   //Set h to be the estimated distance to node_goal
	   //(Using heuristic function)
	   //--> already set while we were getting successors

	   //Add node_successor to the OPEN list
	   OPEN.add(node_successor);
	  
	  }
	  //Add node_current to the CLOSED list
	  CLOSED.add(node_current);

	}
	// Once we get to the goal, follow parent nodes to find the solution path.

	// follow the parentNode from goal to start node
	// to find solution
	Node p = node_goal;
	while(p!=null)
	{
		SolutionPathList.add(0, new Point(p.getX(),p.getY()));
		p = p.getParent();
	}
	return SolutionPathList;
}

}
