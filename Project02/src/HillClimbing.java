import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class HillClimbing {
	
	private int sideWalk = 1;
	private boolean randomRestart = false;

	private int restartCount = 0;
	private int stepCount = 0;
	private Node curr;
	private Queue<Node> queue;
	

	public HillClimbing(int n) {
		curr = new Node(n);
		queue = new LinkedList<>();
	}
	
	public HillClimbing(int n, int sideWalk, boolean randomRestart) {
		this.sideWalk = sideWalk;
		this.randomRestart = randomRestart;
		curr = new Node(n);
	}

	/**
	 * This method performs the hill climbing algorithm.
	 * @return isFound
	 */
	public boolean runAlgorithm() {
		
		int steps = sideWalk;
		
		curr.reset(); // generate random boards
		curr.calculateHeuristicCost(); // calculate heuristic cost
		this.queue.clear(); // clear out a queue
		
		// set curr heuristic as the best heuristic found so far
		int bestHeuristicVal = curr.getHeuristicValue();
		
		// check if goal is found or no more steps allowed
		while(!isGoal(curr) && steps > 0) {
			
			stepCount++;
			
			// calculate heuristic for all cells based on position of the queens
			ArrayList<Node> successors = curr.genSuccessors();
			
			// pick the smallest heuristic
			Node bestSuccessor = successors.get(this.findSmallest(successors));

			if (bestSuccessor.getHeuristicValue() < bestHeuristicVal) { // found a new successor
				queue.add(new Node(curr.getN(), curr.getBoard(), curr.getHeuristicValue()));
				curr = bestSuccessor;
				bestHeuristicVal = bestSuccessor.getHeuristicValue();
			} else { // can't beat current best successor
				steps--; // deduct allowed steps
			}
			
			// reset configurations if randomRestart is true
			if (randomRestart && steps == 0 && !isGoal(curr)) {
				curr.reset();
				curr.calculateHeuristicCost();
				bestHeuristicVal = curr.getHeuristicValue();
				steps = sideWalk;
				this.queue.clear();
				this.restartCount++;
			}
			
		}
		
		// add final node to queue if goal is found
		if (isGoal(curr)) {
			curr.printBoard();
			queue.add(new Node(curr.getN(), curr.getBoard(), curr.getHeuristicValue()));
		}
		
		return isGoal(curr);	
	}
	
	/**
	 * Test if curr state is a goal state.
	 * Goal state has heuristic of 0
	 * @param curr
	 * @return
	 */
	private boolean isGoal(Node curr) {
		return curr.getHeuristicValue() == 0;
	}
	
	/**
	 * Return the index of the cell that has the smallest
	 * heuristic value
	 * @param list
	 * @return
	 */
	private int findSmallest(ArrayList<Node> list) {
		int cost = list.get(0).getHeuristicValue();
		int idx = 0;
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).getHeuristicValue() < cost) {
				idx = i;
				cost = list.get(i).getHeuristicValue();
			}
		}
		return idx;
	}

	public int getRestartCount() {
		return restartCount;
	}

	public void setRestartCount(int restartCount) {
		this.restartCount = restartCount;
	}

	public int getStepCount() {
		return stepCount;
	}

	public void setStepCount(int stepCount) {
		this.stepCount = stepCount;
	}

	public void setRandomRestart(boolean randomRestart) {
		this.randomRestart = randomRestart;
	}

	public Queue<Node> getQueue() {
		return queue;
	}

	public void setQueue(Queue<Node> queue) {
		this.queue = queue;
	}
}
