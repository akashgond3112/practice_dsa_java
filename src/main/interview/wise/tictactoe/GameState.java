package main.interview.wise.tictactoe;

import java.util.*;

class GameState {
    private final String gameId;
    private char[][] board = new char[3][3];
    private Player playerX;
    private Player playerO;
    private char currentTurn = 'X';
    private GameStatus status = GameStatus.WAITING_FOR_PLAYERS;
    private long version = 0;
    private final long createdAt;

    public GameState(String gameId, Player creator) {
        this.gameId = gameId;
        this.playerX = creator;
        this.createdAt = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) {
            Arrays.fill(board[i], '-');
        }
    }

    // Second player joins
    public synchronized boolean joinGame(Player player) {
        if (status != GameStatus.WAITING_FOR_PLAYERS)
            return false;
        if (playerX.equals(player))
            return false;

        playerO = player;
        status = GameStatus.IN_PROGRESS;
        System.out.println("[Game " + gameId + "] " + playerO.getPlayerName() +
                " joined. Game starts!");
        return true;
    }

    public synchronized boolean makeMove(Player player, int row, int col) {
        // Validate game state
        if (status != GameStatus.IN_PROGRESS) {
            System.out.println("Game not in progress. Status: " + status);
            return false;
        }

        // Validate it's the player's turn
        char playerSymbol = getPlayerSymbol(player);
        if (playerSymbol == ' ') {
            System.out.println("Player not in this game");
            return false;
        }

        if (playerSymbol != currentTurn) {
            System.out.println("Not " + player.getPlayerName() + "'s turn");
            return false;
        }

        // Validate move
        if (row < 0 || row > 2 || col < 0 || col > 2)
            return false;
        if (board[row][col] != '-')
            return false;

        // Make move
        board[row][col] = playerSymbol;
        currentTurn = (playerSymbol == 'X') ? 'O' : 'X';
        version++;

        checkGameEnd();
        return true;
    }

    private char getPlayerSymbol(Player player) {
        if (player.equals(playerX))
            return 'X';
        if (player.equals(playerO))
            return 'O';
        return ' ';
    }

    private void checkGameEnd() {
        // Check rows, columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                status = (board[i][0] == 'X') ? GameStatus.X_WINS : GameStatus.O_WINS;
                return;
            }
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                status = (board[0][i] == 'X') ? GameStatus.X_WINS : GameStatus.O_WINS;
                return;
            }
        }

        // Check diagonals
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            status = (board[0][0] == 'X') ? GameStatus.X_WINS : GameStatus.O_WINS;
            return;
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            status = (board[0][2] == 'X') ? GameStatus.X_WINS : GameStatus.O_WINS;
            return;
        }

        // Check draw
        boolean full = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    full = false;
                    break;
                }
            }
        }
        if (full)
            status = GameStatus.DRAW;
    }

    public synchronized GameState copy() {
        GameState copy = new GameState(this.gameId, this.playerX);
        for (int i = 0; i < 3; i++) {
            System.arraycopy(this.board[i], 0, copy.board[i], 0, 3);
        }
        copy.playerO = this.playerO;
        copy.currentTurn = this.currentTurn;
        copy.status = this.status;
        copy.version = this.version;
        return copy;
    }

    public String getGameId() {
        return gameId;
    }

    public char[][] getBoard() {
        return board;
    }

    public Player getPlayerX() {
        return playerX;
    }

    public Player getPlayerO() {
        return playerO;
    }

    public char getCurrentTurn() {
        return currentTurn;
    }

    public GameStatus getStatus() {
        return status;
    }

    public long getVersion() {
        return version;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
