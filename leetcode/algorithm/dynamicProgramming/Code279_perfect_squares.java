package leetcode.algorithm.dynamicProgramming;

public class Code279_perfect_squares {

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, dp[i - j * j]);
            }
            dp[i] = 1 + minn;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Code279_perfect_squares c = new Code279_perfect_squares();
        int n = 12;
        int ans = c.numSquares(n);
        System.out.println(ans);
    }
}