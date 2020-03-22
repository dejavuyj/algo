package leetcode.algorithm.dynamicProgramming;

public class CountThePath {

	public int countThePath(char[][] board) {
		int m = board.length;
		int n = board[0].length;
		int[][] record = new int[n][m];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (i == n - 1 || j == m - 1) {
					record[i][j] = 1;
				} else if (board[i][j] == 's') {
					record[i][j] = 0;
				} else {
					record[i][j] = record[i][j + 1] + record[i + 1][j];
				}
			}
		}
		return record[0][0];
	}

	public static void main(String[] args) {
		CountThePath c = new CountThePath();
		char[][] board = new char[8][8];
		// stones
		board[1][2] = 's';
		board[1][6] = 's';
		board[2][4] = 's';
		board[3][0] = 's';
		board[3][2] = 's';
		board[3][5] = 's';
		board[4][2] = 's';
		board[5][3] = 's';
		board[5][4] = 's';
		board[5][6] = 's';
		board[6][1] = 's';
		board[6][5] = 's';
		int ans = c.countThePath(board);
		System.out.println(ans);
	}
}
