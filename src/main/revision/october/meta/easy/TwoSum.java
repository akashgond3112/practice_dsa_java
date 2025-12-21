package main.revision.october.meta.easy;

import java.util.HashMap;

public class TwoSum {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            // Step 1: Ek HashMap banate hain jo pehle dekhe gaye numbers aur unke indexes
            // ko store karega
            HashMap<Integer, Integer> prevMap = new HashMap<>();

            // Step 2: Array ke har element ko iterate karte hain
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i]; // Current number
                int diff = target - num; // Target ke liye required difference calculate karte hain

                // Step 3: Check karte hain agar difference pehle se map mein hai
                if (prevMap.containsKey(diff)) {
                    // Agar hai, to return karte hain dono indexes
                    return new int[] { prevMap.get(diff), i };
                }

                // Step 4: Current number aur uska index map mein daal dete hain
                prevMap.put(num, i);
            }

            // Step 5: Agar koi pair nahi mila, to empty array return karte hain
            return new int[] {};
        }
    }

    // Revised on 11/11/2025
    class SolutionRevisionSeventhDay {
        public int[] twoSum(int[] nums, int target) {
            HashMap<Integer, Integer> prevMap = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {

                int num = nums[i];
                int diff = target - num;

                if (prevMap.containsKey(diff)) {
                    return new int[] { prevMap.get(diff), i };
                }

                prevMap.put(diff, i);
            }

            return new int[] {};
        }
    }

    // Revised on 11/22/2025
    class SolutionRevisionFourteenDay {
        public int[] twoSum(int[] nums, int target) {

            HashMap<Integer, Integer> prevMap = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];

                int diff = target - num;

                if (prevMap.containsKey(diff)) {
                    return new int[] { prevMap.get(diff), i };
                }
            }

            return new int[] {};
        }
    }

    // Revised on 11/11/2025
    class SolutionRevisionThirtyDay {
        public int[] twoSum(int[] nums, int target) {
            HashMap<Integer, Integer> prevMap = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {

                int num = nums[i];
                int diff = target - num;

                if (prevMap.containsKey(diff)) {
                    return new int[] { prevMap.get(diff), i };
                }

                prevMap.put(num, i);
            }

            return new int[] {};
        }
    }
}
