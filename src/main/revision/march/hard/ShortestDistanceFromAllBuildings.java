package main.revision.march.hard;

import java.util.*;

public class ShortestDistanceFromAllBuildings {

    private static final int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    // 21/03/2026
    public class SolutionOnDayFirst {

        // Main method to find shortest distance from empty land to all buildings
        // Time: O(B * R * C) where B = number of buildings, R = rows, C = cols
        // Each BFS from a building explores all reachable cells once
        // Space: O(R * C) for reachCount, totalDistance, and intermediate visited
        // arrays
        public int shortDistance(int[][] grid) {

            // Step 1: grid ka size nikaalo
            int rows = grid.length;
            int cols = grid[0].length;

            // Step 2: helper matrices banao
            // reachCount[r][c] = kitni buildings se ye cell reachable hai
            // totalDistance[r][c] = sab buildings se distance ka sum
            int[][] reachCount = new int[rows][cols];
            int[][] totalDistance = new int[rows][cols];

            // Step 3: total buildings count karo
            int totalBuilding = 0;

            // Step 4: har building (value=1) se BFS chalao
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        totalBuilding++;

                        bfs(grid, i, j, reachCount, totalDistance);
                    }
                }
            }

            // Step 5: valid empty land cells me minimum distance pick karo
            int minDistance = Integer.MAX_VALUE;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 0 && reachCount[i][j] == totalBuilding) {
                        minDistance = Math.min(minDistance, totalDistance[i][j]);
                    }
                }
            }

            // Step 6: agar koi valid candidate nahi mila to -1 return
            return minDistance == Integer.MAX_VALUE ? -1 : minDistance;

        }

        // BFS from a single building to all reachable empty lands
        // Time: O(R * C) - each cell visited at most once in this BFS
        // Space: O(R * C) - visited array + queue can hold up to O(R * C) cells
        private void bfs(int[][] grid, int row, int col, int[][] reachCount, int[][] totalDistance) {
            // Step 1: dimensions nikaalo
            int rows = grid.length;
            int cols = grid[0].length;

            // Track visited cells in current BFS to avoid revisits
            boolean[][] visited = new boolean[rows][cols];
            Queue<int[]> q = new LinkedList<>();

            // Step 2: current building se BFS start karo
            q.offer(new int[] { row, col });
            visited[row][col] = true;

            // Step 3: level-order BFS ke liye distance variable
            int distance = 0;

            // Step 4: queue empty hone tak process karo
            while (!q.isEmpty()) {

                int size = q.size();

                // Current BFS level ke saare nodes process karo
                for (int i = 0; i < size; i++) {
                    int[] cur = q.poll();

                    // 4 directions me neighbors check karo
                    for (int[] dir : directions) {
                        int r = cur[0] + dir[0];
                        int c = cur[i] + dir[1];

                        // Sirf valid empty aur unvisited cell ko process karo
                        if (isValid(grid, r, c, visited)) {

                            visited[r][c] = true;

                            // Ye building is cell tak pahunch sakti hai
                            reachCount[r][c]++;
                            // Distance sum me current level + 1 add karo
                            totalDistance[r][c] += distance + 1;
                            q.offer(new int[] { r, c });

                        }
                    }
                }
                // Next level means distance +1
                distance++;
            }
        }

        // Validation helper for BFS
        // Time: O(1) - constant time boundary and value checks
        // Space: O(1) - no extra space used
        private boolean isValid(int[][] grid, int row, int col, boolean[][] visited) {
            int rows = grid.length;
            int cols = grid[0].length;

            // Hinglish rule check:
            // 1) cell grid ke andar hona chahiye
            // 2) cell empty land (0) hona chahiye
            // 3) current BFS me pehle visit nahi hua hona chahiye
            return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == 0 && !visited[row][col];
        }
    }

    public class SolutionRevisedOnDayThird {

        // Same high-level approach:
        // 1) Har building se BFS
        // 2) reachCount + totalDistance fill
        // 3) final pass me min distance choose
        // Time: O(B * R * C), Space: O(R * C)
        public int shortDistance(int[][] grid) {

            // Step 1: dimensions
            int rows = grid.length;
            int cols = grid[0].length;

            // Step 2: aggregation arrays
            int[][] reachCount = new int[rows][cols];
            int[][] totalDistance = new int[rows][cols];

            // Step 3: count buildings and run BFS from each building
            int totalBuilding = 0;

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] == 1) {
                        totalBuilding++;
                        bfs(grid, r, c, reachCount, totalDistance);
                    }
                }
            }

            // Step 4: final answer scan
            int minDistance = Integer.MAX_VALUE;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 0 && reachCount[rows][cols] == totalBuilding) {
                        minDistance = Math.min(minDistance, totalDistance[i][j]);
                    }
                }
            }

            return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
        }

        // BFS per building
        // Time: O(R * C), Space: O(R * C)
        private void bfs(int[][] grid, int row, int col, int[][] reachCount, int[][] totalDistance) {
            int rows = grid.length;
            int cols = grid[0].length;

            // current BFS ka visited set
            boolean[][] visited = new boolean[rows][cols];
            Queue<int[]> q = new LinkedList<>();

            // source building push
            q.offer(new int[] { row, col });
            visited[row][col] = true;

            int distance = 0;

            // level-order traversal
            while (!q.isEmpty()) {
                int size = q.size();

                for (int i = 0; i < size; i++) {

                    int[] cur = q.poll();

                    for (int[] dir : directions) {
                        int curRow = cur[0] + dir[0];
                        int curCol = cur[1] + dir[1];

                        if (isValid(grid, curRow, curCol, visited)) {

                            visited[curRow][curCol] = true;

                            // aggregation update
                            reachCount[curRow][curCol]++;
                            totalDistance[curRow][curCol] += distance + 1;
                            q.offer(new int[] { curRow, curCol });
                        }
                    }
                }
                distance++;
            }
        }

        // O(1) validity check helper
        private boolean isValid(int[][] grid, int row, int col, boolean[][] visited) {
            int rows = grid.length;
            int cols = grid[0].length;

            return row >= 0 && col >= 0 && row < rows && col < cols && grid[row][col] == 0 && !visited[row][col];
        }
    }

    // 30/03/2016
    public class SolutionRevisedOnDayFifth {

        public int shortDistance(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;

            int[][] reachCount = new int[rows][cols];
            int[][] totalDistance = new int[rows][cols];

            int totalBuilding = 0;

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] == 1) {
                        totalBuilding++;
                        bfs(grid, r, c, reachCount, totalDistance);
                    }
                }
            }

            int min = Integer.MAX_VALUE;

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] == 0 && reachCount[rows][cols] == totalBuilding) {
                        min = Math.min(min, totalDistance[r][c]);
                    }
                }
            }

            return min == Integer.MAX_VALUE ? -1 : min;

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
                        int curRow = dir[0] + cur[0];
                        int curCol = dir[1] + cur[1];

                        if (isValid(grid, curRow, curCol, visited)) {
                            visited[curRow][curCol] = true;

                            reachCount[curRow][curCol]++;
                            totalDistance[curRow][curCol] += distance + 1;
                            q.offer(new int[] { curRow, curCol });
                        }
                    }
                }
            }
        }

        private boolean isValid(int[][] grid, int row, int col, boolean[][] visited) {

            int rows = grid.length;
            int cols = grid[0].length;

            return row >= 0 && col >= 0 && row < rows && col < cols && grid[row][col] == 0 && !visited[row][col];
        }
    }

    // 13/04/2026
    public class SolutionRevisedOnDayFourteen {

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

            int min = Integer.MAX_VALUE;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {

                    if (grid[i][j] == 0 && reachCount[rows][cols] == totalBuilding) {
                        min = Math.min(min, totalDistance[i][j]);
                    }
                }
            }

            return min == Integer.MAX_VALUE ? -1 : min;
        }

        private void bfs(int[][] grid, int i, int j, int[][] reachCount, int[][] totalDistance) {
            int rows = grid.length;
            int cols = grid[0].length;

            boolean[][] visited = new boolean[rows][cols];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] { i, i });
            visited[i][j] = true;

            int distance = 0;

            while (!q.isEmpty()) {

                int size = q.size();

                for (int k = 0; k < size; k++) {
                    int[] cur = q.poll();

                    for (int[] dir : directions) {
                        int curRow = dir[0] + cur[0];
                        int curCol = dir[1] + cur[1];

                        if (isValid(grid, curRow, curCol, visited)) {

                            visited[curRow][curCol] = true;

                            reachCount[curRow][curCol]++;
                            totalDistance[curRow][curCol] += distance += 1;

                            q.offer(new int[] { curRow, curCol });
                        }

                    }
                }

                distance++;
            }
        }

        private boolean isValid(int[][] grid, int row, int col, boolean[][] visited) {
            int rows = grid.length;
            int cols = grid[0].length;

            return row >= 0 && col >= 0 && row < rows && col < cols && grid[row][col] == 0 && !visited[row][col];
        }
    }
}