
public class Node implements Comparable<Node>{

	private Node parentNode;
	private State currState;
	private int costSoFar = 0;
	private int totalCost = 0;
	
	public Node(State state, Node parent, int costSoFar, int totalCost) {
		this.setCurrState(state);
		this.setParentNode(parent);
		this.setCostSoFar(costSoFar);
		this.setTotalCost(totalCost);
	}

	/**
	 * @return the parentNode
	 */
	public Node getParentNode() {
		return parentNode;
	}

	/**
	 * @param parentNode the parentNode to set
	 */
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	/**
	 * @return the currState
	 */
	public State getCurrState() {
		return currState;
	}

	/**
	 * @param currState the currState to set
	 */
	public void setCurrState(State currState) {
		this.currState = currState;
	}

	/**
	 * @return the costSoFar
	 */
	public int getCostSoFar() {
		return costSoFar;
	}

	/**
	 * @param costSoFar the costSoFar to set
	 */
	public void setCostSoFar(int costSoFar) {
		this.costSoFar = costSoFar;
	}

	/**
	 * @return the totalCost
	 */
	public int getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public boolean equals(Object obj) {
		Node n = (Node) obj;
		return this.getCurrState().equals(n.getCurrState());
	}

	@Override
	public int compareTo(Node node) {
		return this.getTotalCost() - node.getTotalCost();
	}
}
