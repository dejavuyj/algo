package leetcode.dataStructure.stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

public class Code155_min_stack {
	Deque<Integer> xStack;
	Deque<Integer> minStack;

	public Code155_min_stack() {
		xStack = new LinkedList<>();
		minStack = new LinkedList<>();
		minStack.push(Integer.MAX_VALUE);
	}

	public void push(int val) {
		xStack.push(val);
		minStack.push(Math.min(minStack.peek(), val));
	}

	public void pop() {
		xStack.pop();
		minStack.pop();
	}

	public int top() {
		return xStack.peek();
	}

	public int getMin() {
		return minStack.peek();
	}

	public static void main(String[] args) {
		Code155_min_stack minStack = new Code155_min_stack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin()); //   --> 返回 -3.
		minStack.pop();
		System.out.println(minStack.top()); //      --> 返回 0.
		System.out.println(minStack.getMin()); //   --> 返回 -2.
	}
}
