package leetcode.dataStructure.hashTableAndSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code187_repeated_dna_sequences {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> resultList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int n = s.length();
        int L = 10;
        for (int i = 0; i <= n - L; i++) {
            String sub = s.substring(i, i + L);
            map.put(sub, map.getOrDefault(sub, 0) + 1);
            if (map.get(sub) == 2) {
                resultList.add(sub);
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        Code187_repeated_dna_sequences c = new Code187_repeated_dna_sequences();
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> ret = c.findRepeatedDnaSequences(s);
        ret.forEach(System.out::println);
    }
}
