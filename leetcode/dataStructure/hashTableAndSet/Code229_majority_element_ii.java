package leetcode.dataStructure.hashTableAndSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code229_majority_element_ii {

	public List<Integer> majorityElement(int[] nums) {
		Map<Integer, Integer> cntMap = new HashMap<>();
		for (int n : nums) {
			if (cntMap.containsKey(n)) {
				cntMap.put(n, cntMap.get(n) + 1);
			} else {
				cntMap.put(n, 1);
			}
		}

		List<Integer> ret = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : cntMap.entrySet()) {
			if (entry.getValue() > nums.length / 3) {
				ret.add(entry.getKey());
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		Code229_majority_element_ii c = new Code229_majority_element_ii();
		int[] nums = {1, 2, 3, 1, 4};
		List<Integer> ret = c.majorityElement(nums);
		System.out.print(String.join("\n", ret.toString()));
	}
}
