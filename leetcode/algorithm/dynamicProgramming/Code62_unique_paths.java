package leetcode.algorithm.dynamicProgramming;

public class Code62_unique_paths {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][n - 1] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[m - 1][i] = 1;
        }
        for (int row = m - 2; row >= 0; row--) {
            for (int col = n - 2; col >= 0; col--) {
                dp[row][col] = dp[row + 1][col] + dp[row][col + 1];
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        Code62_unique_paths c = new Code62_unique_paths();
        int m = 3;
        int n = 7;
        int ans = c.uniquePaths(m, n);
        System.out.println(ans);
    }
}
