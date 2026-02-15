/**
 * Part 1: The In-Memory Solution (HashMap)
 * <p>
 * Since the list is sorted by timestamp, we iterate through the events. When we see a START, we save it.
 * When we see an END, we look up the start time and check the duration.
 * <p>
 * Assumptions:
 * <ul>
 *   <li>The input is a stream/list of events.</li>
 *   <li>There is a way to distinguish Start from End (e.g., a type field or implicit ordering).</li>
 *   <li>id refers to a specific session/correlation ID.</li>
 * </ul>
 * <p>
 * Time Complexity: O(N) (One pass).
 * Space Complexity: O(N)
 * (In the worst case, all events start before any end).
 *
 * -------------------------------------------------------------------------
 *
 * Part 2: Follow-up - O(1) Space Complexity
 * <p>
 * Constraint Analysis:
 * If the input is sorted by Timestamp, consecutive events (Start A ... End A) can be millions of lines apart.
 * To match them without storing Start A in memory (Map) until End A arrives, we must change the order of the data.
 * <p>
 * The Solution: Sort by ID
 * If we sort the list by ID (and secondarily by Timestamp), the START and END events for the same ID become adjacent.
 * <p>
 * Algorithm:
 * <ol>
 *   <li>Sort the array in place (or virtually) by ID.</li>
 *   <li>Iterate through the array.</li>
 *   <li>Compare currentEvent with previousEvent.</li>
 *   <li>If current.id == prev.id and current.type == END, calculate duration.</li>
 * </ol>
 *
 * -------------------------------------------------------------------------
 *
 * Part 3: Follow-up - Events in Files (Big Data)
 * <p>
 * Scenario: The events are stored in a 100GB text file. You cannot load the file into RAM. You cannot use a 50GB HashMap.
 * <p>
 * Solution: Hash Partitioning (Sharding)
 * Since we need to match a START and END that share the same ID, we need to guarantee that both events end up in the same small
 * "bucket" that fits in memory.
 * <p>
 * Step 1: Partitioning
 * <ol>
 *   <li>Read the main file line by line. For each event, calculate hash(id) % K (where K is the number of temporary files).</li>
 *   <li>Write the event to temp_file_0, temp_file_1, ... temp_file_K.</li>
 * </ol>
 * <p>
 * Result: All events for ID="user_123" are guaranteed to be in the same file (e.g., temp_file_3).
 * <p>
 * Step 2: Process Partitions
 * <p>
 * Process each temporary file one by one.
 * <ul>
 *   <li>Option A (HashMap): Since the partition is small, load it into a HashMap (like Part 1) and check for timeouts. Clear Map after file is done.</li>
 *   <li>Option B (Sort): Read the partition, Sort by ID (like Part 2), and scan.</li>
 * </ul>
 * <p>
 * Why this works:
 * <ul>
 *   <li>RAM Usage: You only need buffer space for the current partition.</li>
 *   <li>Scalability: Works whether the input is 100GB or 1PB (just increase K).</li>
 * </ul>
 *
 * -------------------------------------------------------------------------
 *
 * Part 4: Follow-up - Events Split Across Multiple Files
 * <p>
 * Scenario: Input is already split into file_1.log, file_2.log ... file_100.log.
 * Problem: START might be in file_1 and END might be in file_100.
 * <p>
 * Solution 1: MapReduce (Distributed)
 * <p>
 * This is exactly what MapReduce / Hadoop / Spark is designed for.
 * <ul>
 *   <li>Map Step: Read all files. Emit key-value pair (ID, EventData).</li>
 *   <li>Shuffle Step: The framework groups all data with the same ID together.</li>
 *   <li>Reduce Step: Receive (ID, [EventStart, EventEnd]). Calculate diff. If &gt; timeout, emit result.</li>
 * </ul>
 * <p>
 * Solution 2: Consistent Hashing (Single Machine Stream)
 * <p>
 * If you are on a single machine processing multiple files:
 * <ul>
 *   <li>Do not process files sequentially (1, then 2, then 3).</li>
 *   <li>Treat all files as a single input stream for the Partitioning step described in Part 3.</li>
 *   <li>Read file_1, hash IDs, distribute to temp files.</li>
 *   <li>Read file_2, hash IDs, distribute to same temp files.</li>
 *   <li>After all source files are read, process the temp files.</li>
 * </ul>
 *
 * -------------------------------------------------------------------------
 *
 * Event (data model):
 * <ul>
 *   <li>String id;</li>
 *   <li>long timestamp;</li>
 *   <li>String type; // "START" or "END"</li>
 * </ul>
 *
 * -------------------------------------------------------------------------
 *
 * Methods overview:
 * <p>
 * hasTimeoutEvent(List<Event> events, long timeout)
 * <ul>
 *   <li>Map to store Start times: Key = ID, Value = Timestamp</li>
 *   <li>Iterate through events; on START save start time, on END check map for start and compute duration.</li>
 *   <li>If duration &gt; timeout, return true; otherwise remove mapping (optional).</li>
 * </ul>
 *
 * hasTimeoutEventConstantSpace(List<Event> events, long timeout)
 * <ul>
 *   <li>1. Sort by ID, then by Timestamp (Time: O(N log N), Space: O(1) depending on sort implementation).</li>
 *   <li>2. Iterate linearly and check adjacent START/END pairs for the same ID; if duration &gt; timeout return true.</li>
 * </ul>
 */
package main.dsa.google.leetCodeDiscussion;

import java.util.*;

class Event {
    String id;
    long timestamp;
    String type; // "START" or "END"

    public Event(String id, long timestamp, String type) {
        this.id = id;
        this.timestamp = timestamp;
        this.type = type;
    }
}

public class EventMonitor {

    public boolean hasTimeoutEvent(List<Event> events, long timeout) {
        // Map to store Start times: Key = ID, Value = Timestamp
        Map<String, Long> activeEvents = new HashMap<>();

        for (Event event : events) {
            if ("START".equals(event.type)) {
                activeEvents.put(event.id, event.timestamp);
            } else if ("END".equals(event.type)) {
                if (activeEvents.containsKey(event.id)) {
                    long startTime = activeEvents.get(event.id);
                    long duration = event.timestamp - startTime;

                    if (duration > timeout) {
                        return true; // Found one!
                    }

                    // Remove to keep map size managed (optional, depends on if IDs are reused)
                    activeEvents.remove(event.id);
                }
            }
        }
        return false;
    }

    public boolean hasTimeoutEventConstantSpace(List<Event> events, long timeout) {
        // 1. Sort by ID, then by Timestamp
        // Time: O(N log N), Space: O(1) (depending on sort implementation, e.g.
        // Heapsort)
        Collections.sort(events, (a, b) -> {
            if (!a.id.equals(b.id)) {
                return a.id.compareTo(b.id);
            }
            return Long.compare(a.timestamp, b.timestamp);
        });

        // 2. Iterate linearly
        for (int i = 1; i < events.size(); i++) {
            Event prev = events.get(i - 1);
            Event curr = events.get(i);

            // Check if they are the pair we are looking for
            if (curr.id.equals(prev.id) &&
                    "START".equals(prev.type) &&
                    "END".equals(curr.type)) {

                if (curr.timestamp - prev.timestamp > timeout) {
                    return true;
                }
            }
        }
        return false;
    }
}
