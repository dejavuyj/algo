package leetcode.dataStructure.string;

import java.math.BigDecimal;

public class Code8_string_to_integer_atoi {

	public int myAtoi(String str) {
		int res = 0;
		if (str == null || str.length() == 0) {
			return res;
		}
		str = str.trim();
		if (str.length() == 0) {
			return res;
		}
		char c0 = str.charAt(0);
		if (c0 != '+' && c0 != '-' && !Character.isDigit(c0)) {
			return res;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(c0);
		for (int i = 1; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.isDigit(c)) {
				sb.append(c);
			} else {
				break;
			}
		}
		String strRes = sb.toString();
		if (strRes.equals("+") || strRes.equals("-")) {
			return 0;
		}
		BigDecimal b = new BigDecimal(strRes);
		if (b.compareTo(new BigDecimal(Integer.MIN_VALUE)) < 0) {
			return Integer.MIN_VALUE;
		}
		if (b.compareTo(new BigDecimal(Integer.MAX_VALUE)) > 0) {
			return Integer.MAX_VALUE;
		}
		res = b.intValue();
		return res;
	}

	public int myAtoi2(String str) {
		int res = 0;
		if (str == null || str.length() == 0) {
			return res;
		}
		int index = 0;
		int sign = 1;
		while (index < str.length() - 1 && str.charAt(index) == ' ') {
			index++;
		}
		if (str.charAt(index) == '+' || str.charAt(index) == '-') {
			sign = str.charAt(index) == '+' ? 1 : -1;
			index++;
		}

		for (; index < str.length(); index++) {
			int digit = str.charAt(index) - '0';
			if (digit < 0 || digit > 9) {
				break;
			}
			if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
				return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
			res = res * 10 + digit;
		}

		return res * sign;
	}

	public static void main(String[] args) {
		String str = " ";
		Code8_string_to_integer_atoi c = new Code8_string_to_integer_atoi();
		System.out.println(c.myAtoi2(str));
	}
}
