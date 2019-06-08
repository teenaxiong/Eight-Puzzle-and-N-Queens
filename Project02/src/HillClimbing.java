
public class HillClimbing {

	Queen[] currentQueen = null;
	private int currentHeuristic = 0;
	private int tempHeuristic = 0;
	private int stepCounts = 0; 
	private int stepForBestCounts = 0; 

	public HillClimbing() {
		super();
	}

	/*
	 * This method performs the hill climbing alogorithm.
	 */
	public void runHillClimbingAlgorithm(Queen[] currentQueen) {
		this.currentQueen = currentQueen;

		Queen[] bestQueen = new Queen[currentQueen.length];
		Queen[] tempQueen = new Queen[currentQueen.length];

		// copy the initial board into the tempQueen and bestQueen board.
		for (int x = 0; x < currentQueen.length; x++) {
			bestQueen[x] = new Queen(currentQueen[x].getRow(), currentQueen[x].getColumn());
			tempQueen[x] = new Queen(currentQueen[x].getRow(), currentQueen[x].getColumn());
		}

		currentHeuristic = calculateHeuristic(currentQueen);
		int bestHeuristic = calculateHeuristic(currentQueen);

		tempHeuristic = calculateHeuristic(tempQueen);
		
		while (currentHeuristic != 0) {
			for (int x = 0; x < currentQueen.length; x++) {
				// this loop enable you to move the Queen down the row
				for (int y = 0; y < currentQueen.length; y++) {
					tempHeuristic = calculateHeuristic(tempQueen);
					stepCounts++; 
					System.out.println();
					print(tempQueen); 
					System.out.println("Steps: " + stepCounts);	
					System.out.println("Heuristic: " + tempHeuristic);
					System.out.println("-----------------------------");
		
					// if the new heuristic is lower then the current one,
					// that means that is the best board. we are closer to our goal
					if (tempHeuristic < bestHeuristic) {
						for (int z = 0; z < currentQueen.length; z++) {
							bestQueen[z] = new Queen(tempQueen[z].getRow(), tempQueen[z].getColumn());
							
						}
						stepForBestCounts++; 
						bestHeuristic = tempHeuristic;
					}

					// counting how many times we are iterating through the rows.
					// moves the queen down one row at a time (unless at the very end, then moves
					// back to 0)
					tempQueen[x].moveQueen(currentQueen.length);
					
				}
			}
			
			/*
			 * If bestHeuristic is 0, we found the goal.
			 * If bestHeuristic of neighbor queen is less then the current queen,
			 * we do another hill climbing search on the neighbor queen. 
			 */
			if(bestHeuristic == 0) {
				System.out.println();
				System.out.println("Goal Reached");
				print(bestQueen); 
				System.out.println("Steps to reach goal: " + stepForBestCounts);
				currentHeuristic = bestHeuristic; 
			}else if(currentHeuristic > bestHeuristic) {
				runHillClimbingAlgorithm(bestQueen); 				
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
