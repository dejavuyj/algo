package leetcode.algorithm.recurse;

import java.util.ArrayList;
import java.util.List;

public class Code77_combinations {

    List<Integer> tmpList = new ArrayList<>();
    List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backTrack(n, k, 1);
        return resultList;
    }

    private void backTrack(int n, int k, int pos) {
        if (tmpList.size() + (n - pos + 1) < k) {
            return;
        }
        if (tmpList.size() == k) {
            resultList.add(new ArrayList<>(tmpList));
            return;
        }
        tmpList.add(pos);
        backTrack(n, k, pos + 1);
        tmpList.remove(tmpList.size() - 1);
        backTrack(n, k, pos + 1);
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        Code77_combinations c = new Code77_combinations();
        List<List<Integer>> r = c.combine(n, k);
        r.forEach(l -> {
            l.forEach(i -> {
                System.out.print(i + ",");
            });
            System.out.println();
        });
    }
}
