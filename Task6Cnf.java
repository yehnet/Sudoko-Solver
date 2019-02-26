
public class Task6Cnf {

	public static int[][] atLeastOne(int[] vars) {
		int[][] output = new int[1][vars.length];

		for (int i = 0; i < vars.length; i = i + 1) {
			output[0][i] = vars[i];
		}
		return output;
	}

	public static int[][] atMostOne(int[] vars) {
		int numOfOpts = 0;
		int num1 = 0;
		int num2 = 0;
		int[][] output;

		// count the number of options of the clauses
		// every clause will be a pair of variables
		while (num1 < vars.length) {
			num2 = num1 + 1;
			while (num2 < vars.length) {
				numOfOpts = numOfOpts + 1;
				num2 = num2 + 1;
			}
			num1 = num1 + 1;
		}

		output = new int[numOfOpts][2];
		num1 = 0;
		num2 = 1;

		// enter all the clauses into the new array
		for (int h = 0; h < numOfOpts; h = h + 1) {
			vars[num1] = vars[num1] * -1;
			vars[num2] = vars[num2] * -1;
			output[h][0] = vars[num1];
			output[h][1] = vars[num2];
			vars[num1] = vars[num1] * -1;
			vars[num2] = vars[num2] * -1;
			if (num2 < vars.length - 1) {
				num2 = num2 + 1;
			} else {
				num1 = num1 + 1;
				num2 = num1 + 1;
			}
		}
		return output;
	}

	public static int[][] exactlyOne(int[] vars) {
		int[][] cnf;
		int[][] temp;
		int[][] temp2;

		temp = atMostOne(vars);
		cnf = new int[temp.length + 1][];

		for (int i = 0; i < temp.length; i++) {
			cnf[i] = new int[2];
			for (int j = 0; j < 2; j++) {
				cnf[i][j] = temp[i][j];
			}
		}
		temp2 = atLeastOne(vars);
		cnf[((cnf.length) - 1)] = new int[temp2[0].length];

		for (int i = 0; i < temp2[0].length; i++) {
			cnf[((cnf.length) - 1)][i] = (temp2[0][i]);
		}
		return cnf;
	}
}
