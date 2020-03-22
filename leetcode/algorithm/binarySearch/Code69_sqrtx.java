package leetcode.algorithm.binarySearch;

public class Code69_sqrtx {

	public int mySqrt(int x) {
		long left = 0;
		long right = x/2 +1;
		while (left < right) {
			long mid = (left + right +1) >>> 1;
			long sqrtMid = mid * mid;
			if (sqrtMid > x) {
				right = mid - 1;
			} else {
				left = mid;
			}
		}
		return (int)left;
	}

	public double mySqrt2(int x) {
		double r = x;
		while (r*r > x) {
			r = (r + x/r) /2;
		}
		return r;
	}

	//precision 精确到小数点后多少位
	public double mySqrt3(int x, int precision) {
		double left = 0;
		double right = x/2 +1;
		while (left < right) {
			double mid = (left + right) / 2;
			double sqrtMid = mid * mid;
			if(right-left < Math.pow(10, -1*precision)) {
				return left;
			} else if (sqrtMid > x) {
				right = mid;
			} else {
				left = mid;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		Code69_sqrtx code = new Code69_sqrtx();
		int i = 9;
		//System.out.println(code.mySqrt(i));
//		System.out.println(code.mySqrt2(i));
		System.out.println(code.mySqrt3(i,9));
		System.out.println(Math.sqrt(i));
	}
}
