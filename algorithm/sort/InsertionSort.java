package algorithm.sort;

//��������
public class InsertionSort {

	// �����ݷ�Ϊ����������δ������
	// ÿ�ΰ�һ��δ�����������ݲ��뵽��������,��������������ʼ������
	public void insertionSort(int[] a) {
		if (a == null || a.length <= 1) {
			return;
		}
		int nSize = a.length;
		// 0~iΪ��������
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
