package leetcode.dataStructure.tree;

public class Code96_unique_binary_search_trees {

    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    public int numTrees2(int n) {
        long C = 1;
        for (int i = 0; i < n; i++) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }

    public static void main(String[] args) {
        Code96_unique_binary_search_trees c = new Code96_unique_binary_search_trees();
        int ans = c.numTrees(3);
        System.out.println(ans);
    }
}
