package main.revision.october.meta.medium;

import java.util.*;

public class KClosestPointsToOrigin {

    /**
     * Approach: Using Max Heap
     * Time Complexity: O(n log k)
     * Space Complexity: O(k)
     */
    public int[][] kClosest(int[][] points, int k) {

        if (points == null || points.length == 0 || k <= 0) {
            return new int[0][0];
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(getDistanceFromOrigin(b),
                getDistanceFromOrigin(a)));

        for (int[] point : points) {

            pq.offer(point);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[][] result = new int[k][2];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = pq.poll();
        }

        return result;
    }

    // Revised on 19/10/2025
    public int[][] kClosestRevisionThirdDay(int[][] points, int k) {

        if (points == null || points.length == 0 || k <= 0) {
            return new int[0][0];
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(getDistanceFromOrigin(b),
                getDistanceFromOrigin(a)));

        for (int[] point : points) {

            pq.offer(point);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[][] result = new int[k][2];
        int i = 0;
        while (!pq.isEmpty()) {
            result[i++] = pq.poll();
        }
        return result;
    }

    /**
     * Helper method to calculate distance from origin.
     * Note: We don't need to calculate actual distance (sqrt),
     * comparing squared distances is sufficient.
     *
     * @param point an array of two integers representing the x and y coordinates of
     *              a point
     * @return the squared distance from the origin
     */
    private int getDistanceFromOrigin(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    // Revised on 25/10/2025
    class SolutionRevisionSeventhDay {
        public int[][] kClosest(int[][] points, int k) {
            if (points == null || points.length == 0 || k <= 0) {
                return new int[0][0];
            }

            PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(getDistanceFromOrigin(b),
                    getDistanceFromOrigin(a)));

            for (int[] point : points) {

                q.offer(point);

                if (q.size() > k) {
                    q.poll();
                }
            }

            int[][] res = new int[k][2];
            int i = 0;
            while (!q.isEmpty()) {
                res[i++] = q.poll();
            }

            return res;
        }
    }

    // Revised on 25/10/2025
    class SolutionRevisionDayFourteen {
        public int[][] kClosest(int[][] points, int k) {
            if (points == null || points.length == 0 || k <= 0) {
                return new int[0][0];
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>(
                    (a, b) -> Integer.compare(getDistanceFromOrigin(b), getDistanceFromOrigin(a)));

            for (int[] point : points) {
                pq.offer(point);

                if (pq.size() > k) {
                    pq.poll();
                }
            }

            int[][] res = new int[k][2];
            int i = 0;
            while (!pq.isEmpty()) {
                res[i++] = pq.poll();
            }

            return res;
        }
    }

    // Revised on 12/7/2025
    class SolutionRevisionDayThirty {
        public int[][] kClosest(int[][] points, int k) {

            if (points == null || points.length == 0 || k <= 0) {
                return new int[0][0];
            }

            PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(getDistanceFromOrigin(b),
                    getDistanceFromOrigin(a)));

            for (int[] point : points) {

                q.offer(point);

                if (q.size() > k) {
                    q.poll();
                }
            }

            int[][] res = new int[k][k];
            int i = 0;
            while (!q.isEmpty()) {
                res[i++] = q.poll();
            }

            return res;

        }
    }
}
