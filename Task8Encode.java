
public class Task8Encode {

	public static void encode(int sqrtN, int[][] hints, int[][][] map) {
		int[][][] tempMap = new int[map.length][map[0].length][map[0][0].length];
		int[] trueVars = new int[hints.length];
		int N = sqrtN * sqrtN;
		int[] trueVar = new int[1];
		int[] vars = new int[N];

		int nVars = N * N * N;
		SATSolver.init(nVars);

		// hints as true literals
		//(assuming hints is by order)
		for (int i = 0; i < trueVars.length; i = i + 1) {
			trueVar[0] = (Task7Map.varName(hints[i][0], hints[i][1], hints[i][2], N)) - 1;
			trueVars[i] = trueVar[0];
			SATSolver.addClause(trueVar);
		}
		//cells
		for (int i = 0; i < N; i = i + 1) {
			for (int j = 0; j < N; j = j + 1) {
				for (int k = 0; k < N; k = k + 1) {
					vars[k] = map[i][j][k];
				}
				SATSolver.addClauses(Task6Cnf.exactlyOne(vars));
			}
		}

		// Rows
		addTheClauses(sqrtN, trueVars, map);

		// columns
		tempMap = columns(map);
		addTheClauses(sqrtN, trueVars, tempMap);

		// blocks
		tempMap = blocks(map, sqrtN);
		addTheClauses(sqrtN, trueVars, tempMap);
	}

	public static void addTheClauses(int sqrtN, int[] trueVars, int[][][] map) {
		int N = sqrtN * sqrtN;
		int[] vars = new int[N];
		boolean isThereaHint = false;

		// making a clause with the same index at every cell
		// for all the rows of the matrix.
		// except if the row have a hint in it,
		// don't add that index clause.
		for (int k = 0; k < N; k = k + 1) {
			for (int i = 0; (i < N) & (!(isThereaHint)); i = i + 1) {
				for (int j = 0; (j < N) & (!(isThereaHint)); j = j + 1) {
					vars[j] = map[i][j][k];
				}
				SATSolver.addClauses(Task6Cnf.exactlyOne(vars));
				isThereaHint = false;
			}
		}
	}

	public static int[][][] columns(int[][][] map) {
		// copy from Taskarrays.java and changed to 3d array
		int[][][] tempMap = new int[map.length][map[0].length][map[0][0].length];
		for (int i = 0; i < tempMap.length; i = i + 1) {
			for (int j = 0; j < tempMap.length; j = j + 1) {
				tempMap[j][i] = map[i][j];
			}
		}
		return tempMap;
	}

	public static int[][][] blocks(int[][][] map, int sqrtN) {
		// copy from Taskarrays.java and changed to 3d array
		int[][][] tempMap = new int[map.length][map[0].length][map[0][0].length];
		int tempRow = 0;
		int tempCol = 0;
		int row = 0;
		int maxRow = sqrtN;
		int col = 0;
		int maxCol = sqrtN;

		// we'll run block by block and put it in the new matrix row by row
		for (int block = 1; block < (map.length + 1); block = block + 1) {
			while (row < maxRow) {
				while (col < maxCol) {
					tempMap[tempRow][tempCol] = map[row][col];
					tempCol = tempCol + 1;
					col = col + 1;
				}
				row = row + 1;
				col = col - sqrtN;
			}
			tempCol = 0;
			tempRow = tempRow + 1;

			// checking at the "block matrix" if to change row or column
			if (block % sqrtN != 0) {
				col = col + sqrtN;
				maxCol = maxCol + sqrtN;
				row = row - sqrtN;
			} else {
				maxRow = maxRow + sqrtN;
				col = 0;
				maxCol = sqrtN;
			}
		}
		return tempMap;
	}

	public static int[] falseByHints(int[] hints, int[][][] map, int N) {
		int numOfSureVars = hints.length * N;
		int[] output = new int[numOfSureVars];

		return output;
	}
}
