package main.revision.october.meta.medium;

import java.util.*;

public class Subsets {

    class Solution {

        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> subsets = new ArrayList<>();
            generateSubsets(0, nums, new ArrayList<>(), subsets);
            return subsets;
        }

        private void generateSubsets(int index, int[] nums, List<Integer> currentSubset, List<List<Integer>> subsets) {
            if (index == nums.length) {
                subsets.add(new ArrayList<>(currentSubset));
                return;
            }

            currentSubset.add(nums[index]);
            generateSubsets(index + 1, nums, currentSubset, subsets);

            currentSubset.remove(currentSubset.size() - 1);
            generateSubsets(index + 1, nums, currentSubset, subsets);

        }
    }

    class SolutionRevisonThirdDay {

        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> subsets = new ArrayList<>();

            generateSubset(0, nums, new ArrayList<>(), subsets);

            return subsets;
        }

        private void generateSubset(int index, int[] nums, List<Integer> currentSubset, List<List<Integer>> subsets) {

            if (index == nums.length) {
                subsets.add(new ArrayList<>(currentSubset));
            }

            currentSubset.add(nums[index]);
            generateSubset(index + 1, nums, currentSubset, subsets);

            currentSubset.remove(currentSubset.size() - 1);
            generateSubset(index + 1, nums, currentSubset, subsets);
        }

    }

    class SolutionRevisonSeventhDay {

        public List<List<Integer>> subsets(int[] nums) {

            List<List<Integer>> subsets = new ArrayList<>();

            generateSubset(0, nums, new ArrayList<>(), subsets);

            return subsets;
        }

        private void generateSubset(int index, int[] nums, List<Integer> currentSubset, List<List<Integer>> subsets) {

            if (index == nums.length) {
                subsets.add(new ArrayList<>(currentSubset));
            }

            currentSubset.add(nums[index]);
            generateSubset(index + 1, nums, currentSubset, subsets);

            currentSubset.remove(currentSubset.size() - 1);
            generateSubset(index + 1, nums, currentSubset, subsets);
        }
    }

    class SolutionRevisonThirtyDay {
        public List<List<Integer>> subsets(int[] nums) {

            List<List<Integer>> subsets = new ArrayList<>();

            generateSubset(0, nums, new ArrayList<>(), subsets);

            return subsets;
        }

        private void generateSubset(int index, int[] nums, List<Integer> currentSubset, List<List<Integer>> subsets) {

            if (index == nums.length) {
                subsets.add(new ArrayList<>(currentSubset));
            }

            currentSubset.add(nums[index]);
            generateSubset(index + 1, nums, currentSubset, subsets);

            currentSubset.remove(currentSubset.size() - 1);
            generateSubset(index + 1, nums, currentSubset, subsets);
        }

    }

    // revised on 1/13/2026
    class SolutionRevisedOnDayThirty {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            generateSubsets(0, nums, new ArrayList<>(), result);
            return result;
        }

        private void generateSubsets(int index, int[] nums, List<Integer> currentSubset, List<List<Integer>> subsets) {

            if (index == nums.length) {
                subsets.add(new ArrayList<>(currentSubset));
                return;
            }

            currentSubset.add(nums[index]);
            generateSubsets(index + 1, nums, currentSubset, subsets);

            currentSubset.removeLast();
            generateSubsets(index + 1, nums, currentSubset, subsets);
        }
    }
}
