
public class AStarMisplacedTiles extends AStar {

	int rows = 3;
	int columns = 3;
	
	public AStarMisplacedTiles(Node root, Node goal) {
		super(root, goal);
	}

	@Override
	public int heuristicFn(State s) {
		int misplaceValue = 0;
		int[] currentArray1d = s.getCurrBoard();
		int[][] currentArray2d = new int[rows][columns];

		int[] goalArray1d = goal.getCurrState().getCurrBoard();
		int[][] goalArray2d = new int[rows][columns];

		// converting array1d to a 2d array for the goal state
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				goalArray2d[x][y] = goalArray1d[(x * columns) + y];
			}
		}

		// converting array1d to a 2d array for the current state
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				currentArray2d[x][y] = currentArray1d[(x * columns) + y];
			}
		}
		
		//comparing and calculating the state of current node to goal node
		//will give the total amount of misplace tiles
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				for (int z = 0; z < rows; z++) {
					for (int j = 0; j < columns; j++) {
						if (currentArray2d[x][y] == goalArray2d[z][j]) {
							if(Math.abs(x - z) + Math.abs(y - j) != 0) {
								misplaceValue++; 
		
								//found a misplace tile. increment z and j to break
								//out of inner two loops
								z = 3;
								j = 3; 
							}
						}
					}
				}

			}
		}
		
		return misplaceValue;

	}

}
