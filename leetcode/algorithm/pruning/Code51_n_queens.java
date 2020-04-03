package leetcode.algorithm.pruning;

import java.util.ArrayList;
import java.util.List;

public class Code51_n_queens {

	int N;
	int[] queens;
	int[] rows;
	int[] hills;
	int[] dales;
	List<List<String>> result;

	private void placeQueen(int row, int col) {
		queens[row] = col;
		rows[col] = 1;
		hills[row + col] = 1;
		dales[row - col + N] = 1;
	}

	private void removeQueen(int row, int col) {
		queens[row] = 0;
		rows[col] = 0;
		hills[row + col] = 0;
		dales[row - col + N] = 0;
	}

	private boolean isValidPos(int row, int col) {
		int num = rows[col] + hills[row + col] + dales[row - col + N];
		return num == 0;
	}

	private void addResult() {
		List<String> l = new ArrayList<String>();
		for (int i = 0; i < N; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < queens[i]; j++) {
				sb.append(".");
			}
			sb.append("Q");
			for (int j = queens[i] + 1; j < N; j++) {
				sb.append(".");
			}
			l.add(sb.toString());
		}
		result.add(l);
	}

	private void backTrack(int row) {
		for (int col = 0; col < N; col++) {
			if (isValidPos(row, col)) {
				placeQueen(row, col);
				if (row == N - 1) {
					addResult();
				} else {
					backTrack(row + 1);
				}
				removeQueen(row, col);
			}
		}
	}

	public List<List<String>> solveNQueens(int n) {
		N = n;
		queens = new int[n];
		rows = new int[n];
		hills = new int[2 * n - 1];
		dales = new int[2 * n];
		result = new ArrayList<List<String>>();
		backTrack(0);
		return result;
	}

	public static void main(String[] args) {
		Code51_n_queens c = new Code51_n_queens();
		List<List<String>> ans = c.solveNQueens(8);
		int count = 0;
		for (List<String> l : ans) {
			count++;
			System.out.println(count + " ---start---");
			for (String s : l) {
				System.out.println(s);
			}
			System.out.println(count + " ---end---");
			System.out.println();
		}
	}
}
