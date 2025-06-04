/**
 * @author agond
 * @date Jun 04, 2025
 * @time 9:43:34 PM
 */
package main.revision.meta.medium;

import java.util.*;

public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] + a[1]));

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
}
