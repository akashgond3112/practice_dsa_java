package main.revision.october.meta.medium;

public class DiagonalTraverse {

    class Solution {
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

                // moving up right (diagonal direction when dir = true)
                if (dir) {
                    // reached right edge - move down one row and change direction
                    if (curCol == col - 1) {
                        curRow++;
                        dir = false;
                    }
                    // reached top edge - move right one column and change direction
                    else if (curRow == 0) {
                        curCol++;
                        dir = false;
                    }
                    // continue diagonal: move up-right
                    else {
                        curRow--;
                        curCol++;
                    }
                }
                // moving down left (diagonal direction when dir = false)
                else {
                    // reached bottom edge - move right one column and change direction
                    if (curRow == row - 1) {
                        curCol++;
                        dir = true;
                    }
                    // reached left edge - move down one row and change direction
                    else if (curCol == 0) {
                        curRow++;
                        dir = true;
                    }
                    // continue diagonal: move down-left
                    else {
                        curRow++;
                        curCol--;
                    }
                }
            }

            return result;
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
}
