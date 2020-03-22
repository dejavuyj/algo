package leetcode.dataStructure.stackAndQueue;

import java.util.PriorityQueue;

public class Code703_kth_largest_element_in_a_stream {

	final PriorityQueue<Integer> q;
	final int k;

	public Code703_kth_largest_element_in_a_stream(int k, int[] nums) {
		this.k = k;
		q = new PriorityQueue<Integer>(k);
		for (int n : nums) {
			add(n);
		}
	}

	public int add(int val) {
		if (q.size() < k) {
			q.add(val);
		} else if (q.peek() < val) {
			q.poll();
			q.offer(val);
		}
		return q.peek();
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 4, 5, 8, 2 };
		Code703_kth_largest_element_in_a_stream c = new Code703_kth_largest_element_in_a_stream(3, arr);
		System.out.println(c.add(3));
		System.out.println(c.add(5));
		System.out.println(c.add(10));
		System.out.println(c.add(9));
		System.out.println(c.add(4));
	}
}
