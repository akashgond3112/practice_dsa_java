package main.revision.october.meta.medium;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
    class Solution {
        public int shortestPathBinaryMatrix(int[][] grid) {
            int n = grid.length;
            if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
                return -1;

            int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { -1, -1 }, { 1, -1 },
                    { -1, 1 } };
            boolean[][] visit = new boolean[n][n];

            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] { 0, 0, 1 }); // [row, col, path_length]
            visit[0][0] = true;

            while (!q.isEmpty()) {
                int[] cell = q.poll();
                int row = cell[0];
                int col = cell[1];
                int length = cell[2];

                if (row == n - 1 && col == n - 1) {
                    return length;
                }

                for (int[] dir : directions) {
                    int nr = row + dir[0];
                    int nc = col + dir[1];

                    if (nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc] == 0 && !visit[nr][nc]) {
                        q.offer(new int[] { nr, nc, length + 1 }); // [row, col, path_length]
                        visit[nr][nc] = true;
                    }
                }
            }

            return -1;
        }
    }

    // revised on 02/11/2025
    class SolutionRevisionThirdDay {
        public int shortestPathBinaryMatrix(int[][] grid) {
            int n = grid.length;

            if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
                return -1;
            }

            int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { -1, -1 }, { 1, -1 },
                    { -1, 1 } };

            boolean[][] visit = new boolean[n][n];
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[] { 0, 0, 1 });
            visit[0][0] = true;

            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int row = cell[0];
                int col = cell[1];
                int length = cell[2];

                if (row == n - 1 && col == n - 1) {
                    return length;
                }

                for (int[] dir : directions) {

                    int nRow = row + dir[0];
                    int nCol = col + dir[1];

                    if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < n && grid[nRow][nCol] == 0 && !visit[nRow][nCol]) {
                        queue.offer(new int[] { nRow, nCol, length + 1 }); // [row, col, path_length]
                        visit[nRow][nCol] = true;
                    }
                }

            }

            return -1;
        }
    }
}
