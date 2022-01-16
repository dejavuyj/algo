package leetcode.dataStructure.string;

import java.util.ArrayList;
import java.util.List;

public class Code68_text_justification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> retList = new ArrayList<>();
        int startIndex = 0;
        int endIndex = 0;
        while (startIndex <= words.length - 1) {
            int i = startIndex;
            int rowWidth = 0;
            while (i < words.length) {
                if (rowWidth + words[i].length() + 1 <= maxWidth + 1) {
                    rowWidth += words[i].length() + 1;
                    i++;
                } else {
                    break;
                }
            }
            endIndex = i - 1;
            int wordNum = endIndex - startIndex + 1;

            StringBuilder sb = new StringBuilder();
            int unitSpaceNum = 0;
            int lastSpaceNum = 0;
            if (endIndex != words.length - 1) {
                // 不是最后一行, 两端对齐
                rowWidth -= startIndex == words.length - 1 ? 1 : wordNum;
                int remainedSpaceNum = maxWidth - rowWidth;

                for (int j = startIndex; j <= endIndex; j++) {
                    sb.append(words[j]);

                    unitSpaceNum = wordNum == 1 ? remainedSpaceNum : remainedSpaceNum / (wordNum - 1);
                    lastSpaceNum = wordNum == 1 ? 0 : remainedSpaceNum % (wordNum - 1);
                    if (lastSpaceNum != 0) {
                        unitSpaceNum += 1;
                    }
                    wordNum--;
                    if (wordNum != 0) {
                        for (int k = 0; k < unitSpaceNum; k++) sb.append(" ");
                        remainedSpaceNum -= unitSpaceNum;
                    } else {
                        for (int k = 0; k < remainedSpaceNum; k++) sb.append(" ");
                    }
                }
            } else {
                // 最后一行, 左对齐
                lastSpaceNum = maxWidth - rowWidth;

                for (int j = startIndex; j <= endIndex; j++) {
                    sb.append(words[j]).append(" ");
                }
                if (lastSpaceNum < 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                for (int k = 0; k < lastSpaceNum; k++) sb.append(" ");
            }

            retList.add(sb.toString());
            startIndex = endIndex + 1;
        }
        return retList;
    }

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        Code68_text_justification c = new Code68_text_justification();
        List<String> stringList = c.fullJustify(words, maxWidth);
        stringList.forEach(System.out::println);
    }
}
