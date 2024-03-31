package leetcode.algorithm.math;

public class Code233_number_of_digit_one {

    public int countDigitOne(int n) {
        long mulk = 1;
        int ans = 0;
        for (int k = 0; n >= mulk; k++) {
            ans += (n / (mulk * 10)) * mulk + Math.min(Math.max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        Code233_number_of_digit_one c = new Code233_number_of_digit_one();
        int n = 10;
        System.out.println(c.countDigitOne(n));
    }
}
