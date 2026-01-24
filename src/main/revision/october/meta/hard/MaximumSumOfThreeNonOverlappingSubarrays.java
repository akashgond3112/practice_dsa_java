package main.revision.october.meta.hard;

import java.util.*;

public class MaximumSumOfThreeNonOverlappingSubarrays {

    /*
     * The problem asks for three non-overlapping subarrays of a fixed size `k`
     * that have the maximum possible sum of all their elements. We need to return
     * the starting indices of these three subarrays.
     *
     * This solution uses a top-down dynamic programming approach with memoization.
     *
     * Steps:
     * 1. Pre-computation: Calculate a prefix sum array. This allows us to find the
     * sum of any subarray `nums[i...i+k-1]` in O(1) time using `prefixSum[i+k] -
     * prefixSum[i]`.
     *
     * 2. DP State Definition: We define a function `findMaxSum(position, count)`
     * which returns
     * the maximum sum we can get by picking `3 - count` subarrays from the suffix
     * of the
     * array starting at `position`.
     *
     * 3. DP Recurrence Relation: For each `position`, we have two choices:
     * a) Don't start a subarray at `position`. The max sum is then
     * `findMaxSum(position + 1, count)`.
     * b) Start a subarray at `position`. The sum is `(sum of
     * nums[position...position+k-1]) + findMaxSum(position + k, count + 1)`.
     * We move to `position + k` to ensure the next subarray is non-overlapping.
     * The value for `mem[position][count]` will be the maximum of these two
     * choices.
     *
     * 4. Memoization: We use a 2D array `mem[position][count]` to store the results
     * of
     * `findMaxSum` to avoid recomputing for the same state.
     *
     * 5. Path Reconstruction: After populating the `mem` table with the maximum
     * sums, we
     * need to find the actual starting indices. We create a `findmaxSumPath`
     * function
     * that backtracks through our choices. At each `position`, it compares the
     * `start`
     * and `dontStart` options (using the pre-computed `mem` table) to determine
     * which
     * path was taken to achieve the maximum sum. If the `start` option was better,
     * we
     * record the current `position` as part of our result and jump ahead by `k`.
     * Otherwise, we move to the next position.
     *
     * Big O Notation:
     * - Time Complexity: O(n), where n is the length of `nums`.
     * - Calculating the prefix sum array takes O(n).
     * - The DP function `findMaxSum` has `n * 3` possible states. Each state is
     * computed once
     * in O(1) time, so this takes O(n).
     * - The path reconstruction `findmaxSumPath` also traverses the states once,
     * taking O(n).
     * - Total time is O(n) + O(n) + O(n) = O(n).
     *
     * - Space Complexity: O(n).
     * - The prefix sum array requires O(n) space.
     * - The memoization table `mem` requires O(n * 3) = O(n) space.
     * - The recursion depth can go up to O(n), contributing to the call stack
     * space.
     * - Total space is O(n) + O(n) = O(n).
     */
    class Solution {
        static int[] prefixSum;
        static int[][] mem = new int[20001][3];

        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int n = nums.length;

            // Step 1: Memoization table initialize karo with -1
            for (int i = 0; i < mem.length; i++) {
                Arrays.fill(mem[i], -1);
            }

            // Step 2: Prefix sum array calculate karo for O(1) subarray sum lookup
            prefixSum = new int[n + 1];
            prefixSum[0] = 0;
            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }

            // Step 3: Memoization table populate karo using DP
            findMaxSum(nums, 0, 0, k);

            // Step 4: Maximum sum ke indices reconstruct karo
            int[] path = new int[3];
            findmaxSumPath(nums, 0, 0, k, path, 0);

