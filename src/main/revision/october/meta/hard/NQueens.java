package main.revision.october.meta.hard;

import java.util.*;

public class NQueens {

    /**
     * Time Complexity: O(N!) - Each column has N choices, decreasing as we place
     * queens
     * Space Complexity: O(N²) - For the board storage
     */
    class Solution {

        public static List<List<String>> solveNQueens(int n) {
            // Step 1: Initialize n×n board with all empty cells ('.')
            char[][] board = new char[n][n];

            for (int row = 0; row < n; row++)
                for (int col = 0; col < n; col++)
                    board[row][col] = '.';

            // Step 2: Initialize result list to store all valid configurations
            List<List<String>> res = new ArrayList<>();

            // Step 3: Start backtracking from column 0
            solve(0, board, res);

            return res;
        }

        static void solve(int col, char[][] board, List<List<String>> res) {
            // Step 4: Base case - if all queens placed (reached last column), save
            // configuration
            if (col == board.length) {
                res.add(construct(board));
                return;
            }

            // Step 5: Try placing queen in each row of current column
            for (int row = 0; row < board.length; row++) {
                // Step 6: Check if placement is safe (no conflicts)
                if (isPossible(board, row, col)) {
                    // Step 7: Place queen at current position
                    board[row][col] = 'Q';

                    // Step 8: Recursively solve for next column
                    solve(col + 1, board, res);

                    // Step 9: Backtrack - remove queen to try next row
                    board[row][col] = '.';
                }
            }
        }

        static boolean isPossible(char[][] board, int row, int col) {
            int duprow = row;
            int dupcol = col;

            // Step 10: Check upper-left diagonal for attacking queens
            while (row >= 0 && col >= 0) {
                if (board[row][col] == 'Q')
                    return false;
                row--;
                col--;
            }

            // Step 11: Reset and check left row for attacking queens
            row = duprow;
            col = dupcol;
            while (col >= 0) {
                if (board[row][col] == 'Q')
                    return false;
                col--;
            }

            // Step 12: Reset and check lower-left diagonal for attacking queens
            col = dupcol;
            while (col >= 0 && row < board.length) {
                if (board[row][col] == 'Q')
                    return false;
                col--;
                row++;
            }

            // Step 13: Position is safe if no conflicts found
            return true;
        }

        static List<String> construct(char[][] board) {
            // Step 14: Convert 2D char array to list of strings for output
            List<String> res = new LinkedList<String>();
            for (char[] chars : board) {
                String s = new String(chars);
                res.add(s);
            }
            return res;
        }
    }

    // revision on 11/26/2025
    class SolutionRevisonOnThirdDay {

        public static List<List<String>> solveNQueens(int n) {

            char[][] board = new char[n][n];

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    board[row][col] = '.';
                }
            }

            List<List<String>> res = new ArrayList<>();

            solve(0, board, res);

