package leetcode.algorithm.pruning;

public class Code36_valid_sudoku {

	class BlockLimit {
		int min_j;
		int max_j;
		int min_i;
		int max_i;
	}

	BlockLimit get_block_limit(int block_id) {
		BlockLimit bl = new BlockLimit();
		switch (block_id) {
		case 1:
			bl.min_j = 0;
			bl.max_j = 2;
			bl.min_i = 0;
			bl.max_i = 2;
			break;
		case 2:
			bl.min_j = 0;
			bl.max_j = 2;
			bl.min_i = 3;
			bl.max_i = 5;
			break;
		case 3:
			bl.min_j = 0;
			bl.max_j = 2;
			bl.min_i = 6;
			bl.max_i = 8;
			break;
		case 4:
			bl.min_j = 3;
			bl.max_j = 5;
			bl.min_i = 0;
			bl.max_i = 2;
			break;
		case 5:
			bl.min_j = 3;
			bl.max_j = 5;
			bl.min_i = 3;
			bl.max_i = 5;
			break;
		case 6:
			bl.min_j = 3;
			bl.max_j = 5;
			bl.min_i = 6;
			bl.max_i = 8;
			break;
		case 7:
			bl.min_j = 6;
			bl.max_j = 8;
			bl.min_i = 0;
			bl.max_i = 2;
			break;
		case 8:
			bl.min_j = 6;
			bl.max_j = 8;
			bl.min_i = 3;
			bl.max_i = 5;
			break;
		case 9:
			bl.min_j = 6;
			bl.max_j = 8;
			bl.min_i = 6;
			bl.max_i = 8;
			break;
		default:
			break;
		}
		return bl;
	}

	boolean isNotLegal(char cal[][], int jj, int ii, BlockLimit bl) {
		int i, j;
		char value = cal[jj][ii];

		// 同一行是否有相同的元素
		for (i = 0; i < 9; i++) {
			if (i == ii)
				continue;

			if (cal[jj][i] == value)
				return true;
		}

		// 同一列是否有相同的元素
		for (j = 0; j < 9; j++) {
			if (j == jj)
				continue;

			if (cal[j][ii] == value)
				return true;
		}

		// 同一块是否有相同的元素
//		int block_id = findblock(jj, ii);
//		BlockLimit bl = get_block_limit(block_id);
		for (j = bl.min_j; j <= bl.max_j; j++) {
			for (i = bl.min_i; i <= bl.max_i; i++) {
				if (i == ii && j == jj)
					continue;

				if (cal[j][i] == value)
					return true;
			}
		}

		return false;
	}

	public boolean isValidSudoku(char[][] board) {
		int b, i, j;

		for (b = 1; b <= 9; b++) {
			BlockLimit bl = get_block_limit(b);
			for (j = bl.min_j; j <= bl.max_j; j++) {
				for (i = bl.min_i; i <= bl.max_i; i++) {
					if(board[j][i] != '.') {						
						if (isNotLegal(board, j, i, bl)) {
							return false;
						}
					}
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		Code36_valid_sudoku c = new Code36_valid_sudoku();
		char[][] board = { 
				{ '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' },
				{ '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' },
				{ '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' },
				{ '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' },
				{ '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		boolean ans = c.isValidSudoku(board);
		System.out.println(ans);
	}
}
