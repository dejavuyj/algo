package algorithm;

// 背包问题
public class Backpack {

	int maxW = 0;
	int maxV = 0;

	/**
	 * 0-1 无价值背包, lintcode92, 在n个物品中挑选若干物品装入背包，最多能装多满？ 假设背包的大小为m，每个物品的大小为A[i]
	 */
	public int backPack(int m, int[] A) {
		// return backPack_bt(m, A); //回溯
		return backPack_dp2(m, A); // 动态规划
	}

	// 回溯算法,0-1 无价值背包, 时间复杂度O(2^n)
	public int backPack_bt(int m, int[] A) {
		maxW = 0;
		backTrack1(A, 0, 0, m);
		return maxW;
	}

	private void backTrack1(int[] A, int i, int cw, int m) {
		if (cw == m || i == A.length) {
			if (cw > maxW) {
				maxW = cw;
			}
			return;
		}

		backTrack1(A, i + 1, cw, m);
		if (cw + A[i] <= m) {
			backTrack1(A, i + 1, cw + A[i], m);
		}
	}

	// 动态规划,0-1 无价值背包, 时间复杂度m*n, 空间复杂度m*n
	public int backPack_dp(int m, int[] A) {
		int len = A.length;
		boolean[][] dp = new boolean[len][m + 1];
		dp[0][0] = true;
		if (A[0] <= m) {
			dp[0][A[0]] = true;
		}
		for (int i = 1; i < len; i++) {
			// 不放入此物品
			for (int j = 0; j < m; j++) {
				if (dp[i - 1][j] == true) {
					dp[i][j] = true;
				}
			}
			// 放入此物品
			for (int j = 0; j <= m - A[i]; j++) {
				if (dp[i - 1][j] == true) {
					dp[i][j + A[i]] = true;
				}
			}
		}
		for (int i = m; i >= 0; i--) {
			if (dp[len - 1][i] == true) {
				return i;
			}
		}
		return 0;
	}

	// 动态规划,0-1 无价值背包, 时间复杂度m*n, 空间复杂度m
	public int backPack_dp2(int m, int[] A) {
		int len = A.length;
		boolean[] dp = new boolean[m + 1];
		dp[0] = true;
		if (A[0] <= m) {
			dp[A[0]] = true;
		}
		for (int i = 1; i < len; i++) {
			// 放入此物品,一定要从后往前遍历,否则会重复计算导致出错
			for (int j = m - A[i]; j >= 0; j--) {
				if (dp[j] == true) {
					dp[j + A[i]] = true;
				}
			}
		}
		for (int i = m; i >= 0; i--) {
			if (dp[i] == true) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * 0-1 有价值背包, lintcode125, 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V
	 * 表示每个物品的价值. 问最多能装入背包的总价值是多大?
	 */
	public int backPackII(int m, int[] A, int[] V) {
		// return backPackII_bt(m, A, V);
		return backPackII_dp(m, A, V);
	}

	// 回溯算法,0-1 有价值背包, 时间复杂度O(2^n)
	public int backPackII_bt(int m, int[] A, int[] V) {
		maxV = 0;
		backTrack2(A, V, 0, 0, 0, m);
		return maxV;
	}

	private void backTrack2(int[] A, int[] V, int i, int cw, int cv, int m) {
		if (cw == m || i == A.length) {
			if (cv > maxV) {
				maxV = cv;
			}
			return;
		}

		backTrack2(A, V, i + 1, cw, cv, m);
		if (cw + A[i] <= m) {
			backTrack2(A, V, i + 1, cw + A[i], cv + V[i], m);
		}
	}

	// 动态规划,0-1 有价值背包, 时间复杂度m*n, 空间复杂度m
	public int backPackII_dp(int m, int[] A, int[] V) {
		int maxV = 0;
		int n = A.length;
		int[] dp = new int[m + 1];
		dp[0] = 0;
		if (A[0] <= m) {
			dp[A[0]] = V[0];
		}
		for (int i = 1; i < n; i++) {
			for (int j = m - A[i]; j >= 0; j--) {
				dp[j + A[i]] = Math.max(dp[j + A[i]], dp[j] + V[i]);
			}
		}
		for (int i = 0; i <= m; i++) {
			maxV = Math.max(maxV, dp[i]);
		}
		return maxV;
	}

	public static void main(String[] args) {
		Backpack c = new Backpack();
		int[] A = { 2, 3, 5, 7 };
		int[] V = { 1, 5, 2, 4 };
		int w = 10;
		// System.out.println(c.backPack(w, A));
		System.out.println(c.backPackII(w, A, V));
	}
}