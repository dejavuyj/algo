package leetcode.algorithm.greedy;

public class Code121_best_time_to_buy_and_sell_stock {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int len = prices.length;
		int ans = 0;

		int left = 0;
		while (left < len) {
			int currentAns = 0;
			int currentLeft = left;
			int currentRight = len - 1;
			int currentmax = prices[currentRight];
			while (currentLeft < currentRight) {
				if (prices[currentLeft + 1] < prices[currentLeft]) {
					currentLeft++;
					continue;
				}
				if (prices[--currentRight] > currentmax) {
					currentmax = prices[currentRight];
				}
			}
			currentAns = currentmax - prices[currentLeft];
			if (currentAns > ans) {
				ans = currentAns;
			}
			left = currentLeft + 1;
		}
		return ans;
	}

	public static int maxProfit2(int[] prices) {
		int maxProfit = 0;
		if (prices == null) {
			return maxProfit;
		}

		int minValue = Integer.MAX_VALUE;
		for (int price: prices) {
			if (price < minValue) {
				minValue = price;
			} else if (price - minValue > maxProfit) {
				maxProfit = price - minValue;
			}
		}
		return maxProfit;
	}

	public static void main(String[] args) {
//		 int[] prices = { 7, 1, 5, 3, 6, 4 };
//		 int[] prices = { 7, 6, 4, 3, 1 };
//		 int[] prices = { 2, 1, 2, 1, 0, 1, 2 };
//		int[] prices = { 2, 4, 1 };
		int[] prices = null;
		System.out.println(maxProfit2(prices));
	}
}