import java.util.ArrayList;
import java.util.Arrays;

/**
 * State class contains position of numbers on 
 * the board.
 */
public class State {

	/** Hole's value */
	private final static int HOLE = 0;
	
	/** an array to store position of numbers */
	private int[] currBoard;
	
	private int size;
	private int side;
	
	public State(int[] board, int size, int side) {
		this.currBoard = board;
		this.size = size;
		this.side = side;
	}
	
	/**
	 * Generate potential successors 
	 * based on current state
	 * @return list of successors
	 */
	public ArrayList<State> genSuccessors() {
		
		int holeIdx = findIndex(HOLE);
		
		ArrayList<State> successors = new ArrayList<State>();
		
		// leftwise
		if (holeIdx % side != 0) {
			successors.add(this.swap(holeIdx, holeIdx - 1));
		}
		
		// rightwise
		if (holeIdx % side != side - 1) {
			successors.add(this.swap(holeIdx, holeIdx + 1));
		}
		
		// topwise
		if (holeIdx >= side) {
			successors.add(this.swap(holeIdx, holeIdx - side));
		}
		
		// bottomwise
		if (holeIdx <  side * (side - 1)) {
			successors.add(this.swap(holeIdx, holeIdx + side));
		}
		
		return successors;
	}
	
	/**
	 * Find the index of value on the board
	 * @param value
	 * @return index of value found
	 */
	public int findIndex(int value) {
		for (int i = 0; i < currBoard.length; i++)
			if (currBoard[i] == value)
				return i;

		return -1;
	}
	
	/**
	 * Print the board in 2D format
	 */
	public void printCurrBoard() {
		for (int i = 0; i < side; i++) {
			for (int j = 0; j < side; j++) {
				System.out.print(this.currBoard[i * side + j] + " ");
			}
			System.out.println();
		}
		System.out.println();
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
	
	public int getHoleValue() {
		return HOLE;
	}
	
	/**
	 * Swap two adjacent elements on the board
	 * @param c1
	 * @param c2
	 * @return new state
	 */
	private State swap(int c1, int c2) {
		int[] cpy = copyState(currBoard);
		int temp = cpy[c1];
		cpy[c1] = currBoard[c2];
		cpy[c2] = temp;
		return new State(cpy, size, side);
	}

	/**
	 * Clone a new state
	 * @param state
	 * @return new state
	 */
	private int[] copyState(int[] state) {
		int[] ret = new int[size];
		System.arraycopy(state, 0, ret, 0, size);
		return ret;
	}
	
	@Override
	public boolean equals(Object obj) {
		return Arrays.equals(this.getCurrBoard(), ((State) obj).getCurrBoard());
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}
}
