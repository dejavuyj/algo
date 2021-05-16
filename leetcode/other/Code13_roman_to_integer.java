package leetcode.other;

import java.util.HashMap;
import java.util.Map;

public class Code13_roman_to_integer {

    Map<Character, Integer> m = new HashMap() {{
        put('M', 1000);
        put('D', 500);
        put('C', 100);
        put('L', 50);
        put('X', 10);
        put('V', 5);
        put('I', 1);
    }};

    Map<String, Integer> m2 = new HashMap() {{
        put("CM", 900);
        put("CD", 400);
        put("XC", 90);
        put("XL", 40);
        put("IX", 9);
        put("IV", 4);
    }};

    public int romanToInt(String s) {
        int ret = 0;
        int i = 0;
        for (; i < s.length() - 1; i++) {
            Character c = s.charAt(i);
            if (c == 'I' || c == 'X' || c == 'C') {
                String s2 = "" + c + s.charAt(i + 1);
                if (m2.keySet().contains(s2)) {
                    ret += m2.get(s2);
                    i++;
                    continue;
                }
            }
            ret += m.get(c);
        }
        ret += i < s.length() ? m.get(s.charAt(i)) : 0;

        return ret;
    }

    public int romanToInt2(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int value = m.get(s.charAt(i));
            if (i < n - 1 && value < m.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }

        return ans;
    }

    public int romanToInt3(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int value = getValue(s.charAt(i));
            if (i < n - 1 && value < getValue(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }

        return ans;
    }

    private int getValue(Character c) {
        int value = 0;
        switch (c) {
            case 'I' :
                value = 1;
                break;
            case 'V' :
                value = 5;
                break;
            case 'X' :
                value = 10;
                break;
            case 'L' :
                value = 50;
                break;
            case 'C' :
                value = 100;
                break;
            case 'D' :
                value = 500;
                break;
            case 'M' :
                value = 1000;
                break;
        }
        return value;
    }

    public int romanToInt4(String s) {
        int i = 0;
        int res = 0;
        while (i < s.length()) {
            switch (s.charAt(i)) {
                case 'I':
                    i++;
                    if (i<s.length() && s.charAt(i) == 'V') {
                        res += 4;
                        i++;
                    } else if (i<s.length() && s.charAt(i) == 'X') {
                        res += 9;
                        i++;
                    } else{
                        res += 1;
                    }
                    break;
                case 'X':
                    i++;
                    if (i<s.length() && s.charAt(i) == 'L') {
                        res += 40;
                        i++;
                    } else if (i<s.length() && s.charAt(i) == 'C') {
                        res += 90;
                        i++;
                    } else{
                        res += 10;
                    }
                    break;
                case 'C':
                    i++;
                    if (i<s.length() && s.charAt(i) == 'D') {
                        res += 400;
                        i++;
                    } else if (i<s.length() && s.charAt(i) == 'M') {
                        res += 900;
                        i++;
                    } else{
                        res += 100;
                    }
                    break;
                case 'V':
                    res += 5;
                    i++;
                    break;
                case 'L':
                    res += 50;
                    i++;
                    break;
                case 'D':
                    res += 500;
                    i++;
                    break;
                case 'M':
                    res += 1000;
                    i++;
                    break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "MCMXCIV";
        Code13_roman_to_integer c = new Code13_roman_to_integer();
        int ret = c.romanToInt2(s);
        System.out.print(ret);
    }
}
