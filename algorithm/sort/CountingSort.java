package algorithm.sort;

public class CountingSort {

	public void countingSort(int[] a) {
		if (a == null || a.length <= 1) {
			return;
		}
		int nSize = a.length;
		int max = a[0];
		for (int i = 1; i < nSize; i++) {
			if (a[i] > max) {
				max = a[i];			}
		}

		int[] c = new int[max + 1];
		for (int i = 0; i < nSize; i++) {
			c[a[i]]++;
		}

		for (int i = 1; i < max + 1; i++) {
			c[i] += c[i - 1];
		}

		int[] r = new int[nSize];
		for (int i = nSize - 1; i >= 0; i--) {
			int index = c[a[i]] - 1;
			r[index] = a[i];
			c[a[i]]--;
		}

		for (int i = 0; i < nSize; i++) {
			a[i] = r[i];
		}
	}

	public static void main(String[] args) {
		CountingSort c = new CountingSort();
		int[] a = { 2, 5, 3, 0, 2, 3, 0, 3 };
		c.countingSort(a);
		for (int i : a) {
			System.out.print(i + ", ");
		}
	}
}
