package main.revision.october.meta.hard;

public class UniquePathsIII {

    public class Solution {
        public int uniquePathsIII(int[][] grid) {
            int m = grid.length; // Grid ki rows ki length nikalte hain
            int n = grid[0].length; // Grid ki columns ki length nikalte hain
            int startRow = 0; // Starting row ka index
            int startCol = 0; // Starting column ka index
            int emptySquares = 0; // Empty squares ki count

            // Grid ko traverse karte hain
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) { // Agar square empty hai
                        emptySquares++; // Empty squares ki count badhao
                    } else if (grid[i][j] == 1) { // Agar starting point hai
                        startRow = i; // Starting row set karo
                        startCol = j; // Starting column set karo
                    }
                }
            }
            // Sabhi empty squares aur starting square ko visit karna hai
            return backtrack(grid, startRow, startCol, m, n, emptySquares + 1);
        }

        private int backtrack(int[][] grid, int row, int col, int m, int n, int squaresToVisit) {
            // Agar grid ke bahar chale gaye ya obstacle pe hain
            if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == -1) {
                return 0; // Valid path nahi hai
            }

            // Agar end point pe pahunch gaye
            if (grid[row][col] == 2) {
                return squaresToVisit == -1 ? 1 : 0; // Agar sabhi squares visit kiye hain to 1 return karo
            }

            grid[row][col] = -1; // Is square ko visited mark karo
            squaresToVisit--; // Ek square visit ho gaya

            int pathCount = 0; // Possible paths ki count

            pathCount += backtrack(grid, row + 1, col, m, n, squaresToVisit);
            pathCount += backtrack(grid, row - 1, col, m, n, squaresToVisit);
            pathCount += backtrack(grid, row, col + 1, m, n, squaresToVisit);
            pathCount += backtrack(grid, row, col - 1, m, n, squaresToVisit);

            grid[row][col] = 0; // Backtrack karke square ko unvisited mark karo
            squaresToVisit++; // previous square ka value store karo

            return pathCount; // Total paths return karo
        }
    }

    // revision on 12/8/2025
    public class SolutionRevisedOnDayThird {
        public int uniquePathsIII(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int startRow = 0;
            int startCol = 0;
            int emptySquares = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        emptySquares++;
                    } else if (grid[i][j] == 1) {
                        startRow = i;
                        startCol = j;
                    }
                }
            }

            return dfs(grid, startRow, startCol, m, n, emptySquares + 1);
        }

        private int dfs(int[][] grid, int row, int col, int m, int n, int squaresToVisit) {

            if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == -1) {
                return 0;
            }

            if (grid[row][col] == 2) {
                return squaresToVisit == -1 ? 1 : 0;
            }

            grid[row][col] = -1;
            squaresToVisit--;

            int pathCount = 0; // Possible paths ki count

            pathCount += dfs(grid, row + 1, col, m, n, squaresToVisit);
            pathCount += dfs(grid, row - 1, col, m, n, squaresToVisit);
            pathCount += dfs(grid, row, col + 1, m, n, squaresToVisit);
            pathCount += dfs(grid, row, col - 1, m, n, squaresToVisit);

            grid[row][col] = 0; // Backtrack karke square ko unvisited mark karo
            squaresToVisit++; // previous square ka value store karo

            return pathCount;
        }
    }

    // revision on 12/14/2025
    public class SolutionRevisedOnDaySeventh {
        public int uniquePathsIII(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int startRow = 0;
            int startCol = 0;
            int emptySquares = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (grid[i][j] == 0) {
                        emptySquares++;
                    } else if (grid[i][j] == 1) {
                        startRow = i;
                        startCol = j;
                    }
                }
            }

            return dfs(grid, startRow, startCol, m, n, emptySquares + 1);
        }

        private int dfs(int[][] grid, int row, int col, int m, int n, int squaresToVisit) {

            if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == -1) {
                return 0;
            }

            // end case
            if (grid[row][col] == 2) {
                return squaresToVisit == -1 ? 1 : 0;
            }

            grid[row][col] = -1;
            squaresToVisit--;

            int pathCount = 0; // Possible paths ki count

            pathCount += dfs(grid, row + 1, col, m, n, squaresToVisit);
            pathCount += dfs(grid, row - 1, col, m, n, squaresToVisit);
            pathCount += dfs(grid, row, col + 1, m, n, squaresToVisit);
            pathCount += dfs(grid, row, col - 1, m, n, squaresToVisit);

            grid[row][col] = 0; // Backtrack karke square ko unvisited mark karo
            squaresToVisit++; // previous square ka value store karo

            return pathCount;
        }
    }

    // revision on 12/28/2025
    class SolutionRevisedOnDayFourteen {
        public int uniquePathsIII(int[][] grid) {

            int m = grid.length;
            int n = grid[0].length;
            int startRow = 0;
            int startCol = 0;
            int emptySquares = 0;

            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    if (grid[row][col] == 0) {
                        emptySquares++;
                    } else if (grid[row][col] == 1) {
                        startRow = row;
                        startCol = col;
                    }
                }
            }

            return dfs(grid, startRow, startCol, m, n, emptySquares);
        }

        private int dfs(int[][] grid, int row, int col, int m, int n, int squaresToVisit) {

            if (row < 0 ||
                    col < 0 ||
                    row >= m ||
                    col >= n ||
                    grid[row][col] == -1) {
                return 0;
            }

            if (grid[row][col] == 2) {
                return squaresToVisit == -1 ? 1 : 0;
            }

            grid[row][col] = -1;
            squaresToVisit--;

            int pathCount = 0;

            pathCount += dfs(grid, row + 1, col, m, n, squaresToVisit);
            pathCount += dfs(grid, row - 1, col, m, n, squaresToVisit);
            pathCount += dfs(grid, row, col + 1, m, n, squaresToVisit);
            pathCount += dfs(grid, row, col - 1, m, n, squaresToVisit);

            grid[row][col] = 0; // Backtrack karke square ko unvisited mark karo
            squaresToVisit++; // previous square ka value store karo

            return pathCount;
        }
    }

    // revised on 1/25/2026
    class SolutionRevisedOnDayThirty {
        public int uniquePathsIII(int[][] grid) {

            int m = grid.length;
            int n = grid[0].length;
            int startRow = 0;
            int startCol = 0;
            int emptySquares = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        emptySquares++;
                    } else if (grid[i][j] == 1) {
                        startRow = i;
                        startCol = j;
                    }
                }
            }

            return dfs(grid, startRow, startCol, m, n, emptySquares);
        }

        private int dfs(int[][] grid, int row, int col, int m, int n, int squaresToVisit) {

            if (row < 0 ||
                    col < 0 ||
                    row >= m ||
                    col >= n ||
                    grid[row][col] == -1) {
                return 0;
            }

            if (grid[row][col] == 2) {
                return squaresToVisit == -1 ? 1 : 0;
            }

            grid[row][col] = -1;
            squaresToVisit--;

            int pathCount = 0;
            pathCount += dfs(grid, row + 1, col, m, n, squaresToVisit);
            pathCount += dfs(grid, row - 1, col, m, n, squaresToVisit);
            pathCount += dfs(grid, row, col + 1, m, n, squaresToVisit);
            pathCount += dfs(grid, row, col - 1, m, n, squaresToVisit);

            grid[row][col] = 0;
            squaresToVisit++;

            return pathCount;
        }
    }
}
