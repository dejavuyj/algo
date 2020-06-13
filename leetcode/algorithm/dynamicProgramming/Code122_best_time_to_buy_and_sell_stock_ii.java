package leetcode.algorithm.dynamicProgramming;

public class Code122_best_time_to_buy_and_sell_stock_ii {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int len = prices.length;
		int ans = 0;

		int left = 0;
		while (left < len - 1) {
			if (prices[left + 1] < prices[left]) {
				left++;
				continue;
			}

			int right = left;
			int max = prices[right];
			while (right < len - 1 && prices[++right] > max) {
				max = prices[right];
			}

			ans += max - prices[left];
			left = right;
		}

		return ans;
	}

	public static int maxProfit2(int[] prices) {
		int ans = 0;
		for (int i = 0; i < prices.length - 1; i++) {
			if (prices[i + 1] > prices[i]) {
				ans += prices[i + 1] - prices[i];
			}
		}
		return ans;
	}

	// 动态规划
	public static int maxProfit3(int[] prices) {
		if (prices == null) {
			return 0;
		}

		int[][] dp = new int[prices.length][2];
		dp[0][0] = 0;
		dp[0][1] = -prices[0];

		for (int i = 1; i < prices.length; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
			dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
		}
		return dp[prices.length - 1][0];
	}

	// 动态规划
	public static int maxProfit4(int[] prices) {
		if (prices == null) {
			return 0;
		}

		int dp_i_0 = 0;
		int dp_i_1 = Integer.MIN_VALUE;

		for (int i = 0; i < prices.length; i++) {
			dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
			dp_i_1 = Math.max(dp_i_1, dp_i_0 - prices[i]);
		}
		return dp_i_0;
	}

	public static void main(String[] args) {
		int[] prices = { 7, 1, 5, 3, 6, 4 };
		// int[] prices = { 1, 2, 3, 4, 5 };
		// int[] prices = { 7, 6, 4, 3, 1 };
		System.out.println(maxProfit3(prices));
	}
}