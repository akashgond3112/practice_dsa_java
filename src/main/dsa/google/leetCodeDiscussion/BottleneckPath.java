/**
 * BottleneckPath
 *
 * Solves the Bottleneck (Min–Max) Path problem on a directed, weighted graph.
 * The cost of a path is defined as the maximum edge weight along that path; this class
 * finds a path from a source to a destination that minimizes this maximum edge weight.
 *
 * Approach:
 * - Modified Dijkstra: treat the "distance" of a node as the minimum achievable bottleneck
 *   (minimum possible maximum edge weight) to reach that node.
 * - Use a min-heap ordered by current bottleneck value. When relaxing edge (u->v, w),
 *   the candidate bottleneck for v is max(bottleneck[u], w). Update if this is smaller
 *   than the current best for v.
 *
 * Assumptions:
 * - Graph is provided as an edge list int[][] edges where each entry is {from, to, weight}.
 * - Nodes are indexed 0..n-1. Edges are treated as directed (an undirected graph can be
 *   represented by adding both directions).
 * - Edge weights are non-negative.
 *
 * Complexity:
 * - Time: O(E log V) where E is number of edges and V is number of vertices.
 * - Space: O(V + E) for adjacency lists and distance (bottleneck) storage.
 *
 * Alternative:
 * - Binary search on edge weights + BFS/DFS connectivity check (O((V+E) log UniqueWeights)).
 *
 * Public API:
 *   int findMinMaxPath(int n, int[][] edges, int startNode, int endNode)
 *     @param n         number of vertices (0..n-1)
 *     @param edges     edge list, each element {from, to, weight}
 *     @param startNode source node index
 *     @param endNode   destination node index
 *     @return the minimum possible maximum edge weight along any path from startNode to endNode,
 *             or -1 if no path exists
 *
 * Notes:
 * - Inner helper classes represent adjacency entries and priority-queue entries.
 * - The algorithm returns immediately when the destination is popped from the queue because
 *   the first pop of a node yields its optimal bottleneck value.
 */
package main.dsa.google.leetCodeDiscussion;

import java.util.*;

/*
This is a classic graph problem known as the Bottleneck Path Problem (or Min-Max Path).
We need to find a path where the "cost" is determined not by the sum of edge weights, but by the largest edge weight on that path. We want to minimize this cost.
The Approach: Modified Dijkstra's Algorithm
Standard Dijkstra finds the path with the minimum sum. We can modify the "relaxation" logic to track the minimum maximum edge weight instead.
Logic:
State: minMax[node] stores the lowest possible "max-edge-weight" required to reach node from startNode.
Initialization: Set all minMax values to Infinity, except minMax[startNode] = 0.
Priority Queue: Use a Min-Heap. Always expand the node that has the smallest bottleneck value so far.
Relaxation (The Key Change):
When moving from node u to node v via an edge with weight w:
The max weight to reach v via this path is Math.max(minMax[u], w).
If this value is less than the current minMax[v], we update minMax[v] and add it to the queue.
Java Solution
code
Java
Complexity Analysis
Time Complexity: 
O
(
E
log
⁡
V
)
O(ElogV)
This is identical to standard Dijkstra. We visit every edge once, and priority queue operations take logarithmic time relative to the number of vertices.
Space Complexity: 
O
(
V
+
E
)
O(V+E)
Used for storing the graph (Adjacency List) and the minMax array.
Alternative Approach (Binary Search)
If you are asked for an alternative, you can mention Binary Search + BFS/DFS:
Collect all unique edge weights and sort them.
Binary search on the weights.
For a chosen weight W (mid), run BFS/DFS considering only edges with weight <= W.
If endNode is reachable, try a smaller W; otherwise, try larger.
Time: 
O
(
(
V
+
E
)
log
⁡
(
UniqueWeights
)
)
O((V+E)log(UniqueWeights))
. This is also a very acceptable solution but slightly harder to implement than Modified Dijkstra.
*/
public class BottleneckPath {

    // Helper class to represent edges
    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // Helper class for Priority Queue
    static class NodeEntry {
        int node;
        int maxValSoFar;

        public NodeEntry(int node, int maxValSoFar) {
            this.node = node;
            this.maxValSoFar = maxValSoFar;
        }
    }

    public int findMinMaxPath(int n, int[][] edges, int startNode, int endNode) {
        // 1. Build Adjacency List
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            // edge: [from, to, weight]
            graph.get(edge[0]).add(new Edge(edge[1], edge[2]));
        }

        // 2. Initialize Distances (Bottleneck values)
        int[] minMax = new int[n];
        Arrays.fill(minMax, Integer.MAX_VALUE);
        minMax[startNode] = 0;

        // 3. Priority Queue ordered by the bottleneck value (min-heap)
        PriorityQueue<NodeEntry> pq = new PriorityQueue<>((a, b) -> a.maxValSoFar - b.maxValSoFar);
        pq.offer(new NodeEntry(startNode, 0));

        // 4. Process
        while (!pq.isEmpty()) {
            NodeEntry current = pq.poll();
            int u = current.node;
            int currentVal = current.maxValSoFar;

            // Optimization: If we reached the end node, this is the answer
            // because Dijkstra guarantees the first time we pop a node, it's optimal.
            if (u == endNode) {
                return currentVal;
            }

            // Stale entry check (standard Dijkstra optimization)
            if (currentVal > minMax[u]) {
                continue;
            }

            // Explore neighbors
            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                // The bottleneck to reach 'v' via 'u' is the larger of:
                // 1. The bottleneck to reach 'u'
                // 2. The weight of the edge (u, v)
                int newPathMax = Math.max(currentVal, weight);

                // If this path offers a smaller bottleneck than previously found for 'v'
                if (newPathMax < minMax[v]) {
                    minMax[v] = newPathMax;
                    pq.offer(new NodeEntry(v, newPathMax));
                }
            }
        }

        // If path not found
        return -1;
    }

    // Driver for testing
    public static void main(String[] args) {
        BottleneckPath solver = new BottleneckPath();

        // Example:
        // 0 -> 1 (weight 5)
        // 0 -> 2 (weight 10)
        // 1 -> 3 (weight 8)
        // 2 -> 3 (weight 2)
        // Path 0->1->3: Max edge is 8
        // Path 0->2->3: Max edge is 10
        // Result should be 8

        int n = 4;
        int[][] edges = {
                { 0, 1, 5 },
                { 0, 2, 10 },
                { 1, 3, 8 },
                { 2, 3, 2 }
        };

        System.out.println(solver.findMinMaxPath(n, edges, 0, 3)); // Output: 8
    }
}
