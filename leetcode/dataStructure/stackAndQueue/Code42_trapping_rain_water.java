package leetcode.dataStructure.stackAndQueue;

import java.util.Stack;

public class Code42_trapping_rain_water {

	//栈,时间复杂度O(n),空间复杂度O(n)
	public int trap(int[] height) {
		int sum = 0;
		int current = 0;
		Stack<Integer> stack = new Stack<Integer>();
		while (current < height.length) {
			while (!stack.empty() && height[current] > height[stack.peek()]) {
				int h = height[stack.pop()];
				if (stack.empty()) {
					break;
				}
				int distance = current - stack.peek() - 1;
				int min = Math.min(height[stack.peek()], height[current]);
				sum += distance * (min - h);
			}
			stack.push(current);
			current++;
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] arr = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		Code42_trapping_rain_water c = new Code42_trapping_rain_water();
		System.out.println(c.trap(arr));
	}
}
