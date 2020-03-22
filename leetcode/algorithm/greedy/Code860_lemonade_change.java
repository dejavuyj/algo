package leetcode.algorithm.greedy;

public class Code860_lemonade_change {

	public static boolean lemonadeChange(int[] bills) {
		if (bills == null) {
			return true;
		}

		int fiveCounts = 0;
		int tenCounts = 0;
		for (int bill : bills) {
			if (bill == 5) {
				fiveCounts++;
			} else if (bill == 10) {
				fiveCounts--;
				tenCounts++;
			} else if (bill == 20) {
				if (tenCounts > 0) {
					tenCounts--;
					fiveCounts--;
				} else {
					fiveCounts -= 3;
				}
			}
			if (fiveCounts < 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		 int[] prices = { 5, 5, 5, 10, 20 };
		// int[] prices = { 5, 5, 10 };
		// int[] prices = { 10, 10 };
//		int[] prices = { 5, 5, 10, 10, 20 };
		System.out.println(lemonadeChange(prices));
	}
}
