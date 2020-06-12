package leetcode.dataStructure.stackAndQueue;

public class Code641_design_circular_deque {

	public static void main(String[] args) {
		MyCircularDeque c = new MyCircularDeque(3);
		System.out.println(c.insertLast(1));
		System.out.println(c.insertLast(2));
		System.out.println(c.insertLast(3));
		System.out.println(c.insertLast(4));
		System.out.println(c.getRear());
		System.out.println(c.isFull());
		System.out.println(c.deleteLast());
		System.out.println(c.insertFront(4));
		System.out.println(c.getFront());
	}
}

class MyCircularDeque {

	int[] array;
	int cap;
	int size;
	int head;
	int tail;

	/**
	 * Initialize your data structure here. Set the size of the deque to be k.
	 */
	public MyCircularDeque(int k) {
		head = -1;
		tail = -1;
		cap = k;
		array = new int[cap];
		size = 0;
	}

	/**
	 * Adds an item at the front of Deque. Return true if the operation is
	 * successful.
	 */
	public boolean insertFront(int value) {
		if (isFull()) {
			return false;
		}
		if (head == -1) {
			head = 0;
			tail = 0;
		} else {
			head = (head - 1 + cap) % cap;
		}
		array[head] = value;
		size++;
		return true;
	}

	/**
	 * Adds an item at the rear of Deque. Return true if the operation is
	 * successful.
	 */
	public boolean insertLast(int value) {
		if (isFull()) {
			return false;
		}
		if (tail == -1) {
			head = 0;
			tail = 0;
		} else {
			tail = (tail + 1) % cap;
		}
		array[tail] = value;
		size++;
		return true;
	}

	/**
	 * Deletes an item from the front of Deque. Return true if the operation is
	 * successful.
	 */
	public boolean deleteFront() {
		if (isEmpty()) {
			return false;
		}
		head = (head + 1) % cap;
		size--;
		return true;
	}

	/**
	 * Deletes an item from the rear of Deque. Return true if the operation is
	 * successful.
	 */
	public boolean deleteLast() {
		if (isEmpty()) {
			return false;
		}
		tail = (tail - 1 + cap) % cap;
		size--;
		return true;

	}

	/** Get the front item from the deque. */
	public int getFront() {
		if (isEmpty()) {
			return -1;
		}
		return array[head];
	}

	/** Get the last item from the deque. */
	public int getRear() {
		if (isEmpty()) {
			return -1;
		}
		return array[tail];
	}

	/** Checks whether the circular deque is empty or not. */
	public boolean isEmpty() {
		return size == 0;
	}

	/** Checks whether the circular deque is full or not. */
	public boolean isFull() {
		return size == cap;
	}
}