package leetcode.algorithm.bitwiseOperations;

public class Code201_bitwise_and_of_numbers_range {

    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        while(left < right) {
            left >>= 1;
            right >>= 1;
            ++shift;
        }
        return left << shift;
    }
    public int rangeBitwiseAnd2(int left, int right) {
        while(left < right) {
            right = right & (right - 1);
        }
        return right;
    }

    public static void main(String[] args) {
        Code201_bitwise_and_of_numbers_range c = new Code201_bitwise_and_of_numbers_range();
        int left = 1;
        int right = 2147483647;
        System.out.println(c.rangeBitwiseAnd(left, right));
    }
}
