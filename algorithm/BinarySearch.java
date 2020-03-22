package algorithm;

public class BinarySearch {

	// 无重复元素-递归
	public int binarySearchNonRepeatRecursion(int[] a, int value) {
		if (a == null || a.length == 0) {
			return -1;
		}
		return binarySearchNonRepeatRecursion(a, 0, a.length - 1, value);
	}

	private int binarySearchNonRepeatRecursion(int[] a, int s, int e, int value) {
		if (s > e) {
			return -1;
		}

		// int mid = (s + e) / 2; // 可能会溢出
		int mid = s + ((e - s) >> 1);
		if (a[mid] == value) {
			return mid;
		} else if (a[mid] > value) {
			return binarySearchNonRepeatRecursion(a, s, mid - 1, value);
		} else {
			return binarySearchNonRepeatRecursion(a, mid + 1, e, value);
		}
	}

	// 无重复元素-非递归
	public int binarySearchNonRepeat(int[] a, int value) {
		if (a == null || a.length == 0) {
			return -1;
		}
		int s = 0;
		int e = a.length - 1;
		while (s <= e) {
			// int mid = (s + e) / 2; // 可能会溢出
			int mid = s + ((e - s) >> 1);
			if (a[mid] == value) {
				return mid;
			} else if (a[mid] > value) {
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return -1;
	}

	// 变体一：查找第一个值等于给定值的元素
	public int bsearch1(int[] a, int value) {
		int low = 0;
		int high = a.length - 1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (a[mid] > value) {
				high = mid - 1;
			} else if (a[mid] < value) {
				low = mid + 1;
			} else {
				if (mid == 0 || a[mid - 1] != value) {
					return mid;
				}
				high = mid - 1;
			}
		}
		return -1;
	}

	// 变体二：查找最后一个值等于给定值的元素
	public int bsearch2(int[] a, int value) {
		int low = 0;
		int high = a.length - 1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (a[mid] > value) {
				high = mid - 1;
			} else if (a[mid] < value) {
				low = mid + 1;
			} else {
				if (mid == a.length - 1 || a[mid + 1] != value) {
					return mid;
				}
				low = mid + 1;
			}
		}
		return -1;
	}

	// 变体三：查找第一个大于等于给定值的元素
	public int bsearch3(int[] a, int value) {
		int low = 0;
		int high = a.length - 1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (a[mid] >= value) {
				if (mid == 0 || a[mid - 1] < value) {
					return mid;
				}
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	// 变体四：查找最后一个小于等于给定值的元素
	public int bsearch4(int[] a, int value) {
		int low = 0;
		int high = a.length - 1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (a[mid] > value) {
				high = mid - 1;
			} else {
				if (mid == a.length - 1 || a[mid + 1] > value) {
					return mid;
				}
				low = mid + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		BinarySearch c = new BinarySearch();
		int[] a = { 1, 1, 3, 4, 6, 6, 7, 9, 9 };
		// int r = c.binarySearchNonRepeatRecursion(a, 6);
		// int r = c.binarySearchNonRepeat(a, 5);
		// int r = c.bsearch1(a, 1);
		// int r = c.bsearch3(a, 0);
		int r = c.bsearch4(a, 1);
		System.out.println(r);
	}
}
