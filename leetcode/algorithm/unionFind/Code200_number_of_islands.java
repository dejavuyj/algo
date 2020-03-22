package leetcode.algorithm.unionFind;

public class Code200_number_of_islands {

	int[] roots = null;
	int count = 0;

	private int findRoot(int i) {
		int root = i;
		while (root != roots[root]) {
			root = roots[root];
		}
		while (i != roots[i]) {
			int tmp = roots[i];
			roots[i] = root;
			i = tmp;
		}
		return root;
	}

	public boolean connected(int p, int q) {
		return findRoot(p) == findRoot(q);
	}

	public void union(int p, int q) {
		int pRoot = findRoot(p);
		int qRoot = findRoot(q);
		if(pRoot != qRoot) {
			roots[pRoot] = qRoot;
			count--;			
		}
	}

	//并查集使用count
	public int numIslands(char[][] grid) {
		int n = grid.length;
		if (n == 0) {
			return 0;
		}
		int m = grid[0].length;
		if (m == 0) {
			return 0;
		}

		roots = new int[m * n];
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				if (grid[j][i] == '0') {
					roots[j * m + i] = -1;
				} else {
					roots[j * m + i] = j * m + i;
					count++;
				}
			}
		}

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				if (grid[j][i] == '1') {
					grid[j][i] = '0';
					if (i > 0 && grid[j][i - 1] == '1') { //左
						union(j * m + i, j * m + i - 1);
					}
					if (i < m - 1 && grid[j][i + 1] == '1') { //右
						union(j * m + i + 1, j * m + i);
					}
					if (j > 0 && grid[j - 1][i] == '1') { //上
						union(j * m + i, (j - 1) * m + i);
					}
					if (j < n - 1 && grid[j + 1][i] == '1') { //下
						union((j + 1) * m + i, j * m + i);
					}
				}
			}
		}
		return count;
	}

	private boolean[][] visited;
	private static final int[][] directions = {{-1,0},{0,-1},{1,0},{0,1}};
	int m,n;
	//flood fill,dfs
	public int numIslands2(char[][] grid) {
		n = grid.length;
		if (n == 0) {
			return 0;
		}
		m = grid[0].length;
		if (m == 0) {
			return 0;
		}

		visited = new boolean[n][m];
		int count = 0;
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				if (!visited[j][i] && grid[j][i] == '1') {
					count++;
					dfs(grid, j, i);
				}
			}
		}

		return count;
	}

	private void dfs(char[][] grid, int j, int i) {
		visited[j][i] = true;
		for(int k=0; k<4; k++) {
			int newX = i + directions[k][0];
			int newY = j + directions[k][1];
			if(inArea(newY, newX) && grid[newY][newX] == '1' && !visited[newY][newX]) {
				dfs(grid, newY, newX);
			}
		}
	}

	private boolean inArea(int j, int i) {
		return i >= 0 && i < m && j >= 0 && j < n;
	}

	public static void main(String[] args) {
		Code200_number_of_islands c = new Code200_number_of_islands();
//		char[][] grid = { 
//				{ '1', '1', '1', '1', '0' }, 
//				{ '1', '1', '0', '1', '0' },
// 				{ '1', '1', '0', '0', '0' },
//				{ '0', '0', '0', '0', '0' } };
		char[][] grid = { 
				{ '1', '1', '0', '0', '0' }, 
				{ '1', '1', '0', '0', '0' },
 				{ '0', '0', '1', '0', '0' },
				{ '0', '0', '0', '1', '1' } };
//		char[][] grid = {};
//		char[][] grid = { 
//		{ '1', '0', '1', '1', '1' },
//		{ '1', '0', '1', '0', '1' },
//		{ '1', '1', '1', '0', '1' } };
		int ans = c.numIslands2(grid);
		System.out.println(ans);
	}
}