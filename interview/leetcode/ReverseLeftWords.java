package interview.leetcode;

public class ReverseLeftWords {

	public String reverseLeftWords(String s, int n) {
		StringBuilder sb = new StringBuilder();
		sb.append(s.substring(n));
		sb.append(s.substring(0, n));
		return sb.toString();
	}

	public String reverseLeftWords2(String s, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = n; i < s.length(); i++) {
			sb.append(s.charAt(i));
		}
		for (int i = 0; i < n; i++) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		ReverseLeftWords c = new ReverseLeftWords();
		String s = "abcdefg";
		int n = 2;
		String ans = c.reverseLeftWords(s, n);
		System.out.println(ans);
	}
}
