package _dataStructure;

public class Heap {
	private int[] a; // 从1开始存储数据
	private int n; // 堆可以存储的最大数据个数
	private int count; // 堆已经存储的数据个数

	private static int swapCount = 0;

	public Heap(int capacity) {
		a = new int[capacity + 1];
		n = capacity;
		count = 0;
	}

	private static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;

		swapCount++;
		System.out.println("swap " + i + ", " + j);
	}

	// 从下向上堆化
	public void insert(int data) {
		if (count >= n) {
			return;
		}
		count++;
		int cur = count;
		a[cur] = data;
		int parent = cur / 2;
		while (parent >= 1 && a[parent] < a[cur]) {
			swap(a, cur, parent);
			cur = parent;
			parent = cur / 2;
		}
	}

	public void removeMax() {
		if (count == 0) {
			return;
		}

		a[1] = a[count];
		count--;
		heapify(a, count, 1);
	}

	// 从上往下堆化
	private static void heapify(int[] a, int n, int i) {
		while (true) {
			int maxPos = i;
			if (i * 2 <= n && a[i] < a[i * 2])
				maxPos = i * 2;
			if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1])
				maxPos = i * 2 + 1;
			if (maxPos == i)
				break;
			swap(a, i, maxPos);
			i = maxPos;
		}
	}

	public static void buildHeap(int[] a, int n) {
		for (int i = n / 2; i >= 1; i--) {
			heapify(a, n, i);
		}
	}

	// n表示数据的个数, 数组a中的数据从下标1到n的位置
	public static void sort(int[] a, int n) {
		buildHeap(a, n);
		int k = n;
		while (k > 1) {
			swap(a, 1, k);
			k--;
			heapify(a, k, 1);
		}
	}

	public static void main(String[] args) {
		int[] a = { 0, 3, 2, 7, 6, 9, 5, 1, 8, 4 };
		// int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		sort(a, a.length - 1);
		for (int i = 1; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("total swapCount is " + swapCount);
	}
}