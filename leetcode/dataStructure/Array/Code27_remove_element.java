package leetcode.dataStructure.Array;

public class Code27_remove_element {

    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = 0;
        int len = nums.length;
        while (right < len) {
            while (right < len - 1 && nums[right] == val) {
                right++;
            }
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
        Code27_remove_element c = new Code27_remove_element();
        int ret = c.removeElement(nums, val);
        for (int i = 0; i < ret; i++) {
            System.out.print(nums[i] + ", ");
        }
    }
}
