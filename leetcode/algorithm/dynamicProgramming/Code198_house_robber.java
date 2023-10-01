package leetcode.algorithm.dynamicProgramming;

public class Code198_house_robber {

    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        } else if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[len - 1];
    }

    public int rob2(int[] nums) {
        int prev = 0;
        int curr = 0;
        for (int i : nums) {
            int temp = Math.max(prev + i, curr);
            prev = curr;
            curr = temp;
        }
        return curr;
    }

    public static void main(String[] args) {
        Code198_house_robber c = new Code198_house_robber();
        int[] nums = {1, 2, 9, 9};
        System.out.println(c.rob(nums));
    }
}