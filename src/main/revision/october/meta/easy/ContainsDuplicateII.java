package main.revision.october.meta.easy;

import java.util.HashSet;

public class ContainsDuplicateII {

    class Solution {
        public boolean containsNearbyDuplicate(int[] nums, int k) {

            int startIndex = 0;
            int endIndex = 0;

            HashSet<Integer> set = new HashSet<>();

            while (endIndex < nums.length) {

                if (set.size() > k) {
                    set.remove(nums[startIndex++]);
                }

                if (!set.add(nums[endIndex])) {
                    return true;
                }

                endIndex++;
            }
            return false;
        }
    }

    // revised on 11/23/2025
    class SolutionRevisionThirdDay {
        public boolean containsNearbyDuplicate(int[] nums, int k) {

            int startIndex = 0;
            int endIndex = 0;

            HashSet<Integer> set = new HashSet<>();

            while (endIndex < nums.length) {

                if (set.size() > k) {
                    set.remove(nums[startIndex++]);
                }

                if (!set.add(nums[endIndex])) {
                    return true;
                }

                endIndex++;
            }
            return false;
        }
    }

    // revised on 11/29/2025
    class SolutionRevisionSeventhDay {
        public boolean containsNearbyDuplicate(int[] nums, int k) {

            int start = 0;
            int end = 0;

            HashSet<Integer> set = new HashSet<>();

            while (end < nums.length) {
                if (set.size() > k) {
                    set.remove(nums[start++]);
                }

                if (!set.add(nums[end])) {
                    return true;
                }

                end++;
            }
            return false;
        }
    }
}
