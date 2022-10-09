package leetcode.dataStructure.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code119_pascals_triangle_ii {

    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> ansList = new ArrayList<>();
        for (int level = 0; level <= rowIndex; level++) {
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
        return ansList.get(ansList.size() - 1);
    }

    public List<Integer> getRow2(int rowIndex) {
        if (rowIndex == 0) return Arrays.asList(1);
        if (rowIndex == 1) return Arrays.asList(1, 1);
        int[] preList = new int[rowIndex + 1];
        preList[0] = 1;
        preList[1] = 1;

        for (int level = 2; level <= rowIndex; level++) {
            for (int j = level - 1; j >= 1; j--) {
                preList[j] = preList[j] + preList[j - 1];
            }
            preList[level] = 1;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            list.add(preList[i]);
        }
        return list;
    }

    public static void main(String[] args) {
        Code119_pascals_triangle_ii c = new Code119_pascals_triangle_ii();
        int numRows = 3;
        List<Integer> r = c.getRow(numRows);
        r.forEach(i -> System.out.print(i + ","));
    }
}
