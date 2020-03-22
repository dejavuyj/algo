package leetcode.algorithm.dynamicProgramming;

public class Code309_best_time_to_buy_and_sell_stock_with_cooldown {

	public static int maxProfit(int[] prices) {
		int len = prices.length;
		if (len == 0) {
			return 0;
		}

		int dp_pre_0 = 0; // i-2
		int dp_i_0 = 0;
		int dp_i_1 = Integer.MIN_VALUE;

		for (int i = 0; i < len; i++) {
			int temp = dp_i_0;
			dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
			dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
			dp_pre_0 = temp;
		}
		return dp_i_0;
	}

	public static void main(String[] args) {
		int[] prices = { 1, 2, 3, 0, 2 };
		System.out.println(maxProfit(prices));
	}
}