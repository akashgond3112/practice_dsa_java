package main.revision.october.meta.easy;

public class MissingNumber {

    class Solution {
        public int missingNumber(int[] nums) {

            // Ek variable initialize karte hain XOR result store karne ke liye
            int xor = 0;

            // 0 se nums.length tak ke saare numbers ko XOR karte hain
            // Yeh ensure karta hai ki range [0, n] ke saare numbers include ho
            for (int i = 0; i <= nums.length; i++) {
                xor = xor ^ i;
            }

            // Input array ke saare numbers ko XOR karte hain
            // Yeh un numbers ko cancel kar deta hai jo range aur array dono mein hain
            for (int num : nums) {
                xor = xor ^ num;
            }

            // XOR mein jo value bachi hai, wahi missing number hai
            return xor;
        }
    }

    // revision on 12/24/2025
    class SolutionRevisedOnThirdDay {
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
