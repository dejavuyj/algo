package algorithm;

/**
 * 正则表达式
 */
public class Pattern {

	private boolean matched = false;
	private char[] pattern;
	private int plen; // 正则表达式长度

	public Pattern(char[] pattern) {
		this.pattern = pattern;
		this.plen = plen;
	}

	public boolean match(char[] text, int tlen) {
		matched = false;
		rmatch(0, 0, text, tlen);
		return matched;
	}

	private void rmatch(int ti, int pj, char[] text, int tlen) {
		if (matched) {
			return;
		}
		if (pj == pattern.length) { // 正则表达式到结尾
			if (ti == tlen) { // 字符串也到结尾
				matched = true;
			}
			return;
		}
		if (pattern[pj] == '*') { // *匹配任意个字符
			for (int k = 0; k <= tlen - ti; ++k) {
				rmatch(ti + k, pj + 1, text, tlen);
			}
		} else if (pattern[pj] == '?') { // ?匹配0个或者1个字符
			rmatch(ti, pj + 1, text, tlen);
			rmatch(ti + 1, pj + 1, text, tlen);
		} else if (ti < tlen && pattern[pj] == text[ti]) { // 纯字符匹配
			rmatch(ti + 1, pj + 1, text, tlen);
		}
	}

	public static void main(String[] args) {
		String text = "acdcb";
		String pattern = "a*c?b";
		Pattern c = new Pattern(pattern.toCharArray());
		System.out.println(c.match(text.toCharArray(), text.length()));
	}
}