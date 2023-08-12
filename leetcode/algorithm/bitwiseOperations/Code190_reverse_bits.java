package leetcode.algorithm.bitwiseOperations;

public class Code190_reverse_bits {
	// you need treat n as an unsigned value
	public int reverseBits(int n) {
		int ret = 0;
		for (int i=0; i<32 && n!=0; i++) {
			ret |= (n&1) << (31-i);
			n >>>= 1;
		}
		return ret;
	}

	public static void main(String[] args) {
		Code190_reverse_bits c = new Code190_reverse_bits();
		System.out.println(c.reverseBits(43261596));
	}
}
