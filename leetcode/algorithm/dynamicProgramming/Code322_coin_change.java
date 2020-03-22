package leetcode.algorithm.dynamicProgramming;

import java.util.Arrays;

public class Code322_coin_change {

	private int coinChange(int[] coins, int rem, int[] count) {
		if (rem < 0) {
			return -1;
		}
		if (rem == 0) {
			return 0;
		}
		if (count[rem - 1] != 0) {
			return count[rem - 1];
		}
		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int res = coinChange(coins, rem - coin, count);
			if (res >= 0 && res < min) {
				min = 1 + res;
			}
		}
		count[rem - 1] = (min == Integer.MAX_VALUE ? -1 : min);
		return count[rem - 1];
	}

	public int coinChange(int[] coins, int amount) {
		if (amount < 0) {
			return 0;
		}
		return coinChange(coins, amount, new int[amount]);
	}

	public int coinChange2(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, amount+1);
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i) {
					dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
				}
			}
		}
//		for(int i=1;i<=amount;i++) {
//			System.out.println(i+":"+dp[i]);
//		}
		return dp[amount] > amount ? -1 : dp[amount];
	}

	public static void main(String[] args) {
		Code322_coin_change c = new Code322_coin_change();
		// int[] coins = { 1, 2, 5 };
		// int amount = 11;
		 int[] coins = { 2 };
		 int amount = 3;
		// int[] coins = { 2, 5, 10, 1 };
		// int amount = 27;
//		int[] coins = { 186, 419, 83, 408 };
//		int amount = 6249;
		int ans = c.coinChange2(coins, amount);
		System.out.println(ans);
	}
}