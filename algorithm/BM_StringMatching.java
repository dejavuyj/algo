package algorithm;

// BM字符串匹配算法
public class BM_StringMatching {

	// 假设字符集为ascii比较简单
	private static final int SIZE = 256;

	private void generateBC(char[] b, int m, int[] bc) {
		for (int i = 0; i < SIZE; i++) {
			bc[i] = -1;
		}
		for (int i = 0; i < m; i++) {
			int ascii = (int) b[i]; // 计算b[i]的ASCII值
			bc[ascii] = i;
		}
	}

	public int bm(char[] a, char[] b) {
		int n = a.length;
		int m = b.length;
		int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
		generateBC(b, m, bc); // 构建坏字符哈希表
		int[] suffix = new int[m];
		boolean[] prefix = new boolean[m];
		generateGS(b, m, suffix, prefix);
		int i = 0; // i表示主串与模式串对齐的第一个字符
		while (i <= n - m) {
			int j;
			for (j = m - 1; j >= 0; j--) { // 模式串从后往前匹配
				if (a[i + j] != b[j]) { // 坏字符对应模式串中的下标是j
					break;
				}
			}
			if (j < 0) {
				return i; // 匹配成功,返回主串与模式串第一个匹配的字符的位置
			}
			int x = j - bc[(int) a[i + j]]; // 坏字符移动位数
			int y = 0;
			if (j < m - 1) {
				// 如果有好后缀的话
				y = moveByGS(j, m, suffix, prefix);
			}
			i = i + Math.max(x, y);
		}
		return -1;
	}

	// b表示模式串,m为模式串长度
	private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
		// 初始化
		for (int i = 0; i < m; i++) {
			suffix[i] = -1;
			prefix[i] = false;
		}
		for (int i = 0; i < m - 1; i++) { // b[0, i]
			int j = i;
			int k = 0; // 公共后缀子串长度
			while (j >= 0 && b[j] == b[m - 1 - k]) { // 与b[0, m-1]求公共后缀子串
				j--;
				k++;
				suffix[k] = j + 1; // j+1表示公共后缀子串在b[0, i]中的起始下标
			}
			if (j == -1) {
				prefix[k] = true; // 公共后缀子串也是模式串的前缀子串
			}
		}
	}

	// j表示坏字符对应的模式串中的字符下标, m表示模式串长度
	private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
		int k = m - 1 - j;// 好后缀长度
		// 存在好后缀
		if (suffix[k] != -1) {
			return j - suffix[k] + 1;
		}
		// 查找好后缀与模式串的前缀
		for (int r = j + 2; r <= m - 1; r++) {
			if (prefix[m - r] == true) {
				return r;
			}
		}
		return m;
	}

	public static void main(String[] args) {
		BM_StringMatching c = new BM_StringMatching();
		char[] a = "acjacjamcjdjsdmc".toCharArray();
		char[] b = "cjamc".toCharArray();
		System.out.println(c.bm(a, b));
	}
}