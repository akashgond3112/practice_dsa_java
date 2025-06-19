/**
 * @author agond
 * @date Jun 19, 2025
 * @time 8:03:40 PM
 */
package main.revision.meta.medium;

import java.util.*;

public class ShortestPathInBinaryMatrix {

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 },
                { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, 0, 1 });

        boolean[][] vis = new boolean[n][n];

        vis[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int row = cur[0];
            int col = cur[1];
            int length = cur[2];

            if (row == n - 1 && col == n - 1) {
                return length;
            }

            for (int[] d : dir) {

                int newRow = row + d[0];
                int newCol = col + d[1];

                if (newRow >= 0 && newCol > 0 && newRow < n && newCol < n &&
                        grid[newRow][newCol] == 0 && !vis[newRow][newCol]) {
                    q.offer(new int[] { newRow, newCol, length + 1 });
                    vis[newRow][newCol] = true;
                }
            }
        }

        return -1;
    }

}
