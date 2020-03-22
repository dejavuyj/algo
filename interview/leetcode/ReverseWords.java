package interview.leetcode;

public class ReverseWords {
	private String reverseWord(String s) {
		StringBuilder sb = new StringBuilder();
		char[] array = s.trim().toCharArray();
		for (int i = array.length - 1; i >= 0; i--) {
			sb.append(array[i]);
		}
		return sb.toString();
	}

//	public String reverseWords(String s) {
//		String wholeReverseWord = reverseWord(s);
////		String[] words = wholeReverseWord.split(" ");
//		StringBuilder tmpSb = new StringBuilder();
//		StringBuilder totalSb = new StringBuilder();
////		int wordCount = words.length;
//		int count = 0;
//		for (char c : wholeReverseWord.toCharArray()) {
//			if(c == ' ') {
//				sb.append(sb);
//				sb.toString();
//			} else {
//				sb.append(c);
//			}
//			sb.append(reverseWord(str));
//			if (count < wordCount) {
//				sb.append(" ");
//			}
//		}
//		return sb.toString();
//	}

	public static void main(String[] args) {
		ReverseWords instance = new ReverseWords();
//		String s = "the sky is blue";
//		String s = "  hello world!  ";
		String s = "a good   example";
//		String ans = instance.reverseWords(s);
//		System.out.println(ans);
	}
}
