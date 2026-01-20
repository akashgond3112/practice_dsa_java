package main.revision.october.meta.medium;

public class NumberOfIslands {

    class Solution {
        private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        // yeh function grid mein number of islands count karta hai
        public int numIslands(char[][] grid) {
            int ROWS = grid.length, COLS = grid[0].length; // grid ke rows aur columns ka size nikalte hain
            int islands = 0; // islands ka counter initialize karte hain

            // har cell ko traverse karte hain
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    // agar cell '1' hai, to ek naya island mila
                    if (grid[r][c] == '1') {
                        dfs(grid, r, c); // dfs call karke pura island visit karte hain
                        islands++; // island counter increment karte hain
                    }
                }
            }

            return islands; // total islands return karte hain
        }

        // yeh function dfs traversal karta hai aur island ke cells ko visit karta hai
        private void dfs(char[][] grid, int r, int c) {
            // agar cell grid ke bahar hai ya '0' hai, to return karte hain
            if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
                return;
            }

            grid[r][c] = '0'; // current cell ko '0' mark karte hain taaki dobara visit na ho
            for (int[] dir : directions) { // har direction mein move karke dfs call karte hain
                dfs(grid, r + dir[0], c + dir[1]);
            }
        }
    }

    // revised on 11/30/2025
    class SolutionRevisedOnThirdDay {
        private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        public int numIslands(char[][] grid) {
            int ROWS = grid.length, COLS = grid[0].length;
            int islands = 0;

            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (grid[r][c] == '1') {
                        dfs(grid, r, c);
                        islands++;
                    }
                }
            }

            return islands;
        }

        private void dfs(char[][] grid, int r, int c) {
            if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
                return;
            }

            grid[r][c] = '0';
            for (int[] dir : directions) {
                dfs(grid, r + dir[0], c + dir[1]);
            }
        }
    }

    // revised on 12/6/2025
    class SolutionRevisedOnSeventhDay {
        private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        public int numIslands(char[][] grid) {
            int ROWS = grid.length, COLS = grid[0].length;
            int islands = 0;

            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (grid[r][c] == '1') {
                        dfs(grid, r, c);
                        islands++;
                    }
                }
            }

            return islands;
        }

        private void dfs(char[][] grid, int r, int c) {
            if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
                return;
            }

            grid[r][c] = '0';
            for (int[] dir : directions) {
                dfs(grid, r + dir[0], c + dir[1]);
            }
        }
    }

    // revised on 12/6/2025
    class SolutionRevisedOnFourteenDay {
        private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        public int numIslands(char[][] grid) {

            int rows = grid.length;
            int cols = grid[0].length;
            int island = 0;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {

                    if (grid[row][col] == '1') {
                        dfs(grid, row, col);
                        island++;
                    }
                }
            }

            return island;
        }

        private void dfs(char[][] grid, int r, int c) {

            if (r < 0 || c < 0 || r > grid.length || c > grid[0].length || grid[r][c] != '0') {
                return;
            }

            grid[r][c] = '0';

            for (int[] dir : directions) {
                dfs(grid, r + dir[0], c + dir[1]);
            }
        }
    }

    // revised on 1/17/2026
    class SolutionRevisedOnDayThirty {
        private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        public int numIslands(char[][] grid) {

            int rows = grid.length;
            int cols = grid[0].length;
            int island = 0;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {

                    if (grid[row][col] == '1') {
                        dfs(grid, row, col);
                        island++;
                    }
                }
            }
            return island;
        }

        private void dfs(char[][] grid, int row, int col) {
            if (row < 0 ||
                    col < 0 ||
                    row >= grid.length ||
                    col >= grid[0].length ||
                    grid[row][col] == '0') {
                return;
            }

            grid[row][col] = '0';

            for (int[] dir : directions) {
                dfs(grid, row + dir[0], col + dir[1]);
            }
        }
    }
}
