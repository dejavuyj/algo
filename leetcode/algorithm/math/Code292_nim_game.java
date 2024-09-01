package leetcode.algorithm.math;

public class Code292_nim_game {

    public boolean canWinNim(int n) {
        return n < 4 || (n % 4 != 0);
    }

    public static void main(String[] args) {
        Code292_nim_game c = new Code292_nim_game();
        int n = 10;
        System.out.println(c.canWinNim(n));
    }
}
