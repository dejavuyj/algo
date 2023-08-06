package leetcode.dataStructure.Array;

public class Code189_rotate_array {

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        int[] tmpNums = new int[len];
        for (int i = 0; i < k; i++) {
            tmpNums[i] = nums[len - (k - i)];
        }
        System.arraycopy(nums, 0, tmpNums, k, len - k);
        System.arraycopy(tmpNums, 0, nums, 0, len);
    }

    public static void main(String[] args) {
        Code189_rotate_array c = new Code189_rotate_array();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        c.rotate(nums, k);
        for (int n : nums) {
            System.out.print(n + ", ");
        }
    }
}
