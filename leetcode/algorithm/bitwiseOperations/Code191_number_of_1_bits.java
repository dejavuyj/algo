package leetcode.algorithm.bitwiseOperations;

public class Code191_number_of_1_bits {
	// you need to treat n as an unsigned value
	public int hammingWeight(int n) {
		int count = 0;
		while (n != 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		Code191_number_of_1_bits c = new Code191_number_of_1_bits();
		System.out.println(c.hammingWeight(9999999));
	}
}
