package leetcode.algorithm.binarySearch;

public class Code154_find_minimum_in_rotated_sorted_array_ii {

    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] < nums[high]) {
                high = mid;
            } else if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high--;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        Code154_find_minimum_in_rotated_sorted_array_ii c = new Code154_find_minimum_in_rotated_sorted_array_ii();
        int[] nums = {3, 1, 2};
        System.out.println(c.findMin(nums));
    }
}
