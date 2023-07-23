package leetcode.other;

import java.util.Stack;

/**
 * @create 2021/3/17 0:26 t
 */
public class Code7_reverse_integer {

    public int reverse(int x) {
        int ret = 0;
        boolean flag = false;
        if (x < 0) {
            flag = true;
            x = x * -1;
        }

        Stack<Integer> stack = new Stack<>();

        while (x != 0) {
            int digit = x % 10;
            stack.push(digit);
            x /= 10;
        }

        int cnt = 0;
        while (!stack.isEmpty()) {
            int digit = stack.pop();
            int p = (int) Math.pow(10, cnt);
            if (cnt == 9 && digit > 2) {
                return 0;
            }
            p *= digit;
            if (ret > Integer.MAX_VALUE - p) {
                return 0;
            }
            ret += p;
            cnt++;
        }

        return flag ? ret * -1 : ret;
    }

    public int reverse2(int x) {
        int ret = 0;
        boolean flag = false;
        if (x < 0) {
            flag = true;
            x = x * -1;
        }

        int x2 = x;
        int cnt = 0;
        while (x2 != 0) {
            x2 /= 10;
            cnt++;
        }

        if (cnt == 10 && x % 10 > 2) {
            return 0;
        }
        cnt--;

        while (cnt >= 0) {
            int digit = x % 10;
            int p = digit * (int) Math.pow(10, cnt);
            if (ret > Integer.MAX_VALUE - p) {
                return 0;
            }
            ret += p;
            x /= 10;
            cnt--;
        }

        return flag ? ret * -1 : ret;
    }

    public int reverse3(int x) {
        int ret = 0;
        boolean flag = false;
        if (x < 0) {
            flag = true;
            x = x * -1;
        }

        while (x != 0) {
            int digit = x%10;
            if (ret > (Integer.MAX_VALUE - digit) / 10) {
                return 0;
            }
            ret = ret*10 + digit;
            x /= 10;
        }
        return flag ? ret * -1 : ret;
    }

    public static void main(String[] args) {
        int x2 = 12%10;
        Code7_reverse_integer c = new Code7_reverse_integer();
        int x = 1231121212;
        int y = c.reverse3(x);
        System.out.println(y);
    }
}
