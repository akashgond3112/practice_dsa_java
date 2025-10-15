package main.revision.october.meta.hard;

import java.util.*;

public class ShortestDistanceFromAllBuildings {
    private static final int[][] DIRECTIONS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public int shortestDistance(int[][] grid) {

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

    private void bfs(int[][] grid, int startRow, int startCol, int[][] reachCount, int[][] totalDistance) {

        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[] { startRow, startCol });
        visited[startRow][startCol] = true;

        int distance = 0;

        while (!queue.isEmpty()) {
            distance++;

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                for (int[] dir : DIRECTIONS) {

                    int newRow = current[0] + dir[0];
                    int newCol = current[1] + dir[1];

                    if (isValid(grid, newRow, newCol, visited)) {

                        visited[newRow][newCol] = true;

                        reachCount[newRow][newCol]++;

                        totalDistance[newRow][newCol] += distance;

                        queue.offer(new int[] { newRow, newCol });
                    }
                }
            }
        }
    }

    private boolean isValid(int[][] grid, int row, int col, boolean[][] visited) {
        int rows = grid.length;
        int cols = grid[0].length;

        return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == 0 && !visited[row][col];
    }
}
