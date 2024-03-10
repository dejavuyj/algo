package leetcode.dataStructure.Array;


import java.util.ArrayList;
import java.util.List;

public class Code228_summary_ranges {

    public List<String> summaryRanges(int[] nums) {
        List<String> ret = new ArrayList<>();
        int i = 0;
        int n = nums.length;
        while (i < n) {
            int start = nums[i];
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            StringBuffer tmp = new StringBuffer(Integer.toString(start));
            if (nums[i - 1] != start) {
                tmp.append("->").append(nums[i - 1]);
            }
            ret.add(tmp.toString());
        }
        return ret;
    }

    public static void main(String[] args) {
//        int[] nums = {0, 1, 2, 4, 5, 7};
        int[] nums = {0, 1, 2, 4, 5, 7, 8};
        Code228_summary_ranges c = new Code228_summary_ranges();
        List<String> ret = c.summaryRanges(nums);
        System.out.print(String.join("\n", ret));
    }
}
