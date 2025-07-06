/**
 * @author agond
 * @date Jul 06, 2025
 * @time 9:27:49 AM
 */
package main.revision.meta.medium;

public class DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] mat) {

        int m = mat.length; // row
        int n = mat[0].length; // col

        // Create result array to store our diagonal walk
        int[] result = new int[m * n];

        // Start from top-left corner
        int row = 0;
        int col = 0;
        boolean dir = true; // true means going up-right, false means going down-left
        int index = 0; // Index for result array

        // Continue until we've filled the result array
        while (index < m * n) {
            // Add current element to result
            result[index] = mat[row][col];
            index++;

            // Moving up-right
            if (dir) {
                if (col == n - 1) { // Reached right boundary
                    row++; // Move down
                    dir = false; // Change direction to down-left
                } else if (row == 0) { // Reached top boundary
                    col++; // Move right
                    dir = false; // Change direction to down-left
                } else { // Normal up-right movement
                    row--;
                    col++;
                }
            }
            // Moving down-left
            else {
                if (row == m - 1) { // Reached bottom boundary
                    col++; // Move right
                    dir = true; // Change direction to up-right
                } else if (col == 0) { // Reached left boundary
                    row++; // Move down
                    dir = true; // Change direction to up-right
                } else { // Normal down-left movement
                    row++;
                    col--;
                }
            }
        }

        return result;
    }

}
