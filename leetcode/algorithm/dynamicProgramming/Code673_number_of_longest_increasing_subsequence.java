package leetcode.algorithm.dynamicProgramming;

import java.util.Arrays;

public class Code673_number_of_longest_increasing_subsequence {

	int lis = 0;

	// // 回溯算法求最长子序列
	// public int getLis_bt(int[] nums) {
	// if (nums == null) {
	// return 0;
	// }
	//
	// backTrack(nums, 0, Integer.MIN_VALUE, 0);
	// return lis;
	// }
	// private void backTrack(int[] nums, int ci, int cv, int clis) {
	// if (ci == nums.length) {
	// if (clis > lis) {
	// lis = clis;
	// }
	// return;
	// }
	//
	// backTrack(nums, ci + 1, cv, clis);
	// if (nums[ci] > cv) {
	// backTrack(nums, ci + 1, nums[ci], clis + 1);
	// }
	// }

	// 动态规划
	public int findNumberOfLIS(int[] nums) {
		int N = nums.length;
		if (N <= 1)
			return N;
		// lengths[i] = length of longest ending in nums[i]
		int[] lengths = new int[N];
		// count[i] = number of longest ending in nums[i]
		int[] counts = new int[N];
		Arrays.fill(counts, 1);

		for (int j = 0; j < N; ++j) {
			for (int i = 0; i < j; ++i)
				if (nums[i] < nums[j]) {
					if (lengths[i] >= lengths[j]) {
						lengths[j] = lengths[i] + 1;
						counts[j] = counts[i];
					} else if (lengths[i] + 1 == lengths[j]) {
						counts[j] += counts[i];
					}
				}
		}

		int longest = 0, ans = 0;
		for (int length : lengths) {
			longest = Math.max(longest, length);
		}
		for (int i = 0; i < N; ++i) {
			if (lengths[i] == longest) {
				ans += counts[i];
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		Code673_number_of_longest_increasing_subsequence c = new Code673_number_of_longest_increasing_subsequence();
		int[] nums = { 2, 9, 3, 6, 5, 1, 7 };
		System.out.println(c.findNumberOfLIS(nums));
	}
}