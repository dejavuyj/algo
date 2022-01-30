package leetcode.dataStructure.Array;

import java.util.ArrayList;
import java.util.List;

public class Code73_set_matrix_zeroes {

    public void setZeroes(int[][] matrix) {
        List<Integer[]> zeroIndexs = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    Integer[] index = {i, j};
                    zeroIndexs.add(index);
                }
            }
        }
        zeroIndexs.forEach(index -> {
            int i = index[0];
            int j = index[1];
            for(int zj=0; zj<matrix[0].length; zj++) {
                matrix[i][zj] = 0;
            }
            for(int zi=0; zi<matrix.length; zi++) {
                matrix[zi][j] = 0;
            }
        });
    }

    public void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] column = new boolean[n];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void setZeroes3(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false, flagRow0 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                flagRow0 = true;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (flagCol0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (flagRow0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Code73_set_matrix_zeroes c = new Code73_set_matrix_zeroes();
        int[][] M = {{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
        c.setZeroes2(M);
        for (int[] array : M) {
            for (int i : array) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }
}