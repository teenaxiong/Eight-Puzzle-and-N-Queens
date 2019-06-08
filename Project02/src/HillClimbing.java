
public class HillClimbing {

	Queen[] initial = null;
	private int heuristic = 0;
	private int tempHeuristic = 0;
	private int stepCounts = 0; 
	private int stepForBestCounts = 0; 

	public HillClimbing(Queen[] initial) {
		super();
		this.initial = initial;
	}

	/*
	 * This method performs the hill climbing alogorithm.
	 */
	public void runHillClimbingAlgorithm() {

		Queen[] bestQueen = new Queen[initial.length];
		Queen[] tempQueen = new Queen[initial.length];

		// copy the initial board into the tempQueen and bestQueen board.
		for (int x = 0; x < initial.length; x++) {
			bestQueen[x] = new Queen(initial[x].getRow(), initial[x].getColumn());
			tempQueen[x] = new Queen(initial[x].getRow(), initial[x].getColumn());
			;
		}

		heuristic = calculateHeuristic(initial);

		while (heuristic != 0) {
			for (int x = 0; x < initial.length; x++) {
				// this loop enable you to move the Queen down the row
				for (int y = 0; y < initial.length; y++) {
					tempHeuristic = calculateHeuristic(tempQueen);
					System.out.println("Heuristic: " + tempHeuristic);
					stepCounts++; 
					System.out.println();
					print(tempQueen); 
					System.out.println("Steps: " + stepCounts);
					System.out.println("-----------------------------");

					// if the new heuristic is lower then the current one,
					// that means that is the best board. we are closer to our goal
					if (tempHeuristic < heuristic) {
						for (int z = 0; z < initial.length; z++) {
							bestQueen[z] = new Queen(tempQueen[z].getRow(), tempQueen[z].getColumn());
							stepForBestCounts++; 
						}
						heuristic = tempHeuristic;
					}

					// counting how many times we are iterating through the rows.
					// moves the queen down one row at a time (unless at the very end, then moves
					// back to 0)
					tempQueen[x].moveQueen(initial.length);
					
				}
			}
			
			//Have completed moving the queens all around the board;
			//Either succeed or fail to find goal; 
			if(heuristic != 0) {
				System.out.println("Fail to succeed");
				System.out.println("Steps to failure: " + stepCounts);
				heuristic = 0; //to get out of while loop
			}else {
				if(heuristic==0) {
					System.out.println();
					System.out.println("Goal Reached");
					print(bestQueen); 
					System.out.println("Steps to reach goal: " + stepForBestCounts);
				}
			}
		}		
	}

	/*
	 * Find the heuristic value for the current board
	 */
	private int calculateHeuristic(Queen[] initial) {
		int calHeuristicVal = 0;
		for (int x = 0; x < initial.length; x++) {
			for (int j = x + 1; j < initial.length; j++) {
				// calls the pathCross function, were it determines if there is another queen in
				// the
				// same row, column, or diagonal. If there is, then heuristic value is
				// incremented.
				if (initial[x].pathCross(initial[j])) {
					calHeuristicVal++;
				}
			}
		}
		return calHeuristicVal;
	}

	/*
	 * This will display the board to the user
	 */
	public void print(Queen[] queen) {

		// Create a string 2-D array
		String[][] board = new String[queen.length][queen.length];

		// Iterating through the queen array (which holds all the Queen)
		// getting each Queen's row and column, and assigning the row/column to
		// board[row][column] and designated it a "Q" (Representing the location of
		// Queen).
		for (int x = 0; x < queen.length; x++) {
			board[queen[x].getRow()][queen[x].getColumn()] = "Q";
		}

		
		// Iterating through the board, and displaying it to user
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == null) {
					System.out.print(" * ");
				} else
					System.out.print(" " + board[i][j] + " ");
			}
			System.out.println();
		}
		
	}

}
