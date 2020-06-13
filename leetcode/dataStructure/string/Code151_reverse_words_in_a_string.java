package leetcode.dataStructure.string;

public class Code151_reverse_words_in_a_string {

	public String reverseWords(String s) {
		StringBuilder sb = new StringBuilder();
		String[] strs = s.trim().split(" ");
		for (int i = strs.length - 1; i >= 0; i--) {
			if (strs[i].equals("")) {
				continue;
			}
			sb.append(strs[i] + " ");
		}
		return sb.toString().trim();
	}

	// 双指针
	public String reverseWords2(String s) {
		s = s.trim();
		int j = s.length() - 1;
		int i = j;
		StringBuilder sb = new StringBuilder();
		while (i >= 0) {
			while (i >= 0 && s.charAt(i) != ' ')
				i--; // 找到单词的起始位置
			sb.append(s.substring(i + 1, j + 1) + " ");
			while (i >= 0 && s.charAt(i) == ' ')
				i--; // 跳过单词间的空格
			j = i; // j指向下个单词的词尾
		}
		return sb.toString().trim();
	}

	public static void main(String[] args) {
		String s = "a good   example";
		Code151_reverse_words_in_a_string c = new Code151_reverse_words_in_a_string();
		System.out.println(c.reverseWords(s));
	}
}
