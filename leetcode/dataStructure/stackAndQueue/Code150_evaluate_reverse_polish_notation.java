package leetcode.dataStructure.stackAndQueue;

import java.util.Stack;

public class Code150_evaluate_reverse_polish_notation {

	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<Integer>();
		for (String s : tokens) {
			switch (s) {
			case "+":
				stack.push(stack.pop() + stack.pop());
				break;
			case "-":
				stack.push((stack.pop() - stack.pop()) * -1);
				break;
			case "*":
				stack.push(stack.pop() * stack.pop());
				break;
			case "/":
				Integer n1 = stack.pop();
				stack.push(stack.pop() / n1);
				break;
			default:
				stack.push(Integer.valueOf(s));
				break;
			}
		}
		return stack.pop();
	}

	// 用数组模拟栈
	public int evalRPN2(String[] tokens) {
		int[] stack = new int[tokens.length / 2 + 1];
		int index = 0;
		for (String s : tokens) {
			switch (s) {
			case "+":
				stack[index - 2] += stack[--index];
				break;
			case "-":
				stack[index - 2] -= stack[--index];
				break;
			case "*":
				stack[index - 2] *= stack[--index];
				break;
			case "/":
				stack[index - 2] /= stack[--index];
				break;
			default:
				stack[index++] = Integer.parseInt(s);
				break;
			}
		}
		return stack[0];
	}

	public static void main(String[] args) {
		String[] arr = { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" };
		Code150_evaluate_reverse_polish_notation c = new Code150_evaluate_reverse_polish_notation();
		System.out.println(c.evalRPN2(arr));
	}
}
