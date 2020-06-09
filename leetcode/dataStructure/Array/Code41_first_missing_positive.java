package leetcode.dataStructure.Array;

import java.util.Arrays;

public class Code41_first_missing_positive {

	// O(nlogn)
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

	private void swap(int[] nums, int a, int b) {
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}

	// O(n),use array as a hashtable
	public int firstMissingPositive2(int[] nums) {
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			while (nums[i] > 0 && nums[i] <= len && nums[i] != nums[nums[i] - 1]) {
				swap(nums, i, nums[i] - 1);
			}
		}

		for (int i = 0; i < len; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}

		return len + 1;
	}

	public static void main(String[] args) {
		Code41_first_missing_positive c = new Code41_first_missing_positive();
		int[] M = { 3, 4, -1, 1 };
		int ans = c.firstMissingPositive2(M);
		System.out.print(ans);
	}
}