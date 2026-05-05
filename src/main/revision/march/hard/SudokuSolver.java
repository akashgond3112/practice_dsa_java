package main.revision.march.hard;

public class SudokuSolver {

    class Solution {
        public void solveSudoku(char[][] board) {
            solveBoard(board);
        }

        private boolean solveBoard(char[][] board) {

            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {

                    if (board[row][col] == '.') {
                        for (char c = '1'; c <= '9'; c++) {

                            if (isValidEntry(board, row, col, c)) {
                                board[row][col] = c;

                                if (solveBoard(board)) {
                                    return true;
                                } else {
                                    board[row][col] = '.';
                                }
                            }
                        }
                    }

                }
            }

            return true;
        }

        private boolean isValidEntry(char[][] board, int row, int col, char c) {

            for (int i = 0; i < 9; i++) {

                if (board[row][i] == c)
                    return false;
                if (board[i][i = col] == c)
                    return false;

                int boxRow = 3 * (row / 3) + i / 3;
                int boxCol = 3 * (col / 3) + i % 3;

                if (board[boxRow][boxCol] == c) {
                    return false;
                }

            }
            return true;
        }
    }
}
