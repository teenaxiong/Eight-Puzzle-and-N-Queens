
public class AStarManhattanDist extends AStar {

	public AStarManhattanDist(Node root, Node goal) {
		super(root, goal);
	}

	@Override
	public int heuristicFn(State s) {
		return 0;

	}

}
