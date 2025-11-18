package main.revision.october.meta.easy;

public class ToeplitzMatrix {

    public class Solution {
        public boolean isToeplitzMatrix(int[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;

            for (int i = 1; i < rows; i++) {
                if (!checkDiagonalEquality(matrix, i, 0)) {
                    return false;
                }
            }

            for (int i = 0; i < cols; i++) {
                if (!checkDiagonalEquality(matrix, 0, i)) {
                    return false;
                }
            }

            return true;
        }

        private boolean checkDiagonalEquality(int[][] matrix, int row, int col) {

            int val = matrix[row][col];

            int numRows = matrix.length;
            int numCols = matrix[0].length;

            while (row < numRows && col < numCols) {

                if (matrix[row][col] != val) {
                    return false;
                }
                row++;
                col++;
            }

            return true;
        }
    }

    // Revised on 21/10/2025
    public class SolutionRevisionThirdDay {

        public boolean isToeplitzMatrix(int[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;

            for (int i = 0; i < rows; i++) {

                if (!checkDiagonally(matrix, i, 0)) {
                    return false;
                }
            }

            for (int i = 0; i < cols; i++) {

                if (!checkDiagonally(matrix, 0, i)) {
                    return false;
                }
            }

            return true;
        }

        private boolean checkDiagonally(int[][] matrix, int i, int j) {

            int val = matrix[i][j];

            int rows = matrix.length;
            int cols = matrix[0].length;

            while (i < rows && j < cols) {
                if (matrix[i][j] != val) {
                    return false;
                }
                i++;
                j++;
            }

            return true;
        }
    }

    // Revised on 27/10/2025
    public class SolutionRevisionSeventhDay {

        public boolean isToeplitzMatrix(int[][] matrix) {

            int rows = matrix.length;
            int cols = matrix[0].length;

            for (int i = 0; i < rows; i++) {
                if (!checkDiagonally(matrix, i, 0)) {
                    return false;
                }
            }

            for (int i = 0; i < cols; i++) {
                if (!checkDiagonally(matrix, 0, i)) {
                    return false;
                }
            }

            return true;
        }

        private boolean checkDiagonally(int[][] matrix, int i, int j) {

            int rows = matrix.length;
            int cols = matrix[0].length;

            int val = matrix[i][j];

            while (i < rows && j < cols) {

                if (matrix[i][j] != val) {
                    return false;
                }
                i++;
                j++;
            }

            return true;
        }
    }

    // Revised on 11/10/2025
    public class SolutionRevisionFourteenDay {

        public boolean isToeplitzMatrix(int[][] matrix) {

            int rows = matrix.length;
            int cols = matrix[0].length;

            for (int i = 0; i < rows; i++) {

                if (!checkDiagonally(matrix, i, 0)) {
                    return false;
                }
            }

            for (int i = 0; i < cols; i++) {

                if (!checkDiagonally(matrix, 0, i)) {
                    return false;
                }
            }

            return false;
        }

        private boolean checkDiagonally(int[][] matrix, int i, int j) {

            int rows = matrix.length;
            int cols = matrix[0].length;

            int val = matrix[i][j];

            if (i < rows && j < cols) {

                if (matrix[i][j] != val) {
                    return false;
                }
                i++;
                j++;
            }
            return true;
        }
    }

}
