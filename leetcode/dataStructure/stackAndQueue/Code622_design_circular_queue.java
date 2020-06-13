package leetcode.dataStructure.stackAndQueue;

public class Code622_design_circular_queue {

	int[] array;
	int cap;
	int size;
	int head;
	int tail;

	/** Initialize your data structure here. Set the size of the queue to be k. */
	public Code622_design_circular_queue(int k) {
		cap = k;
		size = 0;
		head = -1;
		tail = -1;
		array = new int[cap];
	}

	/**
	 * Insert an element into the circular queue. Return true if the operation
	 * is successful.
	 */
	public boolean enQueue(int value) {
		if (size == cap) {
			return false;
		}
		tail = (tail + 1) % cap;
		array[tail] = value;
		if(size == 0) {
			head = tail;
		}
		size++;
		return true;
	}
	
	/**
	 * Delete an element from the circular queue. Return true if the operation
	 * is successful.
	 */
	public boolean deQueue() {
		if (size == 0) {
			return false;
		}
		if (size == 1) {
			head = tail = -1;
		} else {			
			head = (head + 1) % cap;
		}
		size--;
		return true;
	}

	/** Get the front item from the queue. */
	public int Front() {
		if (size == 0) {
			return -1;
		}
		return array[head];
	}

	/** Get the last item from the queue. */
	public int Rear() {
		if (size == 0) {
			return -1;
		}
		return array[tail];
	}

	/** Checks whether the circular queue is empty or not. */
	public boolean isEmpty() {
		return size == 0;
	}

	/** Checks whether the circular queue is full or not. */
	public boolean isFull() {
		return size == cap;
	}

	public static void main(String[] args) {
		Code622_design_circular_queue c = new Code622_design_circular_queue(3);
		System.out.println(c.enQueue(1));
		System.out.println(c.enQueue(2));
		System.out.println(c.enQueue(3));
		System.out.println(c.enQueue(4));
		System.out.println(c.Rear());
		System.out.println(c.isFull());
		System.out.println(c.deQueue());
		System.out.println(c.enQueue(4));
		System.out.println(c.Rear());
	}
}


class MyCircularQueue {

	int[] array;
	int size = 0;
	int head = 0;
	int tail = 0;

	/** Initialize your data structure here. Set the size of the queue to be k. */
	public MyCircularQueue(int k) {
		size = k + 1;
		array = new int[size];
	}

	/**
	 * Insert an element into the circular queue. Return true if the operation
	 * is successful.
	 */
	public boolean enQueue(int value) {
		if (isFull()) {
			return false;
		}
		array[tail] = value;
		tail = (tail + 1) % size;
		return true;
	}

	/**
	 * Delete an element from the circular queue. Return true if the operation
	 * is successful.
	 */
	public boolean deQueue() {
		if (isEmpty()) {
			return false;
		}
		head = (head + 1) % size;
		return true;
	}

	/** Get the front item from the queue. */
	public int Front() {
		if (isEmpty()) {
			return -1;
		}
		return array[head];
	}

	/** Get the last item from the queue. */
	public int Rear() {
		if (isEmpty()) {
			return -1;
		}
		int tailIndex = -1;
		if(tail == 0) {
			tailIndex = size - 1;
		} else {
			tailIndex = tail - 1;
		}
		return array[tailIndex];
	}

	/** Checks whether the circular queue is empty or not. */
	public boolean isEmpty() {
		return head == tail;
	}

	/** Checks whether the circular queue is full or not. */
	public boolean isFull() {
		return (tail + 1) % size == head;
	}
}