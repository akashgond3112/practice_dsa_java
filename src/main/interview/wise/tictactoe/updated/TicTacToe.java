package main.interview.wise.tictactoe.updated;

import java.util.*;
import java.util.concurrent.*;

/**
 * Distributed Tic Tac Toe - Evolving Solution for Interview
 * 
 * Step 1: Basic game with multiple users
 * Step 2: Support multiple concurrent games
 * Step 3: Add fault tolerance and replication
 */
public class TicTacToe {

    // ========================================================================
    // STEP 1: Basic Structure - Multiple Users Playing
    // ========================================================================

    private final int rows;
    private final int cols;
    private final int winLength; // How many in a row to win
    private final char[][] board;
    private String playerX; // Player ID for X
    private String playerO; // Player ID for O
    private char currentTurn;
    private boolean gameOver;
    private String winner;

    public TicTacToe(int rows, int cols, int winLength) {
        this.rows = rows;
        this.cols = cols;
        this.winLength = winLength;
        this.board = new char[rows][cols];
        this.currentTurn = 'X';
        this.gameOver = false;

        // Initialize board
        for (int i = 0; i < rows; i++) {
            Arrays.fill(board[i], '-');
        }
    }

    // Join game as a player
    public synchronized boolean joinGame(String playerId) {
        if (playerX == null) {
            playerX = playerId;
            System.out.println("Player " + playerId + " joined as X");
            return true;
        } else if (playerO == null && !playerId.equals(playerX)) {
            playerO = playerId;
            System.out.println("Player " + playerId + " joined as O");
            return true;
        }
        return false;
    }

    // Make a move - validates player turn
    public synchronized boolean makeMove(String playerId, int row, int col) {
        // Check if game is ready
        if (playerX == null || playerO == null) {
            System.out.println("Waiting for both players");
            return false;
        }

        // Check if game is over
        if (gameOver) {
            System.out.println("Game is over");
            return false;
        }

        // Validate it's the player's turn
        char playerSymbol = getPlayerSymbol(playerId);
        if (playerSymbol == ' ') {
            System.out.println("Player not in this game");
            return false;
        }

        if (playerSymbol != currentTurn) {
            System.out.println("Not your turn! Current turn: " + currentTurn);
            return false;
        }

        // Validate position
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            System.out.println("Invalid position");
            return false;
        }

        if (board[row][col] != '-') {
            System.out.println("Position already occupied");
            return false;
        }

        // Make the move
        board[row][col] = playerSymbol;
        System.out.println("Player " + playerId + " (" + playerSymbol + ") moved to (" + row + "," + col + ")");

        // Check win condition
        if (checkWin(row, col, playerSymbol)) {
            gameOver = true;
            winner = playerId;
            System.out.println("Player " + playerId + " wins!");
            return true;
        }

        // Check draw
        if (isBoardFull()) {
            gameOver = true;
            System.out.println("Game is a draw!");
            return true;
        }

