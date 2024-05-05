package leetcode.algorithm.math;

public class Code258_add_digits {

    public int addDigits(int n) {
        if (n < 10) {
            return n;
        }
        return addDigits(n % 10 + n / 10);
    }

    public static void main(String[] args) {
        Code258_add_digits c = new Code258_add_digits();
        int n = 238;
        System.out.println(c.addDigits(n));
    }
}
