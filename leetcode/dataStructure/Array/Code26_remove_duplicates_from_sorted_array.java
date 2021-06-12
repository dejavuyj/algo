package leetcode.dataStructure.Array;

public class Code26_remove_duplicates_from_sorted_array {

    public int removeDuplicates(int[] nums) {
        int left = 0;
        int right = 0;
        int len = nums.length;
        while (right < len) {
            while (right < len - 1 && nums[right] == nums[right + 1]) {
                right++;
            }
            nums[left] = nums[right];
            left++;
            right++;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        Code26_remove_duplicates_from_sorted_array c = new Code26_remove_duplicates_from_sorted_array();
        int ret = c.removeDuplicates(nums);
        for (int i = 0; i < ret; i++) {
            System.out.print(nums[i] + ", ");
        }
    }
}
