package algorithm.sort;

//计数排序
public class CountingSort {

	public void countingSort(int[] a) {
		if (a == null || a.length <= 1) {
			return;
		}
		int nSize = a.length;
		int max = a[0];
		for (int i = 1; i < nSize; i++) {
			if (a[i] > max) {
				max = a[i];
			}
		}
		// 用一个数组记录每个值出现的次数
		int[] c = new int[max + 1];
		for (int i = 0; i < nSize; i++) {
			c[a[i]]++;
		}
		// 从前往后累加
		for (int i = 1; i < max + 1; i++) {
			c[i] += c[i - 1];
		}

		// 临时数组,存储排序之后的结果
		// 从后向前遍历是稳定排序,从前向后排序则是不稳定排序
		// 因为从c数组定位index是用递减来求出来的,所以从后往前遍历能保证相同数据,原数组里后面的能继续保持在相对靠后的位置
		int[] r = new int[nSize];
		for (int i = nSize - 1; i >= 0; i--) {
			int index = c[a[i]] - 1;
			r[index] = a[i];
			c[a[i]]--;
		}

		// 把临时数组拷贝回原数组
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
