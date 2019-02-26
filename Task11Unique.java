
public class Task11Unique {

	public static int[][] solveUnique(int sqrtN, int[][] hints) {
		int N = sqrtN * sqrtN;
		int[][][] map = new int[N][N][N];
		int numOfVars = N * N * N;
		int[][] board ;
		int[][] tempBoard ;
		boolean[] assignment;
		int[] tempVars = new int[numOfVars];
		int[] vars;
		int k = 0;

		//get one solution
		map = Task7Map.varsMap(N);
		Task8Encode.encode(sqrtN, hints, map);
		assignment = SATSolver.getSolution();
		
		//take that solution and force one true to false.
		tempBoard = Task9Decode.mapToBoard(map, N, assignment);
		for (int i = 1; i < (numOfVars) + 1; i = i + 1) {
			if (assignment[i] == true) {
				tempVars[k] = i * -1;
				k = k + 1;
			}
		}
		
		//put the extra clause in array
		vars = new int[k];
		for (int i = 0; i < vars.length; i = i + 1) {
			vars[i] = tempVars[i];
		}
		SATSolver.init(numOfVars);
		
		//check if there's a second solution with the extra clause
		SATSolver.addClause(vars);
		Task8Encode.encode(sqrtN, hints, map);
		assignment = SATSolver.getSolution();
		if (assignment == null) {//there's only one soultion
			board = tempBoard;
		} else {
			if ( assignment.length == 0) {
				throw new IllegalStateException("Error! time run out");
			}
			else {//there's another solution
				board = null;
			}
		}

		return board;
	}

}
