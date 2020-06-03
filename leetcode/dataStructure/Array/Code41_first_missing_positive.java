package leetcode.dataStructure.Array;

import java.util.Arrays;

public class Code41_first_missing_positive {

	public int firstMissingPositive(int[] nums) {
		Arrays.sort(nums);
		int lastNum = 0;
		for (int num : nums) {
			if (num <= 0) {
				continue;
			}
			if (num != lastNum && num != lastNum + 1) {
				return lastNum + 1;
			} else {
				lastNum = num;
			}
		}
		return lastNum + 1;
	}

	public static void main(String[] args) {
		Code41_first_missing_positive c = new Code41_first_missing_positive();
		int[] M = { 3, 4, -1, 1 };
		int ans = c.firstMissingPositive(M);
		System.out.print(ans);
	}
}