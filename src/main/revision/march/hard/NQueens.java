package main.revision.march.hard;

import java.util.*;

public class NQueens {

    class Solution {
        public List<List<String>> solveNQueens(int n) {

            char[][] board = new char[n][n];

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    board[row][col] = '.';
                }
            }

            List<List<String>> result = new ArrayList<>();

            solve(board, result, 0);
            return result;
        }

        private void solve(char[][] board, List<List<String>> result, int col) {

            if (board.length == col) {
                result.add(construct(board));
                return;
            }

            for (int row = 0; row < board.length; row++) {
                if (isPossible(board, row, col)) {
                    board[row][col] = 'Q';
                    solve(board, result, col + 1);
                    board[row][col] = '.';
                }
            }
        }

        private boolean isPossible(char[][] board, int row, int col) {
            int cRow = row;
            int cCol = col;

            if (row >= 0 && col >= 0) {
                if (board[row][col] == 'Q') {
                    return false;
                }
                row--;
                col--;
            }

            row = cRow;
            col = cCol;

            if (col >= 0) {
                if (board[row][col] == 'Q') {
                    return false;
                }
                col--;
            }

            col = cCol;
            if (col >= 0 && col < board.length) {
                if (board[row][col] == 'Q') {
                    return false;
                }
                row++;
                col--;
            }

            return true;
        }

        private List<String> construct(char[][] board) {
            List<String> res = new LinkedList<String>();
            for (char[] chars : board) {
                String s = new String(chars);
                res.add(s);
            }
            return res;
        }
    }

    // 22/04/2026
    class SolutionRevisedOnDayThird {
        public List<List<String>> solveNQueens(int n) {

            char[][] board = new char[n][n];

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    board[row][col] = '.';
                }
            }

            List<List<String>> result = new ArrayList<>();

            solve(0, board, result);

            return result;
        }

        private void solve(int col, char[][] board, List<List<String>> result) {
            if (col == board.length) {
                result.add(construct(board));
                return;
            }

            for (int row = 0; row < board.length; row++) {
                if (isPossible(board, row, col)) {
                    board[row][col] = 'Q';
                    solve(col + 1, board, result);
                    board[row][col] = '.';
                }
            }
        }

        private boolean isPossible(char[][] board, int row, int col) {
            int cRow = board.length;
            int cCol = board.length;

            while (row >= 0 && col >= 0) {
                if (board[row][col] == 'Q') {
                    return false;
                }
                row--;
                col++;
            }

            row = cRow;
            col = cCol;
            while (col >= 0) {
                if (board[row][col] == 'Q') {
                    return false;
                }
                col--;
            }

            col = cCol;
            while (col >= 0 && row < board.length) {
                if (board[row][col] == 'Q') {
                    return false;
                }

                col--;
                row++;
            }

            return true;
        }

        private List<String> construct(char[][] board) {
            List<String> res = new LinkedList<String>();
            for (char[] chars : board) {
                String s = new String(chars);
                res.add(s);
            }
            return res;
        }
    }
}
