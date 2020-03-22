package leetcode.algorithm.dynamicProgramming;

public class Code188_best_time_to_buy_and_sell_stock_iv {

	public static int maxProfit(int[] prices) {
		int ans = 0;
		for (int i = 0; i < prices.length - 1; i++) {
			if (prices[i + 1] > prices[i]) {
				ans += prices[i + 1] - prices[i];
			}
		}
		return ans;
	}

	public static int maxProfit(int k, int[] prices) {
		int len = prices.length;
		if (len == 0) {
			return 0;
		}

		if(k >= prices.length / 2) {
			return maxProfit(prices);
		}

		int K = k;
		int[] dp = new int[K + 1];
		int[] min = new int[K + 1];
		for (int i = 1; i <= K; i++) {
			min[i] = prices[0];
		}

		for (int i = 1; i < len; i++) {
			for (int kk = 1; kk <= K; kk++) {
				min[kk] = Math.min(prices[i] - dp[kk - 1], min[kk]);
				dp[kk] = Math.max(dp[kk], prices[i] - min[kk]);
			}
		}
		return dp[K];
	}

	public static int maxProfit2(int k, int[] prices) {
		int len = prices.length;
		if (len == 0) {
			return 0;
		}

		if(k >= prices.length / 2) {
			return maxProfit(prices);
		}

		int K = k;
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
		// int[] prices = { 2, 4, 1 };
//		int[] prices = { 3, 2, 6, 5, 0, 3 };
		int[] prices = { 1,2,4,2,5,7,2,4,9,0 };
		System.out.println(maxProfit2(2, prices));
	}
}