package interview.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 汉诺塔
 */
public class Hanota {

	public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
		printStatus(A, B, C);
		recurse(A.size(), A, B, C);
	}

	private void recurse(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
		if (n > 0) {
			recurse(n - 1, A, C, B);
			move(A, C);
			printStatus(A, B, C);
			recurse(n - 1, B, A, C);
		}
	}

	private void move(List<Integer> source, List<Integer> target) {
		target.add(source.get(source.size() - 1));
		System.out.println("move " + source.get(source.size() - 1));
		source.remove(source.size() - 1);
	}

	private void printStatus(List<Integer> A, List<Integer> B, List<Integer> C) {
		A.forEach(i -> System.out.print(i + ","));
		System.out.println();
		B.forEach(i -> System.out.print(i + ","));
		System.out.println();
		C.forEach(i -> System.out.print(i + ","));
		System.out.println();
	}

	public static void main(String[] args) {
		Hanota c = new Hanota();
		int n = 5;
		List<Integer> A = new LinkedList<>();
		for (int i = n - 1 ; i >=0; i--) {
			A.add(i);
		}
		c.hanota(A, new LinkedList<>(), new LinkedList<>());
	}
}
