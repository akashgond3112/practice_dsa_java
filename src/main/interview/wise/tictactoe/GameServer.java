package main.interview.wise.tictactoe;

import java.util.*;
import java.util.concurrent.*;

class GameServer {
    private final String serverId;
    private final ConcurrentHashMap<String, GameState> games = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Player> activePlayers = new ConcurrentHashMap<>();
    private final List<GameServer> replicas = new CopyOnWriteArrayList<>();
    private volatile boolean isAlive = true;

    public GameServer(String serverId) {
        this.serverId = serverId;
    }

    public void addReplica(GameServer replica) {
        if (!replicas.contains(replica)) {
            replicas.add(replica);
        }
    }

    // Player registration
    public Player registerPlayer(String playerName) {
        String playerId = UUID.randomUUID().toString().substring(0, 8);
        Player player = new Player(playerId, playerName);
        activePlayers.put(playerId, player);
        System.out.println("[" + serverId + "] Player registered: " + playerName + " (ID: " + playerId + ")");
        return player;
    }

    // Create new game
    public GameState createGame(Player creator) {
        if (!activePlayers.containsKey(creator.getPlayerId())) {
            System.out.println("Player not registered");
            return null;
        }

        String gameId = "game-" + UUID.randomUUID().toString().substring(0, 6);
        GameState game = new GameState(gameId, creator);
        games.put(gameId, game);

        replicateToAll(game);

        System.out.println("[" + serverId + "] Game created: " + gameId +
                " by " + creator.getPlayerName() + " (waiting for opponent)");
        return game;
    }

    // Join existing game
    public boolean joinGame(String gameId, Player player) {
        if (!activePlayers.containsKey(player.getPlayerId())) {
            System.out.println("Player not registered");
            return false;
        }

        GameState game = games.get(gameId);
        if (game == null) {
            game = recoverFromReplicas(gameId);
            if (game == null)
                return false;
        }

        boolean joined = game.joinGame(player);
        if (joined) {
            replicateToAll(game);
        }
        return joined;
    }

    // Make move
    public boolean makeMove(String gameId, Player player, int row, int col) {
        if (!activePlayers.containsKey(player.getPlayerId())) {
            System.out.println("Player not registered");
            return false;
        }

        GameState game = games.get(gameId);
        if (game == null) {
            game = recoverFromReplicas(gameId);
            if (game == null)
                return false;
        }

        boolean success = game.makeMove(player, row, col);
        if (success) {
            System.out.println("[" + serverId + "] " + player.getPlayerName() +
                    " moved at (" + row + "," + col + ") in game " + gameId);
            replicateToAll(game);
        }
        return success;
    }

    // List available games to join
    public List<GameState> listAvailableGames() {
        List<GameState> available = new ArrayList<>();
        for (GameState game : games.values()) {
            if (game.getStatus() == GameStatus.WAITING_FOR_PLAYERS) {
                available.add(game.copy());
            }
        }
        return available;
    }

    // Get game state
    public GameState getGame(String gameId) {
        GameState game = games.get(gameId);
        if (game == null) {
            game = recoverFromReplicas(gameId);
        }
        return game != null ? game.copy() : null;
    }

    // Replication
    private void replicateToAll(GameState game) {
        for (GameServer replica : replicas) {
            if (replica.isAlive) {
                replica.receiveReplication(game.copy());
            }
        }
    }

    void receiveReplication(GameState game) {
        GameState existing = games.get(game.getGameId());
        if (existing == null || game.getVersion() > existing.getVersion()) {
            games.put(game.getGameId(), game);
        }
    }

    private GameState recoverFromReplicas(String gameId) {
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
        }
        return latest;
    }

    public void crash() {
        isAlive = false;
        System.out.println("[" + serverId + "] CRASHED!");
    }

    public void recover() {
        isAlive = true;
        System.out.println("[" + serverId + "] RECOVERED!");
        for (GameServer replica : replicas) {
            if (replica.isAlive) {
                for (Map.Entry<String, GameState> entry : replica.games.entrySet()) {
                    receiveReplication(entry.getValue().copy());
                }
            }
        }
    }

    public String getServerId() {
        return serverId;
    }
}
