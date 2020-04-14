package algorithm;

import java.util.LinkedList;

// 拓扑排序 有向图
public class TopologicalSort {

	private int v; // 顶点个数
	private LinkedList<Integer>[] adj; // 邻接表

	@SuppressWarnings("unchecked")
	public TopologicalSort(int v) {
		this.v = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	// 有向图一条边存一次 s先于t,边s->t
	public void addEdge(int s, int t) {
		adj[s].add(t);
	}

	public void topoSortByKahn() {
		int[] inDegree = new int[v]; // 统计每个顶点的入度
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < adj[i].size(); j++) {
				int w = adj[i].get(j); // i->w
				inDegree[w]++;
			}
		}
		LinkedList<Integer> queue = new LinkedList<>();
		for (int i = 0; i < v; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}
		while (!queue.isEmpty()) {
			int i = queue.remove();
			System.out.print("->" + i);
			for (int j = 0; j < adj[i].size(); j++) {
				int k = adj[i].get(j);
				inDegree[k]--;
				if (inDegree[k] == 0) {
					queue.add(k);
				}
			}
		}
	}

	public void topoSortByDFS() {
		// 先构建逆邻接表,边s->t表示s依赖t,t先于s
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] inverseAdj = new LinkedList[v];
		for (int i = 0; i < v; i++) { // 申请空间
			inverseAdj[i] = new LinkedList<Integer>();
		}
		for (int i = 0; i < v; i++) { // 通过邻接表生成逆邻接表
			for (int j = 0; j < adj[i].size(); j++) {
				int w = adj[i].get(j); // i->w
				inverseAdj[w].add(i); // w->i
			}
		}
		boolean[] visited = new boolean[v];
		for (int i = 0; i < v; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				dfs(i, inverseAdj, visited);
			}
		}
	}

	private void dfs(int vertex, LinkedList<Integer>[] inverseAdj, boolean[] visited) {
		for (int i = 0; i < inverseAdj[vertex].size(); i++) {
			int w = inverseAdj[vertex].get(i);
			if (visited[w] == true) {
				continue;
			}
			visited[w] = true;
			dfs(w, inverseAdj, visited);
		}
		// 先把vertex这个顶点可达的所有顶点都打印出来,再打印自己
		System.out.print("->" + vertex);
	}

	public static void main(String[] args) {
		// https://time.geekbang.org/column/article/70891
		TopologicalSort g = new TopologicalSort(8);
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

		System.out.println("Kahn:");
		g.topoSortByKahn();
		System.out.println("");
		System.out.println("dfs:");
		g.topoSortByDFS();
	}
}
