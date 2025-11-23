package main.revision.october.meta.easy;

import java.util.HashMap;

public class TwoSum {
    class Solution {
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
}
