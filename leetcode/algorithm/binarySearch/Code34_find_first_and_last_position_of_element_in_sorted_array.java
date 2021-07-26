package leetcode.algorithm.binarySearch;

public class Code34_find_first_and_last_position_of_element_in_sorted_array {

    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1, -1};
        int pos = search(nums, target);
        if (pos != -1) {
            int l = pos, r = pos;
            while (l >= 0 && nums[l] == target) {
                l--;
            }
            while (r <= nums.length - 1 && nums[r] == target) {
                r++;
            }
            ans[0] = l + 1;
            ans[1] = r - 1;
        }
        return ans;
    }

    private int search(int[] nums, int target) {
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

            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Code34_find_first_and_last_position_of_element_in_sorted_array c = new Code34_find_first_and_last_position_of_element_in_sorted_array();
        int[] nums = {};
        int target = 0;
        int[] ans = c.searchRange(nums, target);
        System.out.println(ans[0]);
        System.out.println(ans[1]);
    }
}
