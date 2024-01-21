package leetcode.dataStructure.hashTableAndSet;


import java.util.HashMap;
import java.util.Map;

public class Code219_contains_duplicate_ii {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                int preIndex = map.get(num);
                if (i - preIndex <= k) {
                    return true;
                }
            }
            map.put(num, i);
        }
        return false;
    }

    public static void main(String[] args) {
        Code219_contains_duplicate_ii c = new Code219_contains_duplicate_ii();
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        System.out.println(c.containsNearbyDuplicate(nums, k));
    }
}
