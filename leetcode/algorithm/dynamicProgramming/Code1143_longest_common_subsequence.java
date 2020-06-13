package leetcode.algorithm.dynamicProgramming;

public class Code1143_longest_common_subsequence {

	// 空间复杂度(m*n)
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

	// 空间复杂度min(m,n)
	public int longestCommonSubsequence2(String text1, String text2) {
		int m = text1.length();
		int n = text2.length();
		if (m == 0 || n == 0) {
			return 0;
		}
		String rowText;
		String colText;
		if (m > n) {
			rowText = text1;
			colText = text2;
		} else {
			rowText = text2;
			colText = text1;
		}
		int[] dp = new int[colText.length() + 1];
		for (int i = 1; i < rowText.length() + 1; i++) {
			int tmp = 0;
			for (int j = 1; j < colText.length() + 1; j++) {
				int prev = tmp; // 对应上一行前一列的值,等价于二维数组中的dp[i-1][j-1]
				tmp = dp[j];
				if (colText.charAt(j - 1) == rowText.charAt(i - 1)) {
					dp[j] = prev + 1;
				} else {
					// dp[j]  为上一行当前列, 相当于二维数组中的dp[i-1][j]
					// dp[j-1]为当前行前一列, 相当于二维数组中的dp[i][j-1]
					dp[j] = Math.max(dp[j], dp[j - 1]);
				}
			}
		}
		return dp[colText.length()];
	}

	public static void main(String[] args) {
		Code1143_longest_common_subsequence c = new Code1143_longest_common_subsequence();
		int ans = c.longestCommonSubsequence("bsbininm", "jmjkbkjkv");
		// int ans = c.longestCommonSubsequence("abc", "def");
		System.out.println(ans);
	}
}