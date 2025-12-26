package main.revision.october.meta.easy;

import java.util.*;

public class FindAllNumbersDisappearedInAnArray {

    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> result = new ArrayList<>();

            // Step 1: Har number ko visit mark karne ke liye uske corresponding index par
            // value ko negate karte hain
            for (int num : nums) {
                int index = Math.abs(num) - 1; // Index nikalte hain (1-based to 0-based conversion)
                if (nums[index] > 0) { // Agar value positive hai, to negate karte hain
                    nums[index] = -nums[index];
                }
            }

            // Step 2: Jo indices positive bache hain, unka matlab hai ki wo numbers missing
            // hain
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) { // Agar value positive hai, to wo index + 1 missing number hai
                    result.add(i + 1);
                }
            }

            return result; // Missing numbers ki list return karte hain
        }
    }

    // revised on 12/20/2025
    class SolutionRevisedOnThirdDay {
        public List<Integer> findDisappearedNumbers(int[] nums) {

            List<Integer> result = new ArrayList<>();

            for (int num : nums) {
                int index = Math.abs(num) - 1;

                if (nums[index] > 0) {
                    nums[index] = -nums[index];
                }
            }

            for (int i = 0; i < nums.length; i++) {

                if (nums[i] > 0) {
                    result.add(i + 1);
                }
            }

            return result;
        }
    }

    // revised on 12/26/2025
    class SolutionRevisedOnDayFourteen {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> result = new ArrayList<>();

            for (int num : nums) {
                int index = Math.abs(num) - 1;
                if (nums[index] > 0) {
                    nums[index] = -nums[index];
                }
            }

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    result.add(i + 1);
                }
            }

            return result;
        }
    }
}
