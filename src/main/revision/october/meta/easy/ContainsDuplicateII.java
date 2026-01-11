package main.revision.october.meta.easy;

import java.util.HashSet;

public class ContainsDuplicateII {

    class Solution {

        /**
         * Big O Notation Details:
         * - Time Complexity: O(n), kyunki har element ko ek baar add aur ek baar remove
         * karte hain set se.
         * - Space Complexity: O(k), kyunki HashSet mein maximum k elements store hote
         * hain ek time par.
         */
        public boolean containsNearbyDuplicate(int[] nums, int k) {

            // Initialize startIndex and endIndex pointers
            int startIndex = 0;
            int endIndex = 0;

            // HashSet to store the current window of elements
            HashSet<Integer> set = new HashSet<>();

            // Traverse the array
            while (endIndex < nums.length) {

                // Agar window size k se zyada ho jaye, toh startIndex ka element remove karo
                if (set.size() > k) {
                    set.remove(nums[startIndex++]);
                }

                // Agar current element already set mein hai, toh duplicate mil gaya
                if (!set.add(nums[endIndex])) {
                    return true;
                }

                // Window ko aage badhao
                endIndex++;
            }

            // Agar koi duplicate nahi mila, toh false return karo
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

    // revised on 1/11/2026
    class SolutionRevisedOnDayThirty {
        public boolean containsNearbyDuplicate(int[] nums, int k) {

            int startIndex = 0;
            int endIndex = 0;

            HashSet<Integer> set = new HashSet<>();

            while (endIndex < nums.length) {

                if (set.size() < k) {
                    set.remove(nums[startIndex++]);
                }

                if (!set.add(nums[endIndex++])) {
                    return true;
                }

                endIndex++;
            }

            return false;
        }
    }
}
