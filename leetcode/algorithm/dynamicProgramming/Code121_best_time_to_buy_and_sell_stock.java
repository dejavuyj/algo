package leetcode.algorithm.dynamicProgramming;

public class Code121_best_time_to_buy_and_sell_stock {

	public int maxProfit(int[] prices) {
		int maxProfit = 0;
		if (prices == null) {
			return maxProfit;
		}

		int minValue = Integer.MAX_VALUE;
		for (int price : prices) {
			if (price < minValue) {
				minValue = price;
			} else if (price - minValue > maxProfit) {
				maxProfit = price - minValue;
			}
		}
		return maxProfit;
	}

	// ¶¯Ì¬¹æ»®
	public int maxProfit2(int[] prices) {
		if (prices == null) {
			return 0;
		}

		int dp_i_0 = 0;
		int dp_i_1 = Integer.MIN_VALUE;

		for (int i = 0; i < prices.length; i++) {
			dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
			dp_i_1 = Math.max(dp_i_1, -prices[i]);
		}
		return dp_i_0;
	}

	public static void main(String[] args) {
		Code121_best_time_to_buy_and_sell_stock c = new Code121_best_time_to_buy_and_sell_stock();
		 int[] prices = { 7, 1, 5, 3, 6, 4 };
		// int[] prices = { 7, 6, 4, 3, 1 };
		// int[] prices = { 2, 1, 2, 1, 0, 1, 2 };
		// int[] prices = { 2, 4, 1 };
//		int[] prices = null;
		System.out.println(c.maxProfit2(prices));
	}
}