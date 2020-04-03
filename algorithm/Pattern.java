package algorithm;

/**
 * 正则表达式
 */
public class Pattern {

	private boolean matched = false;
	private char[] pattern;

	public Pattern(char[] pattern) {
		this.pattern = pattern;
	}

	private void rmatch(int ti, int pj, char[] text) {
		if (matched) {
			return;
		}
		int tlen = text.length;
		if (pj == pattern.length) { // 正则表达式到结尾
			if (ti == tlen) { // 字符串也到结尾
				matched = true;
			}
			return;
		}
		if (pattern[pj] == '*') { // *匹配任意个字符
			for (int k = 0; k < tlen - ti; k++) {
				rmatch(ti + k, pj + 1, text);
			}
		} else if (pattern[pj] == '?') { // ?匹配0个或者1个字符
			rmatch(ti, pj + 1, text);
			rmatch(ti + 1, pj + 1, text);
		} else if (ti < tlen && pattern[pj] == text[ti]) { // 纯字符匹配
			rmatch(ti + 1, pj + 1, text);
		}
	}

	public boolean match(char[] text) {
		matched = false;
		rmatch(0, 0, text);
		return matched;
	}

	public static void main(String[] args) {
		String pattern = "a*2";
		String text = "ab2";
		Pattern c = new Pattern(pattern.toCharArray());
		System.out.println(c.match(text.toCharArray()));
		;
	}
}