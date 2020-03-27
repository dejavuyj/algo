package algorithm;

// BM�ַ���ƥ���㷨
public class BMStringMatching {

	// �����ַ���Ϊascii�Ƚϼ�
	private static final int SIZE = 256;

	private void generateBC(char[] b, int m, int[] bc) {
		for (int i = 0; i < SIZE; i++) {
			bc[i] = -1;
		}
		for (int i = 0; i < m; i++) {
			int ascii = (int) b[i]; // ����b[i]��ASCIIֵ
			bc[ascii] = i;
		}
	}

	public int bm(char[] a, char[] b) {
		int n = a.length;
		int m = b.length;
		int[] bc = new int[SIZE]; // ��¼ģʽ����ÿ���ַ������ֵ�λ��
		generateBC(b, m, bc); // �������ַ���ϣ��
		int[] suffix = new int[m];
		boolean[] prefix = new boolean[m];
		generateGS(b, m, suffix, prefix);
		int i = 0; // i��ʾ������ģʽ������ĵ�һ���ַ�
		while (i <= n - m) {
			int j;
			for (j = m - 1; j >= 0; j--) { // ģʽ���Ӻ���ǰƥ��
				if (a[i + j] != b[j]) { // ���ַ���Ӧģʽ���е��±���j
					break;
				}
			}
			if (j < 0) {
				return i; // ƥ��ɹ�,����������ģʽ����һ��ƥ����ַ���λ��
			}
			int x = j - bc[(int) a[i + j]]; // ���ַ��ƶ�λ��
			int y = 0;
			if (j < m - 1) {
				// ����кú�׺�Ļ�
				y = moveByGS(j, m, suffix, prefix);
			}
			i = i + Math.max(x, y);
		}
		return -1;
	}

	// b��ʾģʽ��,mΪģʽ������
	private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
		// ��ʼ��
		for (int i = 0; i < m; i++) {
			suffix[i] = -1;
			prefix[i] = false;
		}
		for (int i = 0; i < m - 1; i++) { // b[0, i]
			int j = i;
			int k = 0; // ������׺�Ӵ�����
			while (j >= 0 && b[j] == b[m - 1 - k]) { // ��b[0, m-1]�󹫹���׺�Ӵ�
				j--;
				k++;
				suffix[k] = j + 1; // j+1��ʾ������׺�Ӵ���b[0, i]�е���ʼ�±�
			}
			if (j == -1) {
				prefix[k] = true; // ������׺�Ӵ�Ҳ��ģʽ����ǰ׺�Ӵ�
			}
		}
	}

	// j��ʾ���ַ���Ӧ��ģʽ���е��ַ��±�, m��ʾģʽ������
	private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
		int k = m - 1 - j;// �ú�׺����
		// ���ںú�׺
		if (suffix[k] != -1) {
			return j - suffix[k] + 1;
		}
		// ���Һú�׺��ģʽ����ǰ׺
		for (int r = j + 2; r <= m - 1; r++) {
			if (prefix[m - r] == true) {
				return r;
			}
		}
		return m;
	}

	public static void main(String[] args) {
		BMStringMatching c = new BMStringMatching();
		char[] a = "acjacjamcjdjsdmc".toCharArray();
		char[] b = "cjamc".toCharArray();
		System.out.println(c.bm(a, b));
	}
}