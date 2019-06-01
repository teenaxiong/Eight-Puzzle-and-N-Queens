import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public abstract class AStar {

	private final static int STEP_COST = 1;
	
	int nodeGenerated = 0;
	int nodeExpanded = 0;
	int size = 0;
	int side = 0;
	Stack<Node> path;
	Node root = null;
	Node goal = null;
	
	public AStar(Node root, Node goal, int size, int side) {
		this.root = root;
		this.goal = goal;
		this.size = size;
		this.side = side;
		path = new Stack<Node>();
	}
	
	public void runAlgorithm() {
		
		// open list
		List<Node> open = new ArrayList<Node>();
		
		// close list
		List<Node> closed = new ArrayList<Node>();
		
		// add first node to open list
		open.add(root);
		
		Node currNode = null;
		
		while (!open.isEmpty()) {
			
			// return node that has smallest cost in open list
			currNode = open.get(0);
			
			// perform Goal test
			if (!this.isGoal(currNode)) { // not a goal
				
				// generate successors for curr node
				ArrayList<State> successors = currNode.getCurrState().genSuccessors();
				
				// loop through all successors of curr node
				for (State s : successors) {
					Node endNode = generateNode(s, currNode);
					
					// check if endNode in closed list
					if (closed.contains(endNode)) {
						int endNodeRecordIdx = closed.indexOf(endNode);
						Node endNodeRecord = closed.get(endNodeRecordIdx);
						
						// check if found shorter route
						if (endNodeRecord.getTotalCost() > endNode.getTotalCost()) {
							closed.remove(endNodeRecordIdx);
						}
						
					} else if (!open.contains(endNode)) {
						
						// add endNode to open list
						open.add(endNode);
						
						// sort open list to place lowest cost node in front
						Collections.sort(open);
						
						// increment number of generated nodes
						nodeGenerated++;
					}
					
				}
				
				// remove curr node from open list
				open.remove(0);
				
				// add curr to closed list
				closed.add(currNode);
				
				// increment number of expanded nodes
				nodeExpanded++;
			
			} else {	// is a goal
				break;
			}
			
		}
		
		if (!isGoal(currNode)) { // empty open list and found no goal
			System.out.println("Cannot find any path to goal state");
		} else {
			print(currNode);
		}
	}
	
	private void print(Node temp) {
		// push final node to list
		path.push(temp);
		
		// push parent node to the list
		addToPath(temp.getParentNode());
		
		// print path
		System.out.println("---------------");
		System.out.println();
		int size = path.size();
		for (int i = 0; i < size; i++) {
			Node n = path.pop();
			n.getCurrState().printCurrBoard();
		}
		
		System.out.println("---------------");
		System.out.println("Number of nodes generated: " + nodeGenerated);
		System.out.println("Number of nodes expanded: " + nodeExpanded);
		
	}
	
	private void addToPath(Node temp) {
		if (temp != null) {
			path.push(temp);
			addToPath(temp.getParentNode());
		}
	}
	
	
	public abstract int heuristicFn(State s);
	
	
	private Node generateNode(State s, Node parent) {
		int costSoFar = parent.getCostSoFar() + STEP_COST;
		int totalCost = costSoFar + heuristicFn(s);
		return new Node(s, parent, costSoFar, totalCost);
	}
	
	private boolean isGoal(Node node) {
		return Arrays.equals(node.getCurrState().getCurrBoard(), 
							 this.goal.getCurrState().getCurrBoard());
	}

}
