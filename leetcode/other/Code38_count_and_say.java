package leetcode.other;

import java.util.HashMap;
import java.util.Map;

public class Code38_count_and_say {

    private String getNextStr(String preString) {
        StringBuilder ss = new StringBuilder();
        char[] chars = preString.toCharArray();
        char c = chars[0];
        int cnt = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == c) {
                cnt++;
            } else {
                ss.append(cnt).append(c);
                cnt = 1;
                c = chars[i];
            }
        }
        ss.append(cnt).append(c);

        return ss.toString();
    }

    public String countAndSay(int n) {
        String ans = "1";
        if (n == 1) {
            return ans;
        }
        for (int i = 2; i <= n; i++) {
            ans = getNextStr(ans);
        }
        return ans;
    }

    static Map<Integer, String> map = new HashMap<>();
    public String countAndSay2(int n) {
        String ans = "1";
        if (n == 1) {
            return ans;
        }
        for (int i = 2; i <= n; i++) {
            String alreay = map.get(i);
            if (alreay == null) {
                ans = getNextStr(ans);
                map.put(i, ans);
            } else {
                ans = alreay;
            }
        }
        return ans;
    }

    public String countAndSay3(int n) {
        String[] dp = new String[n];
        dp[0] = "1";
        for(int i = 1;i < n;i++){
            StringBuilder ss = new StringBuilder();
            char s[] = dp[i-1].toCharArray();
            char zero = s[0];
            for(int j = 0;j < s.length;j++){
                zero = s[j];
                int k = 0;
                while(j < s.length && s[j] == zero){
                    k++;
                    j++;
                }
                j--;
                ss.append(k).append(zero);
            }
            dp[i] = ss.toString();
        }
        return dp[n-1];
    }

    public String countAndSay4(int n) {
        String preString = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            char[] chars = preString.toCharArray();
            char c = chars[0];
            int cnt = 1;
            for (int j = 1; j < chars.length; j++) {
                if (chars[j] == c) {
                    cnt++;
                } else {
                    sb.append(cnt);
                    sb.append(c);
                    cnt = 1;
                    c = chars[j];
                }
            }
            sb.append(cnt);
            sb.append(c);
            preString = sb.toString();
        }
        return preString;
    }

    public static void main(String[] args) {
        Code38_count_and_say c = new Code38_count_and_say();
        int n = 1;
        String ret = c.countAndSay3(n);
        System.out.print(ret);
    }
}
