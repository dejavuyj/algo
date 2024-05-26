package leetcode.algorithm.math;

public class Code263_ugly_number {

    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }

    public static void main(String[] args) {
        Code263_ugly_number c = new Code263_ugly_number();
        int n = 10;
        System.out.println(c.isUgly(n));
    }
}
