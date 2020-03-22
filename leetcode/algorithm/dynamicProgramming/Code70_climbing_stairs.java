package leetcode.algorithm.dynamicProgramming;

public class Code70_climbing_stairs {

	//ͬCode509_fibonacci_number

	public int climbStairs(int n) {
		if (n == 1) {
			return 1;
		}
		if (n <= 0) {
			return 0;
		}
		int[] count = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			if (i == n - 1) {
				count[i] = 1;
			} else if (i == n - 2) {
				count[i] = 2;
			} else {
				count[i] = count[i + 1] + count[i + 2];
			}
		}
		return count[0];
	}

	public int climbStairs2(int n) {
		if (n <= 2) {
			return n;
		}
		int one_step_before = 2;
		int two_steps_before = 1;
		int count = 0;
		for (int i = 2; i < n; i++) {
			count = one_step_before + two_steps_before;
			two_steps_before = one_step_before;
			one_step_before = count;
		}
		return count;
	}

	public int climbStairs3(int n) {
		if (n <= 2) {
			return n;
		}
		int[][] q = { { 1, 1 }, { 1, 0 } };
		int[][] res = pow(q, n);
		return res[0][0];
	}

	public int[][] pow(int[][] a, int n) {
		int[][] ret = { { 1, 0 }, { 0, 1 } };
		while (n > 0) {
			if ((n & 1) == 1) {
				ret = multiply(ret, a);
			}
			a = multiply(a, a);
			n >>= 1;
		}
		return ret;
	}

	public int[][] multiply(int[][] a, int[][] b) {
		int[][] c = new int[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
			}
		}
		return c;
	}

	public static void main(String[] args) {
		Code70_climbing_stairs c = new Code70_climbing_stairs();
		int ans = c.climbStairs2(10);
		System.out.println(ans);
	}
}
