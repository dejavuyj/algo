package leetcode.dataStructure.Array;

public class Code289_game_of_life {

    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        int[][] newBoard = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i][j] = getNewState(board, i, j, m, n);
            }
        }

        for (int i = 0; i < board.length; i++) {
            System.arraycopy(newBoard[i], 0, board[i], 0, n);
        }
    }

    private int getNewState(int[][] board, int i, int j, int m, int n) {
        int startX = j == 0 ? 0 : j - 1;
        int endX = j == n - 1 ? n - 1 : j + 1;
        int startY = i == 0 ? 0 : i - 1;
        int endY = i == m - 1 ? m - 1 : i + 1;

        int cnt = 0;
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                if (x == j && y == i) {
                    continue;
                }
                if (board[y][x] == 1) {
                    cnt++;
                }
            }
        }

        if (cnt < 2 || cnt > 3) {
            return 0;
        } else {
            if (cnt == 3) {
                return 1;
            } else {
                return board[i][j];
            }
        }
    }

    public void gameOfLife2(int[][] board) {
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int startX = j == 0 ? 0 : j - 1;
                int endX = j == n - 1 ? n - 1 : j + 1;
                int startY = i == 0 ? 0 : i - 1;
                int endY = i == m - 1 ? m - 1 : i + 1;

                int cnt = 0;
                for (int x = startX; x <= endX; x++) {
                    for (int y = startY; y <= endY; y++) {
                        if (x == j && y == i) {
                            continue;
                        }
                        if (Math.abs(board[y][x]) == 1) {
                            cnt++;
                        }
                    }
                }

                if (cnt < 2 || cnt > 3) {
                    board[i][j] = board[i][j] == 1 ? -1 : 0;
                } else {
                    if (cnt == 3) {
                        board[i][j] = board[i][j] == 0 ? 2 : 1;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] > 0 ? 1 : 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] board = {{0, 1, 0},
                        {0, 0, 1},
                        {1, 1, 1},
                        {0, 0, 0}};
        Code289_game_of_life c = new Code289_game_of_life();
        c.gameOfLife(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + ", ");
            }
            System.out.println();
        }
    }
}
