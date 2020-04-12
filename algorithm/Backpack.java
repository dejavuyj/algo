package algorithm;

// 背包问题
@SuppressWarnings("unused")
public class Backpack {

	int maxW = 0;
	int maxV = 0;
	int resolveCnt = 0;

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

	/**
	 * 完全背包, lintcode562, 给出 n 个物品, 以及一个数组, nums[i]代表第i个物品的大小, 保证大小均为正数并且没有重复,
	 * 正整数 target 表示背包的大小, 找到能填满背包的方案数。 每一个物品可以使用无数次
	 */
	public int backPackIV(int[] nums, int target) {
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int num : nums) {
			for (int i = num; i <= target; i++) {
				dp[i] += dp[i - num];
			}
		}
		return dp[target];
	}

	/**
	 * 0-1 完全背包, lintcode563, 给出 n 个物品, 以及一个数组, nums[i] 代表第i个物品的大小, 保证大小均为正数,
	 * 正整数 target 表示背包的大小, 找到能填满背包的方案数。每一个物品只能使用一次?
	 */
	public int backPackV(int[] nums, int target) {
		// return backPackV_bt(nums, target);
		// return backPackV_dp(nums, target);
		return backPackV_dp2(nums, target);
	}

	private int backPackV_bt(int[] nums, int target) {
		backTrackV(nums, target, 0, 0);
		return resolveCnt;
	}

	private void backTrackV(int[] nums, int target, int ci, int cw) {
		if (cw == target || ci == nums.length) {
			if (cw == target) {
				resolveCnt++;
			}
			return;
		}

		backTrackV(nums, target, ci + 1, cw);
		if (cw + nums[ci] <= target) {
			backTrackV(nums, target, ci + 1, cw + nums[ci]);
		}
	}

	private int backPackV_dp(int[] nums, int target) {
		int n = nums.length;
		int[][] dp = new int[n][target + 1];
		dp[0][0] = 1;
		if (nums[0] <= target) {
			dp[0][nums[0]] = 1;
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= target; j++) {
				dp[i][j] += dp[i - 1][j];
			}
			for (int j = 0; j <= target - nums[i]; j++) {
				dp[i][j + nums[i]] += dp[i - 1][j];
			}
		}
		return dp[n - 1][target];
	}

	private int backPackV_dp2(int[] nums, int target) {
		int n = nums.length;
		int[] dp = new int[target + 1];
		dp[0] = 1;
		if (nums[0] <= target) {
			dp[nums[0]] = 1;
		}

		for (int i = 1; i < n; i++) {
			for (int j = target - nums[i]; j >= 0; j--) {
				dp[j + nums[i]] += dp[j];
			}
		}
		return dp[target];
	}

	public static void main(String[] args) {
		Backpack c = new Backpack();
		// int[] A = { 2, 3, 6, 7 };
		// int[] V = { 1, 5, 2, 4 };
		// int w = 7;
		// System.out.println(c.backPack(w, A));
		// System.out.println(c.backPackII(w, A, V));
		int[] A = { 1, 2, 3, 3, 7 };
		int w = 7;
		System.out.println(c.backPackV(A, w));
	}
}