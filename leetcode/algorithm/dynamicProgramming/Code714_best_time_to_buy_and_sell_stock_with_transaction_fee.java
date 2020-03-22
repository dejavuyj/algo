package leetcode.algorithm.dynamicProgramming;

public class Code714_best_time_to_buy_and_sell_stock_with_transaction_fee {

	public static int maxProfit(int[] prices, int fee) {
		int len = prices.length;
		if (len == 0) {
			return 0;
		}

		int dp_i_0 = 0;
		int dp_i_1 = Integer.MIN_VALUE;

		for (int i = 0; i < len; i++) {
			dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]); // - (i==0?0:fee)
			dp_i_1 = Math.max(dp_i_1, dp_i_0 - prices[i] - fee);
		}
		return dp_i_0;
	}

	public static void main(String[] args) {
		int[] prices = { 1, 3, 2, 8, 4, 9 };
		System.out.println(maxProfit(prices, 2));
	}
}