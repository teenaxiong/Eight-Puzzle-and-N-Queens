
public class EightQueen {
	
	final static int HILL_CLIMBING_BASIC = 1;
	final static int HILL_CLIMBING_BASIC_SIDEWALK = 2;
	final static int HILL_CLIMBING_RESTART = 3;
	final static int HILL_CLIMBING_RESTART_SIDEWALK = 4;
	
	final static int SIDE_WALK_STEPS = 100;
	
	static int n;
	static int trials;
	static int type;
	static String title;
	static boolean restart = false;
	static int totalFailureSteps = 0;
	static int totalSuccessSteps = 0;
	static int successCount = 0;
	static int failureCount = 0;
	static int restartCount = 0;
	static HillClimbing hc = null;
	
	
	public static void main(String[] args) {
		
		try {
			if (args == null || args.length == 0) {
				throw new IllegalArgumentException("Wrong Input. Try again");
			} else {
				type = Integer.parseInt(args[0]);
				n = Integer.parseInt(args[1]);
				trials = Integer.parseInt(args[2]);
				restart = (type > 2) ? true : false;
				run();
				print();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	private static void run() {
		hc = new HillClimbing(n);

		for (int i = 0; i < trials; i++) {
			boolean result = false;
			
			if (type == HILL_CLIMBING_BASIC) {
				title = "Hill Climbing with Steepest Ascent";
				initAlg(1, false);
			} else if (type == HILL_CLIMBING_BASIC_SIDEWALK) {
				title = "Hill Climbing with Side Walk";
				initAlg(SIDE_WALK_STEPS, false);
			} else if (type == HILL_CLIMBING_RESTART) {
				title = "Hill Climbing with Random Restart, No Sidewalk";
				initAlg(1, true);
			} else if (type == HILL_CLIMBING_RESTART_SIDEWALK) {
				title = "Hill Climbing with Random Restart and Sidewalk";
				initAlg(SIDE_WALK_STEPS, true);
			}
			
			result = hc.runAlgorithm();
			
			if (result) {
				successCount++;
				totalSuccessSteps += hc.getStepCount();
			} else {
				failureCount++;
				totalFailureSteps += hc.getStepCount();
			}
			restartCount += hc.getRestartCount();
		}
		
	}
	
	private static void print() {
		System.out.println(title + "\n");
		
		if (restart) {
			printRestart();
		} else {
			printBasic();
		}

	}

	private static void printBasic() {

		System.out.println("Total: " + trials + 
				". Success: " + successCount +
				". Failure: " + failureCount);
		
		System.out.println("Average number of steps when success: " + totalSuccessSteps / trials);
		System.out.println("Average number of steps when failure: " + totalFailureSteps / trials);
	}
	
	private static void printRestart() {
		System.out.println("Average number of random restart: " + restartCount / trials);
		System.out.println("Average number of steps: " + totalSuccessSteps / trials);
	}
	
	private static void initAlg(int sideWalk, boolean restart) {
		hc.setStepCount(sideWalk);
		hc.setRandomRestart(restart);
	}
	
}
