package leetcode.dataStructure.stackAndQueue;

import java.util.*;

public class Code224_basic_calculator {

    public int calculate(String s) {
        Deque<Integer> ops = new LinkedList<>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
            } else if (c == '+') {
                sign = ops.peek();
                i++;
            } else if (c == '-') {
                sign = -ops.peek();
                i++;
            } else if (c == '(') {
                ops.push(sign);
                i++;
            } else if (c == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Code224_basic_calculator c = new Code224_basic_calculator();
        String s = "(1+(4+5+2)-3)+(6+8)";
        int ans = c.calculate(s);
        System.out.println(ans);
    }
}