        // Switch turn
        currentTurn = (currentTurn == 'X') ? 'O' : 'X';
        return true;
    }

    private char getPlayerSymbol(String playerId) {
        if (playerId.equals(playerX))
            return 'X';
        if (playerId.equals(playerO))
            return 'O';
        return ' ';
    }

    // Check if current move wins the game
    private boolean checkWin(int row, int col, char symbol) {
        // Check horizontal, vertical, and both diagonals
        return checkLine(row, col, 0, 1, symbol) || // Horizontal
                checkLine(row, col, 1, 0, symbol) || // Vertical
                checkLine(row, col, 1, 1, symbol) || // Diagonal \
                checkLine(row, col, 1, -1, symbol); // Diagonal /
    }

    private boolean checkLine(int row, int col, int dRow, int dCol, char symbol) {
        int count = 1;

        // Count in positive direction
        int r = row + dRow, c = col + dCol;
        while (r >= 0 && r < rows && c >= 0 && c < cols && board[r][c] == symbol) {
            count++;
            r += dRow;
            c += dCol;
        }

        // Count in negative direction
        r = row - dRow;
        c = col - dCol;
        while (r >= 0 && r < rows && c >= 0 && c < cols && board[r][c] == symbol) {
            count++;
            r -= dRow;
            c -= dCol;
        }

        return count >= winLength;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '-')
                    return false;
            }
        }
        return true;
    }

    public void printBoard() {
        System.out.println("\nCurrent Board:");
        System.out.print("  ");
        for (int j = 0; j < cols; j++)
            System.out.print(j + " ");
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // ========================================================================
    // STEP 2: Support Multiple Games - GameManager
    // ========================================================================

    static class GameManager {
        private final ConcurrentHashMap<String, TicTacToe> games = new ConcurrentHashMap<>();
        private final ConcurrentHashMap<String, String> playerToGame = new ConcurrentHashMap<>();

        // Create a new game
        public String createGame(int rows, int cols, int winLength, String creatorId) {
            String gameId = "game-" + UUID.randomUUID().toString().substring(0, 8);
            TicTacToe game = new TicTacToe(rows, cols, winLength);
            game.joinGame(creatorId);
            games.put(gameId, game);
            playerToGame.put(creatorId, gameId);
            System.out.println("Game " + gameId + " created by " + creatorId);
            return gameId;
        }

        // Join an existing game
        public boolean joinGame(String gameId, String playerId) {
            TicTacToe game = games.get(gameId);
            if (game == null)
                return false;

            boolean joined = game.joinGame(playerId);
            if (joined) {
                playerToGame.put(playerId, gameId);
            }
            return joined;
        }

        // Make a move
        public boolean makeMove(String gameId, String playerId, int row, int col) {
            TicTacToe game = games.get(gameId);
            if (game == null)
                return false;
            return game.makeMove(playerId, row, col);
        }

        // Get game
        public TicTacToe getGame(String gameId) {
            return games.get(gameId);
        }

        // List all active games
        public List<String> listGames() {
            return new ArrayList<>(games.keySet());
        }
    }

    // ========================================================================
    // STEP 3: Fault Tolerance - Distributed GameManager with Replication
    // ========================================================================

    static class DistributedGameManager {
        private final String nodeId;
        private final GameManager localManager;
        private final List<DistributedGameManager> replicas;
        private final ConcurrentHashMap<String, Long> gameVersions; // Track versions
        private volatile boolean isAlive;

        public DistributedGameManager(String nodeId) {
            this.nodeId = nodeId;
            this.localManager = new GameManager();
            this.replicas = new CopyOnWriteArrayList<>();
            this.gameVersions = new ConcurrentHashMap<>();
            this.isAlive = true;
        }

        public void addReplica(DistributedGameManager replica) {
            if (!replicas.contains(replica)) {
                replicas.add(replica);
            }
        }

        // Create game with replication
        public String createGame(int rows, int cols, int winLength, String creatorId) {
            String gameId = localManager.createGame(rows, cols, winLength, creatorId);
            gameVersions.put(gameId, 0L);

            // Replicate to other nodes
            replicateGame(gameId);
            return gameId;
        }

        // Join game with replication
        public boolean joinGame(String gameId, String playerId) {
            // Try local first
            boolean joined = localManager.joinGame(gameId, playerId);

            // If not found locally, try to recover from replicas
            if (!joined && localManager.getGame(gameId) == null) {
                if (recoverGame(gameId)) {
                    joined = localManager.joinGame(gameId, playerId);
                }
            }

            if (joined) {
                incrementVersion(gameId);
                replicateGame(gameId);
            }
            return joined;
        }

        // Make move with replication
        public boolean makeMove(String gameId, String playerId, int row, int col) {
            // Try local first
            boolean success = localManager.makeMove(gameId, playerId, row, col);

            // If not found locally, try to recover from replicas
            if (!success && localManager.getGame(gameId) == null) {
                if (recoverGame(gameId)) {
                    success = localManager.makeMove(gameId, playerId, row, col);
                }
            }

            if (success) {
                incrementVersion(gameId);
                replicateGame(gameId);
            }
            return success;
        }

        private void incrementVersion(String gameId) {
            gameVersions.compute(gameId, (k, v) -> (v == null ? 0L : v) + 1);
        }

        // Replicate game state to all replicas
        private void replicateGame(String gameId) {
            TicTacToe game = localManager.getGame(gameId);
            Long version = gameVersions.get(gameId);

            if (game == null)
                return;

            for (DistributedGameManager replica : replicas) {
                if (replica.isAlive) {
                    replica.receiveReplication(gameId, game, version);
                }
            }
        }

        // Receive replicated game state
        void receiveReplication(String gameId, TicTacToe game, Long version) {
            Long localVersion = gameVersions.getOrDefault(gameId, -1L);

            // Only accept if newer version
            if (version > localVersion) {
                // Deep copy the game state
                TicTacToe newGame = copyGame(game);
                localManager.games.put(gameId, newGame);
                gameVersions.put(gameId, version);
                System.out.println("[" + nodeId + "] Replicated game " + gameId + " version " + version);
            }
        }

        // Recover game from replicas (fault tolerance)
        private boolean recoverGame(String gameId) {
            System.out.println("[" + nodeId + "] Attempting to recover game " + gameId);

            TicTacToe latestGame = null;
            Long latestVersion = -1L;

            for (DistributedGameManager replica : replicas) {
                if (!replica.isAlive)
                    continue;

                TicTacToe game = replica.localManager.getGame(gameId);
                Long version = replica.gameVersions.get(gameId);

                if (game != null && version != null && version > latestVersion) {
                    latestGame = game;
                    latestVersion = version;
                }
            }

            if (latestGame != null) {
                localManager.games.put(gameId, copyGame(latestGame));
                gameVersions.put(gameId, latestVersion);
                System.out.println("[" + nodeId + "] Recovered game " + gameId + " from replicas");
                return true;
            }

            return false;
        }

        private TicTacToe copyGame(TicTacToe original) {
            TicTacToe copy = new TicTacToe(original.rows, original.cols, original.winLength);
            for (int i = 0; i < original.rows; i++) {
                System.arraycopy(original.board[i], 0, copy.board[i], 0, original.cols);
            }
            copy.playerX = original.playerX;
            copy.playerO = original.playerO;
            copy.currentTurn = original.currentTurn;
            copy.gameOver = original.gameOver;
            copy.winner = original.winner;
            return copy;
        }

        public TicTacToe getGame(String gameId) {
            return localManager.getGame(gameId);
        }

        public void crash() {
            isAlive = false;
            System.out.println("[" + nodeId + "] CRASHED!");
        }

        public void recover() {
            isAlive = true;
            System.out.println("[" + nodeId + "] RECOVERING...");

            // Sync all games from replicas
            Set<String> allGameIds = new HashSet<>();
            for (DistributedGameManager replica : replicas) {
                if (replica.isAlive) {
                    allGameIds.addAll(replica.localManager.listGames());
                }
            }

            for (String gameId : allGameIds) {
                recoverGame(gameId);
            }
            System.out.println("[" + nodeId + "] RECOVERED!");
        }

        public String getNodeId() {
            return nodeId;
        }
    }

    // ========================================================================
    // DEMO - Shows evolution of solution
    // ========================================================================

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Step-by-Step Tic Tac Toe Interview Solution ===\n");

        // STEP 1: Basic game with 2 players
        System.out.println("--- STEP 1: Basic Multi-User Game (3x3) ---");
        TicTacToe game1 = new TicTacToe(3, 3, 3);
        game1.joinGame("Alice");
        game1.joinGame("Bob");
        game1.makeMove("Alice", 0, 0); // X
        game1.makeMove("Bob", 1, 1); // O
        game1.makeMove("Alice", 0, 1); // X
        game1.printBoard();

        Thread.sleep(500);

        // STEP 2: Multiple concurrent games
        System.out.println("\n--- STEP 2: Multiple Concurrent Games ---");
        GameManager manager = new GameManager();
        String gameA = manager.createGame(3, 3, 3, "Alice");
        String gameB = manager.createGame(4, 4, 3, "Charlie");

        manager.joinGame(gameA, "Bob");
        manager.joinGame(gameB, "Diana");

        System.out.println("\nPlaying Game A (3x3):");
        manager.makeMove(gameA, "Alice", 0, 0);
        manager.makeMove(gameA, "Bob", 1, 1);
        manager.getGame(gameA).printBoard();

        System.out.println("Playing Game B (4x4):");
        manager.makeMove(gameB, "Charlie", 0, 0);
        manager.makeMove(gameB, "Diana", 1, 1);
        manager.getGame(gameB).printBoard();

        Thread.sleep(500);

        // STEP 3: Distributed with fault tolerance
        System.out.println("\n--- STEP 3: Distributed with Fault Tolerance ---");
        DistributedGameManager node1 = new DistributedGameManager("Node-1");
        DistributedGameManager node2 = new DistributedGameManager("Node-2");
        DistributedGameManager node3 = new DistributedGameManager("Node-3");

        // Form cluster
        node1.addReplica(node2);
        node1.addReplica(node3);
        node2.addReplica(node1);
        node2.addReplica(node3);
        node3.addReplica(node1);
        node3.addReplica(node2);

        String distGame = node1.createGame(3, 3, 3, "Alice");
        Thread.sleep(100);

        node2.joinGame(distGame, "Bob");
        Thread.sleep(100);

        System.out.println("\nMaking moves through different nodes:");
        node1.makeMove(distGame, "Alice", 0, 0);
        node2.makeMove(distGame, "Bob", 1, 1);
        node3.makeMove(distGame, "Alice", 0, 1);
        Thread.sleep(100);

        node1.getGame(distGame).printBoard();

        // Simulate crash
        System.out.println("--- Node-1 crashes ---");
        node1.crash();
        Thread.sleep(100);

        System.out.println("\nContinuing through Node-2:");
        node2.makeMove(distGame, "Bob", 2, 2);
        node2.getGame(distGame).printBoard();

        // Recovery
        System.out.println("--- Node-1 recovers ---");
        node1.recover();
        Thread.sleep(200);

        System.out.println("\nNode-1 state after recovery:");
        node1.getGame(distGame).printBoard();

        System.out.println("\n=== All Steps Complete ===");
        System.out.println("✓ Multi-user support with turn validation");
        System.out.println("✓ Multiple concurrent games");
        System.out.println("✓ Configurable board size (NxM)");
        System.out.println("✓ Distributed architecture");
        System.out.println("✓ Fault tolerance with replication");
    }
}
