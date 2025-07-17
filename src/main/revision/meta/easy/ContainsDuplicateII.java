/**
 * @author agond
 * @date Jul 17, 2025
 * @time 5:13:43 PM
 */
package main.revision.meta.easy;

import java.util.*;

public class ContainsDuplicateII {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        int startIndex = 0;
        int endIndex = 0;

        HashSet<Integer> set = new HashSet<>();

        while (endIndex < nums.length) {

            if (!set.add(nums[endIndex])) {
                return true;
            }

            if (set.size() >= k) {
                set.remove(nums[startIndex++]);
            }
            endIndex++;
        }
        return false;
    }
}
