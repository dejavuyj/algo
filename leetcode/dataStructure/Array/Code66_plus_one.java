package leetcode.dataStructure.Array;

import java.math.BigDecimal;
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

    public int[] plusOne3(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }

        int last = digits.length - 1;
        if (last == 0 && digits[0] == 0) {
            digits[0] = 1;
            return digits;
        }

        return plusOne(digits, last);
    }

    public int[] plusOne(int[] digits, int last) {
        int[] result;

        // 需要增加数组长度
        if (last == 0 && digits[last] + 1 > 9){
            result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }

        if (last >=0 && digits[last] + 1 < 10) {
            // 不需要进位的情况
            digits[last] += 1;
            return digits;
        }

        // 进位的情况，因为每次加一，所以当前位必为0
        digits[last] = 0;
        // 递归调用，将前面的执行+1
        return plusOne(digits, --last);
    }

    public int[] plusOne4(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }

        StringBuilder sb = new StringBuilder();
        for(Integer integer:digits) {
            sb.append(integer);
        }
        BigDecimal count = new BigDecimal(sb.toString());
        count = count.add(new BigDecimal(1));
        String str = String.valueOf(count);
        int[] results = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            results[i] = Integer.parseInt(str.substring(i, i+1));
//            results[i] = (int) str.charAt(i);
        }
        return results;
    }

    public static void main(String[] args) {
        Code66_plus_one c = new Code66_plus_one();
        int[] M = {9, 9};
        int[] ans = c.plusOne3(M);
        for (int i : ans) {
            System.out.print(i + ", ");
        }
    }
}