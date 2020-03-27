package algorithm;

// KMP�ַ���ƥ���㷨
public class KMP_StringMatching {

	// b��ʾģʽ����m��ʾģʽ���ĳ���
	private static int[] getNexts(char[] b, int m) {
		int[] next = new int[m];
		next[0] = -1;
		int k = -1;
		for (int i = 1; i < m; ++i) {
			while (k != -1 && b[k + 1] != b[i]) {
				k = next[k];
			}
			if (b[k + 1] == b[i]) {
				++k;
			}
			next[i] = k;
		}
		return next;
	}

	// a, b�ֱ���������ģʽ����n, m�ֱ���������ģʽ���ĳ��ȡ�
	public int kmp(char[] a, char[] b) {
		int n = a.length;
		int m = b.length;
		int[] next = getNexts(b, m);
		int j = 0;
		for (int i = 0; i < n; ++i) {
			while (j > 0 && a[i] != b[j]) { // һֱ�ҵ�a[i]��b[j]
				j = next[j - 1] + 1;
			}
			if (a[i] == b[j]) {
				++j;
			}
			if (j == m) { // �ҵ�ƥ��ģʽ������
				return i - m + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		KMP_StringMatching c = new KMP_StringMatching();
		char[] a = "acjacjamcjdjsdmc".toCharArray();
		char[] b = "jamc".toCharArray();
		System.out.println(c.kmp(a, b));
	}
}