package leetcode.dataStructure.string;

public class Code344_reverse_string {

	public void reverseString(char[] s) {
		int len = s.length;
		for (int i = 0; i < len / 2; i++) {
			char tmp = s[i];
			s[i] = s[len - 1 - i];
			s[len - 1 - i] = tmp;
		}
	}

	public static void main(String[] args) {
		char[] arr = { 'H', 'a', 'n', 'n', 'a', 'h' };
		Code344_reverse_string c = new Code344_reverse_string();
		c.reverseString(arr);
		System.out.println(arr);
	}
}
