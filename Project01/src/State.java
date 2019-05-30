import java.util.ArrayList;
import java.util.Arrays;

public class State {

	private final static int TOTAL_CELLS = 9;
	private final static int PUZZLE_SIDE = 3;
	private final static int HOLE = 0;
	
	private int[] currBoard;
	
	public State(int[] board) {
		this.currBoard = board;
	}
	
	public ArrayList<State> genSuccessors() {
		
		int holeIdx = findHole();
		
		ArrayList<State> successors = new ArrayList<State>();
		
		// leftwise
		if (holeIdx % PUZZLE_SIDE != 0) {
			successors.add(this.swap(holeIdx, holeIdx - 1));
		}
		
		// rightwise
		if (holeIdx % PUZZLE_SIDE != PUZZLE_SIDE - 1) {
			successors.add(this.swap(holeIdx, holeIdx + 1));
		}
		
		// topwise
		if (holeIdx >= PUZZLE_SIDE) {
			successors.add(this.swap(holeIdx, holeIdx - PUZZLE_SIDE));
		}
		
		// bottomwise
		if (holeIdx <  PUZZLE_SIDE * (PUZZLE_SIDE - 1)) {
			successors.add(this.swap(holeIdx, holeIdx + PUZZLE_SIDE));
		}
		
		return successors;
	}
	
	public void printCurrBoard() {
		for (int i = 0; i < PUZZLE_SIDE; i++) {
			for (int j = 0; j < PUZZLE_SIDE; j++) {
				System.out.print(this.currBoard[j * PUZZLE_SIDE] + i);
			}
		}
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
		int[] ret = new int[TOTAL_CELLS];
		System.arraycopy(state, 0, ret, 0, TOTAL_CELLS);
		return ret;
	}
	
	
	private int findHole() {
		return Arrays.asList(currBoard).indexOf(HOLE);
	}
}
