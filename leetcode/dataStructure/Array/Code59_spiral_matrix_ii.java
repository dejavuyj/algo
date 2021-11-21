package leetcode.dataStructure.Array;

public class Code59_spiral_matrix_ii {

    public int[][] generateMatrix(int n) {
        int[][] m = new int[n][n];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;

        int rs = 0, cs = 0, re = n, ce = n;

        for (int i = 0; i < n * n; i++) {
            m[r][c] = i + 1;

            int cr = r + dr[di];
            int cc = c + dc[di];

            if (rs <= cr && cr < re && cs <= cc && cc < ce) {
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
                if (cc >= ce) {
                    rs++;
                } else if (cr >= re) {
                    ce--;
                } else if (cc < cs) {
                    re--;
                } else if (cr < rs) {
                    cs++;
                }
            }
        }

        return m;
    }

    public int[][] generateMatrix2(int n) {
        int[][] m = new int[n][n];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;

        for (int i = 0; i < n * n; i++) {
            m[r][c] = i + 1;

            int cr = r + dr[di];
            int cc = c + dc[di];

            if (cr < 0 || cr >= n || cc < 0 || cc >= n || m[cr][cc] != 0) {
                di = (di + 1) % 4;
            }
            r += dr[di];
            c += dc[di];
        }

        return m;
    }

    public static void main(String[] args) {
        int n = 4;
        Code59_spiral_matrix_ii c = new Code59_spiral_matrix_ii();
        int[][] res = c.generateMatrix(n);
        for (int[] array : res) {
            for (int i : array) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }
}
