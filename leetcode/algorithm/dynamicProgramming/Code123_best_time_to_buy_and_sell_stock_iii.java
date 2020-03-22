package leetcode.algorithm.dynamicProgramming;

public class Code123_best_time_to_buy_and_sell_stock_iii {

	public static int maxProfit(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}

		int dp1 = 0;
		int dp2 = 0;
		int min1 = prices[0];
		int min2 = prices[0];

		for (int i = 1; i < prices.length; i++) {
			min1 = Math.min(min1, prices[i]);
			dp1 = Math.max(dp1, prices[i] - min1);

			min2 = Math.min(min2, prices[i] - dp1);
			dp2 = Math.max(dp2, prices[i] - min2);
		}
		return dp2;
	}

	public static int maxProfit2(int[] prices) {
		int len = prices.length;
		if (prices.length == 0) {
			return 0;
		}

		int K = 2;
		int[][][] dp = new int[len][K+1][2];

		for (int i = 0; i < len; i++) {
			for (int kk = K; kk >= 1; kk--) {
				if(i == 0) {
					dp[i][kk][0] = 0;
					dp[i][kk][1] = -prices[i];
					continue;
				}
				dp[i][kk][0] = Math.max(dp[i-1][kk][0], dp[i-1][kk][1] + prices[i]);
				dp[i][kk][1] = Math.max(dp[i-1][kk][1], dp[i-1][kk-1][0] - prices[i]);
			}
		}
		return dp[len-1][K][0];
	}

	public static void main(String[] args) {
		// int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };
		// int[] prices = { 1,2,3,4,5 };
		int[] prices = { 7, 6, 4, 3, 1 };
		System.out.println(maxProfit(prices));
	}
}