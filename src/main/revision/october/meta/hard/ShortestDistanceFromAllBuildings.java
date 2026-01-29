package main.revision.october.meta.hard;

import java.util.*;

public class ShortestDistanceFromAllBuildings {
    private static final int[][] DIRECTIONS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public class Solution {

        // Pre-defined directions for moving up, down, left, and right in the grid.
        private static final int[][] DIRECTIONS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        /**
         * Finds the shortest distance from an empty land cell to all buildings.
         *
         * @param grid A 2D grid where 1 represents a building, 0 is empty land, and 2
         *             is an obstacle.
         * @return The minimum total distance, or -1 if no such meeting point exists.
         */
        public int shortestDistance(int[][] grid) {
            // --- Step 1: Initialization ---
            // Get grid dimensions.
            int rows = grid.length;
            int cols = grid[0].length;

            // `reachCount[i][j]` stores how many buildings can reach the empty land at (i,
            // j).
            int[][] reachCount = new int[rows][cols];

            // `totalDistance[i][j]` stores the sum of distances from all reachable
            // buildings to the empty land at (i, j).
            int[][] totalDistance = new int[rows][cols];

            // `totalBuilding` counts the total number of buildings in the grid.
            int totalBuilding = 0;

            // --- Step 2: BFS from each building ---
            // Iterate through the grid to find all buildings.
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // When a building is found (grid[i][j] == 1)...
                    if (grid[i][j] == 1) {
                        totalBuilding++;
                        // ...start a BFS traversal from this building to calculate distances to all
                        // reachable empty lands.
                        bfs(grid, i, j, reachCount, totalDistance);
                    }
                }
            }

