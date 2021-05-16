package leetcode.other;

public class Code12_integer_to_roman {

    public String intToRoman(int num) {
        StringBuilder roman = new StringBuilder();
        int[] numArray = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanArray = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < numArray.length; i++) {
            int cnt = num / numArray[i];
            if (cnt == 0) {
                continue;
            }
            for (int j = 0; j < cnt; j++) {
                roman.append(romanArray[i]);
            }
            num -= cnt * numArray[i];
        }
        return roman.toString();
    }

    public String intToRoman2(int num) {
        StringBuilder roman = new StringBuilder();
        int[] numArray = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanArray = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < numArray.length; i++) {
            while (num >= numArray[i]) {
                roman.append(romanArray[i]);
                num -= numArray[i];
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }

    String[] thousands = {"", "M", "MM", "MMM"};
    String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    public String intToRoman3(int num) {
        StringBuilder roman = new StringBuilder();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }

    public static void main(String[] args) {
        int num = 1994;
        Code12_integer_to_roman c = new Code12_integer_to_roman();
        String ret = c.intToRoman3(num);
        System.out.print(ret);
    }
}
