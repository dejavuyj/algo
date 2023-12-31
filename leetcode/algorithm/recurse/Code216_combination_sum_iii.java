package leetcode.algorithm.recurse;

import java.util.ArrayList;
import java.util.List;

public class Code216_combination_sum_iii {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ansList = new ArrayList<>();
        backTrack(1, 9, k, n, new ArrayList<>(), ansList);
        return ansList;
    }

    private void backTrack(int cur, int n, int k, int sum, List<Integer> tmpList, List<List<Integer>> ansList) {
        if (tmpList.size() + (n - cur + 1) < k || tmpList.size() > k) {
            return;
        }
        if (tmpList.size() == k) {
            if (tmpList.stream().mapToInt(z -> z).sum() == sum) {
                ansList.add(new ArrayList<>(tmpList));
            }
            return;
        }
        tmpList.add(cur);
        backTrack(cur + 1, n, k, sum, tmpList, ansList);
        tmpList.remove(tmpList.size() - 1);
        backTrack(cur + 1, n, k, sum, tmpList, ansList);
    }

    public static void main(String[] majorityElement2) {
        int k = 3, n = 7;
        Code216_combination_sum_iii c = new Code216_combination_sum_iii();
        List<List<Integer>> ansList = c.combinationSum3(k, n);
        ansList.forEach(l -> {
            System.out.print("[");
            l.forEach(i -> System.out.print(i + ","));
            System.out.println("]");
        });
    }
}
