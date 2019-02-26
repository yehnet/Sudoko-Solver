
public class Task5Verify {

	public static boolean isSolution(int sqrtN, int[][] hints, int[][] board) {
		boolean verify = true;
		int N = sqrtN * sqrtN;
		int[] hint ;

		// checking if board is legal
		if (!(TasksArrays.isMatrixBetween(board, N, 1, N)))
			throw new RuntimeException(" The matrix is Illegal ");

		// checking if all the values are different
		for (int a = 0; a < board.length; a = a + 1) {
			if (!(TasksArrays.isAllDiff(board[a])))
				verify = false;
		}

		// confirm the hints with the board
		for (int i = 0; (i < hints.length) & (verify); i = i + 1) {
			hint = hints[i];
			if (!(hint[2] == board[hint[0]][hint[1]]))
				verify = false;
		}
		return verify;
	}

}
