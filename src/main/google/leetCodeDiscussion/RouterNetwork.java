package main.google.leetCodeDiscussion;

import java.util.*;

public class RouterNetwork {

    /**
     * @param adjList     The network topology (Adjacency List)
     * @param defective   Set of IDs of defective routers
     * @param source      Starting router ID
     * @param destination Target router ID
     * @return true if the message can reach the destination
     *         Time: O(V + E) — V = number of routers (nodes), E = number of links
     *         (edges). BFS visits each node and edge at most once (defective
     *         checks/visited checks are O(1)).
     *         Space: O(V) — queue + visited set (plus O(1) for the defective set
     *         reference).
     *         Note: may finish earlier on early-found destination, but worst-case
     *         cost above.
     */
    public boolean canReachDestination(Map<Integer, List<Integer>> adjList,
            Set<Integer> defective,
            int source,
            int destination) {

        // Edge Case: If source or destination are defective, no broadcast possible
        if (defective.contains(source) || defective.contains(destination)) {
            return false;
        }

        // Edge Case: Already at destination
        if (source == destination) {
            return true;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // Get neighbors from the adjacency list
            List<Integer> neighbors = adjList.getOrDefault(current, new ArrayList<>());

            for (int neighbor : neighbors) {
                if (neighbor == destination) {
                    return true;
                }

                // Only proceed if the router is functional and not yet visited
                if (!defective.contains(neighbor) && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Example Setup
        Map<Integer, List<Integer>> network = new HashMap<>();
        network.put(1, Arrays.asList(2, 3));
        network.put(2, Arrays.asList(4));
        network.put(3, Arrays.asList(4));
        network.put(4, Arrays.asList(5));

        Set<Integer> defective = new HashSet<>(Arrays.asList(3)); // Router 3 is down

        RouterNetwork sol = new RouterNetwork();
        System.out.println("Can reach 5 from 1? " + sol.canReachDestination(network, defective, 1, 5));
    }
}
