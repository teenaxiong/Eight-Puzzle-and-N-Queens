import java.util.ArrayList;

public class HillClimbing {
	
	private int sideWalk = 1;
	private boolean randomRestart = false;
	private int restartCount = 0;
	private int stepCount = 0;
	private Node curr;
	

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
		
		curr.reset();
		curr.calculateHeuristicCost();
		
		int bestHeuristicVal = curr.getHeuristicValue();
		
		while(!isGoal(curr) && steps > 0) {
			
			stepCount++;
			
			ArrayList<Node> successors = curr.genSuccessors();
			Node bestSuccessor = successors.get(this.findSmallest(successors));

			if (bestSuccessor.getHeuristicValue() < bestHeuristicVal) {
				curr = bestSuccessor;
				curr.printBoard();
				bestHeuristicVal = bestSuccessor.getHeuristicValue();
			} else if (bestSuccessor.getHeuristicValue() == bestHeuristicVal) {
				curr.printBoard();
				steps--;
			}
			
			if (randomRestart && steps == 0 && !isGoal(curr)) {
				curr.reset();
				curr.calculateHeuristicCost();
				bestHeuristicVal = curr.getHeuristicValue();
				steps = sideWalk;
				this.restartCount++;
			}
			
		}
		
		return isGoal(curr);
				
	}
	
	public void print() {
		if (randomRestart) {
			
		}
	}

	private boolean isGoal(Node curr) {
		return curr.getHeuristicValue() == 0;
	}
	
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

}
