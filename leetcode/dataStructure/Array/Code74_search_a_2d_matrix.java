package leetcode.dataStructure.Array;

public class Code74_search_a_2d_matrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int rowLeft = 0;
        int rowRight = matrix.length - 1;
        int colEnd = matrix[0].length - 1;

        boolean inRow = false;
        int rowId = 0;
        while (rowLeft <= rowRight) {
            int rowMid = rowLeft + (rowRight - rowLeft) / 2;
            if (matrix[rowMid][0] > target) {
                rowRight = rowMid - 1;
            } else if (matrix[rowMid][colEnd] < target) {
                rowLeft = rowMid + 1;
            } else {
                inRow = true;
                rowId = rowMid;
                break;
            }
        }
        if (!inRow) {
            return false;
        }
        int colLeft = 0;
        int colRight = colEnd;
        while (colLeft <= colRight) {
            int colMid = colLeft + (colRight - colLeft) / 2;
            if (matrix[rowId][colMid] > target) {
                colRight = colMid - 1;
            } else if (matrix[rowId][colMid] < target) {
                colLeft = colMid + 1;
            } else {
                return true;
            }
        }

        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0;
        int high = m * n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int x = matrix[mid / n][mid % n];
            if (x > target) {
                high = mid - 1;
            } else if (x < target) {
                low = mid + 1;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Code74_search_a_2d_matrix c = new Code74_search_a_2d_matrix();
        int[][] M = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 13;
        boolean ret = c.searchMatrix(M, target);
        System.out.println(ret);
    }
}