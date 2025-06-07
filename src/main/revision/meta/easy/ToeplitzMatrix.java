/**
 * @author agond
 * @date Jun 07, 2025
 * @time 1:25:40 PM
 */
package main.revision.meta.easy;

public class ToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {

            if (isDiagnoalInValid(matrix, i, 0)) {
                return false;
            }
        }

        for (int i = 0; i < cols; i++) {

            if (isDiagnoalInValid(matrix, 0, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean isDiagnoalInValid(int[][] matrix, int row, int col) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        int val = matrix[row][col];

        while (row < rows && col < cols) {
            if (matrix[row][col] != val) {
                return true;
            }
            row++;
            col++;
        }
        return false;
    }
}
