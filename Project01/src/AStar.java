import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
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
		Queue<Node> open = new LinkedList<Node>();
		open.add(root);
		
		while (!open.isEmpty()) {
			
			Node temp = open.poll();
			
			// perform Goal test
			if (!this.isGoal(temp)) { // not a goal
				
				nodeExpanded++;
				
				// array list to store successor nodes
				ArrayList<Node> nodeSuccessors = new ArrayList<Node>();
				
				// generate successors for temp node
				ArrayList<State> tempSuccessors = temp.getCurrState().genSuccessors();
				
				// loop through all successors of temp node
				for (State s : tempSuccessors) {
					Node addedNode = generateNode(s, temp);
					
					// add addedNode to list if it is not a repeated state
					if (!this.checkRepeatedState(addedNode)) {
						nodeSuccessors.add(addedNode);
					}
					
				}
				
				// find lowest cost among successors
				if (!nodeSuccessors.isEmpty()) { // list of successors is not empty
					
					// set lowest cost to the cost of first node in the list
					int lowestCost = nodeSuccessors.get(0).getTotalCost();
					
					for (Node n : nodeSuccessors) {
						if (n.getTotalCost() < lowestCost) {
							lowestCost = n.getTotalCost();
						}
					}
					
					// add nodes that has lowest cost to open queue
					for (Node n : nodeSuccessors) {
						if (n.getTotalCost() == lowestCost) {
							open.add(n);
						}
					}
				}
				
			
			} else {	// is a goal
				print(temp);
			}
			
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
		this.nodeGenerated++;
		return new Node(s, parent, costSoFar, totalCost);
	}
	
	private boolean isGoal(Node node) {
		return Arrays.equals(node.getCurrState().getCurrBoard(), 
							 this.goal.getCurrState().getCurrBoard());
	}
	
	private boolean checkRepeatedState(Node n) {
		State s = n.getCurrState();
		return hasRepeatedState(n.getParentNode(), s);
	}

	private boolean hasRepeatedState(Node node, State s) {
		if (node == null) {
			return false;
		}
		if (node.getCurrState().equals(s)) {
			return true;
		}
		return hasRepeatedState(node.getParentNode(), s);
	}

}
