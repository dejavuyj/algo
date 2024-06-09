package leetcode.algorithm.bitwiseOperations;

public class Code268_missing_number {

    public int missingNumber(int[] nums) {
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }
        for (int i = 0; i <= nums.length; i++) {
            xor ^= i;
        }
        return xor;
    }

    public static void main(String[] args) {
        Code268_missing_number c = new Code268_missing_number();
        int[] nums = {3, 0, 1};
        System.out.println(c.missingNumber(nums));
    }
}