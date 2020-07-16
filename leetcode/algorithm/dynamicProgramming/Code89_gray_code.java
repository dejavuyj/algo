package leetcode.algorithm.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class Code89_gray_code {

	public List<Integer> grayCode(int n) {
		List<Integer> ansList = new ArrayList<Integer>(1 << n);
		ansList.add(0);
		for (int i = 0; i < n; i++) {
			int add = 1 << i;
			for (int j = ansList.size() - 1; j >= 0; j--) {
				ansList.add(ansList.get(j) + add);
			}
		}
		return ansList;
	}

	public static void main(String[] args) {
		Code89_gray_code c = new Code89_gray_code();
		List<Integer> ansList = c.grayCode(12);
		// for (int i : ansList) {
		// System.out.print(i + ", ");
		// }
		ansList.stream().forEach(i -> System.out.print(i + ", "));
	}
}
