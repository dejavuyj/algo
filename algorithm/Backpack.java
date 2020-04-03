package algorithm;

// 背包问题
public class Backpack {

	int maxW = 0;
	int maxV = 0;

	/**
	 * 0-1 无价值背包, lintcode92, 在n个物品中挑选若干物品装入背包，最多能装多满？ 假设背包的大小为m，每个物品的大小为A[i]
	 */
	// 回溯算法
	public int backPack(int m, int[] A) {
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

	/**
	 * 0-1 有价值背包, lintcode125, 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V
	 * 表示每个物品的价值. 问最多能装入背包的总价值是多大?
	 */
	// 回溯算法
	public int backPackII(int m, int[] A, int[] V) {
		maxW = 0;
		maxV = 0;
		backTrack2(A, V, 0, 0, 0, m);
		return maxV;
	}

	private void backTrack2(int[] A, int[] V, int i, int cw, int cv, int m) {
		if (cw == m || i == A.length) {
			if (cw > maxW) {
				maxW = cw;
			}
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

	// 动态规划

	public static void main(String[] args) {
		Backpack c = new Backpack();
		int[] A = { 1, 3, 5, 11, 5 };
		int[] V = { 33, 3, 5, 11, 52 };
		int w = 20;
		// System.out.println(c.backPack(w, A));
		System.out.println(c.backPackII(w, A, V));
	}
}