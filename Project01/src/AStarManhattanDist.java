
public class AStarManhattanDist extends AStar {

	public AStarManhattanDist(Node root, Node goal, int size, int side) {
		super(root, goal, size, side);
	}

	@Override
	public int heuristicFn(State s) {
		int manhattanValue = 0;
		
		for (int i = 0; i < size; i++) {
			int value = s.getCurrBoard()[i];
			int goalIdx = goal.getCurrState().findIndex(value);
			
			if (value != s.getHoleValue() && goalIdx != i) { // curr value is in wrong place
				// actual row and column
				int actualRow = getRow(i);
				int actualCol = getColumn(i);
				
				// goal row and column
				int goalRow = getRow(goalIdx);
				int goalCol = getColumn(goalIdx);
				
				manhattanValue += (Math.abs(goalRow - actualRow) + Math.abs(goalCol - actualCol));
			}
			
		}

		return manhattanValue;

	}
	
	private int getRow(int i) {
		return i / side;
	}
	
	private int getColumn(int i) {
		return i % side;
	}

}
