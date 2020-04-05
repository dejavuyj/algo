package leetcode.algorithm.dynamicProgramming;

public class Code1143_longest_common_subsequence {

    public int longestCommonSubsequence(String text1, String text2) {
		int m = text1.length();
		int n = text2.length();
		if (m * n == 0) {
			return 0;
		}
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[m][n];
	}

	public static void main(String[] args) {
		Code1143_longest_common_subsequence c = new Code1143_longest_common_subsequence();
		// int ans = c.longestCommonSubsequence("abcde", "ace");
		int ans = c.longestCommonSubsequence("abc", "def");
		System.out.println(ans);
	}
}