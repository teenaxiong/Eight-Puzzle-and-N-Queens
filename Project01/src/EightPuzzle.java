import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EightPuzzle {

	private final static int MISPLACED_TILES = 0;
	private final static int MANHATTAN_DISTANCE = 1;
	private final static int SIZE = 9;
	private final static int SIDE = 3;
	
	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		try {
			System.out.println("Enter initial state: ");
			line = br.readLine();
			int[] initArray = convertIntArray(line);
			
			System.out.println("Enter goal state: ");
			line = br.readLine();
			int[] goalArray = convertIntArray(line);
			
			System.out.println("Enter 0 for Misplaced Tiles Heuristic, "
					+ "1 for Manhattan Distance Heuristic: ");
			line = br.readLine();
			int heuristic = Integer.parseInt(line);
			
			Node initNode = constructNode(initArray);
			Node goalNode = constructNode(goalArray);
			
			if (heuristic == MANHATTAN_DISTANCE) {
				AStar alg = new AStarManhattanDist(initNode, goalNode, SIZE, SIDE);
				alg.runAlgorithm();
			} else if (heuristic == MISPLACED_TILES) {
				AStar alg = new AStarMisplacedTiles(initNode, goalNode, SIZE, SIDE);
				alg.runAlgorithm();
			}
			
		} catch (IOException e) {
			System.out.println("Invalid Input. Exit program");
		}

	}


	private static Node constructNode(int[] array) {
		State s = new State(array, SIZE, SIDE);
		return new Node(s, null, 0, 0);
	}


	private static int[] convertIntArray(String line) {
		
		String[] inputArray = line.trim().split("\\s+");
		int[] intArray = new int[inputArray.length];
		
		for (int i = 0; i < inputArray.length; i++) {
			intArray[i] = Integer.parseInt(inputArray[i]);
		}
		return intArray;
		
	}

}
