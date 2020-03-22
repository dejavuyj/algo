package interview;

import java.util.Stack;

public class ValidParentheses {

	// Q1: 给定一个只包括 '('，')'的字符串，判断字符串是否有效。注：空字符串属于有效字符串 leetcode20
	// 空间复杂度O(n)
	public boolean isValid(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < str.length(); i++) {
			Character c = str.charAt(i);
			if (c.equals('(')) {
				stack.push(c);
			} else {
				if (stack.empty()) {
					return false;
				}
				stack.pop();
			}
		}
		return stack.empty();
	}

	// Q1-优化空间复杂度
	public boolean isValid2(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		int leftNum = 0;
		for (int i = 0; i < str.length(); i++) {
			Character c = str.charAt(i);
			if (c.equals('(')) {
				leftNum++;
			} else {
				if (leftNum <= 0) {
					return false;
				}
				leftNum--;
			}
		}
		return leftNum == 0;
	}

	// Q2: 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。 leetcode32
	// Q2-用栈,空间复杂度O(n)
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

	// Q2-不用栈,空间复杂度为O(1)
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
		ValidParentheses c = new ValidParentheses();
		String str = "(())";
		// String str = "())(";
		System.out.println(c.isValid2(str));
	}
}