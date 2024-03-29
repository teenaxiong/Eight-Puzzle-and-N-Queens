import java.util.ArrayList;
import java.util.Random;

public class Node {
	private int[] board;
	private int heuristicValue;
	private int n;
	
	public Node(int n) {
		this.n = n;
		board = new int[n];
	}
	
	public Node(int n, int[] board, int heuristicValue) {
		this.n = n;
		this.board = board;
		this.heuristicValue = heuristicValue;
	}
	
	/**
	 * Compute number of attacks on curr board
	 */
	public void calculateHeuristicCost() {
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				this.heuristicValue = getHeuristicValue() + (this.isUnderAttack(i, j) ? 1 : 0);
			}
		}
	}
	
	/**
	 * Generate new board
	 */
	public void reset() {
		this.genRandomBoard();
	}
	
	/**
	 * Generate potential successors 
	 * based on current state
	 * @return list of successors
	 */
	public ArrayList<Node> genSuccessors() {
		ArrayList<Node> successors = new ArrayList<Node>();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int idx = j * n + i;
				if (idx != board[i]) { 
					Node node = new Node(n);
					int[] b = this.copy(board, n);
					b[i] = idx;
					node.setBoard(b);
					node.calculateHeuristicCost();
					successors.add(node);
				}
			}
		}
	
		return successors;
		
	}
	
	/**
	 * Takes in a Queen and compare it with the current Queen's position
	 * to see if there is a conflict between each other
	 * @param enemy
	 * @return
	 */
	private boolean isUnderAttack(int curr, int enemy) {
		
		int currRow = this.getRow(board[curr]);
		int currCol = this.getColumn(board[curr]);
		
		int enemyRow = this.getRow(board[enemy]);
		int enemyCol = this.getColumn(board[enemy]);
		
		//see if there's another in the same row
		if(currRow == enemyRow) {
			return true; 
		}
		
		//see if there's another queen diagonal 
		if(Math.abs(currRow - enemyRow) == Math.abs(currCol - enemyCol)) {
			return true; 
		}
		
		//returns false if there's no queen in the same row column, or diagonal
		return false;
		
	}
	
	/**
	 * Generate a random board by randomly picking N number from 0 to n-1. This generated number will
	 * represent the index location of the queen
	 */
	private void genRandomBoard() {
		Random r = new Random();
		
		for (int i = 0; i < n; i++) {
			board[i] = r.nextInt(n - 1) * n + i;

		}
	}
	
	/**
	 * Clone a new array
	 * @param array
	 * @param n
	 * @return
	 */
	private int[] copy(int[] array, int n) {
		int[] ret = new int[n];
		System.arraycopy(array, 0, ret, 0, n);
		return ret;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	private int getRow(int i) {
		return i / n;
	}
	
	private int getColumn(int i) {
		return i % n;
	}

	public int getHeuristicValue() {
		return heuristicValue;
	}

	public void setHeuristicValue(int heuristicValues) {
		this.heuristicValue = heuristicValues;
	}

	public int[] getBoard() {
		return board;
	}

	public void setBoard(int[] board) {
		this.board = board;
	}
	
	/**
	 * Prints the board 
	 */
	public StringBuffer printBoard() {
		
		StringBuffer sb = new StringBuffer();
		
		int[] printBoard = new int[n*n];
		
		for (int i = 0; i < n; i++) {
			int idx = this.board[i];
			printBoard[idx] = 1;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int idx = i * n + j;
				if (printBoard[idx] == 1) { 
					sb.append(" Q ");
				} else {
					sb.append(" * ");
				}
			}
			sb.append("\n");
		}
		return sb;
	}
	
	
}
