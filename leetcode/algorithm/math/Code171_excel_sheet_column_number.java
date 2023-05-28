package leetcode.algorithm.math;

public class Code171_excel_sheet_column_number {

    public int titleToNumber(String columnTitle) {
        char[] charArray = columnTitle.toCharArray();
        int ret = 0;
        int multiplier = 1;
        for (int i = charArray.length - 1; i >= 0; i--) {
            char c = charArray[i];
            ret += ((c - 'A') + 1) * multiplier;
            multiplier *= 26;
        }
        return ret;
    }

    public static void main(String[] args) {
        Code171_excel_sheet_column_number c = new Code171_excel_sheet_column_number();
        String columnTitle = "ZY";
        System.out.println(c.titleToNumber(columnTitle));
    }
}
