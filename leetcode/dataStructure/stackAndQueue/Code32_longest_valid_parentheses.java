package leetcode.dataStructure.stackAndQueue;

import java.util.Stack;

public class Code32_longest_valid_parentheses {

	// 用栈,空间复杂度O(n)
	public int longestValidParentheses(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(-1);

		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);
			if (c == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.empty()) {
					stack.push(i);
				} else {
					max = Math.max(max, i - stack.peek());
				}
			}
		}
		return max;
	}

	// 不用栈,空间复杂度为O(1)
	public int longestValidParentheses2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int left = 0;
		int right = 0;
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);
			if (c == '(') {
				left++;
			} else {
				right++;
			}
			if (left == right) {
				max = Math.max(max, left << 1);
			}
			if (right > left) {
				left = right = 0;
			}
		}
		left = right = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			Character c = s.charAt(i);
			if (c == ')') {
				right++;
			} else {
				left++;
			}
			if (left == right) {
				max = Math.max(max, left << 1);
			}
			if (left > right) {
				left = right = 0;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		Code32_longest_valid_parentheses c = new Code32_longest_valid_parentheses();
		String s = "(()";
		// String s = ")()())";
		// String s = "()(()";
		// String s = ")()())";
		System.out.println(c.longestValidParentheses2(s));
	}
}
