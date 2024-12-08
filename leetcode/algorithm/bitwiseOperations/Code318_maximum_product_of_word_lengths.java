package leetcode.algorithm.bitwiseOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Code318_maximum_product_of_word_lengths {

    public int maxProduct(String[] words) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = words.length;
        for (int i = 0; i < length; i++) {
            int mask = 0;
            String word = words[i];
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                mask |= 1 << (word.charAt(j) - 'a');
            }
            if (wordLength > map.getOrDefault(mask, 0)) {
                map.put(mask, wordLength);
            }
        }
        int maxProd = 0;
        Set<Integer> maskSet = map.keySet();
        for (int mask1 : maskSet) {
            int wordLength1 = map.get(mask1);
            for (int mask2 : maskSet) {
                if ((mask1 & mask2) == 0) {
                    int wordLength2 = map.get(mask2);
                    maxProd = Math.max(maxProd, wordLength1 * wordLength2);
                }
            }
        }
        return maxProd;
    }

    public static void main(String[] args) {
        Code318_maximum_product_of_word_lengths c = new Code318_maximum_product_of_word_lengths();
        String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        System.out.println(c.maxProduct(words));
    }
}
