package leetcode.dataStructure.graph;

import java.util.*;

public class Code210_course_schedule_ii {

    List<List<Integer>> edges;
    int[] indeg;
    int[] result;
    int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        indeg = new int[numCourses];
        result = new int[numCourses];
        index = 0;
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            result[index++] = u;
            for (int v : edges.get(u)) {
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        if (index != numCourses) {
            return new int[0];
        }
        return result;
    }

    public static void main(String[] args) {
        Code210_course_schedule_ii c = new Code210_course_schedule_ii();
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        int[] resultArray = c.findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(resultArray));
    }
}
