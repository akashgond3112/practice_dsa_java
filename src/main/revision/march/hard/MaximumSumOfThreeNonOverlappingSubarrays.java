package main.revision.march.hard;

import java.util.*;

public class MaximumSumOfThreeNonOverlappingSubarrays {

    class Solution {

        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

            int n = nums.length;

            int[] prefixSum = new int[n + 1];
            int[][] mem = new int[20001][3];

            for (int i = 0; i < mem.length; i++) {
                Arrays.fill(mem[i], -1);
            }

            prefixSum[0] = 0;
            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }

            findMaxSum(nums, k, 0, 0, prefixSum, mem);

            int[] path = new int[3];
            findMaxSumPath(nums, k, 0, 0, 0, path, prefixSum, mem);

            return path;
        }

        private void findMaxSumPath(int[] nums, int k, int position, int count, int pathIdx, int[] path,
                int[] prefixSum, int[][] mem) {
            if (count == 3) {
                return;
            }
            if (position > nums.length - k) {
                return;
            }

            int dontStart = findMaxSum(nums, k, position + 1, count + 1, prefixSum, mem);
            int start = findMaxSum(nums, k, position + k, count + 1, prefixSum, mem) +
                    prefixSum[position + k] - prefixSum[position];

            if (start >= dontStart) {
                path[pathIdx] = position;
                findMaxSumPath(nums, k, position + k, count + 1, pathIdx + 1, path, prefixSum, mem);
            } else {
                findMaxSumPath(nums, k, position + k, count, pathIdx, path, prefixSum, mem);
            }
        }

        private int findMaxSum(int[] nums, int k, int position, int count, int[] prefixSum, int[][] mem) {

            if (count == 3) {
                return 0;
            }

            if (position > nums.length - k) {
                return 0;
            }

            if (mem[position][count] != -1)
                return mem[position][count];

            int dontStart = findMaxSum(nums, k, position + 1, count + 1, prefixSum, mem);
            int start = findMaxSum(nums, k, position + k, count + 1, prefixSum, mem) +
                    prefixSum[position + k] - prefixSum[position];

            mem[position][count] = Math.max(dontStart, start);

            return mem[position][count];
        }
    }
}
