package leetcode.dataStructure.Array;

public class Code240_search_a_2d_matrix_ii {

    public boolean searchMatrix(int[][] matrix, int target) {
        int c = matrix[0].length - 1;
        int r = 0;
        while (c >= 0 && r <= matrix.length - 1) {
            if (matrix[r][c] < target) {
                r++;
            } else if (matrix[r][c] > target) {
                c--;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int target = 20;
        Code240_search_a_2d_matrix_ii c = new Code240_search_a_2d_matrix_ii();
        boolean ret = c.searchMatrix(matrix, target);
        System.out.print(ret);
    }
}
