/**
 * Utility for counting ordered index pairs (i, j) in an integer array that satisfy:
 *     arr[i] - arr[j] == i - j
 *
 * Rationale:
 * By rearranging the equation we get arr[i] - i == arr[j] - j. Thus indices can be grouped
 * by the value diff = arr[k] - k. If a group has frequency k, it contributes k * k ordered pairs
 * (including pairs where i == j).
 *
 * Behavior:
 * - Null or empty input returns 0.
 * - Pairs are ordered: (i, j) and (j, i) are counted separately when i != j.
 * - Self-pairs (i == j) are included.
 *
 * Complexity:
 * - Time: O(n) — single pass to compute diffs and build frequency map, plus iteration over map values.
 * - Space: O(n) in the worst case (distinct diffs for all indices).
 *
 * Example:
 * Input: [2, 4, 6, 5, 9, 9, 11]
 * Diffs: [2, 3, 4, 2, 5, 4, 5] -> freqs {2:2, 3:1, 4:2, 5:2}
 * Result: 2^2 + 1^2 + 2^2 + 2^2 = 13
 */

/**
 * Count ordered pairs (i, j) such that arr[i] - arr[j] == i - j.
 *
 * @param arr the input integer array; may be null
 * @return the number of ordered pairs (as a long). Returns 0 for null or empty arrays.
 *
 * Notes:
 * - Implementation groups indices by the value (arr[i] - i) and sums the square of each group's frequency.
 * - Uses a HashMap<Integer, Long> to store frequencies to avoid integer overflow for large counts.
 */
package main.dsa.google.leetCodeDiscussion;

import java.util.HashMap;
import java.util.Map;

/*
The Logic
The core of this problem lies in simplifying the mathematical condition.
The Equation:
a
r
r
[
i
]
−
a
r
r
[
j
]
=
i
−
j
arr[i]−arr[j]=i−j
Rearrange terms:
Move all terms related to 
i
i
 to one side and 
j
j
 to the other:
a
r
r
[
i
]
−
i
=
a
r
r
[
j
]
−
j
arr[i]−i=arr[j]−j
The Transformation:
If we define a value diff = arr[k] - k, the problem essentially asks us to find pairs 
(
i
,
j
)
(i,j)
 where this diff value is the same.
Counting Strategy:
Calculate arr[i] - i for every element.
Store the frequency of each result in a Hash Map.
If a specific difference value appears 
k
k
 times, it means there are 
k
k
 indices that share this property. Since we need ordered pairs and 
(
i
,
i
)
(i,i)
 is allowed, any of the 
k
k
 indices can be paired with any of the 
k
k
 indices.
Total pairs for that difference value = 
k
×
k
k×k
 (
k
2
k 
2
 
).
Java Solution
code
Java
Explanation of Example 1
Input: [2, 4, 6, 5, 9, 9, 11]
Calculate arr[i] - i:
Index 0: 
2
−
0
=
2
2−0=2
Index 1: 
4
−
1
=
3
4−1=3
Index 2: 
6
−
2
=
4
6−2=4
Index 3: 
5
−
3
=
2
5−3=2
Index 4: 
9
−
4
=
5
9−4=5
Index 5: 
9
−
5
=
4
9−5=4
Index 6: 
11
−
6
=
5
11−6=5
Frequencies:
Value 2: appears 2 times (indices 0, 3)
Value 3: appears 1 time (index 1)
Value 4: appears 2 times (indices 2, 5)
Value 5: appears 2 times (indices 4, 6)
Calculation:
2
2
+
1
2
+
2
2
+
2
2
2 
2
 +1 
2
 +2 
2
 +2 
2
 
4
+
1
+
4
+
4
=
13
4+1+4+4=13
Complexity Analysis
Time Complexity: 
O
(
N
)
O(N)

We iterate through the array once to populate the map, and iterate through the map values once to calculate the sum. Both are linear operations.
Space Complexity: 
O
(
N
)
O(N)

In the worst case (where every arr[i] - i is unique), the HashMap will store 
N
N
 entries.
*/

public class ProblemOne {

    public class PairCounter {

        public static long countPairs(int[] arr) {
            if (arr == null || arr.length == 0) {
                return 0;
            }

            // Map to store the frequency of (arr[i] - i)
            Map<Integer, Long> frequencyMap = new HashMap<>();

            // Step 1: Transform the array and count frequencies
            for (int i = 0; i < arr.length; i++) {
                int diff = arr[i] - i;
                frequencyMap.put(diff, frequencyMap.getOrDefault(diff, 0L) + 1);
            }

            long totalPairs = 0;

            // Step 2: Sum the squares of the frequencies
            // If a value appears 'k' times, it forms k*k ordered pairs
            for (long count : frequencyMap.values()) {
                totalPairs += (count * count);
            }

            return totalPairs;
        }

        // Driver code for testing
        public static void main(String[] args) {
            int[] input1 = { 2, 4, 6, 5, 9, 9, 11 };
            System.out.println("Output 1: " + countPairs(input1)); // Expected: 13

            int[] input2 = { 1, 2, 3 };
            System.out.println("Output 2: " + countPairs(input2)); // Expected: 9

            int[] input3 = { 0, 0, 0 };
            System.out.println("Output 3: " + countPairs(input3)); // Expected: 3
                                                                   // (0-0=0, 0-1=-1, 0-2=-2) -> 1^2 + 1^2 + 1^2 = 3
        }
    }
}
