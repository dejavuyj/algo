package leetcode.dataStructure.Array;

import java.util.ArrayList;
import java.util.List;

public class Code54_spiral_matrix {

	// if-else
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> ansList = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0) {
			return ansList;
		}
		int rowStart = 0;
		int colStart = 0;
		int rowEnd = matrix.length;
		int colEnd = matrix[0].length;
		int totalSize = rowEnd * colEnd;

		int count = 0;
		int curRow = 0;
		int curCol = 0;
		// left, right, up, down
		char direction = 'r';
		while (count < totalSize) {
			ansList.add(matrix[curRow][curCol]);
			switch (direction) {
			case 'r':
				if (curCol >= colEnd - 1) {
					direction = 'd';
					rowStart++;
					curRow++;
				} else {
					curCol++;
				}
				break;
			case 'd':
				if (curRow >= rowEnd - 1) {
					direction = 'l';
					colEnd--;
					curCol--;
				} else {
					curRow++;
				}
				break;
			case 'l':
				if (curCol <= colStart) {
					direction = 'u';
					rowEnd--;
					curRow--;
				} else {
					curCol--;
				}
				break;
			case 'u':
				if (curRow <= rowStart) {
					direction = 'r';
					colStart++;
					curCol++;
				} else {
					curRow--;
				}
				break;
			}
			count++;
		}

		return ansList;
	}

	// 状态机
	public List<Integer> spiralOrder2(int[][] matrix) {
		List<Integer> ansList = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0) {
			return ansList;
		}
		int rowStart = 0;
		int colStart = 0;
		int rowEnd = matrix.length - 1;
		int colEnd = matrix[0].length - 1;
		int totalSize = matrix.length * matrix[0].length;

		int count = 0;
		int curRow = 0;
		int curCol = 0;
		int d = 0; // 0:right 1:down 2:left 3:up
		int[] dc = { 1, 0, -1, 0 };
		int[] dr = { 0, 1, 0, -1 };

		while (count < totalSize) {
			ansList.add(matrix[curRow][curCol]);
			if (curRow == rowStart && d == 3) {
				colStart++;
				d = (d + 1) % 4;
			}
			if (curRow == rowEnd && d == 1) {
				colEnd--;
				d = (d + 1) % 4;
			}
			if (curCol == colEnd && d == 0) {
				rowStart++;
				d = (d + 1) % 4;
			}
			if (curCol == colStart && d == 2) {
				rowEnd--;
				d = (d + 1) % 4;
			}
			curCol += dc[d];
			curRow += dr[d];
			count++;
		}

		return ansList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Integer> spiralOrder3(int[][] matrix) {
		List ans = new ArrayList();
		if (matrix.length == 0)
			return ans;
		int R = matrix.length, C = matrix[0].length;
		// boolean[][] seen = new boolean[R][C];
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		int r = 0, c = 0, di = 0;
		for (int i = 0; i < R * C; i++) {
			ans.add(matrix[r][c]);
			// seen[r][c] = true;
			int cr = r + dr[di];
			int cc = c + dc[di];
			// if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]) {
			if (0 <= cr && cr < R && 0 <= cc && cc < C) {
				r = cr;
				c = cc;
			} else {
				di = (di + 1) % 4;
				r += dr[di];
				c += dc[di];
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		Code54_spiral_matrix c = new Code54_spiral_matrix();
		// int[][] M = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13,
		// 14, 15 }, { 16, 17, 18, 19, 20 },
		// { 21, 22, 23, 24, 25 } };
		int[][] M = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		List<Integer> ansList = c.spiralOrder2(M);
		for (int i : ansList) {
			System.out.print(i + ",");
		}
	}
}