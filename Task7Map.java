
public class Task7Map {

	public static int varName(int i, int j, int k, int n) {
		int output;
		output = (n * n * i) + (n * j) + k + 1;
		return output;
	}

	public static int[] nameToIndex(int x, int n) {
		int[] output = new int[3];
		int N = n * n;

		// there's N variables per row
		while (x > N) {
			x = x - N;
			output[0] = output[0] + 1;
		}
		// there's n variables per column
		while (x > n) {
			x = x - n;
			output[1] = output[1] + 1;
		}
		// minus 1 to fix it to index
		output[2] = x - 1;
		return output;
	}

	public static int[][][] varsMap(int n) {
		int variable = 1;
		int[][][] output = new int[n][n][n];

		// n*n*n
		for (int i = 0; i < n; i = i + 1) {
			for (int j = 0; j < n; j = j + 1) {
				for (int k = 0; k < n; k = k + 1) {
					output[i][j][k] = variable;
					variable = variable + 1;
				}
			}
		}
		return output;
	}
}
