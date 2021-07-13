package leetcode.algorithm.bitwiseOperations;

public class Code29_divide_two_integers {

    public int divide(int dividend, int divisor) {
        long x = dividend, y = divisor;
        boolean isNeg = false;
        if ((x > 0 && y < 0) || (x < 0 && y > 0)) isNeg = true;
        x = Math.abs(x);
        y = Math.abs(y);
        long l = 0, r = x;
        while (l < r) {
            long mid = (l + r + 1) >> 1;
            if (mul(mid, y) <= x) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        long ans = isNeg ? -l : l;
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        return (int) ans;
    }

    private long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) ans += a;
            k >>= 1;
            a += a;
        }
        return ans;
    }

    public static void main(String[] args) {
        Code29_divide_two_integers c = new Code29_divide_two_integers();
        int dividend = 500;
        int divisor = 243;
        System.out.println(c.divide(dividend, divisor));
    }
}
