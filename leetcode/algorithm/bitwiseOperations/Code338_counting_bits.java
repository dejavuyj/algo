package leetcode.algorithm.bitwiseOperations;

public class Code338_counting_bits {

	public int hammingWeight(int n) {
		int count = 0;
		while (n != 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}

	public int[] countBits(int num) {
		int[] ans = new int[num + 1];
		for (int i = 0; i <= num; i++) {
			ans[i] = hammingWeight(i);
		}
		return ans;
	}

	public int[] countBits2(int num) {
		int[] ans = new int[num + 1];
		int i = 0;
		int b = 1;
		while (b <= num) {
			while (i < b && b + i <= num) {
				ans[b + i] = ans[i] + 1;
				i++;
			}
			i = 0;
			b <<= 1;
		}
		return ans;
	}

	public int[] countBits3(int num) {
		int[] ans = new int[num + 1];
		for(int i=1;i<=num;i++) {
			ans[i] = ans[i>>1] + (i&1);
		}
		return ans;
	}

	public int[] countBits4(int num) {
		int[] ans = new int[num + 1];
		for(int i=1;i<=num;i++) {
			ans[i] = ans[i&(i-1)] + 1;
		}
		return ans;
	}

	public static void main(String[] args) {
		Code338_counting_bits c = new Code338_counting_bits();
		int[] ans = c.countBits2(15);
		for (int i : ans) {
			System.out.print(i + ", ");
		}
		System.out.println();
		int[] ans2 = c.countBits4(15);
		for (int i : ans2) {
			System.out.print(i + ", ");
		}
	}
}
