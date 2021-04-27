package leetcode.other;

public class Code6_zigzag_conversion {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int zNum = 2 * numRows - 2;
        int sLen = s.length();
        int col = (sLen % zNum == 0 ? sLen / zNum : sLen / zNum + 1) * (numRows - 1);
        char[][] array = new char[numRows][col];
        int vIndex = 0;
        int hIndex = 0;
        boolean down = true;
        for (int i = 0; i < sLen; i++) {
            array[vIndex][hIndex] = s.charAt(i);
            if (vIndex == numRows - 1) {
                down = false;
            } else if (vIndex == 0) {
                down = true;
            }

            if (down) {
                vIndex++;
            } else {
                vIndex--;
                hIndex++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < numRows; j++) {
            for (int i = 0; i < col; i++) {
                if (array[j][i] != 0) {
                    sb.append(array[j][i]);
                }
            }
        }
        return sb.toString();
    }

    public String convert2(String s, int numRows) {
        if (numRows == 1)
            return s;

        int zNum = 2 * numRows - 2;
        int sLen = s.length();
        int zCnt = (sLen % zNum == 0 ? sLen / zNum : sLen / zNum + 1);
        StringBuilder sb = new StringBuilder();

        for (int curRow=0; curRow<numRows; curRow++) {
            for (int i = 0; i < zCnt; i++) {
                int curIndex = i * zNum + curRow;
                if (curIndex < sLen) {
                    sb.append(s.charAt(curIndex));
                }
                if (curRow != 0 && curRow != numRows - 1) {
                    curIndex += zNum - 2 * curRow;
                    if (curIndex < sLen) {
                        sb.append(s.charAt(curIndex));
                    }
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "PAYPALISHIRING";
        int numRows = 3;
        Code6_zigzag_conversion c = new Code6_zigzag_conversion();
        str = c.convert2(str, numRows);
        System.out.print(str);
    }
}
