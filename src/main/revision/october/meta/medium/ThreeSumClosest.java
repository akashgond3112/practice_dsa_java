package main.revision.october.meta.medium;

import java.util.Arrays;

public class ThreeSumClosest {

    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            // Step 1: Array ko sort kar lo taaki hum two-pointer approach use kar saken
            Arrays.sort(nums);
            // Step 2: Pehle teen elements ka sum le lo as initial result
            int resultSum = nums[0] + nums[1] + nums[2];

            int n = nums.length;

            // Step 3: Har element ke liye loop chalao (except last two elements)
            for (int i = 0; i < n - 2; i++) {

                // Step 4: Two-pointer initialize karo, ek left aur ek right
                int left = i + 1;
                int right = n - 1;

                // Step 5: Jab tak left pointer right pointer se chhota hai, loop chalao
                while (left < right) {
                    // Step 6: Current triplet ka sum calculate karo
                    int sum = nums[i] + nums[left] + nums[right];

                    // Step 7: Agar sum target ke barabar hai, to wahi closest hoga
                    if (sum == target) {
                        return target;
                    } else if (sum < target) {
                        // Step 8: Agar sum target se chhota hai, to left pointer badhao
                        left++;
                    } else {
                        // Step 9: Agar sum target se bada hai, to right pointer ghatao
                        right--;
                    }

                    // Step 10: Agar current sum aur target ka difference kam hai resultSum se, to
                    // result update karo
                    if (Math.abs(sum - target) < Math.abs(resultSum - target)) {
                        resultSum = sum;
                    }

                }
            }

            // Step 11: Closest sum return karo
            return resultSum;
        }
    }

    // Revised on 12/16/2025
    class SolutionRevisedOnThirdDay {
        public int threeSumClosest(int[] nums, int target) {

            Arrays.sort(nums);

            int resultSum = nums[0] + nums[1] + nums[2];

            int n = nums.length;

            for (int i = 0; i < n - 2; i++) {
                int left = i + 1;
                int right = n - 1;

                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];

                    if (sum == target) {
                        return target;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }

                    if (Math.abs(sum - target) < Math.abs(resultSum - target)) {
                        resultSum = sum;
                    }
                }
            }

            return resultSum;
        }
    }

    // Revised on 12/22/2025
    class SolutionRevisedOnSeventhDay {
        public int threeSumClosest(int[] nums, int target) {

            Arrays.sort(nums);
            int resultSum = nums[0] + nums[1] + nums[2];

            int n = nums.length;

            for (int i = 0; i < n - 2; i++) {

                int left = 0;
                int right = n - 1;

                while (left < right) {

                    int sum = nums[i] + nums[left] + nums[right];

                    if (sum == target) {
                        return target;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }

                    if (Math.abs(sum - target) < Math.abs(resultSum - target)) {
                        resultSum = sum;
                    }
                }
            }

            return resultSum;
        }
    }

    // revised on 1/5/2026
    class SolutionRevisedOnDayFourteen {
        public int threeSumClosest(int[] nums, int target) {

            Arrays.sort(nums);

            int resultSum = nums[0] + nums[1] + nums[2];
            int n = nums.length;

            for (int i = 0; i < n - 2; i++) {

                int left = i + 1;
                int right = n - 1;

                while (left < right) {

                    int sum = nums[i] + nums[left] + nums[right];

                    if (sum == target) {
                        return target;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }

                    if (Math.abs(sum - target) < Math.abs(resultSum - target)) {
                        resultSum = sum;
                    }
                }
            }

            return resultSum;
        }
    }
}
