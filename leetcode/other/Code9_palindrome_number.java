package leetcode.other;

public class Code9_palindrome_number {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        }

        int nf = 1000000000;
        int n = 9;
        while (x % nf == x) {
            nf /= 10;
            n--;
        }

        int mid = (n + 1) / 2;
        int z = 1;
        for (int i = 0; i < mid; i++) {
            int high = (x / nf) % 10;
            int y = x / z;
            int low = y - y / 10 * 10;
            if (high != low) {
                return false;
            }
            nf /= 10;
            z *= 10;
        }

        return true;
    }

    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }

        String str = String.valueOf(x);
        int n = str.length();
        int endIndex = n - 1;
        int mid = n / 2;
        for (int i = 0; i < mid; i++) {
            if (str.charAt(i) != str.charAt(endIndex - i)) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindrome3(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNum = 0;
        while (x > revertedNum) {
            revertedNum = revertedNum * 10 + x % 10;
            x /= 10;
        }

        return x == revertedNum || x == revertedNum / 10;
    }

    public static void main(String[] args) {
        int x = 1001;
        Code9_palindrome_number c = new Code9_palindrome_number();
        boolean ret = c.isPalindrome2(x);
        System.out.print(ret);
    }
}
