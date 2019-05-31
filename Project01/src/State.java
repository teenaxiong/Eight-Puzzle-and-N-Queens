import java.util.ArrayList;
import java.util.Arrays;

public class State {

	private final static int HOLE = 0;
	
	private int[] currBoard;
	private int size;
	private int side;
	
	public State(int[] board, int size, int side) {
		this.currBoard = board;
		this.size = size;
		this.side = side;
	}
	
	public ArrayList<State> genSuccessors() {
		
		int holeIdx = findHole();
		
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
	
	private State swap(int c1, int c2) {
		int[] cpy = copyState(currBoard);
		int temp = cpy[c1];
		cpy[c1] = currBoard[c2];
		cpy[c2] = temp;
		return new State(cpy, size, side);
	}
	

	private int[] copyState(int[] state) {
		int[] ret = new int[size];
		System.arraycopy(state, 0, ret, 0, size);
		return ret;
	}
	
	
	private int findHole() {
		for (int i = 0; i < currBoard.length; i++)
			if (currBoard[i] == HOLE)
				return i;

		return -1;
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
