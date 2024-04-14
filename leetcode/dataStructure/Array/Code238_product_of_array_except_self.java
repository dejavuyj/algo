package leetcode.dataStructure.Array;

public class Code238_product_of_array_except_self {

    public int[] productExceptSelf(int[] nums) {
        int[] ansArray = new int[nums.length];

        ansArray[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            ansArray[i] = ansArray[i - 1] * nums[i - 1];
        }

        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            ansArray[i] = right * ansArray[i];
            right *= nums[i];
        }

        return ansArray;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        Code238_product_of_array_except_self c = new Code238_product_of_array_except_self();
        int[] ret = c.productExceptSelf(nums);
        for (int i = 0; i < ret.length; i++) {
            System.out.println(ret[i]);
        }
    }
}
