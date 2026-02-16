package main.dsa.google.leetCodeDiscussion;

import java.util.ArrayList;
import java.util.List;

/**
 * The Logic
 * This is a two-step mathematical problem.
 * 1. Minimize the Number of Packets (Primary Goal)
 * To use the fewest packets possible, we must conceptually fill them up to their maximum capacity.
 * k=⌈dataSize/maxCapacity⌉
 * In integer arithmetic, this is calculated as: (dataSize + maxCapacity - 1) / maxCapacity.
 * 2. Minimize the Maximum Packet Size (Secondary Goal)
 * Now that we have fixed the number of packets to k, we need to distribute the dataSize across them.
 * To minimize the maximum element in a set of numbers that must sum up to a fixed total, the numbers should be as equal as possible.
 * If we use a "greedy" approach (e.g., fill the first packet to capacity, then the next), we might end up with [10, 4] (Max: 10).
 * If we use a "balanced" approach, we get [7, 7] (Max: 7).
 * Since 7 < 10, the balanced approach is the winner.
 *
 * Algorithm:
 * Calculate packetCount.
 * Calculate the baseSize = dataSize / packetCount.
 * Calculate the remainder = dataSize % packetCount.
 * To distribute the data perfectly, remainder packets will have size baseSize + 1, and the rest will have size baseSize.
 *
 * Complexity Analysis
 * Time Complexity: O(k)
 * , where k is the number of packets required. We simply loop k times to build the list.
 * Space Complexity: O(k) to store the output list.
 *
 * Why this works (Example Trace)
 * Inputs: dataSize = 14, maxCapacity = 10
 * Packet Count: (14 + 10 - 1) / 10 = 2 packets.
 * Greedy (Wrong) Approach: Fill first packet to max. [10, 4].
 * Max packet size = 10.
 * Balanced (Correct) Approach:
 *
 * baseSize = 14/2=7.
 * remainder = 14%2=0.
 * Result: [7, 7].
 * Max packet size = 7.
 * Since 7 < 10 7<10 , the balanced approach satisfies the condition "minimize the maximum packet size".
 */
public class PacketSplitter {

    public static List<Integer> splitData(int dataSize, int maxCapacity) {
        List<Integer> result = new ArrayList<>();

        if (dataSize == 0) {
            return result;
        }

        // Step 1: Calculate Minimum Number of Packets (k)
        // Formula equivalent to Math.ceil(dataSize / maxCapacity)
        int packetCount = (dataSize + maxCapacity - 1) / maxCapacity;

        // Step 2: Distribute load evenly to minimize the max packet size
        // Everyone gets at least this much
        int baseSize = dataSize / packetCount;

        // This many packets need to take 1 extra unit to account for the remainder
        int remainder = dataSize % packetCount;

        // Step 3: Build the result
        for (int i = 0; i < packetCount; i++) {
            // Distribute the remainder among the first few packets
            if (i < remainder) {
                result.add(baseSize + 1);
            } else {
                result.add(baseSize);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1:
        // dataSize = 14, maxCapacity = 10
        // Min packets = ceil(1.4) = 2.
        // Distribution: 14 / 2 = 7.
        // Output: [7, 7] (Better than [10, 4])
        System.out.println("Test 1: " + splitData(14, 10));

        // Example 2:
        // dataSize = 10, maxCapacity = 3
        // Min packets = ceil(3.33) = 4.
        // Distribution: 10 / 4 = 2 remainder 2.
        // Output: [3, 3, 2, 2]
        System.out.println("Test 2: " + splitData(10, 3));
    }
}
