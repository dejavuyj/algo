package leetcode.dataStructure.hashTableAndSet;

import java.util.HashMap;
import java.util.Map;

public class Code205_isomorphic_strings {

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if ((s2t.containsKey(sc) && !s2t.get(sc).equals(tc))
                    || (t2s.containsKey(tc) && !t2s.get(tc).equals(sc))) {
                return false;
            }
            s2t.put(sc, tc);
            t2s.put(tc, sc);

        }
        return true;
    }

    public static void main(String[] args) {
        Code205_isomorphic_strings c = new Code205_isomorphic_strings();
        String s = "badc";
        String t = "baba";
        System.out.println(c.isIsomorphic(s, t));
    }
}
