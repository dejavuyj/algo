package leetcode.other;

import java.util.Arrays;

public class Code16_3sum_closest {

    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        int minDiff = Integer.MAX_VALUE;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(target - sum) < minDiff) {
                        minDiff = Math.abs(target - sum);
                        ans = sum;
                    }
                    if (minDiff == 0) {
                        return target;
                    }
                }
            }
        }
        return ans;
    }

    public int threeSumClosest2(int[] nums, int target) {
        int len = nums.length;
        int minDiff = Integer.MAX_VALUE;
        int ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - sum) < minDiff) {
                    minDiff = Math.abs(target - sum);
                    ans = sum;
                }
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return target;
                }
            }
        }
        return ans;
    }

    public int threeSumClosest3(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left != right) {
                int min = nums[i] + nums[left] + nums[left + 1];
                if (target < min) {
                    if (Math.abs(result - target) > Math.abs(min - target)) {
                        result = min;
                    }
                    break;
                }
                int max = nums[i] + nums[right] + nums[right - 1];
                if (target > max) {
                    if (Math.abs(result - target) > Math.abs(max - target)) {
                        result = max;
                    }
                    break;
                }
                int sum = nums[i] + nums[left] + nums[right];
                // 判断三数之和是否等于target
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
                if (sum > target) {
                    right--;
                    while (left != right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else {
                    left++;
                    while (left != right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                }
            }
            while (i < nums.length - 2 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        Code16_3sum_closest c = new Code16_3sum_closest();
        int ret = c.threeSumClosest2(nums, target);
        System.out.print(ret);
    }
}
