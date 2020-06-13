package algorithm.sort;

//归并排序
public class MergeSort {

	private void mergeSort(int[] a, int start, int end) {
		if (start >= end) {
			return;
		}

		// int mid = (end + start) / 2;
		int mid = start + ((end - start) >> 1);
		mergeSort(a, start, mid);
		mergeSort(a, mid + 1, end);

		merge(a, start, end, mid);
	}

	// p-start q-mid r-end
	// i-left j-right
	private void merge(int[] a, int start, int end, int mid) {
		// 合并数据
		int[] tmp = new int[end - start + 1];

		int left = start;
		int right = mid + 1;
		int k = 0;

		while (left <= mid && right <= end) {
			if (a[left] <= a[right]) {
				tmp[k++] = a[left++];
			} else {
				tmp[k++] = a[right++];
			}
		}

		while (left <= mid) {
			tmp[k++] = a[left++];
		}
		while (right <= end) {
			tmp[k++] = a[right++];
		}

		for (int i = 0; i <= end - start; i++) {
			a[start + i] = tmp[i];
		}
	}

	public void mergeSort(int[] a) {
		mergeSort(a, 0, a.length - 1);
	}

	public static void main(String[] args) {
		MergeSort c = new MergeSort();
		int[] a = { 3, 2, 1, 6, 4, 5, 7, 8, 9 };
		c.mergeSort(a);
		for (int i : a) {
			System.out.print(i + ", ");
		}
	}
}
