import java.util.ArrayList;

public class State {

	private final static int PUZZLE_SIZE = 9;
	private final static int STEP_COST = 1;
	
	private int[] currBoard;
	
	public State(int[] board) {
		this.currBoard = board;
	}
	
	public ArrayList<State> genSuccessors() {
		return null;
	}
	
	public void printCurrBoard() {
		
	}
	
	/**
	 * @return the currState
	 */
	public int[] getCurrBoard() {
		return currBoard;
	}

	/**
	 * @param currState the currState to set
	 */
	public void setCurrState(int[] currState) {
		this.currBoard = currState;
	}
	
	private State swap(int c1, int c2) {
		int[] cpy = copyState(currBoard);
		int temp = cpy[c1];
		cpy[c1] = currBoard[c2];
		cpy[c2] = temp;
		return new State(cpy);
	}
	

	private int[] copyState(int[] state) {
		int[] ret = new int[PUZZLE_SIZE];
		System.arraycopy(state, 0, ret, 0, PUZZLE_SIZE);
		return ret;
	}
	
	
	private int findHole() {
		return -1;
	}
}
