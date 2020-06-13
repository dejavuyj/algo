package leetcode.algorithm.dynamicProgramming;

public class Code518_coin_change_2 {

	// 使用组合数而非排列数
	// https://leetcode-cn.com/problems/coin-change-2/solution/ling-qian-dui-huan-iihe-pa-lou-ti-wen-ti-dao-di-yo/
	public int change(int amount, int[] coins) {
		int[] dp = new int[amount + 1];
		dp[0] = 1;
		for (int coin : coins) {
			for (int i = coin; i <= amount; i++) {
				dp[i] += dp[i - coin];
			}
		}
		return dp[amount];
	}

	public static void main(String[] args) {
		Code518_coin_change_2 c = new Code518_coin_change_2();
		int[] coins = { 5, 1, 2 };
		int amount = 5;
		// int[] coins = { 2 };
		// int amount = 3;
		int ans = c.change(amount, coins);
		System.out.println(ans);
	}
}