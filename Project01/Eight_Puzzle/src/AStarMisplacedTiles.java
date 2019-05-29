
public class AStarMisplacedTiles extends AStar {

	public AStarMisplacedTiles(Node root, Node goal) {
		super(root, goal);
	}

	@Override
	public int heuristicFn(State s) {
		return 0;

	}

}
