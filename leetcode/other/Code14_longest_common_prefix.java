package leetcode.other;

public class Code14_longest_common_prefix {

    public String longestCommonPrefix(String[] strs) {
        StringBuilder prefix = new StringBuilder();
        int i = 0;
        boolean loop = true;
        while (loop) {
            for (int a = 0; a < strs.length; a++) {
                if (i >= strs[a].length()
                    || (a > 0 && strs[a].charAt(i) != strs[a - 1].charAt(i))) {
                    loop = false;
                    break;
                }
            }
            if (loop) {
                prefix.append(strs[0].charAt(i));
            }
            i++;
        }
        return prefix.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"cir", "car"};
        Code14_longest_common_prefix c = new Code14_longest_common_prefix();
        String ret = c.longestCommonPrefix(strs);
        System.out.print(ret);
    }
}
