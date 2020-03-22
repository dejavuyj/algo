package algorithm.sort;

//选择排序
public class SelectionSort {

	// 每次把最小的移动到最前面
	public void selectionSort(int[] a) {
		if (a == null || a.length <= 1) {
			return;
		}
		int nSize = a.length;
		for (int i = 0; i < nSize; i++) {
			for (int j = i + 1; j < nSize; j++) {
				if (a[j] < a[i]) {
					int tmp = a[i];
					a[i] = a[j];
					a[j] = tmp;
				}
			}
		}
	}

	public static void main(String[] args) {
		SelectionSort c = new SelectionSort();
		int[] a = { 3, 2, 1, 6, 4, 5 };
		c.selectionSort(a);
		for (int i : a) {
			System.out.print(i + ", ");
		}
	}
}
