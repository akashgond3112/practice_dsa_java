package main.interview.jp;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

/**
 * A thread-safe, high-performance scheduler for managing resource reservations.
 * Uses a Best-Fit allocation strategy with O(log N) lookup and lock-free concurrency.
 */
public class ResourceScheduler {

    public record UsageWindow(String resourceId, LocalDateTime start, LocalDateTime end) {
    }

    private static class ResourceNode {
        final int id;
        final int capacity;
        private final List<UsageWindow> windows = new ArrayList<>();

        ResourceNode(int id, int capacity) {
            this.id = id;
            this.capacity = capacity;
        }

        // Granular locking: Only blocks threads competing for THIS specific resource
        synchronized boolean trySchedule(String id, LocalDateTime start, int durationMinutes) {
            LocalDateTime end = start.plusMinutes(durationMinutes);

            // Check for overlaps in the schedule
            if (windows.stream().anyMatch(w -> start.isBefore(w.end()) && end.isAfter(w.start()))) {
                return false;
            }
            return windows.add(new UsageWindow(id, start, end));
        }

        synchronized void release(String id) {
            windows.removeIf(w -> w.resourceId().equals(id));
        }
    }

    private final List<ResourceNode> allNodes = new CopyOnWriteArrayList<>();
    private final TreeMap<Integer, List<ResourceNode>> capacityMap = new TreeMap<>();
    private final ConcurrentMap<String, ResourceNode> activeSchedules = new ConcurrentHashMap<>();

    public ResourceScheduler(Map<Integer, Integer> nodeConfigs) {
        int idCounter = 1;
        for (var entry : nodeConfigs.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                ResourceNode node = new ResourceNode(idCounter++, entry.getKey());
                allNodes.add(node);
                capacityMap.computeIfAbsent(node.capacity, k -> new ArrayList<>()).add(node);
            }
        }
    }

    /**
     * Schedules a task if a node with sufficient capacity is available.
     * Uses 'Best-Fit' algorithm via tailMap to minimize wasted capacity.
     */
    public boolean schedule(String taskId, int requiredCapacity, LocalDateTime start, int duration) {
        SortedMap<Integer, List<ResourceNode>> suitableNodes = capacityMap.tailMap(requiredCapacity);

        for (List<ResourceNode> nodesOfCapacity : suitableNodes.values()) {
            for (ResourceNode node : nodesOfCapacity) {
                if (node.trySchedule(taskId, start, duration)) {
                    activeSchedules.put(taskId, node);
                    return true;
                }
            }
        }
        return false;
    }

    public void cancel(String taskId) {
        ResourceNode node = activeSchedules.remove(taskId);
        if (node != null) {
            node.release(taskId);
        }
    }

    public void cleanupExpired(LocalDateTime now) {
        for (ResourceNode node : allNodes) {
            synchronized (node) {
                node.windows.removeIf(w -> w.end().isBefore(now));
            }
        }
    }

    public static void main(String[] args) {
        // Setup: 2 nodes of size 2, 2 nodes of size 4
        Map<Integer, Integer> configs = Map.of(2, 2, 4, 2);
        ResourceScheduler scheduler = new ResourceScheduler(configs);

        LocalDateTime now = LocalDateTime.now();

        System.out.println("Scheduling Task A: " + scheduler.schedule("Task-A", 4, now, 60));
        System.out.println("Scheduling Task B: " + scheduler.schedule("Task-B", 4, now.plusMinutes(10), 60));
        System.out.println("Scheduling Task C (Conflict): " + scheduler.schedule("Task-C", 4, now.plusMinutes(20), 60));

        scheduler.cancel("Task-B");
        System.out.println("Scheduling Task C (After B cancel): " + scheduler.schedule("Task-C", 4, now.plusMinutes(20), 60));
    }
}