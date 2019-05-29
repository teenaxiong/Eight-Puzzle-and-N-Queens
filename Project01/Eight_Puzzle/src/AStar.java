import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public abstract class AStar {

	int nodeGenerated = 0;
	int nodeExpanded = 0;
	Node root = null;
	Node goal = null;
	
	public AStar(Node root, Node goal) {
		this.root = root;
		this.goal = goal;
	}
	
	public void runAlgorithm() {
		Queue<Node> open = new LinkedList<Node>();
		open.add(root);
		
		while (!open.isEmpty()) {
			Node temp = open.poll();
			
			// perform Goal test
			if (!this.isGoal(temp)) { // not a goal
				
			} else {	// is a goal
				print();
			}
			
		}
	}
	
	private void print() {
		
	}
	
	public abstract int heuristicFn();
	
	
	private void generateNode() {
		int costSoFar = 0;
		int totalCost = costSoFar + this.heuristicFn();
		// Node node = new Node(this.nodeGenerated, costSoFar, totalCost);
		this.nodeGenerated++;
		
	}
	
	private boolean isGoal(Node node) {
		return Arrays.equals(node.getCurrState().getCurrBoard(), 
							 this.goal.getCurrState().getCurrBoard());
	}

}
