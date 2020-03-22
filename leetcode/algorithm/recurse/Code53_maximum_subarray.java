package leetcode.algorithm.recurse;

public class Code53_maximum_subarray {

    public static int maxSubArray(int[] nums) {
    	int max = Integer.MIN_VALUE;
    	int sum = 0;
        for(int num:nums) {
        	sum += num;
        	if(sum < num) {
        		sum = num;
        	}
        	if(sum > max) {
        		max = sum;
        	}
        }
        return max;
    }

	public static void main(String[] args) {
		int[] nums = {-2,1,-3,4,-1,2,1,-9,4,3,-1};
		System.out.println(maxSubArray(nums));
	}
}
