package leetcode.algorithm.binarySearch;

public class Code704_binary_search {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Code704_binary_search c = new Code704_binary_search();
        // int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        int[] nums = {3, 1};
        System.out.println(c.search(nums, 1));
    }
}
