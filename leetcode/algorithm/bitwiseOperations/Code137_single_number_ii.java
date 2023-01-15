package leetcode.algorithm.bitwiseOperations;

public class Code137_single_number_ii {

    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            b = ~a & (b ^ num);
            a = ~b & (a ^ num);
        }
        return b;
    }

    public static void main(String[] args) {
        Code137_single_number_ii c = new Code137_single_number_ii();
        int[] nums = {4, 1, 2, 1, 2};
        int ans = c.singleNumber(nums);
        System.out.print(ans);
    }
}
