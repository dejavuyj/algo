package leetcode.algorithm.dynamicProgramming;

//等价于0-1背包问题
public class Code416_partition_equal_subset_sum {

	boolean ret = false;

	// 回溯
	public boolean canPartition(int[] nums) {
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		if(sum %2 != 0) {
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
		if(i == nums.length) {
			return;
		}
		backTrack(nums, i + 1, cw, target);
		if (cw + nums[i] <= target) {
			backTrack(nums, i + 1, cw + nums[i], target);
		}
	}

	// 动态规划
	public boolean canPartition2(int[] nums) {
		return false;
	}

	public static void main(String[] args) {
		Code416_partition_equal_subset_sum c = new Code416_partition_equal_subset_sum();
		int[] a = { 1, 5, 11, 5 };
		System.out.println(c.canPartition(a));
	}
}