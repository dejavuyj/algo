package leetcode.dataStructure.stackAndQueue;

import java.util.PriorityQueue;

public class Code295_find_median_from_data_stream {

	static class MedianFinder {
		PriorityQueue<Integer> queMin;
		PriorityQueue<Integer> queMax;

		public MedianFinder() {
			queMin = new PriorityQueue<>((a, b) -> (b - a));
			queMax = new PriorityQueue<>((a, b) -> (a - b));
		}

		public void addNum(int num) {
			if (queMin.isEmpty() || num <= queMin.peek()) {
				queMin.offer(num);
				if (queMax.size() + 1 < queMin.size()) {
					queMax.offer(queMin.poll());
				}
			} else {
				queMax.offer(num);
				if (queMax.size() > queMin.size()) {
					queMin.offer(queMax.poll());
				}
			}
		}

		public double findMedian() {
			if (queMin.size() > queMax.size()) {
				return queMin.peek();
			}
			return (queMin.peek() + queMax.peek()) / 2.0;
		}
	}

	public static void main(String[] args) {
		MedianFinder c = new MedianFinder();
		c.addNum(1);
		c.addNum(2);
		System.out.println(c.findMedian());
		c.addNum(3);
		System.out.println(c.findMedian());
	}
}
