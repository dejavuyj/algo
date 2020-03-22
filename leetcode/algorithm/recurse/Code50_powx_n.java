package leetcode.algorithm.recurse;

public class Code50_powx_n {
	// O(n)
	public static double myPow(double x, int n) {
		if (n >= 0) {
			if (n == 0) {
				return 1;
			}
			return x * myPow(x, n - 1);
		} else {
			return 1 / (myPow(x, -n));
		}
	}

	// O(n)
	public static double myPow2(double x, int n) {
		if (n == 0) {
			return 1;
		}
		int absN = Math.abs(n);
		double ans = x;
		while (absN > 1) {
			ans *= x;
			absN--;
		}
		if (n < 0) {
			ans = 1 / ans;
		}
		return ans;
	}

	private static double fastPow(double x, int n) {
		if (n == 0) {
			return 1;
		}
		double half = fastPow(x, n / 2);
		if (n % 2 == 0) {
			return half * half;
		} else {
			return half * half * x;
		}
	}

	// O(logn)
	public static double myPow3(double x, int n) {
		int N = n;
		if (n < 0) {
			x = 1 / x;
			N = n * -1;
		}

		return fastPow(x, N);
	}

	public static double myPow4(double x, int n) {
		double res = 1.0;
		// long N = n;
		// if (N < 0) {
		// N *= -1;
		// x = 1 / x;
		// }

		for (int i = n; i != 0; i /= 2) {

			if (i % 2 != 0) {
				res *= x;
			}
			x *= x;
		}

		return n < 0 ? 1 / res : res;
	}

	public static double myPow5(double x, int n) {
		double ans = 1;
		long N = n;
		if (N < 0) {
			N = -N;
			x = 1 / x;
		}

		while (N > 0) {
			if (N % 2 != 0) {
				ans *= x;
			}
			x *= x;
			N >>= 1;
		}
		return ans;
	}

	public static double myPow6(double x, int n) {
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}
		double ans = 1;
		double current_product = x;
		while (N > 0) {
			if ((N & 1) == 1) {
				ans *= current_product;
			}
			current_product *= current_product;
			N = N >> 1;
		}
		return ans;
	}

	public static double myPow7(double x, int n) throws Exception {
		if (n < 0) {
			if (x == 0) {
				throw new Exception("divide 0!");
			}
			return 1.0 / myPow7(x, -n);
		}
		double ans = 1;
		double cur = x;
		while (n > 0) {
			if ((n & 1) == 1) {
				ans *= cur;
			}
			cur *= cur;
			n >>= 1;
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(myPow7(2, -1));
	}
}
