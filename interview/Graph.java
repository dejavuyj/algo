package interview;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

//有向无环图是否存在环
//给定一组数据节点，每个节点使用中括号括起来，多个节点之间使用半角逗号分隔，如下所示：
//[1-2],[2-3],[3-4,5,6],[4-],[5-8],[6-7],[7-],[8-2]
//节点内部结构使用“-”分隔，前半部分为节点ID，后半部分为该节点指向的节点，如果没有指向，则为空，多个指向节点使用半角逗号分隔；
//给定任意一组数据，请计算节点之间是否存在环？如果存在环，直接结束；不存在环，则打印出每条路径。

public class Graph {
	private int v; // 顶点数
	private LinkedList<Integer>[] adj; // 邻接表

	public Graph(int v) {
		this.v = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	public void addEdge(int s, int t) {
		adj[s].add(t);
	}

	public boolean detectCircle() {
		boolean hasCircle = false;

		int[] inDegree = new int[v];
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < adj[i].size(); j++) {
				int w = adj[i].get(j);
				inDegree[w]++;
			}
		}

		Set<Integer> visitedVertexs = new HashSet<Integer>();
		Set<String> visitedEdges = new HashSet<String>();

		LinkedList<Integer> queue = new LinkedList<>();
		for (int i = 1; i < v; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
				visitedVertexs.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int i = queue.remove();

			for (int j = 0; j < adj[i].size(); j++) {
				int k = adj[i].get(j);
				String edgeStr = i + "->" + k;
				if (visitedEdges.contains(edgeStr)) {
					// 已访问这条边,图中存在环
					return true;
				} else {
					visitedEdges.add(edgeStr);
				}
				if (!visitedVertexs.contains(k)) {
					visitedVertexs.add(k);
				}
				inDegree[k]--;
				if (inDegree[k] == 0) {
					queue.add(k);
				}
			}
		}

		if (visitedVertexs.size() != v - 1) {
			hasCircle = true;
		}

		if (!hasCircle) {
			// 打印每条边
			for (String edge : visitedEdges) {
				System.out.println(edge);
			}
		}
		return hasCircle;
	}

	// tbd,初始化图

	public static void main(String[] args) {
		Graph g = new Graph(9);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(3, 6);
		// g.addEdge(5, 8);
		g.addEdge(6, 7);
		// g.addEdge(8, 2);
		// g.addEdge(7, 8);

		// g.addEdge(1, 2);
		// g.addEdge(1, 3);
		// g.addEdge(1, 4);
		// g.addEdge(2, 4);
		// g.addEdge(3, 4);

		boolean ret = g.detectCircle();
		System.out.println(ret);
	}
}
