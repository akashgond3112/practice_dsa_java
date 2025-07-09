/**
 * @author agond
 * @date Jul 09, 2025
 * @time 8:41:23 PM
 */
package main.revision.meta.medium;

import java.util.*;

public class Subsets {

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

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        generateSubsets(0, nums, new ArrayList<>(), subsets);
        return subsets;
    }

}
