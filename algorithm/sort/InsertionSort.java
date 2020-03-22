package algorithm.sort;

//插入排序
public class InsertionSort {

	// 把数据分为已排序区和未排序区
	// 每次把一个未排序区的数据插入到已排序区,并保持已排序区始终有序
	public void insertionSort(int[] a) {
		if (a == null || a.length <= 1) {
			return;
		}
		int nSize = a.length;
		// 0~i为已排序区
		for (int i = 1; i < nSize; i++) {
			int value = a[i];
			int j = i - 1;
			for (; j >= 0; j--) {
				if (a[j] > value) {
					a[j + 1] = a[j];
				} else {
					break;
				}
			}
			a[j + 1] = value;
		}
	}

	public static void main(String[] args) {
		InsertionSort c = new InsertionSort();
		int[] a = { 2, 1, 3, 7, 6, 4, 5 };
		c.insertionSort(a);
		for (int i : a) {
			System.out.print(i + ", ");
		}
	}
}
