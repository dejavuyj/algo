package leetcode.algorithm.dynamicProgramming;

public class Code45_jump_game_ii {

    public int jump(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];

        for (int i = 0; i < len; i++) {
            int n = nums[i];
            int right = Math.min(i + n, len - 1);
            for (int j = i + 1; j <= right; j++) {
                if (dp[j] == 0) {
                    dp[j] = dp[i] + 1;
                } else {
                    dp[j] = Math.min(dp[j], dp[i] + 1);
                }
            }
        }

        return dp[len - 1];
    }

    public int jump2(int[] nums) {
        int len = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;

        for (int i = 0; i < len - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }

        return steps;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 2, 4, 2, 3};
        Code45_jump_game_ii c = new Code45_jump_game_ii();
        System.out.println(c.jump2(arr));
    }
}
