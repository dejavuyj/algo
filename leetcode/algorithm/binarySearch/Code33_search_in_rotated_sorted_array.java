package leetcode.algorithm.binarySearch;

public class Code33_search_in_rotated_sorted_array {

	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}

		int low = 0;
		int high = nums.length - 1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (nums[mid] == target) {
				return mid;
			}

			if (nums[mid] >= nums[low]) {
				if (target >= nums[low] && target < nums[mid]) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			} else {
				if (target > nums[mid] && target <= nums[high]) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		Code33_search_in_rotated_sorted_array c = new Code33_search_in_rotated_sorted_array();
		// int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
		int[] nums = { 3, 1 };
		System.out.println(c.search(nums, 1));
	}
}