            return path;
        }

        // DP function jo maximum sum find karega
        private static int findMaxSum(int[] nums, int position, int count, int k) {
            // Base case 1: Agar 3 subarrays mil gaye
            if (count == 3)
                return 0;
            // Base case 2: Agar remaining elements k se kam hain
            if (position > nums.length - k)
                return 0;

            // Memoized result return karo agar available ho
            if (mem[position][count] != -1)
                return mem[position][count];

            // Choice 1: Subarray start mat karo at `position`
            int dontStart = findMaxSum(nums, position + 1, count, k);
            // Choice 2: Subarray start karo at `position`
            int start = findMaxSum(nums, position + k, count + 1, k) +
                    prefixSum[position + k] - prefixSum[position];

            // Best choice memoize karo aur return karo
            mem[position][count] = Math.max(dontStart, start);
            return mem[position][count];
        }

        // Function jo maximum sum ke indices reconstruct karega
        private static void findmaxSumPath(int[] nums, int position, int count, int k, int[] path, int pathIdx) {
            // Base case 1: Agar 3 indices mil gaye
            if (count == 3)
                return;
            // Base case 2: Agar remaining elements k se kam hain
            if (position > nums.length - k)
                return;

            // Recalculate karo dono choices ke values
            int dontStart = findMaxSum(nums, position + 1, count, k);
            int start = findMaxSum(nums, position + k, count + 1, k) +
                    prefixSum[position + k] - prefixSum[position];

            // Agar `position` se start karna better ya equal hai...
            if (start >= dontStart) {
                // ...toh is position ko path mein add karo...
                path[pathIdx] = position;
                // ...aur baaki path find karo `position + k` se
                findmaxSumPath(nums, position + k, count + 1, k, path, pathIdx + 1);
            } else {
                // Agar skip karna better hai, toh `position + 1` se continue karo
                findmaxSumPath(nums, position + 1, count, k, path, pathIdx);
            }
        }
    }

    // revised on 12/6/2025
    class SolutionRevisionOnThirdDay {
        static int[] prefixSum;
        static int[][] mem = new int[20001][3];

        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

            int n = nums.length;

            for (int i = 0; i < mem.length; i++) {
                Arrays.fill(mem, -1);
            }

            prefixSum = new int[n + 1];
            prefixSum[0] = 0;
            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }

            findMaxSum(nums, 0, 0, k);

            int[] path = new int[3];
            findmaxSumPath(nums, 0, 0, k, path, 0);

            return path;
        }

        private static int findMaxSum(int[] nums, int position, int count, int k) {

            if (count == 3)
                return 0;

            if (position > nums.length - 1)
                return 0;

            if (mem[position][count] != 1)
                return mem[position][count];

            int dontStart = findMaxSum(nums, position + 1, count, k);
            int start = findMaxSum(nums, position + 1, count + 1, k) +
                    prefixSum[position + k] - prefixSum[position];

            mem[position][count] = Math.max(dontStart, start);
            return mem[position][count];
        }

        private static void findmaxSumPath(int[] nums, int position, int count, int k, int[] path, int pathIdx) {

            if (count == 3)
                return;

            if (position > nums.length - 1)
                return;

            int dontStart = findMaxSum(nums, position + 1, count, k);
            int start = findMaxSum(nums, position + 1, count + 1, k) +
                    prefixSum[position + k] - prefixSum[position];

            if (start >= dontStart) {
                path[pathIdx] = position;
                findmaxSumPath(nums, position + 1, count + 1, k, path, pathIdx + 1);
            } else {
                findmaxSumPath(nums, position + 1, count, k, path, pathIdx);
            }

        }
    }

    // revised on 12/12/2025
    class SolutionRevisionOnSeventhDay {
        static int[] prefixSum;
        static int[][] mem = new int[20001][3];

        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

            int n = nums.length;

            for (int i = 0; i < mem.length; i++) {
                Arrays.fill(mem, -1);
            }

            prefixSum = new int[n + 1];
            prefixSum[0] = 0;

            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + prefixSum[i + 1];
            }

            findMaxSum(nums, 0, 0, k);

            int[] path = new int[3];

            findmaxSumPath(nums, 0, 0, k, path, 0);

            return path;
        }

        private static int findMaxSum(int[] nums, int position, int count, int k) {

            if (count == 3) {
                return 0;
            }

            if (position > nums.length - k) {
                return 0;
            }

            if (mem[position][count] != -1) {
                return mem[position][count];
            }

            int dontStart = findMaxSum(nums, position + 1, count, k);
            int start = findMaxSum(nums, position + 1, count + 1, k) +
                    prefixSum[position + k] - prefixSum[position];

            mem[position][count] = Math.max(start, dontStart);
            return mem[position][count];

        }

        private static void findmaxSumPath(int[] nums, int position, int count, int k, int[] path, int pathIdx) {

            if (count == 3) {
                return;
            }

            if (position > nums.length - k) {
                return;
            }

            int dontStart = findMaxSum(nums, position + 1, count, k);
            int start = findMaxSum(nums, position + k, count + 1, k) +
                    prefixSum[position + k] - prefixSum[position];

            if (start > dontStart) {
                path[pathIdx] = position;

                findmaxSumPath(nums, position + k, count + 1, k, path, pathIdx);
            } else {
                findmaxSumPath(nums, position + k, count, k, path, pathIdx);

            }
        }
    }

    // revised on 12/26/2025
    class SolutionRevisionOnFourteenDay {
        static int[] prefixSum;
        static int[][] mem = new int[20001][3];

        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int n = nums.length;

            for (int i = 0; i < mem.length; i++) {
                Arrays.fill(mem[i], -1);
            }

            prefixSum = new int[n + 1];
            prefixSum[0] = 0;

            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }

            findMaxSum(nums, 0, 0, k);

            int[] path = new int[3];
            findmaxSumPath(nums, 0, 0, k, path, 0);

            return path;
        }

        private static int findMaxSum(int[] nums, int position, int count, int k) {

            if (count == 3)
                return 0;

            if (position > nums.length - k)
                return 0;

            if (mem[position][count] != -1)
                return mem[position][count];

            int dontStart = findMaxSum(nums, position + 1, count, k);

            int start = findMaxSum(nums, position + 1, count + 1, k) + prefixSum[position + k] - prefixSum[position];

            mem[position][count] = Math.max(dontStart, start);
            return mem[position][count];
        }

        private static void findmaxSumPath(int[] nums, int position, int count, int k, int[] path, int pathIdx) {

            if (count == 3)
                return;

            if (position > nums.length - k)
                return;

            int dontStart = findMaxSum(nums, position + 1, count, k);

            int start = findMaxSum(nums, position + 1, count + 1, k) + prefixSum[position + k] - prefixSum[position];

            if (start >= dontStart) {
                path[pathIdx] = position;
                findmaxSumPath(nums, position + 1, count + 1, k, path, pathIdx + 1);
            } else {
                findmaxSumPath(nums, position + 1, count, k, path, pathIdx);

            }
        }
    }

    // revised on 1/24/2026
    class SolutionRevisedOnDayThirty {

        int[] prefixSum;
        int[][] memo = new int[20001][3];

        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

            int n = nums.length;
            prefixSum = new int[n + 1];

            for (int i = 0; i < memo.length; i++) {
                Arrays.fill(memo[i], -1);
            }

            prefixSum[0] = 0;
            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }

            findMaxSum(nums, 0, 0, k);

            int[] path = new int[3];
            findMaxSumPath(nums, 0, 0, k, path, 0);

            return path;
        }

        private int findMaxSum(int[] nums, int position, int count, int k) {

            if (count == 3) {
                return 0;
            }

            if (position > nums.length - k) {
                return 0;
            }

            if (memo[position][count] != -1) {
                return memo[position][count];
            }

            int dontStart = findMaxSum(nums, position + 1, count, k);
            int start = findMaxSum(nums, position + 1, count + 1, k) + prefixSum[position + k] - prefixSum[position];

            memo[position][count] = Math.max(start, dontStart);
            return memo[position][count];

        }

        private void findMaxSumPath(int[] nums, int position, int count, int k, int[] path, int pathIdx) {
            if (count == 3) {
                return;
            }

            if (position > nums.length - k) {
                return;
            }

            int dontStart = findMaxSum(nums, position + 1, count, k);
            int start = findMaxSum(nums, position + 1, count + 1, k) + prefixSum[position + k] - prefixSum[position];

            if (start > dontStart) {
                path[pathIdx] = position;
                findMaxSumPath(nums, position + 1, count + 1, k, path, pathIdx + 1);
            } else {
                findMaxSumPath(nums, position + 1, count, k, path, pathIdx);
            }
        }
    }
}
