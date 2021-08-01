package leetcode.algorithm.binarySearch;

public class Code35_search_insert_position {

    public int searchInsert(int[] nums, int target) {
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

        return low;
    }

    public static void main(String[] args) {
        Code35_search_insert_position c = new Code35_search_insert_position();
        int[] nums = {1};
        int target = 0;
        int ans = c.searchInsert(nums, target);
        System.out.println(ans);
    }
}
