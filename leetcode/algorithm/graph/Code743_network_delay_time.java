package leetcode.algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Code743_network_delay_time {

	public int networkDelayTime(int[][] times, int N, int K) {
		// graphͼ,��¼����ͱߵ���Ϣ,���ĳ�����㲻ָ����������,�򲻼�¼�˶���
		Map<Integer, List<int[]>> graph = new HashMap<Integer, List<int[]>>();
		for (int[] edge : times) {
			if (!graph.containsKey(edge[0])) {
				graph.put(edge[0], new ArrayList<int[]>());
			}
			graph.get(edge[0]).add(new int[] { edge[1], edge[2] });
		}

		// heapС����,��¼��ʼ���㵽��ǰ����ľ����Լ���ǰ�����ID
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>((info1, info2) -> info1[0] - info2[0]);
		heap.offer(new int[] { 0, K });

		// dist����Map,��¼ÿ�����㵽��ʼ�������̾���
		Map<Integer, Integer> dist = new HashMap<Integer, Integer>();

		while (!heap.isEmpty()) {
			// ȡ��heap������ʼ������̾���Ķ���
			int[] info = heap.poll();
			int d = info[0];
			int node = info[1];
			if (dist.containsKey(node)) {
				continue;
			}

			dist.put(node, d);
			if (graph.containsKey(node)) {
				// ѭ������ǰ����ı�
				for (int[] edge : graph.get(node)) {
					int nextId = edge[0];
					int d2 = edge[1];
					if (!dist.containsKey(nextId)) {
						heap.offer(new int[] { d + d2, nextId });
					}
				}
			}
		}

		if (dist.size() != N) {
			return -1;
		}

		int ans = 0;
		for (int cand : dist.values()) {
			ans = Math.max(ans, cand);
		}
		return ans;
	}

	public int networkDelayTime2(int[][] times, int N, int K) {
		int INF = 0x3F3F3F;
		int[][] graph = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				graph[i][j] = i == j ? 0 : INF;
			}
		}

		for (int[] edge : times) {
			graph[edge[0]][edge[1]] = edge[2];
		}

		boolean[] checked = new boolean[N + 1];
		for (int minN = K, newMin = 0; minN > 0; minN = newMin) {
			// Dijkstra�㷨
			checked[minN] = true;
			newMin = 0;
			for (int j = 1; j < N + 1; j++) {
				graph[K][j] = Math.min(graph[K][j], graph[K][minN] + graph[minN][j]);
				if (!checked[j] && graph[K][j] < graph[K][newMin]) {
					newMin = j;
				}
			}
		}

		int res = -1;
		for (int j = 1; j <= N; j++) {
			res = Math.max(res, graph[K][j]);
			if (res == INF) {
				return -1;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Code743_network_delay_time c = new Code743_network_delay_time();
		// int[][] times = { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 } };
		// int N = 4;
		// int K = 2;
		int[][] times = { { 1, 2, 1 }, { 2, 3, 2 }, { 1, 3, 4 } };
		int N = 3;
		int K = 1;
		int ans = c.networkDelayTime2(times, N, K);
		System.out.println(ans);
	}
}
