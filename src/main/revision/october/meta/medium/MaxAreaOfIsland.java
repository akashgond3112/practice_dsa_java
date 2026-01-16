package main.revision.october.meta.medium;

public class MaxAreaOfIsland {

    class Solution {
        public int maxAreaOfIsland(int[][] grid) {

            int result = 0;

            // Step 1: Agar grid null hai ya empty hai, toh return 0
            if (grid == null || grid.length == 0) {
                return result;
            }

            int rows = grid.length; // Total rows ka count
            int cols = grid[0].length; // Total columns ka count
            boolean[][] visited = new boolean[rows][cols]; // Ek visited array banate hain to track visited cells

            // Step 2: Har cell ko traverse karte hain
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    // Step 3: DFS call karke max area calculate karte hain
                    result = Math.max(result, dfs(row, col, visited, grid));
                }
            }
            return result; // Maximum area return karte hain
        }

        private int dfs(int r, int c, boolean[][] visited, int[][] grid) {
            // Step 4: Agar cell grid ke bahar hai ya water hai ya already visited hai, toh
            // return 0
            if (r < 0 ||
                    c < 0 ||
                    r >= grid.length ||
                    c >= grid[0].length ||
                    grid[r][c] == '0' ||
                    visited[r][c]) {
                return 0;
            }

            visited[r][c] = true; // Current cell ko visited mark karte hain

            // Step 5: 1 add karte hain aur 4 directions mein DFS call karte hain
            return (1
                    + dfs(r + 1, c, visited, grid) // Down
                    + dfs(r - 1, c, visited, grid) // Up
                    + dfs(r, c + 1, visited, grid) // Right
                    + dfs(r, c - 1, visited, grid) // Left
            );
        }
    }

    // revised on 12/26/2025
    class SolutionRevisedOnThirdDay {
        public int maxAreaOfIsland(int[][] grid) {

            int result = 0;
            if (grid == null || grid.length == 0) {
                return result;
            }

            int rows = grid.length;
            int cols = grid[0].length;

            boolean[][] visited = new boolean[rows][cols];

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {

                    result = Math.max(result, dfs(row, col, visited, grid));
                }
            }

            return result;
        }

        private int dfs(int r, int c, boolean[][] visited, int[][] grid) {

            if (r < 0 ||
                    c < 0 ||
                    r >= grid.length ||
                    c >= grid[0].length ||
                    visited[r][c] ||
                    grid[r][c] == '0') {
                return 0;
            }

            visited[r][c] = true;

            return (1
                    + dfs(r + 1, c, visited, grid)
                    + dfs(r - 1, c, visited, grid)
                    + dfs(r, c + 1, visited, grid)
                    + dfs(r, c - 1, visited, grid)

            );
        }
    }

    // revised on 1/1/2026
    class SolutionRevisedOnSeventhDay {
        public int maxAreaOfIsland(int[][] grid) {

            int result = 0;

            if (grid == null || grid.length == 0) {
                return result;
            }

            int rows = grid.length;
            int cols = grid[0].length;

            boolean[][] visited = new boolean[rows][cols];

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    result = Math.max(result, dfs(row, col, visited, grid));
                }
            }

            return result;
        }

        private int dfs(int r, int c, boolean[][] visited, int[][] grid) {

            if (r < 0 ||
                    c < 0 ||
                    r >= grid.length ||
                    c >= grid[0].length ||
                    visited[r][c] ||
                    grid[r][c] == '0') {
                return 0;
            }

            visited[r][c] = true;

            return 1
                    + dfs(r + 1, c, visited, grid)
                    + dfs(r - 1, c, visited, grid)
                    + dfs(r, c + 1, visited, grid)
                    + dfs(r, c - 1, visited, grid);
        }
    }

    // revised on 1/14/2026
    class SolutionRevisedOnDayFourteen {
        class Solution {
            public int maxAreaOfIsland(int[][] grid) {

                int result = 0;

                if (grid == null || grid.length == 0) {
                    return result;
                }

                int rows = grid.length;
                int cols = grid[0].length;

                boolean[][] visited = new boolean[rows][cols];

                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        result = Math.max(result, dfs(row, col, visited, grid));
                    }
                }

                return result;
            }

            private int dfs(int r, int c, boolean[][] visited, int[][] grid) {

                if (r < 0 ||
                        c < 0 ||
                        r >= grid.length ||
                        c >= grid.length ||
                        visited[r][c] ||
                        grid[r][c] == '0') {
                    return 0;
                }

                visited[r][c] = true;

                return 1 +
                        dfs(r + 1, c, visited, grid) +
                        dfs(r - 1, c, visited, grid) +
                        dfs(r, c + 1, visited, grid) +
                        dfs(r, c - 1, visited, grid);
            }
        }
    }
}
