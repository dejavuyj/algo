package leetcode.algorithm.slidingWindow;

public class Code209_minimum_size_subarray_sum {

    public int minSubArrayLen(int target, int[] nums) {
        int ret = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int sum = 0;
        while (right < nums.length) {
            sum += nums[right];
            while (sum >= target) {
                sum -= nums[left];
                ret = Math.min(right - left + 1, ret);
                left++;
            }
            right++;
        }
        return ret == Integer.MAX_VALUE ? 0 : ret;
    }

    public static void main(String[] args) {
        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
        Code209_minimum_size_subarray_sum c = new Code209_minimum_size_subarray_sum();
        int r = c.minSubArrayLen(target, nums);
        System.out.println(r);
    }
}
