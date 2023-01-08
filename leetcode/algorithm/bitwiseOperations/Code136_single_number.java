package leetcode.algorithm.bitwiseOperations;

public class Code136_single_number {

    public int singleNumber(int[] nums) {
        int ret = 0;
        for (int n : nums) {
            ret ^= n;
        }
        return ret;
    }

    public static void main(String[] args) {
        Code136_single_number c = new Code136_single_number();
        int[] nums = {4, 1, 2, 1, 2};
        int ans = c.singleNumber(nums);
        System.out.print(ans);
    }
}
