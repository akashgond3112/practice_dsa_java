
package main.revision.october.meta.hard;

public class SudokuSolver {
    class Solution {
        // solveSudoku method ko call karte hain jo board ko solve karne ke liye solve
        // method ko invoke karta hai
        public void solveSudoku(char[][] board) {
            solve(board);
        }

        // solve method ek recursive function hai jo board ko solve karne ki koshish
        // karta hai
        private boolean solve(char[][] board) {

            // har cell ko traverse karte hain
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {

                    // agar cell empty hai ('.'), toh hum usme ek valid number dalne ki koshish
                    // karte hain
                    if (board[i][j] == '.') {

                        // 1 se 9 tak ke numbers ko try karte hain
                        for (char c = '1'; c <= '9'; c++) {

                            // check karte hain ki kya yeh number valid hai
                            if (isValidEntry(board, i, j, c)) {
                                board[i][j] = c; // agar valid hai toh number ko cell mein daal dete hain

                                // recursive call karte hain solve method ko
                                if (solve(board)) {
                                    return true; // agar solve ho jata hai toh true return karte hain
                                } else {
                                    board[i][j] = '.'; // agar solve nahi hota toh backtrack karte hain
                                }
                            }
                        }
                        return false; // agar koi bhi number valid nahi hai toh false return karte hain
                    }
                }
            }
            return true; // agar board solve ho gaya toh true return karte hain
        }

        // isValidEntry method check karta hai ki kya ek number kisi cell mein valid hai
        private boolean isValidEntry(char[][] board, int row, int col, char c) {

            // row aur column ko check karte hain
            for (int i = 0; i < 9; i++) {

                if (board[i][col] == c) { // agar column mein number already hai toh false return karte hain
                    return false;
                }

                if (board[row][i] == c) { // agar row mein number already hai toh false return karte hain
                    return false;
                }

                // 3x3 box ko check karte hain
                int boxRow = 3 * (row / 3) + i / 3;
                int boxCol = 3 * (col / 3) + i % 3;

                if (board[boxRow][boxCol] == c) { // agar box mein number already hai toh false return karte hain
                    return false;
                }
            }
            return true; // agar number valid hai toh true return karte hain
        }
    }

    // revised on 12/20/2025
    class SolutionRevisedOnThirdDay {
        public void solveSudoku(char[][] board) {
            solve(board);
        }

        private boolean solve(char[][] board) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == '.') {

                        for (char c = '1'; c <= '9'; c++) {

                            if (isValidEntry(board, i, j, c)) {
                                board[i][j] = c;

                                if (solve(board)) {
                                    return true;
                                } else {
                                    board[i][j] = '.';
                                }
                            }
                        }

                        return false;
                    }
                }
            }
            return true;
        }

        private boolean isValidEntry(char[][] board, int row, int col, char c) {

            for (int i = 0; i < 9; i++) {

                if (board[i][col] == c) {
                    return false;
                }

                if (board[row][i] == c) {
                    return false;
                }

                int boxRow = 3 * (row / 3) + i / 3;
                int boxCol = 3 * (col / 3) + i % 3;

                if (board[boxRow][boxCol] == c) {
                    return false;
                }
            }

            return true;
        }

    }

    // revised on 12/26/2025
    class SolutionRevisedOnSeventhDay {
        public void solveSudoku(char[][] board) {
            solve(board);
        }

        private boolean solve(char[][] board) {

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {

                    if (board[i][j] == '.') {

                        for (char c = '1'; c <= '9'; c++) {

                            if (isValidEntry(board, i, j, c)) {
                                board[i][j] = c;

                                if (solve(board)) {
                                    return true;
                                } else {
                                    board[i][j] = '.';
                                }
                            }
                        }
                        return false;
                    }
                }
            }

            return true;
        }

        private boolean isValidEntry(char[][] board, int row, int col, char c) {

            for (int i = 0; i < 9; i++) {

                if (board[i][col] == c) {
                    return false;
                }

                if (board[row][i] == c) {
                    return false;
                }

                int boxRow = 3 * (row / 3) + i / 3;
                int boxCol = 3 * (col / 3) + i % 3;

                if (board[boxRow][boxCol] == c) {
                    return false;
                }
            }

            return true;
        }
    }

    // revised on 1/9/2026
    class SolutionRevisedOnFourteenDay {
        public void solveSudoku(char[][] board) {
            solve(board);
        }

        private boolean solve(char[][] board) {

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {

                    if (board[i][j] == '.') {

                        for (char c = '1'; c <= '9'; c++) {

                            if (isValidEntry(board, 1, j, c)) {
                                board[i][j] = c;

                                if (solve(board)) {
                                    return true;
                                } else {
                                    board[i][j] = '.';
                                }
                            }
                        }
                        return false;
                    }
                }
            }

            return true;
        }

        private boolean isValidEntry(char[][] board, int row, int col, char c) {

            for (int i = 1; i <= 9; i++) {

                if (board[row][i] == c) {
                    return false;
                }

                if (board[i][col] == c) {
                    return false;
                }

                int boxRow = 3 * (row / 3) + i / 3;
                int boxCol = 3 * (col / 3) + i % 3;

                if (board[boxRow][boxCol] == c) {
                    return false;
                }
            }

            return true;
        }
    }

    // revised on 2/7/2026
    class SolutionRevisedOnDayThirty {
        public void solveSudoku(char[][] board) {
            solve(board);
        }

        private boolean solve(char[][] board) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == '.') {
                        for (char c = '1'; c < '9'; c++) {
                            if (isValidEntry(board, i, j, c)) {
                                board[i][j] = c;
                                if (solve(board)) {
                                    return true;
                                } else {
                                    board[i][j] = '.';
                                }
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean isValidEntry(char[][] board, int row, int col, char c) {

            for (int i = 0; i < 9; i++) {

                if (board[i][col] == c)
                    return false;
                if (board[row][i] == c)
                    return false;

                int boxRow = 3 * (row / 3) + i / 3;
                int boxCol = 3 * (col / 3) + i % 3;

                if (board[boxRow][boxCol] == c)
                    return false;

            }
            return true;
        }
    }
}
