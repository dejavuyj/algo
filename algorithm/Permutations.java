package algorithm;

/**ȫ����
 * �磬1��2��3 ���� 3 �����ݣ��������⼸�ֲ�ͬ������
	1, 2, 3
	1, 3, 2
	2, 1, 3
	2, 3, 1
	3, 1, 2
	3, 2, 1
*/
public class Permutations {

	// ���÷�ʽ��
	// int[]a = a={1, 2, 3, 4}; printPermutations(a, 4, 4);
	// k��ʾҪ���������������ݸ���
	public void printPermutations(int[] data, int n, int k) {
		if (k == 1) {
			for (int i = 0; i < n; ++i) {
				System.out.print(data[i] + " ");
			}
			System.out.println();
		}

		for (int i = 0; i < k; ++i) {
			int tmp = data[i];
			data[i] = data[k - 1];
			data[k - 1] = tmp;

			printPermutations(data, n, k - 1);

			tmp = data[i];
			data[i] = data[k - 1];
			data[k - 1] = tmp;
		}
	}

	public static void main(String[] args) {
		Permutations c = new Permutations();
		int[] a = {1, 2, 3};
		c.printPermutations(a, 3, 3);
	}
}