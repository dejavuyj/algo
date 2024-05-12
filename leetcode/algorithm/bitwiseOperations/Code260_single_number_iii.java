package leetcode.algorithm.bitwiseOperations;

import java.util.Arrays;

public class Code260_single_number_iii {

    public int[] singleNumber(int[] nums) {
        int xorsum = 0;
        for (int num : nums) {
            xorsum ^= num;
        }
        int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
        int type1 = 0, type2 = 0;
        for (int num : nums) {
            if ((num & lsb) != 0) {
                type1 ^= num;
            } else {
                type2 ^= num;
            }
        }
        return new int[]{type1, type2};
    }

    public static void main(String[] args) {
        Code260_single_number_iii c = new Code260_single_number_iii();
        int[] nums = {4, 1, 2, 1, 2};
        int[] ans = c.singleNumber(nums);
        System.out.print(Arrays.toString(ans));
    }
}
