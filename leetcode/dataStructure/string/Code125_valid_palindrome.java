package leetcode.dataStructure.string;

public class Code125_valid_palindrome {

    public boolean isPalindrome(String s) {
        char[] array = s.toCharArray();
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            char leftChar = convertChar(array[left]);
            if (leftChar == '?') {
                left++;
                continue;
            }
            char rightChar = convertChar(array[right]);
            if (rightChar == '?') {
                right--;
                continue;
            }
            if (leftChar != rightChar) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private char convertChar(char s) {
        if ((s >= 'a' && s <= 'z') || (s >= '0' && s <= '9')) {
            return s;
        } else if (s >= 'A' && s <= 'Z') {
            return (char)(s + 32);
        } else {
            return '?';
        }
    }

    public boolean isPalindrome2(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "race a car";
        Code125_valid_palindrome c = new Code125_valid_palindrome();
        System.out.println(c.isPalindrome(s));
    }
}
