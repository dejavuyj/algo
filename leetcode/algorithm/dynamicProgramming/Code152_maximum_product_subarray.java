package leetcode.algorithm.dynamicProgramming;

public class Code152_maximum_product_subarray {

	public int maxProduct(int[] nums) {
		int len = nums.length;
		int[] temp = new int[len];
		int max = Integer.MIN_VALUE;
		for(int i=0;i<len;i++) {
			temp[i] = nums[i];
			if(nums[i] > max) {
				max = nums[i];
			}
		}

		for(int i=1;i<=len-1;i++) {
			int currentLen = len-i;
			int currentPtr = i;
			for(int j=0;j<currentLen;j++) {
				temp[j] = temp[j] * nums[currentPtr];
				if(temp[j] > max) {
					max = temp[j];
				}
				currentPtr++;
			}
		}
		
		return max;
	}

	public int maxProduct2(int[] nums) {
		int len = nums.length;
		
		int max = Integer.MIN_VALUE;
		int imax = 1;
		int imin = 1;

		for(int i=0;i<len;i++) {
			if(nums[i] < 0) {
				int tmp = imax;
				imax = imin;
				imin = tmp;
			}
			imax = Math.max(imax*nums[i], nums[i]);
			imin = Math.min(imin*nums[i], nums[i]);

			max = Math.max(max, imax);
		}

		return max;
	}

	public static void main(String[] args) {
		Code152_maximum_product_subarray c = new Code152_maximum_product_subarray();
		int[] nums = { 2,3,-2,4 };
		int ans = c.maxProduct2(nums);
		System.out.println(ans);
	}
}
