package main.interview.wise.tictactoe;

public class DistributedTicTacToe {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Distributed Tic Tac Toe Demo ===\n");

        // Create 3 servers
        GameServer server1 = new GameServer("Server-1");
        GameServer server2 = new GameServer("Server-2");
        GameServer server3 = new GameServer("Server-3");

        // Setup replication (each server knows about others)
        server1.addReplica(server2);
        server1.addReplica(server3);
        server2.addReplica(server1);
        server2.addReplica(server3);
        server3.addReplica(server1);
        server3.addReplica(server2);

        System.out.println("Cluster formed with 3 servers\n");

        // Create game on Server-1
        GameState game = server1.createGame();
        String gameId = game.getId();
        Thread.sleep(100);

        // Make moves through different servers (distributed)
        server1.makeMove(gameId, 0, 0, 'X'); // X at (0,0)
        Thread.sleep(50);
        server2.makeMove(gameId, 1, 1, 'O'); // O at (1,1) via different server!
        Thread.sleep(50);
        server3.makeMove(gameId, 0, 1, 'X'); // X at (0,1) via yet another server!
        Thread.sleep(50);

        printBoard(server1.getGame(gameId));

        // Simulate Server-1 crash
        System.out.println("\n--- Simulating Server-1 failure ---");
        server1.crash();
        Thread.sleep(100);

        // Continue playing through Server-2 (fault tolerance)
        System.out.println("\nContinuing game through Server-2...");
        server2.makeMove(gameId, 2, 2, 'O');
        server2.makeMove(gameId, 0, 2, 'X');
        Thread.sleep(50);

        // Read from Server-3 (data still available)
        GameState finalState = server3.getGame(gameId);
        System.out.println("\nReading game state from Server-3:");
        printBoard(finalState);

        // Server-1 recovers
        System.out.println("\n--- Server-1 recovering ---");
        server1.recover();
        Thread.sleep(200);

        System.out.println("\nServer-1 state after recovery:");
        printBoard(server1.getGame(gameId));

        // Cleanup
        server1.shutdown();
        server2.shutdown();
        server3.shutdown();

        System.out.println("\n=== Demo Complete ===");
    }

    private static void printBoard(GameState game) {
        if (game == null) {
            System.out.println("Game not found!");
            return;
        }

        char[][] board = game.getBoard();
        System.out.println("\nGame: " + game.getId() + " (v" + game.getVersion() + ")");
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Next: " + game.getCurrentPlayer() +
                (game.getWinner() != null ? " | Winner: " + game.getWinner() : ""));
    }
}
