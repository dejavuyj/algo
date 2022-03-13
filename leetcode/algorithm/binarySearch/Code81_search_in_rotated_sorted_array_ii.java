package leetcode.algorithm.binarySearch;

public class Code81_search_in_rotated_sorted_array_ii {

    public boolean search(int[] nums, int target) {
    	int n = nums.length;
    	if (n == 0) {
    		return false;
		}
    	if (n == 1) {
    		return nums[0] == target;
		}

        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] == target) {
                return true;
            }

            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
            	low++;
            	high--;
			} else if (nums[mid] >= nums[low]) {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Code81_search_in_rotated_sorted_array_ii c = new Code81_search_in_rotated_sorted_array_ii();
        int[] nums = {1, 0, 1, 1, 1};
        System.out.println(c.search(nums, 0));
    }
}
