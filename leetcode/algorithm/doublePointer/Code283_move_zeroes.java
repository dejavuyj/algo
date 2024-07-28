package leetcode.algorithm.doublePointer;

public class Code283_move_zeroes {

    public void moveZeroes(int[] nums) {
        int len = nums.length;
        for (int i = 0, j = 0; i < len; i++) {
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        Code283_move_zeroes c = new Code283_move_zeroes();
        c.moveZeroes(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
