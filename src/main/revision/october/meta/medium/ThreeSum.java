package main.revision.october.meta.medium;

import java.util.*;

public class ThreeSum {

    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {

            // Step 1: Array ko sort karo taki two-pointer approach use kar sako
            Arrays.sort(nums);

            // Step 2: Ek set banao duplicate triplets ko avoid karne ke liye
            Set<List<Integer>> set = new HashSet<>();
            int n = nums.length;

            // Step 3: Har element ke liye loop chalao
            for (int i = 0; i < n - 2; i++) {

                // Step 4: Duplicate elements ko skip karo redundant calculations avoid karne ke
                // liye
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                // Step 5: Two-pointer approach ke liye do pointers set karo
                int left = i + 1;
                int right = n - 1;

                // Step 6: Jab tak left pointer right pointer se chhota hai, iterate karo
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];

                    // Step 7: Agar sum zero hai, triplet ko set mein add karo
                    if (sum == 0) {
                        set.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        left++;
                        right--;

                        // Step 8: Left pointer ke duplicate elements ko skip karo
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        // Step 9: Right pointer ke duplicate elements ko skip karo
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }

                    }
                    // Step 10: Agar sum zero se chhota hai, left pointer ko aage badhao
                    else if (sum < 0) {
                        left++;
                    }
                    // Step 11: Agar sum zero se bada hai, right pointer ko peeche le jao
                    else {
                        right--;
                    }
                }
            }

            // Step 12: Set ko list mein convert karo aur return karo
            return new ArrayList<>(set);
        }
    }

    // revised on 12/14/2025
    class SolutionRevisedOnThirdDay {
        public List<List<Integer>> threeSum(int[] nums) {

            Arrays.sort(nums);
            Set<List<Integer>> set = new HashSet<>();

            int n = nums.length;

            for (int i = 0; i < n; i++) {

                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int left = i + 1;
                int right = n - 1;

                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];

                    if (sum == 0) {
                        set.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        left++;
                        right--;

                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }

                    } else if (sum < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }

            return new ArrayList<>(set);
        }
    }

    // revised on 12/20/2025
    class SolutionRevisedOnSeventhDay {
        public List<List<Integer>> threeSum(int[] nums) {

            Arrays.sort(nums);
            Set<List<Integer>> set = new HashSet<>();

            int n = nums.length;

            for (int i = 0; i < n; i++) {

                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int left = i + 1;
                int right = n - 1;

                while (left < right) {

                    int sum = nums[i] + nums[left] + nums[right];

                    if (sum == 0) {
                        set.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        left++;
                        right--;

                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }

                    } else if (sum < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }

            return new ArrayList<>(set);
        }
    }

    // revised on 1/3/2026
    class SolutionRevisedOnDayFourteen {
        public List<List<Integer>> threeSum(int[] nums) {

            Arrays.sort(nums);

            Set<List<Integer>> set = new HashSet<>();
            int n = nums.length;

            for (int i = 0; i < n - 2; i++) {

                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int left = i + 1;
                int right = n - 1;

                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];

                    if (sum == 0) {
                        set.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        left++;
                        right--;

                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }

                    } else if (sum < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            return new ArrayList<>(set);
        }
    }
}
