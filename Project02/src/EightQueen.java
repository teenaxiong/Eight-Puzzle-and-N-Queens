
public class EightQueen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//For testing purposes
		Queen [] initial = {new Queen(0,1), new Queen(1, 3), new Queen(1, 0), new Queen(3,2)}; 
		
	
		
		HillClimbing hillC = new HillClimbing(initial);
		System.out.println("Initial state:");
		hillC.print(initial);
		System.out.println();
		hillC.runHillClimbingAlgorithm();
	
	}
	
}
