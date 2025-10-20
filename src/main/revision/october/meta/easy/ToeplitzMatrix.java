package main.revision.october.meta.easy;

public class ToeplitzMatrix {

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
