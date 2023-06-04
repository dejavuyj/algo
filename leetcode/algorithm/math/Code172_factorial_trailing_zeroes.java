package leetcode.algorithm.math;

public class Code172_factorial_trailing_zeroes {

    public int trailingZeroes(int n) {
        int ans = 0;
        for (int i = 5; i <= n; i += 5) {
            for (int x = i; x % 5 == 0; x /= 5) {
                ++ans;
            }
        }
        return ans;
    }

    public int trailingZeroes2(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }

    public static void main(String[] args) {
        Code172_factorial_trailing_zeroes c = new Code172_factorial_trailing_zeroes();
        int n = 100;
        System.out.println(c.trailingZeroes2(n));
    }
}
