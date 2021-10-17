package leetcode.dataStructure.Array;

public class Code48_rotate_image {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] res = new int[n][n];

        for (int z = 0; z <= n / 2; z++) {
            for (int i = z; i <= n - 1 - z; i++) {
                res[i][n - 1 - z] = matrix[z][i];
            }
            for (int i = z + 1; i <= n - 1 - z; i++) {
                res[n - 1 - z][n - 1 - i] = matrix[i][n - 1 - z];
            }
            for (int i = n - 2 - z; i >= z; i--) {
                res[i][z] = matrix[n - 1 - z][i];
            }
            for (int i = n - 2 - z; i >= z + 1; ) {
                for (int j = z + 1; j <= n - 2 - z; j++, i--) {
                    res[z][j] = matrix[i][z];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = res[i][j];
            }
        }
    }

    public static void main(String[] args) {
        Code48_rotate_image c = new Code48_rotate_image();
        int[][] M = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        c.rotate(M);
        for (int[] array : M) {
            for (int i : array) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }
}