            return res;
        }

        static void solve(int col, char[][] board, List<List<String>> res) {

            if (col == board.length) {
                res.add(construct(board));
                return;
            }

            for (int row = 0; row < board.length; row++) {

                if (isPossible(board, row, col)) {

                    board[row][col] = 'Q';

                    solve(col + 1, board, res);

                    board[row][col] = '.';
                }
            }
        }

        static boolean isPossible(char[][] board, int row, int col) {

            int dupRow = row;
            int dupCol = col;

            while (row >= 0 && col >= 0) {
                if (board[row][col] >= 0) {
                    if (board[row][col] == 'Q') {
                        return false;
                    }
                    row--;
                    col--;
                }
            }

            row = dupRow;
            col = dupCol;

            while (col >= 0) {
                if (board[row][col] == 'Q') {
                    return false;
                }
                col--;
            }

            col = dupCol;
            while (col >= 0 && row < board.length) {
                if (board[row][col] == 'Q')
                    return false;
                col--;
                row++;
            }

            return true;
        }

        static List<String> construct(char[][] board) {

            List<String> res = new LinkedList<>();
            for (char[] cahrs : board) {
                String s = new String(cahrs);
                res.add(s);
            }

            return res;
        }
    }

    // revision on 12/2/2025
    class SolutionRevisonOnSeventhDay {

        public static List<List<String>> solveNQueens(int n) {
            char[][] board = new char[n][n];

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {

                }
            }

            List<List<String>> result = new ArrayList<>();

            solve(0, board, result);

            return result;
        }

        static void solve(int col, char[][] board, List<List<String>> res) {

            if (col == board.length) {
                res.add(construct(board));
                return;
            }

            for (int row = 0; row < board.length; row++) {

                if (isPossible(board, row, col)) {
                    board[row][col] = 'Q';
                    solve(col + 1, board, res);
                    board[row][col] = '.';
                }
            }
        }

        static boolean isPossible(char[][] board, int row, int col) {

            int dupRow = row;
            int dupCol = col;

            while (row >= 0 && col >= 0) {
                if (board[row][col] >= 0) {
                    if (board[row][col] == 'Q') {
                        return false;
                    }
                    row--;
                    col++;
                }
            }

            row = dupRow;
            col = dupCol;

            while (col >= 0) {
                if (board[row][col] == 'Q') {
                    return false;
                }
                col--;
            }

            col = dupCol;
            while (col >= 0 && row < board.length) {
                if (board[row][col] == 'Q') {
                    return false;
                }
                col--;
                row++;
            }
            return true;
        }

        static List<String> construct(char[][] board) {
            List<String> res = new LinkedList<>();
            for (char[] cahrs : board) {
                String s = new String(cahrs);
                res.add(s);
            }

            return res;
        }
    }

    // revision on 12/16/2025
    class SolutionRevisonOnFourteenDay {

        public static List<List<String>> solveNQueens(int n) {
            char[][] board = new char[n][n];

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    board[row][col] = '.';
                }
            }

            List<List<String>> res = new ArrayList<>();
            solve(0, board, res);
            return res;
        }

        static void solve(int col, char[][] board, List<List<String>> res) {

            if (board.length == col) {
                res.add(construct(board));
                return;
            }

            for (int row = 0; row < board.length; row++) {

                if (isPossible(board, row, col)) {
                    board[row][col] = 'Q';

                    solve(col + 1, board, res);

                    board[row][col] = '.';
                }
            }
        }

        static boolean isPossible(char[][] board, int row, int col) {
            int curR = row;
            int curC = col;

            while (row >= 0 && col >= 0) {
                if (board[row][col] == 'Q') {
                    return false;
                }
                row--;
                col--;
            }

            row = curR;
            col = curC;

            while (curC >= 0) {
                if (board[row][col] == 'Q')
                    return false;
                col--;
            }

            col = curC;
            while (col >= 0 && row < board.length) {
                if (board[row][col] == 'Q')
                    return false;
                col--;
                row++;
            }
            return true;
        }

        static List<String> construct(char[][] board) {
            // Step 14: Convert 2D char array to list of strings for output
            List<String> res = new LinkedList<String>();
            for (char[] chars : board) {
                String s = new String(chars);
                res.add(s);
            }
            return res;
        }
    }

    // revised on 1/14/2026
    class SolutionRevisedOnDayThirty {
        public List<List<String>> solveNQueens(int n) {

            char[][] board = new char[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = '.';
                }
            }
            List<List<String>> result = new ArrayList<>();

            solve(0, board, result);

            return result;
        }

        void solve(int col, char[][] board, List<List<String>> res) {

            if (col == board.length) {
                res.add(construct(board));
                return;
            }

            for (int row = 0; row < board.length; row++) {
                if (isPossible(board, row, col)) {
                    board[row][col] = 'Q';
                    solve(col + 1, board, res);
                    board[row][col] = '.';
                }
            }
        }

        boolean isPossible(char[][] board, int row, int col) {
            int dupRow = row;
            int dupCol = col;

            while (row >= 0 && col >= 0) {
                if (board[row][col] == 'Q') {
                    return false;
                }
                row--;
                col--;
            }

            row = dupRow;
            col = dupCol;

            while (col >= 0) {
                if (board[row][col] == 'Q') {
                    return false;
                }
                col--;
            }

            col = dupCol;
            while (col >= 0 && row <= board.length) {
                if (board[row][col] == 'Q') {
                    return false;
                }
                col--;
                row++;
            }

            return true;
        }

        List<String> construct(char[][] board) {

            List<String> result = new ArrayList<>();
            for (char[] chars : board) {
                String s = new String(chars);
                result.add(s);
            }

            return result;
        }
    }
}
