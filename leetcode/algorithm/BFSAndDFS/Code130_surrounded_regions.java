//package leetcode.algorithm.BFSAndDFS;
//
//import javafx.util.Pair;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class Code130_surrounded_regions {
//
//    public void solve(char[][] board) {
//        Set<Pair<Integer, Integer>> innerOSet = new HashSet<>();
//        Set<Pair<Integer, Integer>> borderOSet = new HashSet<>();
//        Set<Pair<Integer, Integer>> visitedSet = new HashSet<>();
//
//        int width = board.length;
//        int height = board[0].length;
//
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                if (board[i][j] != 'O') {
//                    continue;
//                }
//                if (i == 0 || i == width - 1 || j == 0 || j == height - 1) {
//                    borderOSet.add(new Pair<>(i, j));
//                } else {
//                    innerOSet.add(new Pair<>(i, j));
//                }
//            }
//        }
//
//        if (innerOSet.isEmpty()) {
//            return;
//        }
//
//        for (Pair<Integer, Integer> borderPair : borderOSet) {
//            processBorderLinked(borderPair.getKey() - 1, borderPair.getValue(), board, width, height, innerOSet, visitedSet);
//            processBorderLinked(borderPair.getKey(), borderPair.getValue() + 1, board, width, height, innerOSet, visitedSet);
//            processBorderLinked(borderPair.getKey() + 1, borderPair.getValue(), board, width, height, innerOSet, visitedSet);
//            processBorderLinked(borderPair.getKey(), borderPair.getValue() - 1, board, width, height, innerOSet, visitedSet);
//        }
//
//        for (Pair<Integer, Integer> innerPair : innerOSet) {
//            board[innerPair.getKey()][innerPair.getValue()] = 'X';
//        }
//    }
//
//    private void processBorderLinked(int i, int j, char[][] board, int width, int height,
//                                     Set<Pair<Integer, Integer>> innerOSet, Set<Pair<Integer, Integer>> visitedSet) {
//        if (i <= 0 || i >= width - 1 || j <= 0 || j >= height - 1) {
//            return;
//        }
//        if (board[i][j] == 'X' || visitedSet.contains(new Pair<>(i, j))) {
//            return;
//        }
//        innerOSet.remove(new Pair<>(i, j));
//        visitedSet.add(new Pair<>(i, j));
//
//        processBorderLinked(i - 1, j, board, width, height, innerOSet, visitedSet);
//        processBorderLinked(i, j + 1, board, width, height, innerOSet, visitedSet);
//        processBorderLinked(i + 1, j, board, width, height, innerOSet, visitedSet);
//        processBorderLinked(i, j - 1, board, width, height, innerOSet, visitedSet);
//    }
//
//    int n, m;
//
//    public void solve2(char[][] board) {
//        n = board.length;
//        if (n == 0) {
//            return;
//        }
//        m = board[0].length;
//
//        for (int i = 0; i < n; i++) {
//            dfs(board, i, 0);
//            dfs(board, i, m - 1);
//        }
//        for (int i = 1; i < m - 1; i++) {
//            dfs(board, 0, i);
//            dfs(board, n - 1, i);
//        }
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (board[i][j] == 'A') {
//                    board[i][j] = 'O';
//                } else if (board[i][j] == 'O') {
//                    board[i][j] = 'X';
//                }
//            }
//        }
//    }
//
//    private void dfs(char[][] board, int x, int y) {
//        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
//            return;
//        }
//        board[x][y] = 'A';
//        dfs(board, x + 1, y);
//        dfs(board, x - 1, y);
//        dfs(board, x, y + 1);
//        dfs(board, x, y - 1);
//    }
//
//    public static void main(String[] args) {
//        char[][] board = {{'O', 'X', 'X', 'O', 'X'}, {'X', 'O', 'O', 'X', 'O'}, {'X', 'O', 'X', 'O', 'X'}, {'O', 'X', 'O', 'O', 'O'}, {'X', 'X', 'O', 'X', 'O'}};
//        Code130_surrounded_regions c = new Code130_surrounded_regions();
//        c.solve(board);
//        for (char[] chars : board) {
//            for (char aChar : chars) {
//                System.out.print(aChar + ", ");
//            }
//            System.out.println();
//        }
//    }
//}
