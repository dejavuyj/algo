package leetcode.dataStructure.Array;

public class Code303_range_sum_query_immutable {

    static class NumArray {
        int[] sums;

        public NumArray(int[] nums) {
            int n = nums.length;
            sums = new int[n + 1];
            for (int i = 0; i < n; i++) {
                sums[i + 1] = sums[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return sums[right + 1] - sums[left];
        }
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray c = new NumArray(nums);

        System.out.println(c.sumRange(0, 2));
        System.out.println(c.sumRange(2, 5));
        System.out.println(c.sumRange(0, 5));
    }
}
