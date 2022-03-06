package leetcode.dataStructure.Array;

public class Code80_remove_duplicates_from_sorted_array_ii {

    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return len;
        }
        int slow = 2;
        int fast = 2;

        while (fast < len) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3, 4,4,4,4,5,6,7,7,7,7,8,8,9,9,9};
        Code80_remove_duplicates_from_sorted_array_ii c = new Code80_remove_duplicates_from_sorted_array_ii();
        int ret = c.removeDuplicates(nums);
        for (int i = 0; i < ret; i++) {
            System.out.print(nums[i] + ", ");
        }
    }
}
