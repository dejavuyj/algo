package leetcode.algorithm.pruning;

public class Code52_n_queens_ii {
	// public boolean is_not_under_attack(int row, int col, int n, int[] rows,
	// int[] hills, int[] dales) {
	// int res = rows[col] + hills[row - col + 2 * n] + dales[row + col];
	// return (res == 0) ? true : false;
	// }
	//
	// public int backtrack(int row, int count, int n, int[] rows, int[] hills,
	// int[] dales) {
	// for (int col = 0; col < n; col++) {
	// if (is_not_under_attack(row, col, n, rows, hills, dales)) {
	// // place_queen
	// rows[col] = 1;
	// hills[row - col + 2 * n] = 1; // "hill" diagonals
	// dales[row + col] = 1; // "dale" diagonals
	//
	// // if n queens are already placed
	// if (row + 1 == n)
	// count++;
	// // if not proceed to place the rest
	// else
	// count = backtrack(row + 1, count, n, rows, hills, dales);
	//
	// // remove queen
	// rows[col] = 0;
	// hills[row - col + 2 * n] = 0;
	// dales[row + col] = 0;
	// }
	// }
	// return count;
	// }
	//
	// public int totalNQueens(int n) {
	// int rows[] = new int[n];
	// // "hill" diagonals
	// int hills[] = new int[4 * n - 1];
	// // "dale" diagonals
	// int dales[] = new int[2 * n - 1];
	//
	// return backtrack(0, 0, n, rows, hills, dales);
	// }

	public int backtrack(int row, int hills, int next_row, int dales, int count, int n) {
		/**
		 * row:当前放皇后的行号
		 * hills:主对角线占据情况 [1 = 被占据，0 = 未被占据] 
		 * next_row:下一行被占据的情况 [1 = 被占据，0 = 未被占据] 
		 * dales:次对角线占据情况 [1 = 被占据，0 = 未被占据] 
		 * count:所有可行解的个数
		 */
		int columns = (1 << n) - 1;

		if (row >= n) {
			count++;
		} else {
			// 当前行可用的列,[1 = 未被占据，0 = 被占据]
			int free_columns = columns & ~(hills | next_row | dales);
			while (free_columns != 0) {
				// free_colums的第一个为'1'的位,在此列防止皇后
				int curr_column = -free_columns & free_columns;

				//free_columns ^= curr_column;
				free_columns = free_columns & (free_columns-1);

				count = backtrack(row + 1,
						(hills | curr_column) << 1,
						next_row | curr_column,
						(dales | curr_column) >> 1,
						count, n);
			}
		}

		return count;
	}

	public int totalNQueens(int n) {
		return backtrack(0, 0, 0, 0, 0, n);
	}

	public static void main(String[] args) {
		Code52_n_queens_ii c = new Code52_n_queens_ii();
		int ans = c.totalNQueens(8);
		System.out.println(ans);
	}
}
