package leetcode.algorithm.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code618_shopping_offers {

	private int dot(List<Integer> price, List<Integer> needs) {
		int sum = 0;
		for (int i = 0; i < price.size(); i++) {
			sum += price.get(i) * needs.get(i);
		}
		return sum;
	}

	private int shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs,
			Map<List<Integer>, Integer> map) {
		if (map.containsKey(needs)) {
			return map.get(needs);
		}

		// 不使用礼包
		int ret = dot(price, needs);

		for (List<Integer> s : special) {
			int i = 0;
			List<Integer> cn = new ArrayList<Integer>();
			for (; i < needs.size(); i++) {
				int diff = needs.get(i) - s.get(i);
				if (diff < 0) {
					break;
				}
				cn.add(diff);
			}
			if (i == needs.size()) {
				// 可以使用此礼包
				ret = Math.min(ret, s.get(i) + shopping(price, special, cn, map));
			}
		}
		map.put(needs, ret);

		return ret;
	}

	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
		Map<List<Integer>, Integer> map = new HashMap<List<Integer>, Integer>();
		return shopping(price, special, needs, map);
	}

	public static void main(String[] args) {
		Code618_shopping_offers c = new Code618_shopping_offers();
		List<Integer> price = Arrays.asList(2, 5);
		List<List<Integer>> special = Arrays.asList(Arrays.asList(3, 0, 5), Arrays.asList(1, 2, 10));
		List<Integer> needs = Arrays.asList(3, 2);
		// List<Integer> price = Arrays.asList(9, 9);
		// List<List<Integer>> special = Arrays.asList(Arrays.asList(1, 1, 1));
		// List<Integer> needs = Arrays.asList(2, 2);

		int ans = c.shoppingOffers(price, special, needs);
		System.out.println(ans);
	}
}