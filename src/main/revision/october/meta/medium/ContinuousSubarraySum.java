package main.revision.october.meta.medium;

import java.util.*;

public class ContinuousSubarraySum {

    class Solution {
        public boolean checkSubarraySum(int[] nums, int k) {
            Map<Integer, Integer> prefixModMap = new HashMap<>();

            prefixModMap.put(0, -1);

            int currentSum = 0;

            for (int i = 0; i < nums.length; i++) {
                currentSum += nums[i];
                int remainder = currentSum % k;

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
}
