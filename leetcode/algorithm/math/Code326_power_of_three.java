package leetcode.algorithm.math;

public class Code326_power_of_three {

    public boolean isPowerOfThree(int n) {
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    public static void main(String[] args) {
        Code326_power_of_three c = new Code326_power_of_three();
        int n = 2147483647;
        System.out.println(c.isPowerOfThree(n));
    }
}