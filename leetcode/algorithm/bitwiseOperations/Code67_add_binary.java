package leetcode.algorithm.bitwiseOperations;

public class Code67_add_binary {

    public String addBinary(String a, String b) {
        int arrayLen = Math.max(a.length(), b.length()) + 1;
        char[] array = new char[arrayLen];
        int ia = a.length() - 1;
        int ib = b.length() - 1;
        int ir = arrayLen - 1;
        boolean carry = false;
        while (ir >= 0) {
            char ca = ia >= 0 ? a.charAt(ia) : '0';
            char cb = ib >= 0 ? b.charAt(ib) : '0';
            if (ca == '1' && cb == '1') {
                array[ir] = carry ? '1' : '0';
                carry = true;
            } else if (ca == '0' && cb == '0') {
                array[ir] = carry ? '1' : '0';
                carry = false;
            } else {
                array[ir] = carry ? '0' : '1';
            }
            ir--;
            ia--;
            ib--;
        }
        boolean usedOne = array[0] == '1';
        return new String(array, usedOne ? 0 : 1, usedOne ? arrayLen : arrayLen - 1);
    }

    public static void main(String[] args) {
        Code67_add_binary c = new Code67_add_binary();
        String a = "1010";
        String b = "1011";
        System.out.println(c.addBinary(a, b));
    }
}