            // --- Step 3: Find the minimum distance ---
            // Initialize minDistance to the largest possible integer value.
            int minDistance = Integer.MAX_VALUE;
            // Iterate through the grid again to check each empty land cell.
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // A valid meeting point must be an empty land (grid[i][j] == 0) and
                    // reachable from ALL buildings (reachCount[i][j] == totalBuilding).
                    if (grid[i][j] == 0 && reachCount[i][j] == totalBuilding) {
                        // If it's a valid point, update minDistance with the smaller value.
                        minDistance = Math.min(minDistance, totalDistance[i][j]);
                    }
                }
            }

            // --- Step 4: Return the result ---
            // If minDistance was never updated, it means no valid meeting point was found.
            // In that case, return -1. Otherwise, return the found minimum distance.
            return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
        }

        /**
         * Performs BFS from a building to update distances and reach counts.
         *
         * @param grid          The input grid.
         * @param startRow      The row of the starting building.
         * @param startCol      The column of the starting building.
         * @param reachCount    Matrix to track how many buildings can reach each cell.
         * @param totalDistance Matrix to accumulate total distances.
         */
        private void bfs(int[][] grid, int startRow, int startCol, int[][] reachCount, int[][] totalDistance) {
            int rows = grid.length;
            int cols = grid[0].length;

            // --- Step 1: BFS Initialization ---
            // `visited` tracks visited cells for the CURRENT BFS run to avoid cycles.
            boolean[][] visited = new boolean[rows][cols];
            // A queue to hold cells to visit, standard for BFS.
            Queue<int[]> queue = new LinkedList<>();

            // Start the BFS from the building's location.
            queue.offer(new int[] { startRow, startCol });
            visited[startRow][startCol] = true;

            // `distance` tracks the distance from the starting building. It increases by 1
            // at each level of the BFS.
            int distance = 0;

            // --- Step 2: Level-by-Level Traversal ---
            while (!queue.isEmpty()) {
                // --- Step 3: Process a Level ---
                // `size` is the number of cells at the current distance level.
                int size = queue.size();
                // Process all cells at the current level before moving to the next.
                for (int i = 0; i < size; i++) {
                    int[] current = queue.poll();

                    // --- Step 4: Explore Neighbors ---
                    // Check all four directions (up, down, left, right).
                    for (int[] dir : DIRECTIONS) {
                        int newRow = current[0] + dir[0];
                        int newCol = current[1] + dir[1];

                        // --- Step 5: Validate and Update ---
                        // Check if the neighbor is a valid, unvisited empty land cell.
                        if (isValid(grid, newRow, newCol, visited)) {
                            // Mark as visited for this BFS run.
                            visited[newRow][newCol] = true;
                            // Increment the reach count for this cell.
                            reachCount[newRow][newCol]++;
                            // Add the distance from the current building to the total distance for this
                            // cell.
                            totalDistance[newRow][newCol] += distance + 1;
                            // Add the valid neighbor to the queue to be processed in the next level.
                            queue.offer(new int[] { newRow, newCol });
                        }
                    }
                }
                // --- Step 6: Increment Distance ---
                // After processing all nodes at the current level, increment the distance for
                // the next level.
                distance++;
            }
        }

        /**
         * Checks if a cell is a valid target for BFS traversal.
         *
         * @param grid    The main grid.
         * @param row     The row index of the cell to check.
         * @param col     The column index of the cell to check.
         * @param visited A boolean grid indicating visited cells for the current BFS.
         * @return True if the cell is within bounds, is empty land (0), and has not
         *         been visited in the current BFS run.
         */
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

    // Revised on 21/10/2025
    public class SolutionRevisionSeventhDay {

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

                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    int[] current = queue.poll();

                    for (int[] dir : DIRECTIONS) {

                        int newRow = current[0] + dir[0];
                        int newCol = current[1] + dir[1];

                        if (isValid(grid, newRow, newCol, visited)) {

                            visited[newRow][newCol] = true;
                            reachCount[newRow][newCol]++;

                            totalDistance[newRow][newCol] += distance + 1;

                            queue.offer(new int[] { newRow, newCol });
                        }

                    }
                }

                distance++;
            }

        }

        public boolean isValid(int[][] grid, int row, int col, boolean[][] visited) {

            int rows = grid.length;
            int cols = grid[0].length;

            return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == 0 && !visited[row][col];
        }
    }

    // Revised on 04/11/2025
    public class SolutionRevisionFourteenDay {
        public int shortestDistance(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;

            int[][] reachCount = new int[rows][cols];
            int[][] totalDistance = new int[rows][cols];

            int totalBuilding = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
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

                int size = queue.size();

                for (int i = 0; i < size; i++) {

                    int[] current = queue.poll();

                    for (int[] dir : DIRECTIONS) {

                        int newRow = current[0] + dir[0];
                        int newCol = current[1] + dir[1];

                        if (isValid(grid, newRow, newCol, visited)) {
                            visited[newRow][newCol] = true;
                            reachCount[newRow][newCol]++;
                            totalDistance[newRow][newCol] += distance + 1;
                            queue.offer(new int[] { newRow, newCol });
                        }
                    }
                }
                distance++;

            }

        }

        public boolean isValid(int[][] grid, int row, int col, boolean[][] visited) {

            int rows = grid.length;
            int cols = grid[0].length;

            return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == 0 && !visited[row][col];
        }
    }

    // Revised on 12/3/2025
    public class SolutionRevisionThirtyDay {

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

                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    int[] current = queue.poll();

                    for (int[] dir : DIRECTIONS) {

                        int newRow = current[0] + dir[0];
                        int newCol = current[1] + dir[1];

                        if (isValid(grid, newRow, newCol, visited)) {

                            visited[newRow][newCol] = true;
                            reachCount[newRow][newCol]++;

                            totalDistance[newRow][newCol] += distance + 1;

                            queue.offer(new int[] { newRow, newCol });
                        }
                    }
                }
            }

        }

        public boolean isValid(int[][] grid, int row, int col, boolean[][] visited) {

            int rows = grid.length;
            int cols = grid[0].length;

            return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == 0 && !visited[row][col];
        }
    }
}
