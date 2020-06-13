package leetcode.algorithm.dynamicProgramming;

// 等价于0-1背包问题
public class Code416_partition_equal_subset_sum {

	boolean ret = false;

	// 回溯
	public boolean canPartition(int[] nums) {
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		if (sum % 2 != 0) {
			return false;
		}
		int half = sum >> 1;
		ret = false;

		backTrack(nums, 0, 0, half);
		return ret;
	}

	private void backTrack(int[] nums, int i, int cw, int target) {
		if (ret || cw == target) {
			ret = true;
			return;
		}
		if (i == nums.length) {
			return;
		}
		backTrack(nums, i + 1, cw, target);
		if (cw + nums[i] <= target) {
			backTrack(nums, i + 1, cw + nums[i], target);
		}
	}

	// 动态规划
	public boolean canPartition2(int[] nums) {
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		if ((sum & 1) == 1) {
			return false;
		}
		int half = sum >> 1;

		int n = nums.length;
		boolean[] states = new boolean[half + 1];
		states[0] = true;
		if (nums[0] <= half) {
			states[nums[0]] = true;
		}
		for (int i = 1; i < n; i++) {
			for (int j = half - nums[i]; j >= 0; j--) {
				if (states[half]) {
					return true;
				}
				if (states[j] == true) {
					states[j + nums[i]] = true;
				}
			}
		}
		return states[half];
	}

	public static void main(String[] args) {
		Code416_partition_equal_subset_sum c = new Code416_partition_equal_subset_sum();
		int[] a = { 1, 2, 5 };
		System.out.println(c.canPartition2(a));
	}
}