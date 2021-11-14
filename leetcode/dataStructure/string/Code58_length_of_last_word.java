package leetcode.dataStructure.string;

public class Code58_length_of_last_word {

    public int lengthOfLastWord(String s) {
        int lastLen = 0;
        int curLen = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                lastLen = curLen == 0 ? lastLen : curLen;
                curLen = 0;
            } else {
                curLen++;
            }
        }
        lastLen = curLen == 0 ? lastLen : curLen;
        return lastLen;
    }

    public int lengthOfLastWord2(String s) {
        int curLen = 0;
        char[] arr = s.toCharArray();
        for (int i=arr.length-1; i>=0; i--) {
            if (arr[i] != ' ') {
                curLen++;
            } else if (curLen != 0) {
                return curLen;
            }
        }
        return curLen;
    }

    public static void main(String[] args) {
        String s = "luffy is still joyboy";
        Code58_length_of_last_word c = new Code58_length_of_last_word();
        System.out.println(c.lengthOfLastWord2(s));
    }
}
