package leetcode.algorithm.dynamicProgramming;

public class Code72_edit_distance {

	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		if (m * n == 0) {
			return m + 1;
		}
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i < m + 1; i++) {
			dp[i][0] = i;
		}
		for (int j = 0; j < n + 1; j++) {
			dp[0][j] = j;
		}
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					int dp1 = dp[i - 1][j];
					int dp2 = dp[i][j - 1];
					int dp3 = dp[i - 1][j - 1];
					int min12 = Math.min(dp1, dp2);
					int min = Math.min(min12, dp3);
					dp[i][j] = 1 + min;
				}
			}
		}
		return dp[m][n];
	}

	public static void main(String[] args) {
		Code72_edit_distance c = new Code72_edit_distance();
		// int ans = c.minDistance("horse", "ros");
		int ans = c.minDistance("ab", "bc");
		System.out.println(ans);
	}
}