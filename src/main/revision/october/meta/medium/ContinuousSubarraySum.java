package main.revision.october.meta.medium;

import java.util.*;

public class ContinuousSubarraySum {

    class Solution {
        /**
         * Checks if the array contains a continuous subarray of at least size two whose sum is a multiple of k.
         *
         * @param nums the input array of integers
         * @param k the integer to check multiples of
         * @return true if such a subarray exists, false otherwise
         */
        public boolean checkSubarraySum(int[] nums, int k) {
            Map<Integer, Integer> prefixModMap = new HashMap<>();

            prefixModMap.put(0, -1);

            int currentSum = 0;

            for (int i = 0; i < nums.length; i++) {
                currentSum += nums[i];
                int remainder = ((currentSum % k) + k) % k;
                if (k == 0) {
                    remainder = currentSum;
                } else {
                    remainder = currentSum % k;
                }

                if (prefixModMap.containsKey(remainder)) {
                    if (i - prefixModMap.get(remainder) >= 2) {
                        return true;
                    }
                } else {
                    prefixModMap.put(remainder, i);
                }
            }

            return false;
        }
    }

    // revised on 06/11/2025
    class SolutionRevisionOnThirdDay {
        /**
         * Checks if the array contains a continuous subarray of at least size two whose sum is a multiple of k.
         *
         * @param nums the input array of integers
         * @param k the integer to check multiples of
         * @return true if such a subarray exists, false otherwise
         */
        public boolean checkSubarraySum(int[] nums, int k) {

            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);

            int currentSum = 0;

            for (int i = 0; i < nums.length; i++) {
                currentSum += nums[i];
                int remainder = currentSum % k;

                if (map.containsKey(remainder)) {
                    if (i - map.get(remainder) >= 2) {
                        return true;
                    }
                } else {
                    map.put(remainder, i);
                }
            }

            return false;
        }
    }

    /**
     * SolutionRevisionOnSeventhDay provides an implementation to check for a continuous subarray sum.
     * This revision maintains a HashMap to store remainders and their indices, similar to previous versions,
     * but is intended for further review or optimization as of 11/12/2025.
     * The logic is consistent with earlier revisions, focusing on modular arithmetic and prefix sums.
     */
    // revised on 11/12/2025
    class SolutionRevisionOnSeventhDay {
        public boolean checkSubarraySum(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);

            int currentSum = 0;

            for (int i = 0; i < nums.length; i++) {

                currentSum += nums[i];
                int remainder = currentSum % k;

                if (map.containsKey(remainder)) {

                    if (i - map.get(remainder) >= 2) {
                        return true;
                    }
                } else {
                    map.put(remainder, i);
                }
            }
            return false;
        }
    }
}
