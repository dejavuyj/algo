package leetcode.dataStructure.Array;

import java.util.Arrays;

public class Code66_plus_one {

    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int[] ans = new int[len + 1];
        boolean carryFlag = false;

        for (int i = len - 1; i >= 0; i--) {
            int r = digits[i] + (carryFlag ? 1 : 0);
            if (i == len - 1) {
                r += 1;
            }
            if (r == 10) {
                ans[i + 1] = 0;
                carryFlag = true;
            } else {
                ans[i + 1] = r;
                carryFlag = false;
            }
        }
        if (carryFlag) {
            ans[0] = 1;
        } else {
            ans = Arrays.copyOfRange(ans, 1, len + 1);
        }
        return ans;
    }

    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        Code66_plus_one c = new Code66_plus_one();
        int[] M = {9, 9, 9, 9};
        int[] ans = c.plusOne2(M);
        for (int i : ans) {
            System.out.print(i + ", ");
        }
    }
}