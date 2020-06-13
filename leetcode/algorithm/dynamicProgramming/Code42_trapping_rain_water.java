package leetcode.algorithm.dynamicProgramming;

public class Code42_trapping_rain_water {

	// 按列,暴力解法,时间复杂度O(n^2),空间复杂度O(1)
	public int trap(int[] height) {
		int sum = 0;
		for (int i = 1; i < height.length - 1; i++) {
			int currentHeight = height[i];
			int leftMax = Integer.MIN_VALUE;
			for (int l = 0; l < i; l++) {
				if (height[l] > leftMax) {
					leftMax = height[l];
				}
			}
			int rightMax = Integer.MIN_VALUE;
			for (int r = i + 1; r < height.length; r++) {
				if (height[r] > rightMax) {
					rightMax = height[r];
				}
			}
			int min = Math.min(leftMax, rightMax);
			if (min > currentHeight) {
				sum += min - currentHeight;
			}
		}
		return sum;
	}

	// 动态规划,时间复杂度O(n),空间复杂度O(n)
	public int trap2(int[] height) {
		int sum = 0;
		int[] leftMax = new int[height.length];
		int[] rightMax = new int[height.length];
		// 记录每个index左侧的最高值
		for (int i = 1; i < height.length; i++) {
			leftMax[i] = Math.max(height[i - 1], leftMax[i - 1]);
		}
		// 记录每个index右侧的最高值
		for (int i = height.length - 2; i >= 0; i--) {
			rightMax[i] = Math.max(height[i + 1], rightMax[i + 1]);
		}
		for (int i = 1; i < height.length - 1; i++) {
			int currentHeight = height[i];
			int min = Math.min(leftMax[i], rightMax[i]);
			if (min > currentHeight) {
				sum += min - currentHeight;
			}
		}
		return sum;
	}

	// 双指针,时间复杂度O(n),空间复杂度O(1)
	public int trap3(int[] height) {
		int sum = 0;
		int left = 1;
		int right = height.length - 2;
		int left_max = 0;
		int right_max = 0;
		for (int i = 1; i < height.length - 1; i++) {
			if (height[left - 1] < height[right + 1]) {
				// 从左到右
				left_max = Math.max(left_max, height[left - 1]);
				if (left_max > height[left]) {
					sum += left_max - height[left];
				}
				left++;
			} else {
				// 从右到左
				right_max = Math.max(right_max, height[right + 1]);
				if (right_max > height[right]) {
					sum += right_max - height[right];
				}
				right--;
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] arr = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		Code42_trapping_rain_water c = new Code42_trapping_rain_water();
		System.out.println(c.trap3(arr));
	}
}
