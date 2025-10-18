package main.interview.wise.tictactoe;

import java.util.*;
import java.util.concurrent.*;

class GameServer {
    private final String serverId;
    private final ConcurrentHashMap<String, GameState> games = new ConcurrentHashMap<>();
    private final List<GameServer> replicas = new CopyOnWriteArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private volatile boolean isAlive = true;

    public GameServer(String serverId) {
        this.serverId = serverId;
        // Heartbeat to simulate health check
        scheduler.scheduleAtFixedRate(() -> {
            if (!isAlive)
                return;
            System.out.println("[" + serverId + "] Heartbeat - Active games: " + games.size());
        }, 5, 5, TimeUnit.SECONDS);
    }

    // Register peer servers for replication
    public void addReplica(GameServer replica) {
        if (!replicas.contains(replica)) {
            replicas.add(replica);
        }
    }

    // Create new game and replicate
    public GameState createGame() {
        String gameId = UUID.randomUUID().toString().substring(0, 8);
        GameState game = new GameState(gameId);
        games.put(gameId, game);

        // Async replication to replicas
        replicateToAll(game);

        System.out.println("[" + serverId + "] Created game: " + gameId);
        return game;
    }

    // Make move with automatic replication
    public boolean makeMove(String gameId, int row, int col, char player) {
        GameState game = games.get(gameId);
        if (game == null) {
            // Try to recover from replicas
            game = recoverFromReplicas(gameId);
            if (game == null)
                return false;
        }

        boolean success = game.makeMove(row, col, player);
        if (success) {
            System.out.println("[" + serverId + "] Move made in game " + gameId +
                    " at (" + row + "," + col + ") by " + player);
            replicateToAll(game);
        }
        return success;
    }

    // Get game state (read-only)
    public GameState getGame(String gameId) {
        GameState game = games.get(gameId);
        if (game == null) {
            game = recoverFromReplicas(gameId);
        }
        return game != null ? game.copy() : null;
    }

    // Replicate state to all peer servers
    private void replicateToAll(GameState game) {
        for (GameServer replica : replicas) {
            if (replica.isAlive) {
                replica.receiveReplication(game.copy());
            }
        }
    }

    // Receive replicated state from peer
    void receiveReplication(GameState game) {
        GameState existing = games.get(game.getId());
        // Use version number to ensure we keep latest state
        if (existing == null || game.getVersion() > existing.getVersion()) {
            games.put(game.getId(), game);
            System.out.println("[" + serverId + "] Replicated game " +
                    game.getId() + " (version " + game.getVersion() + ")");
        }
    }

    // Fault tolerance: recover game from replicas
    private GameState recoverFromReplicas(String gameId) {
        System.out.println("[" + serverId + "] Attempting recovery of game " + gameId);
        GameState latest = null;

        for (GameServer replica : replicas) {
            if (!replica.isAlive)
                continue;

            GameState game = replica.games.get(gameId);
            if (game != null && (latest == null || game.getVersion() > latest.getVersion())) {
                latest = game;
            }
        }

        if (latest != null) {
            games.put(gameId, latest.copy());
            System.out.println("[" + serverId + "] Recovered game " + gameId + " from replica");
        }
        return latest;
    }

    // Simulate server failure
    public void crash() {
        isAlive = false;
        System.out.println("[" + serverId + "] CRASHED!");
    }

    // Simulate server recovery
    public void recover() {
        isAlive = true;
        System.out.println("[" + serverId + "] RECOVERED!");
        // Sync state from replicas
        for (GameServer replica : replicas) {
            if (replica.isAlive) {
                for (Map.Entry<String, GameState> entry : replica.games.entrySet()) {
                    receiveReplication(entry.getValue().copy());
                }
            }
        }
    }

    public void shutdown() {
        scheduler.shutdown();
    }

    public String getServerId() {
        return serverId;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
