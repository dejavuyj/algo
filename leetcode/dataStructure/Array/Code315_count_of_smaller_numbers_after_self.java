package leetcode.dataStructure.Array;

import java.util.*;

public class Code315_count_of_smaller_numbers_after_self {

    private int[] c;
    private int[] a;

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> resultList = new ArrayList<>();
        discretization(nums);;
        init(nums.length + 5);
        for (int i = nums.length - 1; i >= 0; i--) {
            int id = getId(nums[i]);
            resultList.add(query(id - 1));
            update(id);
        }
        Collections.reverse(resultList);
        return resultList;
    }

    private void init(int length) {
        c = new int[length];
        Arrays.fill(c, 0);
    }

    private int lowBit(int x) {
        return x & (-x);
    }

    private void update(int pos) {
        while (pos < c.length) {
            c[pos] += 1;
            pos += lowBit(pos);
        }
    }

    private int query(int pos) {
        int ret = 0;
        while (pos > 0) {
            ret += c[pos];
            pos -= lowBit(pos);
        }
        return ret;
    }

    private void discretization(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        a = new int[size];
        int index = 0;
        for (int num : set) {
            a[index++] = num;
        }
        Arrays.sort(a);
    }

    private int getId(int x) {
        return Arrays.binarySearch(a, x) + 1;
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 6, 1};
        Code315_count_of_smaller_numbers_after_self c = new Code315_count_of_smaller_numbers_after_self();
        List<Integer> ret = c.countSmaller(nums);
        ret.forEach(i -> System.out.print(i + ","));
    }
}
