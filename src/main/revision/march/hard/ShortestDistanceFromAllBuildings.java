package main.revision.march.hard;

import java.util.*;

public class ShortestDistanceFromAllBuildings {

    private static final int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public class Solution {

        public int shortDistance(int[][] grid) {

            int rows = grid.length;
            int cols = grid[0].length;

            int[][] reachCount = new int[rows][cols];
            int[][] totalDistance = new int[rows][cols];

            int totalBuilding = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        totalBuilding++;

                        bfs(grid, i, j, reachCount, totalDistance);
                    }
                }
            }

            int minDistance = Integer.MAX_VALUE;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 0 && reachCount[i][j] == totalBuilding) {
                        minDistance = Math.min(minDistance, totalDistance[i][j]);
                    }
                }
            }

            return minDistance == Integer.MAX_VALUE ? -1 : minDistance;

        }

        private void bfs(int[][] grid, int row, int col, int[][] reachCount, int[][] totalDistance) {
            int rows = grid.length;
            int cols = grid[0].length;

            boolean[][] visited = new boolean[rows][cols];
            Queue<int[]> q = new LinkedList<>();

            q.offer(new int[] { row, col });
            visited[row][col] = true;

            int distance = 0;

            while (!q.isEmpty()) {

                int size = q.size();

                for (int i = 0; i < size; i++) {
                    int[] cur = q.poll();

                    for (int[] dir : directions) {
                        int r = cur[0] + dir[0];
                        int c = cur[i] + dir[1];

                        if (isValid(grid, r, c, visited)) {

                            visited[r][c] = true;

                            reachCount[r][c]++;
                            totalDistance[r][c] += distance + 1;
                            q.offer(new int[] { r, c });

                        }
                    }
                }
                distance++;
            }
        }

        private boolean isValid(int[][] grid, int row, int col, boolean[][] visited) {
            int rows = grid.length;
            int cols = grid[0].length;

            // A cell is valid if:
            // 1. It's within the grid boundaries.
            // 2. It's an empty land cell (value 0).
            // 3. It has not been visited in the current BFS traversal.
            return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == 0 && !visited[row][col];
        }
    }
}
