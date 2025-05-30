package main.dsa.nonlinear.graph.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Prerequisite Tasks
Difficulty: MediumAccuracy: 37.81%Submissions: 68K+Points: 4
There are a total of N tasks, labeled from 0 to N-1. Some tasks may have prerequisites, for example to do task 0 you have to first complete task 1,
which is expressed as a pair: [0, 1]
Given the total number of tasks N and a list of prerequisite pairs P, find if it is possible to finish all tasks.

Example 1:
Input:
N = 4, P = 3
prerequisites = {{1,0},{2,1},{3,2}}
Output:
Yes
Explanation:
To do task 1 you should have completed
task 0, and to do task 2 you should
have finished task 1, and to do task 3 you
should have finished task 2. So it is possible.

Example 2:
Input:
N = 2, P = 2
prerequisites = {{1,0},{0,1}}
Output:
No
Explanation:
To do task 1 you should have completed
task 0, and to do task 0 you should
have finished task 1. So it is impossible.

Your task:
You don’t need to read input or print anything. Your task is to complete the function isPossible() which takes the integer N denoting the number of tasks,
P denoting the number of prerequisite pairs and prerequisite as input parameters and returns true if it is possible to finish all tasks otherwise returns false.


Expected Time Complexity: O(N + P)
Expected Auxiliary Space: O(N + P)


Constraints:
1 ≤ N ≤ 104
1 ≤ P ≤ 105
*/
public class PrerequisiteTasks {

	public boolean isPossible(int n, int[][] prerequisites) {

		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adjList.add(new ArrayList<>());
		}

		int m = prerequisites.length;
		for (int i = 0; i < m; i++) {
			adjList.get(prerequisites[i][0]).add(prerequisites[i][1]);
		}

		int[] degree = new int[n];
		for (int i = 0; i < n; i++) {
			for (int it : adjList.get(i)) {
				degree[it]++;
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if (degree[i] == 0) {
				queue.add(i);
			}
		}

		List<Integer> integerList = new ArrayList<>();
		CourseSchedule.queueTask(adjList, degree, queue, integerList);

		return integerList.size() == n;

	}

	public static void main(String[] args) {
		int N = 4;
		int[][] prerequisites = new int[3][2];
		prerequisites[0][0] = 1;
		prerequisites[0][1] = 0;

		prerequisites[1][0] = 2;
		prerequisites[1][1] = 1;

		prerequisites[2][0] = 3;
		prerequisites[2][1] = 2;

		PrerequisiteTasks obj = new PrerequisiteTasks();
		boolean ans = obj.isPossible(N, prerequisites);
		if (ans)
			System.out.println("YES");
		else
			System.out.println("NO");
	}

}
