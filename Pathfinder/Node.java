package PathFinder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Node implements Comparable<Node>{
	
	private Node parent;
	private int x;
	private int y;
	private float f;
	float gcost;
	float mvmtcost;
	float heuristic;
	public Node(Node paren, Node goal, int x, int y, float mvmtcost) {
		this.parent=paren;
		this.x=x;
		this.y=y;
		
		f(this, goal, mvmtcost);
	
//	System.out.println(heuristic);
	}
//	public Node(Node paren, int x, int y, float mvmtcost) {
//		this.parent=paren;
//		this.x=x;
//		this.y=y;
//		gcost=0;
//		
//	}
	public Node(Node goal, int x, int y, float mvmtcost) {
		this.x=x;
		this.y=y;
		gcost=mvmtcost;
		if(goal !=null) {
		h(this, goal);
		this.setf();
		}
		
	}
	public Node getParent() {
		return parent;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setParent(Node n) {
		parent=n;
	}
	public float getf() {
		return f;
	}
	public void setf() {
		this.f=this.gcost+this.heuristic;
	}
	
	public ArrayList<Node> GetSuccessors(NodeMap nm, Node goal){
		ArrayList<Node> a=new ArrayList<Node>(0);
		for(int x=-1;x<2;x++) {
			for(int y=-1;y<2;y++) {
//				if((x==0 && y==0)) {
//				}
				if(this.getX()+x>=0 && this.getX()+x<nm.width && this.getY()+y>=0 && this.getY()+y<nm.height ) {
			
					if(nm.Points[this.getY()+y][this.getX()+x] ==null) 
					{
						//if(nm.Points[this.getX()+x][this.getY()+y] ==null) {
						
						}
						
					else {
					
					if((x==1 || x==-1) && (y==1 || y==-1)) {
						a.add(new Node(this, goal,this.getX()+x, this.getY()+y, 1.414f));
					
					}
					else {
						
					a.add(new Node(this, goal, this.getX()+x, this.getY()+y, 1));
					}
					
						 }
				}
			}
		}
		return a;
	}
	public Node pull(PriorityQueue<Node> t) {
		Iterator<Node> n = t.iterator();
		
		while(n.hasNext()) {
			
			
			Node tmp=n.next();
			
			
			if(tmp.getX()==this.getX() && tmp.getY()==this.getY()) {
				
				//System.out.println(tmp.getX()+", "+tmp.getY()+", "+tmp.getf());
				return tmp;
			}
			
			
		}
		
		return null;
	}
	@Override
	public int compareTo( Node parent) {
		
		float a= parent.f-this.f;
		if(a>0) {
			return -1;
		}
		else if(a==0f) {
			return 0;
		}
		else {
			return 1;
		}
		
	}
	private static void f(Node successor, Node goal, float mvmtcost)
	{
	    h(successor, goal);
	    g(successor, mvmtcost);
	    successor.f=successor.gcost+successor.heuristic;
	    
	}

	private static void h(Node successor, Node goal)
	    {
		//EXPLANATION:
		
		//https://stackoverflow.com/questions/26558994/weird-behavior-in-a-algorithm-with-diagonal-heuristic
		
	        float dx = Math.abs(successor.getX()-goal.getX());
        float dy = Math.abs(successor.getY()-goal.getY());
//        float mx=Math.max(dx, dy);
//        float mn=Math.min(dx, dy);
//		float hi =(dx + dy) - .5857f* Math.min(dx, dy);
		float h = (float) Math.sqrt(dx*dx+dy*dy);
//		float  hi=(float) Math.max(dx,dy)+(.414f)*Math.min(dx, dy);
		
	        successor.heuristic=h;
	        
	       // successor.heuristic=(dx + dy) - .586f * Math.min(dx, dy);
	    }

	private static void g(Node successor,float mvmtcost)
	    {
		successor.mvmtcost=mvmtcost;
		successor.gcost=successor.getParent().gcost+mvmtcost;
		
//		successor.mvmtcost=mvmtcost;
//		successor.gcost=0;
//		Node p=successor;
//	       while(p!=null) {
//	    	   successor.gcost+=p.mvmtcost;
//	    	   p=p.getParent();
//	    	   
//	       }
	      
	        
	    }
	public boolean isMatch(Node n) {
		if((n.getX()==this.getX()) && (n.getY()==this.getY())) {
			return true;
		}
		else {
			return false;
		}
	}

	

	
}
