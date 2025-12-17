package main.revision.october.meta.easy;

import java.util.*;

public class FindAllNumbersDisappearedInAnArray {

    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> result = new ArrayList<>();
            
            // Mark numbers as visited by negating the value at their corresponding index
            for (int num : nums) {
                int index = Math.abs(num) - 1;
                if (nums[index] > 0) {
                    nums[index] = -nums[index];
                }
            }
            
            // Collect indices of positive numbers as they represent missing numbers
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    result.add(i + 1);
                }
            }
            
            return result;
        }
    }
}
