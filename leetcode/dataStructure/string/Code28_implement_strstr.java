package leetcode.dataStructure.string;

public class Code28_implement_strstr {

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }

        int aLen = haystack.length();
        int bLen = needle.length();
        int aStart = 0, aPrevStart;
        int aPos, bPos = 0;
        while (aStart <= aLen - bLen) {
            aPos = aStart;
            aPrevStart = aStart;
            while (haystack.charAt(aPos) == needle.charAt(bPos)) {
                aPos++;
                bPos++;
                if (bPos == bLen) {
                    return aPrevStart;
                }
            }
            bPos = 0;
            aStart++;
        }
        return -1;
    }

    // b表示模式串，m表示模式串的长度
    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; ++i) {
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }

    // a, b分别是主串和模式串；n, m分别是主串和模式串的长度。
    public int kmp(char[] a, char[] b) {
        int n = a.length;
        int m = b.length;
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            while (j > 0 && a[i] != b[j]) { // 一直找到a[i]和b[j]
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            if (j == m) { // 找到匹配模式串的了
                return i - m + 1;
            }
        }
        return -1;
    }

    public int strStr2(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }

        return kmp(haystack.toCharArray(), needle.toCharArray());
    }

    public static void main(String[] args) {
        String haystack = "aabaaabaaac";
        String needle = "";
        Code28_implement_strstr c = new Code28_implement_strstr();
        System.out.println(c.strStr2(haystack, needle));
    }
}
