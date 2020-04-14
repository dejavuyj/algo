package algorithm;

import java.util.LinkedList;
import java.util.Queue;

//无向图
public class Graph_BFS_DFS {

	private int v; // 顶点个数
	private LinkedList<Integer>[] adj; // 邻接表

	@SuppressWarnings("unchecked")
	public Graph_BFS_DFS(int v) {
		this.v = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	// 无向图一条边存两次
	public void addEdge(int s, int t) {
		adj[s].add(t);
		adj[t].add(s);
	}

	public void bfs(int s, int t) {
		if (s == t) {
			return;
		}

		boolean[] visited = new boolean[v];
		visited[s] = true;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		int[] prev = new int[v];
		for (int i = 0; i < v; i++) {
			prev[i] = -1;
		}

		while (!queue.isEmpty()) {
			int w = queue.poll();
			for (int i = 0; i < adj[w].size(); i++) {
				int q = adj[w].get(i);
				if (!visited[q]) {
					prev[q] = w;
					if (q == t) {
						print(prev, s, t);
						return;
					}
					visited[q] = true;
					queue.add(q);
				}
			}
		}
	}

	private void print(int[] prev, int s, int t) {
		if (prev[t] != -1 && t != s) {
			print(prev, s, prev[t]);
		}
		System.out.print(t + " ");
	}

	boolean found = false;

	public void dfs(int s, int t) {
		found = false;
		boolean[] visited = new boolean[v];
		int[] prev = new int[v];
		for (int i = 0; i < v; i++) {
			prev[i] = -1;
		}

		recurseDfs(prev, visited, s, t);
		print(prev, s, t);
	}

	private void recurseDfs(int[] prev, boolean[] visited, int w, int t) {
		if (found == true) {
			return;
		}

		if (w == t) {
			found = true;
			return;
		}
		visited[w] = true;
		for (int i = 0; i < adj[w].size(); i++) {
			int q = adj[w].get(i);
			if (!visited[q]) {
				prev[q] = w;
				recurseDfs(prev, visited, q, t);
			}
		}
	}

	public static void main(String[] args) {
		// https://time.geekbang.org/column/article/70891
		Graph_BFS_DFS g = new Graph_BFS_DFS(8);
		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(1, 2);
		g.addEdge(1, 4);
		g.addEdge(3, 4);
		g.addEdge(2, 5);
		g.addEdge(4, 5);
		g.addEdge(4, 6);
		g.addEdge(5, 7);
		g.addEdge(6, 7);

		System.out.println("bfs:");
		g.bfs(0, 6);
		System.out.println("");
		System.out.println("dfs:");
		g.dfs(0, 6);
	}
}
