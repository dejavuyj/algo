package leetcode.algorithm.pruning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Code51_n_queens {
	class AssumeDate {
		private Character[][] data;
		private Integer[] queensInLine; // 每行queen所在的坐标
		private Integer leftChance; // 剩余可以尝试的机会
		private Integer leftCount; // 剩余slots数量
		// private Integer currentX; // 第一个可尝试slot的x坐标
		// private Integer currentY; // 第一个可尝试slot的y坐标
	}

	boolean isOdd;
	int completedNum = 0;
	int nSize = 0;
	int totalSize = 0;
	List<List<String>> ans = new ArrayList<List<String>>();

	private Stack<AssumeDate> stack = new Stack<AssumeDate>();
	// private int currentLeftCount; // 当前剩余slots数量

	private boolean isValid() {
		AssumeDate ad = stack.peek();
		Character[][] data = ad.data;
		Integer[] queensInLine = ad.queensInLine;
		for (int i = 1; i < queensInLine.length; i++) {
			// i为行编号(第0行不可能重复,可以跳过),j为列编号
			int j = queensInLine[i];
			if (j == -1) {
				// 第i行没有queen
				return false;
			}

			// 检查同行有没有其他queen
			for (int jj = 0; jj < nSize; jj++) {
				if (data[i][jj] == 'Q' && jj != j) {
					return false;
				}
			}
			// 检查同列有没有其他queen
			for (int ii = 0; ii < nSize; ii++) {
				if (data[ii][j] == 'Q' && ii != i) {
					return false;
				}
			}
			// 检查jj<j部分对角线上有没有其他queen
			for (int jj = j - 1; jj > 0; jj--) {
				int moved = j - jj;
				int iLeft = i - moved;
				int iRight = i + moved;
				if (iLeft >= 0 && data[iLeft][jj] == 'Q') {
					return false;
				}
				if (iRight < nSize && data[iRight][jj] == 'Q') {
					return false;
				}
			}
			// 检查jj>j部分对角线上有没有其他queen
			for (int jj = j + 1; jj < nSize; jj++) {
				int moved = jj - j;
				int iLeft = i - moved;
				int iRight = i + moved;
				if (iLeft >= 0 && data[iLeft][jj] == 'Q') {
					return false;
				}
				if (iRight < nSize && data[iRight][jj] == 'Q') {
					return false;
				}
			}
		}
		return true;
	}

	private void setExclusions(AssumeDate ad, int i, int j) {
		// 根据queen的坐标设置排除项
		Character[][] data = ad.data;
		// 同行
		for (int jj = 0; jj < nSize; jj++) {
			if (data[i][jj] == null && jj != j) {
				data[i][jj] = '.';
				ad.leftCount--;
			}
		}
		// 同列
		for (int ii = 0; ii < nSize; ii++) {
			if (data[ii][j] == null && ii != i) {
				data[ii][j] = '.';
				ad.leftCount--;
			}
		}
		// jj<j部分对角线上
		// for (int jj = j - 1; jj > 0; jj--) {
		// int moved = j - jj;
		// int iLeft = i - moved;
		// int iRight = i + moved;
		// if (iLeft >= 0) {
		// data[iLeft][jj] = '.';
		// currentLeftCount--;
		// }
		// if (iRight < nSize) {
		// data[iRight][jj] = '.';
		// currentLeftCount--;
		// }
		// }
		// ii>i部分对角线上
		for (int ii = i + 1; ii < nSize; ii++) {
			int moved = ii - i;
			int jLeft = j - moved;
			int jRight = j + moved;
			if (jLeft >= 0 && data[ii][jLeft] == null) {
				data[ii][jLeft] = '.';
				ad.leftCount--;
			}
			if (jRight < nSize && data[ii][jRight] == null) {
				data[ii][jRight] = '.';
				ad.leftCount--;
			}
		}
	}

	private boolean quickCheckNextLine(int i) {
		// 快速检查下一行是否已经被填满
		AssumeDate ad = stack.peek();
		Character[][] data = ad.data;

		boolean hasBlank = false;
		for (int j = 0; j < nSize; j++) {
			if (data[i][j] == null) {
				hasBlank = true;
				break;
			}
		}
		if (!hasBlank) {
			// 当前行全是'.'
			return false;
		}
		return true;
	}

	private void assumeRun() {
		if (stack.empty()) {
			for (int i = completedNum; i < nSize / 2 + nSize % 2; i++) {
				// 只计算到n/2即可,n/2->n和0->n/2是等价的

				// 栈为空,需要新建一个栈帧,压栈
				AssumeDate ad = new AssumeDate();
				ad.leftCount = totalSize;
				Character[][] data = new Character[nSize][nSize];
				Integer[] queensInLine = new Integer[nSize];
				for (int i2 = 1; i2 < nSize; i2++) {
					queensInLine[i2] = -1;
				}
				data[0][i] = 'Q';
				queensInLine[0] = i; // 第0行,第i列为queen
				ad.leftCount--;
				ad.data = data;
				setExclusions(ad, 0, i);
				ad.queensInLine = queensInLine;
				stack.push(ad);
				assumeRun();
			}

		}

		// 出栈并找到第一个空格,将其设为queen
		AssumeDate ad = stack.peek();

		if (ad.leftCount == 0) {
			// 所有格子已填满,开始验证
			// boolean isValid = isValid();
			boolean isValid = true;
			if (isValid) {
				// 找到一个解,放入结果集
				ad = stack.pop();
				Character[][] data = ad.data;
				List<String> oneAnswer = new ArrayList<String>();
				for (Character[] line : data) {
					StringBuffer sb = new StringBuffer();
					sb.append(line);
					oneAnswer.add(sb.toString());
				}
				ans.add(oneAnswer);
				// tbd,加上对称的结果集
				completedNum++;
				return;
			} else {
				// tbd,需要做一次出栈
				stack.pop();
				assumeRun();
			}
		}

		AssumeDate newAd = new AssumeDate();
		newAd.data = new Character[nSize][nSize];
		for (int i = 0; i < nSize; i++) {
			newAd.data[i] = Arrays.copyOf(ad.data[i], nSize);
		}
		newAd.queensInLine = Arrays.copyOf(ad.queensInLine, nSize);
		newAd.leftCount = ad.leftCount;
		stack.push(newAd);

		Character[][] data = newAd.data;
		Integer[] queensInLine = newAd.queensInLine;
		int i = -1;
		int j = -1;
		boolean foundFirstBlank = false;
		for (int ii = 1; ii < nSize; ii++) {
			for (int jj = 0; jj < nSize; jj++) {
				if (data[ii][jj] == null) {
					i = ii;
					j = jj;
					foundFirstBlank = true;
					// jj = nSize; //跳出外层循环
					break;
				}
			}
			if (foundFirstBlank) {
				break;
			}
		}

		// 第i行,第j列为空
		data[i][j] = 'Q';
		queensInLine[i] = j; // 第i行,第j列为queen
		newAd.leftCount--;
		ad.leftChance = 1;
		for (int jj = j + 1; jj < nSize; jj++) {
			if (data[i][jj] == null) {
				ad.leftChance++;
			}
		}
		setExclusions(newAd, i, j);
		if (!quickCheckNextLine(i + 1)) {
			// 没有通过快速检查,不需要再往下做assumeRun了
			// ad.leftChance--;
			stack.pop();
			if (!stack.empty()) {
				AssumeDate adBefore = stack.peek();
				// adBefore.leftChance = ad.leftChance - 1;
				adBefore.leftChance--;
				data = adBefore.data;
				// 将第i行,第j列设为'.'
				data[i][j] = '.';
				adBefore.leftCount--;
				if (adBefore.leftChance > 0) {
					// 继续执行assumeRun()
					assumeRun();
				} else {
					stack.pop();
					completedNum++;
					return;
				}
			}
		} else {
			// 继续执行assumeRun()
			assumeRun();
		}
	}

	public List<List<String>> solveNQueens(int n) {
		if (n <= 3) {
			// 3皇后无解
			return ans;
		}

		nSize = n;
		totalSize = n * n;
		assumeRun();

		return ans;
	}

	// int rows[];
	// // "hill" diagonals
	// int hills[];
	// // "dale" diagonals
	// int dales[];
	// int n;
	// // output
	// List<List<String>> output = new ArrayList<List<String>>();
	// // queens positions
	// int queens[];
	//
	// public boolean isNotUnderAttack(int row, int col) {
	// int res = rows[col] + hills[row - col + 2 * n] + dales[row + col];
	// return (res == 0) ? true : false;
	// }
	//
	// public void placeQueen(int row, int col) {
	// queens[row] = col;
	// rows[col] = 1;
	// hills[row - col + 2 * n] = 1; // "hill" diagonals
	// dales[row + col] = 1; // "dale" diagonals
	// }
	//
	// public void removeQueen(int row, int col) {
	// queens[row] = 0;
	// rows[col] = 0;
	// hills[row - col + 2 * n] = 0;
	// dales[row + col] = 0;
	// }
	//
	// public void addSolution() {
	// List<String> solution = new ArrayList<String>();
	// for (int i = 0; i < n; ++i) {
	// int col = queens[i];
	// StringBuilder sb = new StringBuilder();
	// for (int j = 0; j < col; ++j)
	// sb.append(".");
	// sb.append("Q");
	// for (int j = 0; j < n - col - 1; ++j)
	// sb.append(".");
	// solution.add(sb.toString());
	// }
	// output.add(solution);
	// }
	//
	// public void backtrack(int row) {
	// for (int col = 0; col < n; col++) {
	// if (isNotUnderAttack(row, col)) {
	// placeQueen(row, col);
	// // if n queens are already placed
	// if (row + 1 == n)
	// addSolution();
	// // if not proceed to place the rest
	// else
	// backtrack(row + 1);
	// // backtrack
	// removeQueen(row, col);
	// }
	// }
	// }
	//
	// public List<List<String>> solveNQueens(int n) {
	// this.n = n;
	// rows = new int[n];
	// hills = new int[4 * n - 1];
	// dales = new int[2 * n - 1];
	// queens = new int[n];
	//
	// backtrack(0);
	// return output;
	// }

	// Character[][] data = null;
	//
	// private int[] helper(int n) {
	//
	// }
	//
	// public List<List<String>> solveNQueens2(int n) {
	// data = new Character[n][n];
	//
	// List<String> l = new ArrayList<String>(n);
	//
	// List<List<String>> subAnswer = solveNQueens(n-1);
	//
	// for(List<String> l:subAnswer) {
	// for(int i=0;i<)
	// }
	//
	// return ans;
	// }

	public static void main(String[] args) {
		Code51_n_queens c = new Code51_n_queens();
		List<List<String>> ans = c.solveNQueens(8);
		int count = 0;
		for (List<String> l : ans) {
			count++;
			System.out.println(count + " ---start---");
			for (String s : l) {
				System.out.println(s);
			}
			System.out.println(count + " ---end---");
			System.out.println();
		}
	}
}
