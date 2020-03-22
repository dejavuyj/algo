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
		 * row:��ǰ�Żʺ���к�
		 * hills:���Խ���ռ����� [1 = ��ռ�ݣ�0 = δ��ռ��] 
		 * next_row:��һ�б�ռ�ݵ���� [1 = ��ռ�ݣ�0 = δ��ռ��] 
		 * dales:�ζԽ���ռ����� [1 = ��ռ�ݣ�0 = δ��ռ��] 
		 * count:���п��н�ĸ���
		 */
		int columns = (1 << n) - 1;

		if (row >= n) {
			count++;
		} else {
			// ��ǰ�п��õ���,[1 = δ��ռ�ݣ�0 = ��ռ��]
			int free_columns = columns & ~(hills | next_row | dales);
			while (free_columns != 0) {
				// free_colums�ĵ�һ��Ϊ'1'��λ,�ڴ��з�ֹ�ʺ�
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
