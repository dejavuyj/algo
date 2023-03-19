package leetcode.algorithm.binarySearch;

public class Code153_find_minimum_in_rotated_sorted_array {

    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] < nums[high]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        Code153_find_minimum_in_rotated_sorted_array c = new Code153_find_minimum_in_rotated_sorted_array();
        int[] nums = {3, 1, 2};
        System.out.println(c.findMin(nums));
    }
}
