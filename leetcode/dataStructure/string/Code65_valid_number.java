package leetcode.dataStructure.string;

public class Code65_valid_number {

    // 表驱动法
    public boolean isNumber(String s) {
        int state = 0;
        int finals = 0b101101000;

        int[][] transfer = new int[][]{{0, 1, 6, 2, -1},
                {-1, -1, 6, 2, -1},
                {-1, -1, 3, -1, -1},
                {8, -1, 3, -1, 4},
                {-1, 7, 5, -1, -1},
                {8, -1, 5, -1, -1},
                {8, -1, 6, 3, 4},
                {-1, -1, 5, -1, -1},
                {8, -1, -1, -1, -1}};

        for (char c : s.toCharArray()) {
            int id = make(c);
            if (id < 0) {
                return false;
            }
            state = transfer[state][id];
            if (state < 0) {
                return false;
            }
        }
        return (finals & (1 << state)) > 0;
    }

    public int make(char c) {
        if (c == ' ') {
            return 0;
        } else if (c == '+' || c == '-') {
            return 1;
        } else if (c >= '0' && c <= '9') {
            return 2;
        } else if (c == '.') {
            return 3;
        } else if (c == 'e' || c == 'E') {
            return 4;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        String s = ".1";
        Code65_valid_number c = new Code65_valid_number();
        System.out.println(c.isNumber(s));
    }
}
