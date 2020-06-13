package leetcode.algorithm.dynamicProgramming;

public class Code509_fibonacci_number {

	// https://leetcode-cn.com/problems/fibonacci-number/solution/fei-bo-na-qi-shu-by-leetcode/

	// 递归 时间复杂度O(2^n)
	public int fib(int N) {
		if (N == 0) {
			return 0;
		} else if (N == 1) {
			return 1;
		} else {
			return fib(N - 1) + fib(N - 2);
		}
	}

	// 动态规划 时间复杂度O(n)
	public int fib2(int N) {
		if (N <= 1) {
			return N;
		}
		int p2 = 0, p1 = 1;
		int fib = 0;
		for (int i = 2; i <= N; i++) {
			fib = p2 + p1;
			p2 = p1;
			p1 = fib;
		}
		return fib;
	}

	// 公式法 时间复杂度O(logn) 但是需要计算浮点数的乘方
	// pow需要O(logn)的时间复杂度 参见Code50_powx_n
	public int fib3(int N) {
		// g2 不影响int结果值,可以忽略
		double g1 = (1 + Math.sqrt(5)) / 2;
		// double g2 = (1 - Math.sqrt(5)) / 2;
		// System.out.println("Math.pow(g1, N) / Math.sqrt(5) is "
		// + Math.pow(g1, N) / Math.sqrt(5));
		// System.out.println("Math.pow(g2, N) / Math.sqrt(5) is "
		// + Math.pow(g2, N) / Math.sqrt(5));
		// double d = (Math.pow(g1, N) - Math.pow(g2, N)) / Math.sqrt(5);
		double d = Math.pow(g1, N) / Math.sqrt(5);
		return (int) Math.round(d);
	}

	private int[][] matrix22_multiply(int[][] x, int[][] y) {
		int[][] r = new int[2][2];
		r[0][0] = x[0][0] * y[0][0] + x[0][1] * y[1][0];
		r[0][1] = x[0][0] * y[0][1] + x[0][1] * y[1][1];
		r[1][0] = x[1][0] * y[0][0] + x[1][1] * y[1][0];
		r[1][1] = x[1][0] * y[0][1] + x[1][1] * y[1][1];
		return r;
	}

	private int[][] matrix22_pow(int[][] x, int N) {
		int[][] r = { { 1, 0 }, { 0, 1 } };
		int[][] cur = x;
		while (N > 0) {
			if ((N & 1) == 1) {
				r = matrix22_multiply(r, cur);
			}
			cur = matrix22_multiply(cur, cur);
			N >>= 1;
		}
		return r;
	}

	// 矩阵相乘 2*2
	public int fib4(int N) {
		if (N <= 1) {
			return N;
		}

		int[][] f = { { 0, 1 }, { 0, 0 } };
		int[][] b = { { 0, 1 }, { 1, 1 } };
		// int[][] r = matrix22_multiply(f, matrix22_pow(b, N - 1));
		// return r[0][1];
		int[][] r = matrix22_multiply(f, matrix22_pow(b, N - 2));
		return r[0][0] + r[0][1];
	}

	private int[][] matrix21_multiply(int[][] x, int[][] y) {
		int[][] r = new int[2][1];
		r[0][0] = x[0][0] * y[0][0] + x[0][1] * y[1][0];
		r[1][0] = x[1][0] * y[0][0] + x[1][1] * y[1][0];
		return r;
	}

	// 矩阵相乘 2*1
	public int fib5(int N) {
		if (N <= 1) {
			return N;
		}

		int[][] b = { { 1, 1 }, { 1, 0 } };
		int[][] f = { { 1 }, { 0 } };
		int[][] r = matrix21_multiply(matrix22_pow(b, N - 1), f);
		return r[0][0];
	}

	public static void main(String[] args) {
		Code509_fibonacci_number c = new Code509_fibonacci_number();
		int N = 18;
		System.out.println(c.fib3(N));
		System.out.println(c.fib5(N));
	}
}