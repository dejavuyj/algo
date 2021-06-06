package leetcode.algorithm.recurse;

import java.util.ArrayList;
import java.util.List;

public class Code17_letter_combinations_of_a_phone_number {

    String[][] map =
                    {{}
                    ,{}
                    , {"a", "b", "c"}
                    , {"d", "e", "f"}
                    , {"g", "h", "i"}
                    , {"j", "k", "l"}
                    , {"m", "n", "o"}
                    , {"p", "q", "r", "s"}
                    , {"t", "u", "v"}
                    , {"w", "x", "y", "z"}};

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        int len = digits.length();
        if (len == 0) {
            return list;
        }

        letterCombinations(digits, 0, len, "", list);

        return list;
    }

    private void letterCombinations(String digits, int curIdx, int maxIdx, String str, List<String> list) {
        if (curIdx == maxIdx) {
            list.add(str);
            return;
        }

        int idx = digits.charAt(curIdx) - 48;
        String[] arr = map[idx];
        for (String s : arr) {
            letterCombinations(digits, curIdx + 1, maxIdx, str + s, list);
        }
    }

    public static void main(String[] args) {
        String digits = "2";
        Code17_letter_combinations_of_a_phone_number c = new Code17_letter_combinations_of_a_phone_number();
        List<String> ret = c.letterCombinations(digits);
        ret.stream().forEach(s -> System.out.print(s + ", "));
    }
}
