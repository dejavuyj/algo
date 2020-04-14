package interview;

public class FindInTwoDimension {

	public boolean Find(int target, int[][] array) {
		if (array == null || array[0] == null) {
			return false;
		}
		int rowLen = array.length;
		int colLen = array[0].length;
		int i = 0;
		for (; i < rowLen; i++) {
			int low = 0;
			int high = colLen - 1;
			while (low <= high) {
				int mid = low + ((high - low) >> 1);
				if (target < array[i][mid]) {
					high = mid - 1;
				} else if (target > array[i][mid]) {
					low = mid + 1;
				} else {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		FindInTwoDimension c = new FindInTwoDimension();
		int[][] array = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 },
				{ 6, 8, 11, 15 } };
		System.out.println(c.Find(7, array));
	}
}