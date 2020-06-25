package leetcode.algorithm.recurse;

/**
 * 正则表达式
 */
public class Code10_regular_expression_matching {

	public boolean isMatch(String text, String pattern) {
		if (pattern.isEmpty()) {
			return text.isEmpty();
		}
		boolean first_match = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
		if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
			return (isMatch(text, pattern.substring(2)) || (first_match && isMatch(text.substring(1), pattern)));
		} else {
			return first_match && isMatch(text.substring(1), pattern.substring(1));
		}
	}

	Boolean[][] memo;

	public boolean isMatch2(String text, String pattern) {
		memo = new Boolean[text.length() + 1][pattern.length() + 1];
		return dp(0, 0, text, pattern);
	}

	public boolean dp(int i, int j, String text, String pattern) {
		if (memo[i][j] != null) {
			return memo[i][j];
		}
		boolean ans;
		if (j == pattern.length()) {
			ans = i == text.length();
		} else {
			boolean first_match = (i < text.length()
					&& (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
			if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
				// 分别对应0个或者1个pattern字符
				ans = (dp(i, j + 2, text, pattern) || first_match && dp(i + 1, j, text, pattern));
			} else {
				ans = first_match && dp(i + 1, j + 1, text, pattern);
			}
		}
		memo[i][j] = ans;
		return ans;
	}

	public static void main(String[] args) {
		String text = "aa";
		String pattern = "a*";
		Code10_regular_expression_matching c = new Code10_regular_expression_matching();
		System.out.println(c.isMatch2(text, pattern));
	}
}