package main.revision.october.meta.medium;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {

    // Directions array to move in 8 possible directions
    int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };

    class Solution {
        public int shortestPathBinaryMatrix(int[][] grid) {
            int n = grid.length;

            // Agar starting ya ending cell blocked hai, toh return -1
            if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
                return -1;

            // Visited array banate hain to track visited cells
            boolean[][] visit = new boolean[n][n];

            // BFS ke liye queue initialize karte hain
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] { 0, 0, 1 }); // [row, col, path_length]
            visit[0][0] = true;

            // Jab tak queue empty na ho, BFS chalayenge
            while (!q.isEmpty()) {
                int[] cell = q.poll();
                int row = cell[0];
                int col = cell[1];
                int length = cell[2];

                // Agar destination cell par pahunch gaye, toh path length return karte hain
                if (row == n - 1 && col == n - 1) {
                    return length;
                }

                // 8 directions mein move karte hain
                for (int[] dir : directions) {
                    int nr = row + dir[0];
                    int nc = col + dir[1];

                    // Agar new cell grid ke andar hai, unvisited hai aur blocked nahi hai
                    if (nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc] == 0 && !visit[nr][nc]) {
                        q.offer(new int[] { nr, nc, length + 1 }); // [row, col, path_length]
                        visit[nr][nc] = true; // Cell ko visited mark karte hain
                    }
                }
            }

            // Agar destination tak nahi pahunch paye, toh -1 return karte hain
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

    // revised on 11/8/2025
    class SolutionRevisionDaySeventh {
        public int shortestPathBinaryMatrix(int[][] grid) {

            int n = grid.length;

            if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
                return -1;
            }

            boolean[][] visit = new boolean[n][n];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] { 0, 0, 1 });
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

                    int nRow = row + dir[0];
                    int nCol = col + dir[1];

                    if (nRow >= 0 && nCol >= 0 && nRow <= n && nCol <= n &&
                            grid[nRow][nCol] == 0 && !visit[nRow][nCol]) {
                        q.offer(new int[] { nRow, nCol, length + 1 });
                        visit[nRow][nCol] = true;
                    }
                }
            }
            return -1;
        }
    }

    // revised on 11/22/2025
    class SolutionRevisionDayFourteen {
        public int shortestPathBinaryMatrix(int[][] grid) {

            int n = grid.length;

            if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
                return -1;
            }

            boolean[][] vis = new boolean[n][n];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] { 0, 0, 1 });

            vis[0][0] = true;

            while (!q.isEmpty()) {

                int[] cell = q.poll();
                int row = cell[0];
                int col = cell[1];
                int length = cell[2];

                if (row == n - 1 && col == n - 1) {
                    return length;
                }

                for (int[] dir : directions) {

                    int nRow = row + dir[0];
                    int nCol = col + dir[1];

                    if (nRow >= 0 && nCol >= n && nRow <= n && nCol <= n && !vis[nRow][nCol] && grid[nRow][nCol] == 0) {
                        q.offer(new int[] { nRow, nCol, length + 1 });
                        vis[nRow][nCol] = true;
                    }
                }
            }

            return -1;
        }
    }

    // revised on 12/21/2025
    class SolutionRevisionDayThirty {
        public int shortestPathBinaryMatrix(int[][] grid) {
            int n = grid.length;

            if (grid[0][0] != 1 || grid[n - 1][n - 1] != 1) {
                return -1;
            }

            boolean[][] visited = new boolean[n][n];
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[] { 0, 0, 1 });
            visited[0][0] = true;

            while (!q.isEmpty()) {

                int[] cur = q.poll();
                int curRow = cur[0];
                int curCol = cur[1];
                int curLength = cur[2];

                if (curRow == n - 1 && curCol == n - 1) {
                    return curLength;
                }

                for (int[] dir : directions) {

                    int newRow = curRow + dir[0];
                    int newCol = curCol + dir[1];

                    if (newRow > 0 && newCol > 0 && newRow < n && newCol < n && grid[newRow][newCol] == 0
                            && !visited[newRow][newCol]) {
                        q.offer(new int[] { newRow, newCol, curLength++ });
                        visited[newRow][newCol] = true;
                    }
                }
            }

            return -1;
        }
    }
}
