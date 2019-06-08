
public class EightQueen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//For testing purposes
		Queen [] initial = {new Queen(1,1), new Queen(1, 3), new Queen(2, 0), new Queen(2,2)}; 
		
	
		
		HillClimbing hillC = new HillClimbing();
		System.out.println("Initial state:");
		hillC.print(initial);
		System.out.println();
		hillC.runHillClimbingAlgorithm(initial);
	
	}
	
}
