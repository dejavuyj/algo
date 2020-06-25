package leetcode.algorithm.dynamicProgramming;

public class Code64_minimum_path_sum {

	public int minPathSum(int[][] grid) {
		int col = grid.length;
		int row = grid[0].length;
		int[][] dp = new int[col][row];
		dp[0][0] = grid[0][0];
		for (int i = 1; i < row; i++) {
			dp[0][i] = dp[0][i - 1] + grid[0][i];
		}
		for (int j = 1; j < col; j++) {
			dp[j][0] = dp[j - 1][0] + grid[j][0];
		}
		for (int j = 1; j < col; j++) {
			for (int i = 1; i < row; i++) {
				dp[j][i] = Math.min(dp[j - 1][i], dp[j][i - 1]) + grid[j][i];
			}
		}
		return dp[col - 1][row - 1];
	}

	public int minPathSum2(int[][] grid) {
		for (int j = 0; j < grid.length; j++) {
			for (int i = 0; i < grid[0].length; i++) {
				if (j == 0 && i == 0) {
					continue;
				}
				if (j == 0) {
					grid[j][i] += grid[0][i - 1];
				} else if (i == 0) {
					grid[j][i] += grid[j - 1][0];
				} else {
					grid[j][i] += Math.min(grid[j - 1][i], grid[j][i - 1]);
				}
			}
		}
		return grid[grid.length - 1][grid[0].length - 1];
	}

	public static void main(String[] args) {
		Code64_minimum_path_sum c = new Code64_minimum_path_sum();
		int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		int ans = c.minPathSum2(grid);
		System.out.println(ans);
	}
}
