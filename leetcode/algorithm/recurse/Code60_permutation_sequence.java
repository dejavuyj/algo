package leetcode.algorithm.recurse;

public class Code60_permutation_sequence {

    private boolean[] used;
    private int[] factorial;
    private int n;
    private int k;

    public String getPermutation(int n, int k) {
        this.n = n;
        this.k = k;
        factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        used = new boolean[n + 1];

        StringBuilder path = new StringBuilder();
        dfs(0, path);
        return path.toString();
    }

    private void dfs(int index, StringBuilder path) {
        if (index == n) {
            return;
        }

        int cnt = factorial[n - 1 - index];
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }
            if (cnt < k) {
                k -= cnt;
                continue;
            }
            path.append(i);
            used[i] = true;
            dfs(index + 1, path);
            return;
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 9;
        Code60_permutation_sequence c = new Code60_permutation_sequence();
        String r = c.getPermutation(n, k);
        System.out.println(r);
    }
}
