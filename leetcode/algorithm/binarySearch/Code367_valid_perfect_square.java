package leetcode.algorithm.binarySearch;

public class Code367_valid_perfect_square {

	public boolean isPerfectSquare(int num) {
		long left = 1;
		long right = num / 2 + 1;
		while (left <= right) {
			long mid = (left + right + 1) / 2;
			long midSquare = mid * mid;
			if(midSquare == num) {
				return true;
			} else if (midSquare < num) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Code367_valid_perfect_square code = new Code367_valid_perfect_square();
		System.out.println(code.isPerfectSquare(2147483647));
	}
}
