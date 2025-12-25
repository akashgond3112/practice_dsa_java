package main.revision.october.meta.medium;

import java.util.*;

public class ContinuousSubarraySum {

    class Solution {
        public boolean checkSubarraySum(int[] nums, int k) {
            // Step 1: Ek map banate hain jo prefix sum ka remainder store karega
            Map<Integer, Integer> prefixModMap = new HashMap<>();

            // Step 2: Map mein ek initial value daal dete hain (0, -1)
            // taki agar koi subarray start se divisible ho to handle ho jaye
            prefixModMap.put(0, -1);

            int currentSum = 0;

            // Step 3: Array ke har element ko traverse karte hain
            for (int i = 0; i < nums.length; i++) {
                // Current element ko sum mein add karte hain
                currentSum += nums[i];
                // Sum ka remainder nikalte hain k ke saath
                int remainder = currentSum % k;

                // Step 4: Agar remainder negative ho to usse positive banate hain
                if (remainder < 0) {
                    remainder += k;
                }

                // Step 5: Check karte hain ki remainder pehle se map mein hai ya nahi
                if (prefixModMap.containsKey(remainder)) {
                    // Agar hai, to check karte hain ki subarray ka size >= 2 hai ya nahi
                    if (i - prefixModMap.get(remainder) >= 2) {
                        return true; // Agar condition satisfy hoti hai to true return karte hain
                    }
                } else {
                    // Agar remainder map mein nahi hai to usse map mein daal dete hain
                    prefixModMap.put(remainder, i);
                }
            }

            // Step 6: Agar koi valid subarray nahi mila to false return karte hain
            return false;
        }
    }

    // revised on 06/11/2025
    class SolutionRevisionOnThirdDay {
        public boolean checkSubarraySum(int[] nums, int k) {

            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);

            int currentSum = 0;

            for (int i = 0; i < nums.length; i++) {
                currentSum += nums[i];
                int remainder = currentSum % k;

                if (map.containsKey(remainder)) {
                    if (i - map.get(remainder) >= 2) {
                        return true;
                    }
                } else {
                    map.put(remainder, i);
                }
            }

            return false;
        }
    }

    // revised on 11/12/2025
    class SolutionRevisionOnSeventhDay {
        public boolean checkSubarraySum(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);

            int currentSum = 0;

            for (int i = 0; i < nums.length; i++) {

                currentSum += nums[i];
                int remainder = currentSum % k;

                if (map.containsKey(remainder)) {

                    if (i - map.get(remainder) >= 2) {
                        return true;
                    }
                } else {
                    map.put(remainder, i);
                }
            }
            return false;
        }
    }

    // revised on 11/26/2025
    class SolutionRevisionOnFourteenDay {
        public boolean checkSubarraySum(int[] nums, int k) {

            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);

            int currentSum = 0;

            for (int i = 0; i < nums.length; i++) {

                currentSum += nums[i];
                int remainder = currentSum % k;

                if (map.containsKey(remainder)) {

                    if (i - map.get(remainder) >= 2) {
                        return true;
                    }
                } else {
                    map.put(remainder, i);
                }
            }

            return false;
        }
    }

    // revised on 12/25/2025
    class SolutionRevisedOnDayThirty {
        public boolean checkSubarraySum(int[] nums, int k) {

            Map<Integer, Integer> prefixModMap = new HashMap<>();

            prefixModMap.put(0, -1);

            int currentSum = 0;

            for (int i = 0; i < nums.length; i++) {
                currentSum += nums[i];
                int remainder = currentSum % k;

                if (prefixModMap.containsKey(remainder)) {
                    if (i - prefixModMap.get(remainder) >= 2) {
                        return true;
                    }
                } else {
                    prefixModMap.put(remainder, i);
                }
            }

            return false;
        }
    }
}
