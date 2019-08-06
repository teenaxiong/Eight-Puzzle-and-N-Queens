
public class AStarMisplacedTiles extends AStar {
	
	public AStarMisplacedTiles(Node root, Node goal, int size, int side) {
		super(root, goal, size, side);
	}
 
	@Override
	public int heuristicFn(State s) {
		int misplaceValue = 0;
		
		for (int i = 0; i < size; i++) {
			int value = s.getCurrBoard()[i];

			int goalIdx = goal.getCurrState().findIndex(value); //get the goal index where curr value should be
			
			if (value != s.getHoleValue() && goalIdx != i) { // curr value is in wrong place
				misplaceValue ++; 
			}
		}
		return misplaceValue;

	}
	

}
