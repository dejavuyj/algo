package leetcode.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class Code179_largest_number {

    static int compare(String s1, int index1, String s2, int index2) {
        for (; index1 < s1.length() && index2 < s2.length(); index1++, index2++) {
            char c1 = s1.charAt(index1);
            char c2 = s2.charAt(index2);
            if (c1 != c2) {
                return c2 - c1;
            }
        }
        if (s1.length() == s2.length()) {
            return 0;
        } else if (s1.length() > s2.length()) {
            if (s1.substring(index1).equals(s2)) {
                return 0;
            }
            return compare(s1 + s2, index1, s2, 0);
        } else {
            if (s2.substring(index1).equals(s1)) {
                return 0;
            }
            return compare(s1, 0, s2 + s1, index2);
        }
    }

    static Comparator<Integer> comp = (o1, o2) -> {
        String s1 = o1.toString();
        String s2 = o2.toString();

        return compare(s1, 0, s2, 0);
    };

    public String largestNumber(int[] nums) {
        Integer[] arr = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, comp);
        if (arr[0] == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (Integer integer : arr) {
            sb.append(integer);
        }
        return sb.toString();
    }

    public String largestNumber2(int[] nums) {
        Integer[] arr = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        Arrays.sort(arr, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });

        if (arr[0] == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (Integer integer : arr) {
            sb.append(integer);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] nums = {
                3, 43, 48, 94, 85, 33, 64, 32, 63, 66
        };
        Code179_largest_number c = new Code179_largest_number();
        String ret = c.largestNumber(nums);
        System.out.print(ret);
    }
}
