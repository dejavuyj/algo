package leetcode.dataStructure.hashTableAndSet;

import java.util.HashMap;
import java.util.Map;

public class Code1_two_sum {
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer,Integer> m = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++) {
        	m.put(nums[i], i);
        }
        for(int i=0;i<nums.length;i++) {
        	int left = target - nums[i];
        	if(m.containsKey(left) && i!=m.get(left)) {
        		result[0] = i;
        		result[1] = m.get(left);
        		break;
        	}
        }
        return result;
    }

    public static int[] twoSum2(int[] nums, int target) {
    	int[] result = new int[2];
        Map<Integer,Integer> m = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++) {
        	int left = target - nums[i];
        	//if(m.containsKey(left) && i!=m.get(left)) {
        	if(m.containsKey(left)) {
        		result[0] = i;
        		result[1] = m.get(left);
        		break;
        	}
        	m.put(nums[i], i);
        }
        return result;
    }

    public static void main(String[] args) {
    	int[] r = twoSum2(new int[]{3,3,1,3,2}, 6);
    	for(int i:r) {
    		System.out.println(i);
    	}
    }
}
