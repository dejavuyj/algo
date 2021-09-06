package leetcode.dataStructure.string;

public class Code43_multiply_strings {

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ret = "";
        String lastStr = "0";
        for (int i = num2.length() - 1; i >= 0; i--) {
            int i2 = num2.charAt(i) - '0';
            int step = 0;
            StringBuilder cur = new StringBuilder();
            for (int j = num1.length() - 1; j >= 0; j--) {
                int i1 = num1.charAt(j) - '0';
                int r = i1 * i2 + step;
                step = r / 10;
                int m = r % 10;
                cur.insert(0, m);
            }
            if (step > 0) {
                cur.insert(0, step);
            }
            for (int k = 0; k < num2.length() - 1 - i; k++) {
                cur.append("0");
            }
            String curStr = cur.toString();

            ret = addStr(lastStr, curStr);
            lastStr = ret;
        }
        return ret;
    }

    private String addStr(String s1, String s2) {
        if (s1.equals("0")) {
            return s2;
        }
        StringBuilder cur = new StringBuilder();
        int len1 = s1.length();
        int len2 = s2.length();

        if (len1 > len2) {
            for (int i = len1 - len2; i > 0; i--) {
                s2 = "0" + s2;
            }
        } else {
            for (int i = len2 - len1; i > 0; i--) {
                s1 = "0" + s1;
            }
        }
        int i = s1.length() - 1;
        int step = 0;
        while (i >= 0) {
            int i1 = s1.charAt(i) - '0';
            int i2 = s2.charAt(i) - '0';
            int r = i1 + i2 + step;
            step = r / 10;
            int m = r % 10;
            cur.insert(0, m);
            i--;
        }
        if (step > 0) {
            cur.insert(0, step);
        }
        return cur.toString();
    }

    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = res[i + j + 1] + n1 * n2;
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        Code43_multiply_strings c = new Code43_multiply_strings();
        System.out.println(c.multiply2(num1, num2));
    }
}
