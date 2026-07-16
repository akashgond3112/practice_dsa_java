/**
 * @author akash
 * @date Jul 12, 2026
 * @time 10:56:27 AM
 */
package main.interview.amazon;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public class Solution {
        public int[] twoSum(int[] nums, int target) {
            // Time complexity: O(n) — we scan the array once, doing O(1) hashmap
            // operations for each element.
            // Space complexity: O(n) — in the worst case we store each element in
            // the hashmap.
            Map<Integer, Integer> seen = new HashMap<>(); // value -> index

            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];

                if (seen.containsKey(complement)) {
                    return new int[] { seen.get(complement), i };
                }

                seen.put(nums[i], i);
            }

            throw new IllegalArgumentException("No two sum solution found");
        }
    }

}
