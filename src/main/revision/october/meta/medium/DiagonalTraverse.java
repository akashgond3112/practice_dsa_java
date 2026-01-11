package main.revision.october.meta.medium;

public class DiagonalTraverse {

    /**
     * This method returns the diagonal traversal of a 2D matrix.
     * Time Complexity: O(row * col), where row is the number of rows and col is the
     * number of columns in the matrix.
     * Space Complexity: O(row * col), for the result array.
     */
    class Solution {
        public int[] findDiagonalOrder(int[][] mat) {

            int row = mat.length; // matrix ke rows ka count
            int col = mat[0].length; // matrix ke columns ka count

            int[] result = new int[row * col]; // result array jisme diagonal traversal store hoga

            int curRow = 0; // current row index
            int curCol = 0; // current column index
            boolean dir = true; // direction flag, true = up-right, false = down-left
            int curCell = 0; // result array ka current index

            while (curCell < row * col) {

                result[curCell] = mat[curRow][curCol]; // current cell ko result array mein daalo
                curCell++; // result array ka index badhao

                // agar direction up-right hai (dir = true)
                if (dir) {
                    // agar right edge pe pahunch gaye ho, ek row neeche jao aur direction badlo
                    if (curCol == col - 1) {
                        curRow++;
                        dir = false;
                    }
                    // agar top edge pe pahunch gaye ho, ek column right jao aur direction badlo
                    else if (curRow == 0) {
                        curCol++;
                        dir = false;
                    }
                    // warna diagonal mein up-right move karo
                    else {
                        curRow--;
                        curCol++;
                    }
                }
                // agar direction down-left hai (dir = false)
                else {
                    // agar bottom edge pe pahunch gaye ho, ek column right jao aur direction badlo
                    if (curRow == row - 1) {
                        curCol++;
                        dir = true;
                    }
                    // agar left edge pe pahunch gaye ho, ek row neeche jao aur direction badlo
                    else if (curCol == 0) {
                        curRow++;
                        dir = true;
                    }
                    // warna diagonal mein down-left move karo
                    else {
                        curRow++;
                        curCol--;
                    }
                }
            }

            return result; // diagonal traversal ka result return karo
        }
    }

    // revised on 11/22/2025
    class SolutionRevisonThirdDay {
        public int[] findDiagonalOrder(int[][] mat) {

            int row = mat.length;
            int col = mat[0].length;

            int[] result = new int[col * col];

            int curRow = 0;
            int curCol = 0;
            boolean dir = true;
            int curCell = 0;

            while (curCell < row * col) {

                result[curCell] = mat[curRow][curCol];

                if (dir) {
                    if (curCol == col - 1) {
                        curRow++;
                        dir = false;
                    } else if (curRow == 0) {
                        curCol++;
                        dir = false;
                    } else {
                        curRow--;
                        curCol++;
                    }
                } else {
                    if (curRow == row - 1) {
                        curCol++;
                        dir = true;
                    } else if (curCol == 0) {
                        curRow++;
                        dir = true;
                    } else {
                        curRow++;
                        curCol--;
                    }
                }
            }

            return result;
        }
    }

    // revised on 11/28/2025
    class SolutionRevisonSeventhDay {
        public int[] findDiagonalOrder(int[][] mat) {

            int row = mat.length;
            int col = mat[0].length;

            int[] result = new int[row * col];

            int curRow = 0;
            int curCol = 0;
            boolean dir = true;
            int curCell = 0;

            while (curCell < row * col) {

                result[curCell] = mat[curRow][curCol];

                if (dir) {
                    if (curCol == col - 1) {
                        curRow++;
                        dir = false;
                    } else if (curRow == 0) {
                        curCol++;
                        dir = false;
                    } else {
                        curRow--;
                        curCol++;
                    }
                } else {
                    if (curRow == col - 1) {
                        curCol++;
                        dir = true;
                    } else if (curCol == 0) {
                        curCol++;
                        dir = false;
                    } else {
                        curRow++;
                        curCol--;
                    }
                }
            }

            return result;

        }
    }

    // revised on 12/12/2025
    class SolutionRevisonFourteenDay {
        public int[] findDiagonalOrder(int[][] mat) {

            int row = mat.length;
            int col = mat[0].length;

            int[] result = new int[row * col];

            int curRow = 0;
            int curCol = 0;
            boolean dir = true;
            int curCell = 0;

            while (curCell < row * col) {

                result[curCell] = mat[curRow][curCol];
                curCell++;

                if (dir) {
                    if (curCol == col - 1) {
                        curRow++;
                        dir = false;
                    } else if (row == 0) {
                        curCol++;
                        dir = false;
                    } else {
                        curRow--;
                        curCol++;
                    }
                } else {
                    if (curRow == row - 1) {
                        curCol++;
                        dir = true;
                    } else if (col == 0) {
                        curRow++;
                        dir = true;
                    } else {
                        curCol--;
                        curRow++;
                    }
                }
            }

            return result;
        }
    }

    // revised on 1/10/2026
    class SolutionRevisonDayThirty {
        public int[] findDiagonalOrder(int[][] mat) {

            int row = mat.length;
            int col = mat[0].length;

            int[] result = new int[row * col];

            int curRow = 0;
            int curCol = 0;
            int curCell = 0;
            boolean dir = true;

            while (curCell < row * col) {

                result[curCell] = mat[curRow][curCol];
                curCell++;

                if (dir) {

                    if (curCol == col - 1) {
                        curRow++;
                        dir = false;
                    } else if (curRow == 0) {
                        curCol++;
                        dir = false;
                    } else {
                        curCol++;
                        curRow--;
                    }
                } else {
                    if (curRow == row - 1) {
                        curCol++;
                        dir = false;
                    } else if (curCol == 0) {
                        curRow++;
                        dir = false;
                    } else {
                        curCol--;
                        curRow++;
                    }
                }
            }

            return result;
        }
    }
}
