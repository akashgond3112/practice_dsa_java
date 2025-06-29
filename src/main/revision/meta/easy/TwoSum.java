/**
 * @author agond
 * @date Jun 29, 2025
 * @time 10:47:59 AM
 */
package main.revision.meta.easy;

import java.util.*;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> preMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int diff = target - num;

            if (preMap.containsKey(diff)) {
                return new int[] { preMap.get(diff), i };
            }

            preMap.put(num, i);
        }

        return new int[] {};
    }
}
