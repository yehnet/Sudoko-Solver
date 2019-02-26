import java.util.Arrays;

public class Task10Solve {

	public static int[][] solve(int sqrtN, int[][] hints) {
		int N =sqrtN * sqrtN;
		int[][][] map = new int[N][N][N];
		boolean[] assignment;
		int[][] board;

		//get the solution
		map = Task7Map.varsMap(N);
		Task8Encode.encode(sqrtN, hints, map);
		assignment = SATSolver.getSolution();
		
		//check the solution
		if (assignment == null) {
			board = null;
		} else {
			if ( assignment.length == 0) {
				throw new IllegalStateException("Error! time run out");
			}
			board = Task9Decode.mapToBoard(map, N, assignment);
			if (!(Task5Verify.isSolution(sqrtN, hints, board)) ) {
				throw new IllegalStateException("Error! the solution is illegal");
			}
		}
		return board;
	}
}
