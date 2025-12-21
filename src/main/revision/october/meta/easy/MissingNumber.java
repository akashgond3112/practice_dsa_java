package main.revision.october.meta.easy;

public class MissingNumber {

    class Solution {
        public int missingNumber(int[] nums) {

            // Initialize a variable to store the XOR result
            int xor = 0;

            // XOR all numbers from 0 to nums.length
            // This ensures we include all numbers in the range [0, n]
            for (int i = 0; i <= nums.length; i++) {
                xor = xor ^ i;
            }

            // XOR all numbers in the input array
            // This cancels out all numbers that are present in both the range and the array
            for (int num : nums) {
                xor = xor ^ num;
            }

            // The remaining value in xor is the missing number
            return xor;
        }
    }
}
