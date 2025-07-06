/**
 * @author agond
 * @date Jul 06, 2025
 * @time 8:56:19 AM
 */
package main.revision.meta.medium;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {
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
