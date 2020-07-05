package leetcode.algorithm.sort;

public class Code75_sort_colors {

	public void sortColors(int[] nums) {
		int len = nums.length;
		if (len < 2) {
			return;
		}

		// all in [0, zero) = 0
		// all in [zero, i) = 1
		// all in [two, len - 1] = 2
		int zero = 0;
		int two = len;
		int i = 0;
		while (i < two) {
			if (nums[i] == 0) {
				swap(nums, i, zero);
				zero++;
				i++;
			} else if (nums[i] == 1) {
				i++;
			} else {
				two--;
				swap(nums, i, two);
			}
		}
	}

	private void swap(int[] nums, int a, int b) {
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 0, 2, 1, 2, 1, 0, 0, 1, 2 };
		Code75_sort_colors c = new Code75_sort_colors();
		c.sortColors(nums);
		for (int i : nums) {
			System.out.print(i + ", ");
		}
	}
}
