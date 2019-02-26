
public class TasksArrays {
	public static boolean isAllDiff(int[] array) {
		boolean isDiff = true;
		int value;

		// comparing values from index 0 to the end one by one
		for (int i = 0; i < (array.length - 1) & isDiff; i = i + 1) {
			value = array[i];
			for (int compareTo = i + 1; compareTo < array.length & isDiff; compareTo = compareTo + 1) {
				if (value == array[compareTo])
					isDiff = false;
			}
		}
		return isDiff;
	}

	public static boolean isMatrixBetween(int[][] matrix, int n, int min, int max) {
		boolean isMatrixOk = true;

		// checking the first dimension that its not null or different from n
		if (matrix == null || matrix.length != n)
			isMatrixOk = false;

		for (int i = 0; i < n & isMatrixOk; i = i + 1) {
			// checking the second dimension that its not null or different from n
			if (matrix[i] == null || matrix[i].length != n)
				isMatrixOk = false;

			// checking the values that they're between the min and max
			for (int k = 0; k < n & isMatrixOk; k = k + 1) {
				if (matrix[i][k] < min | matrix[i][k] > max)
					isMatrixOk = false;
			}
		}
		return isMatrixOk;
	}

	public static int[][] columns(int[][] matrix) {
		int[][] tempMatrix = new int[matrix.length][matrix[0].length];

		// turn the rows to columns and the opposite
		for (int i = 0; i < matrix.length; i = i + 1) {
			for (int k = 0; k < matrix[i].length; k = k + 1) {
				tempMatrix[k][i] = matrix[i][k];
			}
		}
		return tempMatrix;
	}

	public static int[][] blocks(int[][] matrix, int sqrtN) {
		int[][] tempMatrix = new int[matrix.length][matrix[0].length];
		int tempRow = 0;
		int tempCol = 0;
		int row = 0;
		int maxRow = sqrtN;
		int col = 0;
		int maxCol = sqrtN;

		// we'll run block by block and put it in the new matrix row by row
		for (int block = 1; block < (matrix.length + 1); block = block + 1) {
			while (row < maxRow) {
				while (col < maxCol) {
					tempMatrix[tempRow][tempCol] = matrix[row][col];
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
		return tempMatrix;
	}
}
