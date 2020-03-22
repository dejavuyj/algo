package algorithm.sort;

//ð������
public class BubbleSort {

	// ÿ�ΰ������Ƶ������
	// ���һ��ѭ����,û���ƶ�����,���ʾ�Ѿ��������,ֱ���˳�
	public void bubbleSort(int[] a) {
		if (a == null || a.length <= 1) {
			return;
		}
		int nSize = a.length;
		for (int i = 0; i < nSize; i++) {
			boolean flag = false;
			for (int j = 0; j < nSize - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					int tmp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = tmp;
					flag = true;
				}
			}
			if (!flag) {
				break;
			}
		}
	}

	public static void main(String[] args) {
		BubbleSort c = new BubbleSort();
		int[] a = { 3, 2, 1, 6, 4, 5 };
		c.bubbleSort(a);
		for (int i : a) {
			System.out.print(i + ", ");
		}
	}
}
