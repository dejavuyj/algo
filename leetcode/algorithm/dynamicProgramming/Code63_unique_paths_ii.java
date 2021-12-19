package leetcode.algorithm.dynamicProgramming;

public class Code63_unique_paths_ii {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        int[][] dp = new int[m][n];
        boolean[][] obstacle = new boolean[m][n];

        dp[m - 1][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    obstacle[i][j] = true;
                } else if (j == n - 1 && i < m - 1) {
                    dp[i][j] = dp[i + 1][j];
                } else if (i == m - 1 && j < n - 1) {
                    dp[i][j] = dp[i][j + 1];
                }
            }
        }
        for (int row = m - 2; row >= 0; row--) {
            for (int col = n - 2; col >= 0; col--) {
                if (!obstacle[row][col]) {
                    dp[row][col] = dp[row + 1][col] + dp[row][col + 1];
                }
            }
        }
        return dp[0][0];
    }

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        dp[m - 1][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (j == n - 1 && i < m - 1) {
                    dp[i][j] = dp[i + 1][j];
                } else if (i == m - 1 && j < n - 1) {
                    dp[i][j] = dp[i][j + 1];
                } else if (i < m - 1 && j < n - 1) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        Code63_unique_paths_ii c = new Code63_unique_paths_ii();
        // int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
//        int[][] grid = {{0, 0}, {1, 1}, {0, 0}};
        int[][] grid = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 1, 0, 0}};
        int ans = c.uniquePathsWithObstacles(grid);
        System.out.println(ans);
    }
}
