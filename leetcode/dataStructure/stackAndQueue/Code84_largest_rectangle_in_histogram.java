package leetcode.dataStructure.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Code84_largest_rectangle_in_histogram {

    // 暴力解法,时间复杂度O(n^2),空间复杂度O(1)
    public int largestRectangleArea(int[] height) {
        int max = 0;
        for (int i = 0; i <= height.length - 1; i++) {
            int currentHeight = height[i];
            int left = i;
            while (left >= 0 && height[left] >= currentHeight) {
                left--;
            }
            int right = i;
            while (right < height.length && height[right] >= currentHeight) {
                right++;
            }

            int currArea = (right - left - 1) * currentHeight;
            max = Math.max(max, currArea);
        }
        return max;
    }

    // 单调栈,时间复杂度O(n),空间复杂度O(n)
    public int largestRectangleArea2(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Deque<Integer> mono_stack = new ArrayDeque<>();
        for (int i = 0; i <= n - 1; i++) {
            while (!mono_stack.isEmpty() && height[mono_stack.peek()] >= height[i]) {
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }
        mono_stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!mono_stack.isEmpty() && height[mono_stack.peek()] >= height[i]) {
                mono_stack.pop();
            }
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
            mono_stack.push(i);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, (right[i] - left[i] - 1) * height[i]);
        }
        return max;
    }

    // 单调栈优化,时间复杂度O(n),空间复杂度O(n)
    public int largestRectangleArea3(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Deque<Integer> mono_stack = new ArrayDeque<>();
        for (int i = 0; i <= n - 1; i++) {
            while (!mono_stack.isEmpty() && height[mono_stack.peek()] >= height[i]) {
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, (right[i] - left[i] - 1) * height[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {6, 7, 5, 2, 4, 5, 9, 3};
        Code84_largest_rectangle_in_histogram c = new Code84_largest_rectangle_in_histogram();
        System.out.println(c.largestRectangleArea3(arr));
    }
}
