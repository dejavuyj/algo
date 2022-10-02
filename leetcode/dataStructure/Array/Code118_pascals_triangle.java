package leetcode.dataStructure.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code118_pascals_triangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ansList = new ArrayList<>();
        for (int level = 1; level <= numRows; level++) {
            Integer[] curArray = new Integer[level];
            curArray[0] = 1;
            curArray[level - 1] = 1;

            if (level > 2) {
                List<Integer> preList = ansList.get(level - 2);
                for (int j = 1; j < level - 1; j++) {
                    curArray[j] = preList.get(j - 1) + preList.get(j);
                }
            }

            ansList.add(Arrays.asList(curArray));
        }
        return ansList;
    }

    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> ansList = new ArrayList<>();
        for (int level = 0; level < numRows; level++) {
            List<Integer> curList = new ArrayList<>();

            for (int j = 0; j <= level; j++) {
                if (j == 0 || j == level) {
                    curList.add(1);
                } else {
                    curList.add(ansList.get(level - 1).get(j - 1) + ansList.get(level - 1).get(j));
                }
            }

            ansList.add(curList);
        }
        return ansList;
    }

    public static void main(String[] args) {
        Code118_pascals_triangle c = new Code118_pascals_triangle();
        int numRows = 5;
        List<List<Integer>> r = c.generate(numRows);
        r.forEach(l -> {
            System.out.print("[");
            l.forEach(i -> System.out.print(i + ","));
            System.out.println("]");
        });
    }
}
