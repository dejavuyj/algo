package leetcode.dataStructure.stackAndQueue;

import java.util.PriorityQueue;
import java.util.Random;

public class Code215_kth_largest_element_in_a_array {

	// 堆,时间复杂度O(nlogk)
	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(k);
		for (int i : nums) {
			if (q.size() < k) {
				q.add(i);
			} else {
				if (i > q.peek()) {
					q.poll();
					q.add(i);
				}
			}
		}
		return q.peek();
	}

	private void swap(int[] nums, int a, int b) {
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}

	private int partition(int[] nums, int low, int high) {
		Random randomNum = new Random();
		int pivotIndex = low + randomNum.nextInt(high - low);
		int pivot = nums[pivotIndex];
		swap(nums, pivotIndex, high);
		int i = low;
		for (int j = low; j <= high; j++) {
			if (nums[j] > pivot) {
				swap(nums, i, j);
				i++;
			}
		}
		swap(nums, i, high);
		return i;
	}

	// 随机选择 时间复杂度O(n)
	public int findKthLargest2(int[] nums, int k) {
		int p = -1;
		k--;
		int low = 0;
		int high = nums.length - 1;
		while(p != k) {
			if(low == high) {
				return nums[low];
			}
			p = partition(nums, low, high);
			if(p < k) {
				low = p + 1;
			} else if(p > k) {
				high = p - 1;
			}
		}
		return nums[k];
	}

	public static void main(String[] args) {
		Code215_kth_largest_element_in_a_array c = new Code215_kth_largest_element_in_a_array();
		int[] arr = new int[] { 1 };
		int k = 1;
		System.out.println(c.findKthLargest2(arr, k));
	}
}
