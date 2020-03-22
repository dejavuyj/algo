package algorithm.sort;

//快速排序
public class QuickSort {

	private static int swapCount = 0;

	private void quickSort(int[] a, int p, int r) {
		if (p >= r) {
			return;
		}

		int q = partition(a, p, r);
		quickSort(a, p, q - 1);
		quickSort(a, q + 1, r);
	}

	// 三数取中法
	private int getPivotMid(int[] a, int p, int r) {
		int pivot;
		int mid = p + ((r - p) >> 1);
		if (a[p] > Math.min(a[mid], a[r]) && a[p] <= Math.max(a[mid], a[r])) {
			pivot = a[p];
		} else if (a[mid] > Math.min(a[p], a[r])
				&& a[mid] <= Math.max(a[p], a[r])) {
			pivot = a[mid];
		} else {
			pivot = a[r];
		}
		return pivot;
	}

	// 随机法
	private int getPivotRandom(int[] a, int p, int r) {
		int random = p + (int) (Math.random() * (r - p));
		return a[random];
	}

	private int partition(int[] a, int p, int r) {
		int pivot = -1;
		// pivot = getPivotMid(a, p, r);
		pivot = getPivotRandom(a, p, r);

		int i = p;
		for (int j = p; j <= r; j++) {
			if (a[j] < pivot) {
				int tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
				i++;

				swapCount++;
				System.out.println("swap " + i + ", " + j);
			}
		}
		a[r] = a[i];
		a[i] = pivot;
		return i;
	}

	public void quickSort(int[] a) {
		quickSort(a, 0, a.length - 1);
	}

	public static void main(String[] args) {
		QuickSort c = new QuickSort();
		int[] a = { 3, 2, 7, 6, 9, 5, 1, 8, 4 };
		// int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		c.quickSort(a);
		for (int i : a) {
			System.out.print(i + ", ");
		}
		System.out.println("total swapCount is " + swapCount);
	}
}