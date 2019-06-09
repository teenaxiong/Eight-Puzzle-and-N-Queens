
public class EightQueen {
	
	final static int HILL_CLIMBING_BASIC = 1;
	final static int HILL_CLIMBING_BASIC_SIDEWALK = 2;
	final static int HILL_CLIMBING_RESTART = 3;
	final static int HILL_CLIMBING_RESTART_SIDEWALK = 4;
	
	final static int SIDE_WALK_STEPS = 100;
	
	static int n;
	static int trials;
	static int type;
	static boolean restart = false;
	static int totalFailureSteps = 0;
	static int totalSuccessSteps = 0;
	static int successCount = 0;
	static int failureCount = 0;
	static int restartCount = 0;
	
	
	public static void main(String[] args) {
		
		try {
			if (args == null || args.length == 0) {
				throw new IllegalArgumentException("Wrong Input. Try again");
			} else {
				type = Integer.parseInt(args[0]);
				n = Integer.parseInt(args[1]);
				trials = Integer.parseInt(args[2]);
				run();
				print();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	private static void run() {
		boolean result = false;
		HillClimbing hc = null;
		
		for (int i = 0; i < trials; i++) {
			if (type == HILL_CLIMBING_BASIC) {
				hc = new HillClimbing(n, 1, false);
			} else if (type == HILL_CLIMBING_BASIC_SIDEWALK) {
				hc = new HillClimbing(n, SIDE_WALK_STEPS, false);
			} else if (type == HILL_CLIMBING_RESTART) {
				hc = new HillClimbing(n, SIDE_WALK_STEPS, true);
				restart = true;
			} else if (type == HILL_CLIMBING_RESTART_SIDEWALK) {
				hc = new HillClimbing(n, SIDE_WALK_STEPS, true);
				restart = true;
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
		
		System.out.println("Average number of steps when success: " + totalSuccessSteps);
		System.out.println("Average number of steps when failure: " + totalFailureSteps);
	}
	
	private static void printRestart() {
		System.out.println("Average number of random restart: " + restartCount);
		System.out.println("Average number of steps: " + totalSuccessSteps);
	}
	
}
