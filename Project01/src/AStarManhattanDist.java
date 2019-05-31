
public class AStarManhattanDist extends AStar {

	public AStarManhattanDist(Node root, Node goal, int size, int side) {
		super(root, goal, size, side);
	}

	@Override
	public int heuristicFn(State s) {
		int manhattanValue = 0;
		int[] currentArray1d = s.getCurrBoard();
		int[][] currentArray2d = new int[side][side];

		int[] goalArray1d = goal.getCurrState().getCurrBoard();
		int[][] goalArray2d = new int[side][side];

		// converting array1d to a 2d array for the goal state
		for (int x = 0; x < side; x++) {
			for (int y = 0; y < side; y++) {
				goalArray2d[x][y] = goalArray1d[(x * side) + y];
			}
		}

		// converting array1d to a 2d array for the current state
		for (int x = 0; x < side; x++) {
			for (int y = 0; y < side; y++) {
				currentArray2d[x][y] = currentArray1d[(x * side) + y];
			}
		}

		// comparing and calculating heuristic value
		//
	
		for (int x = 0; x < side; x++) {
			for (int y = 0; y < side; y++) 
			{
				for (int z = 0; z < side; z++) {
					for (int j = 0; j < side; j++) {
						if (currentArray2d[x][y] == goalArray2d[z][j]) {
							manhattanValue = manhattanValue + (Math.abs(x - z) + Math.abs(y - j));
						}
					}
				}

			}
		}

		return manhattanValue;

	}

}
