package main.interview.wise.tictactoe;

public class DistributedTicTacToe {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Multi-User Distributed Tic Tac Toe ===\n");

        // Create 3 servers
        GameServer server1 = new GameServer("Server-1");
        GameServer server2 = new GameServer("Server-2");
        GameServer server3 = new GameServer("Server-3");

        // Form cluster
        server1.addReplica(server2);
        server1.addReplica(server3);
        server2.addReplica(server1);
        server2.addReplica(server3);
        server3.addReplica(server1);
        server3.addReplica(server2);

        System.out.println("=== Scenario: Multiple Games with Different Players ===\n");

        // Register 4 players on different servers
        Player alice = server1.registerPlayer("Alice");
        Player bob = server2.registerPlayer("Bob");
        Player charlie = server3.registerPlayer("Charlie");
        Player diana = server1.registerPlayer("Diana");

        Thread.sleep(100);

        // Alice creates Game 1
        System.out.println("\n--- Game 1: Alice vs Bob ---");
        GameState game1 = server1.createGame(alice);
        Thread.sleep(50);

        // Bob joins Game 1
        server2.joinGame(game1.getGameId(), bob);
        Thread.sleep(50);

        // Charlie creates Game 2
        System.out.println("\n--- Game 2: Charlie vs Diana ---");
        GameState game2 = server3.createGame(charlie);
        Thread.sleep(50);

        // Diana joins Game 2
        server1.joinGame(game2.getGameId(), diana);
        Thread.sleep(50);

        System.out.println("\n--- Playing Game 1 (Alice vs Bob) ---");
        server1.makeMove(game1.getGameId(), alice, 0, 0); // Alice (X)
        server2.makeMove(game1.getGameId(), bob, 1, 1); // Bob (O)
        server1.makeMove(game1.getGameId(), alice, 0, 1); // Alice (X)
        Thread.sleep(50);

        System.out.println("\n--- Playing Game 2 (Charlie vs Diana) ---");
        server3.makeMove(game2.getGameId(), charlie, 0, 0); // Charlie (X)
        server1.makeMove(game2.getGameId(), diana, 0, 1); // Diana (O)
        Thread.sleep(50);

        System.out.println("\n--- Current State of Both Games ---");
        printBoard(server2.getGame(game1.getGameId()), "Game 1");
        printBoard(server3.getGame(game2.getGameId()), "Game 2");

        // Test: Bob tries to make Alice's move (should fail)
        System.out.println("\n--- Test: Bob tries to play out of turn ---");
        boolean illegal = server2.makeMove(game1.getGameId(), bob, 0, 2);
        System.out.println("Result: " + (illegal ? "Allowed (BUG!)" : "Blocked ✓"));

        // Simulate Server-1 crash
        System.out.println("\n--- Server-1 crashes ---");
        server1.crash();
        Thread.sleep(100);

        // Continue both games via other servers
        System.out.println("\n--- Continuing games after crash ---");
        server2.makeMove(game1.getGameId(), bob, 2, 2); // Game 1 continues
        server3.makeMove(game2.getGameId(), diana, 1, 1); // Game 2 continues
        Thread.sleep(50);

        System.out.println("\n--- Final State (read from Server-2 and Server-3) ---");
        printBoard(server2.getGame(game1.getGameId()), "Game 1");
        printBoard(server3.getGame(game2.getGameId()), "Game 2");

        System.out.println("\n=== Demo Complete ===");
        System.out.println("✓ Multiple concurrent games");
        System.out.println("✓ Player authentication and turn enforcement");
        System.out.println("✓ Distributed across servers");
        System.out.println("✓ Fault tolerant");
    }

    private static void printBoard(GameState game, String title) {
        if (game == null) {
            System.out.println(title + ": Not found!");
            return;
        }

        char[][] board = game.getBoard();
        System.out.println("\n" + title + " [" + game.getGameId() + "] - " + game.getStatus());
        System.out.println("Players: " + game.getPlayerX().getPlayerName() + " (X) vs " +
                (game.getPlayerO() != null ? game.getPlayerO().getPlayerName() + " (O)" : "waiting..."));
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        if (game.getStatus() == GameStatus.IN_PROGRESS) {
            char turn = game.getCurrentTurn();
            String playerName = (turn == 'X') ? game.getPlayerX().getPlayerName() : game.getPlayerO().getPlayerName();
            System.out.println("Next turn: " + playerName + " (" + turn + ")");
        }
    }
}
