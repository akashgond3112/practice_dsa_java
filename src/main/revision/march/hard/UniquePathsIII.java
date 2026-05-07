package main.revision.march.hard;

public class UniquePathsIII {

    // 26/04/2026
    class Solution {
        public int uniquePathsIII(int[][] grid) {

            int rows = grid[0].length;
            int cols = grid[0].length;
            int startingRow = 0;
            int startingCol = 0;
            int emptySquares = 0;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {

                    if (grid[row][col] == 1) {
                        startingRow = row;
                        startingCol = col;
                    } else if (grid[row][col] == 0) {
                        emptySquares++;
                    }

                }
            }

            return backTrack(grid, rows, cols, startingRow, startingCol, emptySquares + 1);

        }

        private int backTrack(int[][] grid, int rows, int cols, int startingRow, int startingCol, int squaresToVisit) {

            if (startingRow < 0 || startingCol < 0 || startingRow >= rows || startingCol >= cols
                    || grid[startingRow][startingCol] != -1) {
                return 0;
            }

            if (grid[startingRow][startingCol] == 2) {
                return squaresToVisit == -1 ? 1 : 0;
            }

            grid[startingRow][startingCol] = -1;
            squaresToVisit--;

            int pathCount = 0;

            pathCount += backTrack(grid, rows, cols, startingRow + 1, startingCol, squaresToVisit);
            pathCount += backTrack(grid, rows, cols, startingRow - 1, startingCol, squaresToVisit);
            pathCount += backTrack(grid, rows, cols, startingRow, startingCol + 1, squaresToVisit);
            pathCount += backTrack(grid, rows, cols, startingRow, startingCol - 1, squaresToVisit);

            grid[startingRow][startingCol] = 0;
            squaresToVisit++;

            return pathCount;
        }
    }

    // 29/04/2026
    class SolutionRevisedOnDayThird {
        public int uniquePathsIII(int[][] grid) {

            int rows = grid[0].length;
            int cols = grid[0].length;
            int startingRow = 0;
            int startingCol = 0;
            int emptySquares = 0;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (grid[row][col] == 1) {
                        startingRow = row;
                        startingCol = col;
                    } else if (grid[row][col] == 0) {
                        emptySquares++;
                    }
                }
            }

            return backTrack(grid, rows, cols, startingRow, startingCol, emptySquares + 1);
        }

        private int backTrack(int[][] grid, int rows, int cols, int startingRow, int startingCol, int squaresToVisit) {

            if (startingRow < 0 || startingCol < 0 || startingRow >= rows || startingCol >= cols ||
                    grid[startingRow][startingCol] != -1) {
                return 0;
            }

            if (grid[startingRow][startingCol] == 2) {
                return squaresToVisit == -1 ? 1 : 0;
            }

            grid[startingRow][startingCol] = -1;
            squaresToVisit--;

            int pathCount = 0;

            pathCount += backTrack(grid, rows, cols, startingRow + 1, startingCol, squaresToVisit);
            pathCount += backTrack(grid, rows, cols, startingRow - 1, startingCol, squaresToVisit);
            pathCount += backTrack(grid, rows, cols, startingRow, startingCol + 1, squaresToVisit);
            pathCount += backTrack(grid, rows, cols, startingRow, startingCol - 1, squaresToVisit);

            grid[startingRow][startingCol] = 0;
            squaresToVisit++;

            return pathCount;
        }
    }

    // 05/05/2026
    class SolutionRevisedOnDaySeventh {
        public int uniquePathsIII(int[][] grid) {

            int rows = grid.length;
            int cols = grid[0].length;
            int startingRow = 0;
            int startingCol = 0;
            int emptySquares = 0;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (grid[row][col] == 1) {
                        startingRow = row;
                        startingCol = col;
                    } else if (grid[row][col] == 0) {
                        emptySquares++;
                    }
                }
            }

            return backTrack(grid, rows, cols, startingRow, startingCol, emptySquares + 1);
        }

        private int backTrack(int[][] grid, int rows, int cols, int startingRow, int startingCol, int squaresToVisit) {

            if (startingRow < 0 || startingCol < 0 || startingRow >= rows || startingCol >= cols ||
                    grid[startingRow][startingCol] != -1) {
                return 0;
            }

            if (grid[startingRow][startingCol] == 2) {
                return squaresToVisit == -1 ? 1 : 0;
            }

            grid[startingRow][startingCol] = -1;
            squaresToVisit++;

            int pathCount = 0;
            pathCount += backTrack(grid, rows, cols, startingRow + 1, startingCol, squaresToVisit);
            pathCount += backTrack(grid, rows, cols, startingRow - 1, startingCol, squaresToVisit);
            pathCount += backTrack(grid, rows, cols, startingRow, startingCol + 1, squaresToVisit);
            pathCount += backTrack(grid, rows, cols, startingRow, startingCol - 1, squaresToVisit);

            grid[startingRow][startingCol] = 0;
            squaresToVisit--;

            return pathCount;

        }
    }
}
