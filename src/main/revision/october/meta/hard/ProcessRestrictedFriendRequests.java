package main.revision.october.meta.hard;

public class ProcessRestrictedFriendRequests {

    /**
     * Solution class to process friend requests with restrictions using Union-Find.
     */
    static class Solution {

        /**
         * Finds the representative (root) of the set that x belongs to with path compression.
         *
         * @param parents The parent array representing the disjoint sets.
         * @param x The element whose set representative is to be found.
         * @return The representative of the set containing x.
         *          
         * */
        private int find(int[] parents, int x) {
            if (parents[x] != x) {
                parents[x] = find(parents, parents[x]);
            }
            return parents[x];
        }

        /**
         * Processes friend requests and determines if each request can be accepted
         * without violating any restrictions.
         *
         * @param n            The number of people.
         * @param restrictions The list of restrictions, where each restriction is a
         *                     pair [a, b] meaning a and b cannot be friends.
         * @param requests     The list of friend requests, where each request is a pair
         *                     [u, v].
         * @return A boolean array where each element is true if the corresponding
         *         request can be accepted, false otherwise.
         */
        public boolean[] processFriendRequestsWithRestrictions(int n, int[][] restrictions, int[][] requests) {
            int[] parents = new int[n];
            int[] rank = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                rank[i] = 1;
            }

            int reqLen = requests.length;
            boolean[] ans = new boolean[reqLen];

            for (int i = 0; i < reqLen; i++) {
                int u = requests[i][0];
                int v = requests[i][1];

                int rootU = find(parents, u);
                int rootV = find(parents, v);

                if (rootU == rootV) {
                    ans[i] = true;
                    continue;
                }

                boolean canBeFriends = true;
                for (int[] restriction : restrictions) {
                    int r1 = restriction[0];
                    int r2 = restriction[1];
                    int rootR1 = find(parents, r1);
                    int rootR2 = find(parents, r2);

                    if ((rootU == rootR1 && rootV == rootR2) || (rootU == rootR2 && rootV == rootR1)) {
                        canBeFriends = false;
                        break;
                    }
                }

                ans[i] = canBeFriends;
                if (canBeFriends) {
                    union(parents, rank, rootU, rootV);
                }
            }

            return ans;
        }

        private void union(int[] parents, int[] rank, int x, int y) {
            int rootX = find(parents, x);
            int rootY = find(parents, y);

            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parents[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parents[rootY] = rootX;
                } else {
                    parents[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }
}
