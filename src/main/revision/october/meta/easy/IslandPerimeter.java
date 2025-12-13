package main.revision.october.meta.easy;

public class IslandPerimeter {
    public class Solution {
        public int islandPerimeter(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        return dfs(grid, i, j);
                    }
                }
            }
            return 0;
        }

        private int dfs(int[][] grid, int r, int c) {
            // If we go out of bounds, we've found a perimeter edge
            if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
                return 1;
            }
            // If we hit water, we've found a perimeter edge
            if (grid[r][c] == 0) {
                return 1;
            }
            // If we've already visited this cell, it doesn't contribute to the perimeter
            if (grid[r][c] == -1) {
                return 0;
            }

            // Mark the cell as visited
            grid[r][c] = -1;

            int perimeter = 0;
            // Explore neighbors
            perimeter += dfs(grid, r + 1, c); // Down
            perimeter += dfs(grid, r - 1, c); // Up
            perimeter += dfs(grid, r, c + 1); // Right
            perimeter += dfs(grid, r, c - 1); // Left

            return perimeter;
        }

        // revised on 12/7/2025
        public class SolutionRevisedOnThirdDay {
            public int islandPerimeter(int[][] grid) {
                if (grid == null || grid.length == 0 || grid[0].length == 0) {
                    return 0;
                }

                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[0].length; j++) {
                        if (grid[i][j] == 1) {
                            return dfs(grid, i, j);
                        }
                    }
                }
                return 0;
            }

            private int dfs(int[][] grid, int r, int c) {

                if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
                    return 1;
                }

                if (grid[r][c] == 0) {
                    return 1;
                }

                if (grid[r][c] == -1) {
                    return 0;
                }

                grid[r][c] = -1;

                int perimeter = 0;

                perimeter += dfs(grid, r + 1, c); // Down
                perimeter += dfs(grid, r - 1, c); // Up
                perimeter += dfs(grid, r, c + 1); // Right
                perimeter += dfs(grid, r, c - 1); // Left

                return perimeter;

            }
        }

        // revised on 12/13/2025
        public class SolutionRevisedOnSeventhDay {
            public int islandPerimeter(int[][] grid) {
                if (grid == null || grid.length == 0 || grid[0].length == 0) {
                    return 0;
                }

                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[0].length; j++) {
                        if (grid[i][j] == 1) {
                            return dfs(grid, i, j);
                        }
                    }
                }
                return 0;
            }

            private int dfs(int[][] grid, int r, int c) {
                // If we go out of bounds, we've found a perimeter edge
                if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
                    return 1;
                }
                // If we hit water, we've found a perimeter edge
                if (grid[r][c] == 0) {
                    return 1;
                }
                // If we've already visited this cell, it doesn't contribute to the perimeter
                if (grid[r][c] == -1) {
                    return 0;
                }

                // Mark the cell as visited
                grid[r][c] = -1;

                int perimeter = 0;
                // Explore neighbors
                perimeter += dfs(grid, r + 1, c); // Down
                perimeter += dfs(grid, r - 1, c); // Up
                perimeter += dfs(grid, r, c + 1); // Right
                perimeter += dfs(grid, r, c - 1); // Left

                return perimeter;
            }
        }
    }
}
