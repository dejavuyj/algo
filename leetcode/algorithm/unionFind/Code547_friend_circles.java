package leetcode.algorithm.unionFind;

public class Code547_friend_circles {

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
	public int findCircleNum(int[][] M) {
		int n = M.length;
		if (n == 0) {
			return 0;
		}
		int m = M[0].length;
		if (m == 0) {
			return 0;
		}

		roots = new int[m * n];
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				if (M[j][i] == 0) {
					roots[j * m + i] = -1;
				} else {
					roots[j * m + i] = j * m + i;
					count++;
				}
			}
		}

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				if (M[j][i] == 1 && j != i) {
					M[j][i] = '0';
					union(i * m + i, j * m + j);
					union(j * m + i, j * m + j);
					union(i * m + j, j * m + j);
				}
			}
		}
		return count;
	}

	private boolean[] visited;
	int n;
	//flood fill,dfs
	public int findCircleNum2(int[][] M) {
		n = M.length;
		if (n == 0) {
			return 0;
		}

		visited = new boolean[n];
		int count = 0;
			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					count++;
					dfs(M, i);
				}
			}

		return count;
	}

	private void dfs(int[][] M, int i) {
		for(int j=0; j<n; j++) {
			if(M[i][j] == 1 && !visited[j]) {
				visited[j] = true;
				dfs(M, j);
			}
		}
	}

	public static void main(String[] args) {
		Code547_friend_circles c = new Code547_friend_circles();
		int[][] M = { 
				{ 1, 1, 0 }, 
				{ 1, 1, 0 }, 
				{ 0, 0, 1 }};
//		int[][] M = { 
//				{ 1, 1, 0 }, 
//				{ 1, 1, 1 }, 
//				{ 0, 1, 1 }};
		int ans = c.findCircleNum2(M);
		System.out.println(ans);
	}
}