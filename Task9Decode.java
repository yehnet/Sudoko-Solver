
public class Task9Decode {

	public static int cellValue(int[][][] map, int i, int j, boolean[] assignment) {
		int cellVal;
		int[] indexAndVal=new int[3];
		int[] vars = new int[map.length];
		boolean foundVal = false;
		
	    //insert all the cells value to an array
		for (int k = 0; k < map.length; k = k + 1) {
			vars[k] = map[i][j][k];
		}

		//
		for (int k = 0; (k < vars.length)&(!(foundVal)); k = k + 1) {
			if ( assignment[vars[k]] ) {
				indexAndVal = Task7Map.nameToIndex(vars[k], map.length);
				indexAndVal[2]=indexAndVal[2]+1;
				foundVal = true;
			}
		}
		//throw out when there's no true value for the cell vars
		if(indexAndVal[2] == 0)
			throw new IllegalStateException("Error! there's no good answer");
		
		//plus one to change from index to val
		cellVal = indexAndVal[2];
		return cellVal;
	}

	public static int[][] mapToBoard(int[][][] map, int n, boolean[] assignment) {
		int[][] board = new int[n][n];
		
		for (int i = 0; i < board.length; i = i + 1) {
			for (int j = 0; j < board.length; j = j + 1) {
				board[i][j] = cellValue(map,i,j,assignment);
			}
		}
		
		return board;
	}
}
