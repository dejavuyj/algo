package leetcode.algorithm.dynamicProgramming;

public class Code55_jump_game {

    // dp
    public boolean canJump(int[] nums) {
        boolean flag = true;
        int zeroPos = 0;
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;
        for (int i = nums.length - 2; i >= 0; i--) {
            dp[i] = true;
            if (nums[i] == 0) {
                dp[i] = false;
                flag = false;
                zeroPos = i;
                continue;
            }
            if (!flag) {
                if (nums[i] <= zeroPos - i) {
                    dp[i] = false;
                } else {
                    for (int j = zeroPos; j <= Math.min(i + nums[i], nums.length - 1); j++) {
                        flag |= dp[j];
                    }
                    dp[i] = flag;
                }
            }
        }
        return flag;
    }

    // 贪心
    public boolean canJump2(int[] nums) {
        int maxRight = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= maxRight) {
                maxRight = Math.max(maxRight, i + nums[i]);
                if (maxRight >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canJump3(int[] nums) {
        int lastIndex = nums.length - 1;
        for (int i=nums.length-2; i >= 0; i--) {
            if (nums[i] >= lastIndex - i) {
                lastIndex = i;
            } else if (i == 0) {
                return false;
            }

        }
        return true;
    }

    public static void main(String[] args) {
        Code55_jump_game c = new Code55_jump_game();
        int[] M = {1,3,0,1,4};
        boolean ans = c.canJump3(M);
        System.out.print(ans);
    }
}