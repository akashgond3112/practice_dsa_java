package main.interview.wise.tictactoe;

import java.util.*;

class GameState {
    private final String id;
    private char[][] board = new char[3][3];
    private char currentPlayer = 'X';
    private String winner = null;
    private long version = 0;

    public GameState(String id) {
        this.id = id;
        for (int i = 0; i < 3; i++) {
            Arrays.fill(board[i], '-');
        }
    }

    public synchronized boolean makeMove(int row, int col, char player) {
        if (row < 0 || row > 2 || col < 0 || col > 2)
            return false;
        if (board[row][col] != '-' || winner != null)
            return false;
        if (currentPlayer != player)
            return false;

        board[row][col] = player;
        currentPlayer = (player == 'X') ? 'O' : 'X';
        version++;
        checkWinner();
        return true;
    }

    private void checkWinner() {
        // Check rows, columns, diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                winner = String.valueOf(board[i][0]);
                return;
            }
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                winner = String.valueOf(board[0][i]);
                return;
            }
        }
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            winner = String.valueOf(board[0][0]);
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            winner = String.valueOf(board[0][2]);
        }
    }

    public synchronized GameState copy() {
        GameState copy = new GameState(this.id);
        for (int i = 0; i < 3; i++) {
            System.arraycopy(this.board[i], 0, copy.board[i], 0, 3);
        }
        copy.currentPlayer = this.currentPlayer;
        copy.winner = this.winner;
        copy.version = this.version;
        return copy;
    }

    public String getId() {
        return id;
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public String getWinner() {
        return winner;
    }

    public long getVersion() {
        return version;
    }
}
