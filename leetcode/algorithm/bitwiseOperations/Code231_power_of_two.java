package leetcode.algorithm.bitwiseOperations;

public class Code231_power_of_two {

	public boolean isPowerOfTwo(int n) {
		return (n > 0) && (n & (n - 1)) == 0;
	}

	public static void main(String[] args) {
		Code231_power_of_two c = new Code231_power_of_two();
		System.out.println(c.isPowerOfTwo(12));
	}
}