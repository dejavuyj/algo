package leetcode.algorithm.dynamicProgramming;

//�ȼ���0-1��������
public class Code416_partition_equal_subset_sum {

	boolean ret = false;

	// ����
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

	// ��̬�滮
	public boolean canPartition2(int[] nums) {
		return false;
	}

	public static void main(String[] args) {
		Code416_partition_equal_subset_sum c = new Code416_partition_equal_subset_sum();
		int[] a = { 1, 5, 11, 5 };
		System.out.println(c.canPartition(a));
	}
}