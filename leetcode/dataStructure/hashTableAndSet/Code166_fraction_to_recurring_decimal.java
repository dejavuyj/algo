package leetcode.dataStructure.hashTableAndSet;

import java.util.HashMap;
import java.util.Map;

public class Code166_fraction_to_recurring_decimal {

    public String fractionToDecimal(int numerator, int denominator) {
        long numeratorLong = numerator;
        long denominatorLong = denominator;
        if (numeratorLong % denominatorLong == 0) {
            return String.valueOf(numeratorLong / denominatorLong);
        }

        StringBuilder sb = new StringBuilder();
        if (numerator < 0 ^ denominator < 0) {
            sb.append("-");
        }

        numeratorLong = Math.abs(numeratorLong);
        denominatorLong = Math.abs(denominatorLong);
        long left = numeratorLong / denominatorLong;
        long reminder = numeratorLong % denominatorLong;
        Map<Long, Integer> numeratorIndexMap = new HashMap<>();
        sb.append(left);
        sb.append(".");
        int i = String.valueOf(left).length() + 1;
        while (reminder != 0) {
            Integer index = numeratorIndexMap.get(reminder);
            if (index != null) {
                String retStr = sb.toString();
                if (retStr.charAt(0) == '-') {
                    index++;
                }
                return retStr.substring(0, index) + "(" + retStr.substring(index, retStr.length()) + ")";
            }
            numeratorIndexMap.put(reminder, i);
            reminder *= 10;
            sb.append(reminder / denominatorLong);
            reminder = reminder % denominatorLong;
            i++;
        }
        return sb.toString();
    }

    public String fractionToDecimal2(int numerator, int denominator) {
        long numeratorLong = (long) numerator;
        long denominatorLong = (long) denominator;
        if (numeratorLong % denominatorLong == 0) {
            return String.valueOf(numeratorLong / denominatorLong);
        }

        StringBuffer sb = new StringBuffer();
        if (numeratorLong < 0 ^ denominatorLong < 0) {
            sb.append('-');
        }

        // 整数部分
        numeratorLong = Math.abs(numeratorLong);
        denominatorLong = Math.abs(denominatorLong);
        long integerPart = numeratorLong / denominatorLong;
        sb.append(integerPart);
        sb.append('.');

        // 小数部分
        StringBuffer fractionPart = new StringBuffer();
        Map<Long, Integer> remainderIndexMap = new HashMap<Long, Integer>();
        long remainder = numeratorLong % denominatorLong;
        int index = 0;
        while (remainder != 0 && !remainderIndexMap.containsKey(remainder)) {
            remainderIndexMap.put(remainder, index);
            remainder *= 10;
            fractionPart.append(remainder / denominatorLong);
            remainder %= denominatorLong;
            index++;
        }
        if (remainder != 0) { // 有循环节
            int insertIndex = remainderIndexMap.get(remainder);
            fractionPart.insert(insertIndex, '(');
            fractionPart.append(')');
        }
        sb.append(fractionPart.toString());

        return sb.toString();
    }

    public static void main(String[] args) {
        Code166_fraction_to_recurring_decimal c = new Code166_fraction_to_recurring_decimal();
        int numerator = 2147483647;
        int denominator = 37;
        String ret = c.fractionToDecimal(numerator, denominator);
        System.out.println(ret);
    }
}
