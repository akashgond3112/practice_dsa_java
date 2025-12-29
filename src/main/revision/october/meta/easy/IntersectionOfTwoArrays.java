package main.revision.october.meta.easy;

import java.util.*;

public class IntersectionOfTwoArrays {

    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            // Use single HashSet for O(1) lookup - optimal approach
            // Time: O(n + m), Space: O(min(n, m))
            Set<Integer> set = new HashSet<>();
            List<Integer> result = new ArrayList<>();

            // Add all elements from nums1 to set - O(n)
            for (int num : nums1) {
                set.add(num);
            }

            // Check intersection and remove from set to avoid duplicates - O(m)
            for (int num : nums2) {
                if (set.remove(num)) {
                    result.add(num);
                }
            }

            // Convert to array - O(k) where k is intersection size
            return result.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    // revised on 12/23/2025
    class SolutionRevisedOnTirdDay {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = new HashSet<>();
            List<Integer> result = new ArrayList<>();

            for (int num : nums1) {
                set.add(num);
            }

            for (int num : nums2) {
                if (set.remove(num)) {
                    result.add(num);
                }
            }

            return result.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    // revised on 12/29/2025
    class SolutionRevisedOnSeventhDay {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = new HashSet<>();
            List<Integer> result = new ArrayList<>();

            for (int num : nums1) {
                set.add(num);
            }

            for (int num : nums2) {
                if (set.remove(num)) {
                    result.add(num);
                }
            }

            return result.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
