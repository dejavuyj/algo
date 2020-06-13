package leetcode.algorithm.dynamicProgramming;

import java.util.Arrays;

public class Code300_longest_increasing_subsequence {

	private int lengthOfLIS(int[] nums, int preVal, int curpos) {
		if (curpos == nums.length) {
			return 0;
		}

		int taken = 0;
		if (nums[curpos] > preVal) {
			taken = 1 + lengthOfLIS(nums, nums[curpos], curpos + 1);
		}
		int notTaken = lengthOfLIS(nums, preVal, curpos + 1);
		return Math.max(taken, notTaken);
	}

	public int lengthOfLIS(int[] nums) {
		return lengthOfLIS(nums, Integer.MIN_VALUE, 0);
	}

	public int lengthOfLIS2(int[] nums) {
		int len = nums.length;
		if (len == 0) {
			return 0;
		}
		int[] dp = new int[len];
		dp[0] = 1;
		int maxans = 1;
		for (int i = 1; i < len; i++) {
			int maxval = 0;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					maxval = Math.max(maxval, dp[j]);
				}
			}
			dp[i] = maxval + 1;
			maxans = Math.max(maxans, dp[i]);
		}
		return maxans;
	}

	//dp+二分查找
	public int lengthOfLIS3(int[] nums) {
		int[] dp = new int[nums.length];
		int len = 0;
		for (int num : nums) {
			int i = Arrays.binarySearch(dp, 0, len, num);
			if (i < 0) {
				i = -(i + 1);
			}
			dp[i] = num;
			if (i == len) {
				len++;
			}
		}
		return len;
	}

	//dp+二分查找
	public int lengthOfLIS4(int[] nums) {
		int[] dp = new int[nums.length];
		int len = 0;
		for (int num : nums) {
			int l = 0;
			int r = len;
			while (l < r) {
				int m = (l + r) / 2;
				if(dp[m] < num) {
					l = m+1;
				} else {
					r = m;
				}
			}
			dp[l] = num;
			if (l == len) {
				len++;
			}
		}
		return len;
	}

	public static void main(String[] args) {
		Code300_longest_increasing_subsequence c = new Code300_longest_increasing_subsequence();
		int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
		int ans = c.lengthOfLIS4(nums);
		System.out.println(ans);
	}
}