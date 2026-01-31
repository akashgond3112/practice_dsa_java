package main.revision.october.meta.medium;

public class SearchInRotatedSortedArray {
    class Solution {
        public int search(int[] nums, int target) {

            // Step 1: Do pointers initialize karo, ek start (l) aur ek end (r) ke liye.
            int l = 0;
            int r = nums.length - 1; // r ko nums.length - 1 hona chahiye

            // Step 2: Jab tak left pointer right se chhota ya barabar hai, loop chalao.
            while (l <= r) {

                // Step 3: Middle element ka index nikalo.
                int mid = l + (r - l) / 2; // Overflow se bachne ke liye (l+r)/2 se behtar hai

                // Step 4: Agar middle element hi target hai, to uska index return kardo.
                if (nums[mid] == target) {
                    return mid;
                }

                // Step 5: Check karo ki left half [l...mid] sorted hai ya nahi.
                if (nums[l] <= nums[mid]) {
                    // Agar left half sorted hai, to check karo target is range me hai ya nahi.
                    if (target >= nums[l] && target < nums[mid]) {
                        // Agar hai, to right half ko discard kardo.
                        r = mid - 1;
                    } else {
                        // Nahi to, left half ko discard kardo.
                        l = mid + 1;
                    }
                }
                // Step 6: Agar left half sorted nahi hai, to right half [mid...r] zaroor sorted
                // hoga.
                else {
                    // Check karo ki target right sorted half ki range me hai ya nahi.
                    if (target > nums[mid] && target <= nums[r]) {
                        // Agar hai, to left half ko discard kardo.
                        l = mid + 1;
                    } else {
                        // Nahi to, right half ko discard kardo.
                        r = mid - 1;
                    }
                }
            }

            // Step 7: Agar loop khatam ho gaya aur target nahi mila, to -1 return kardo.
            return -1;
        }
    }

    // revised on 12/13/2025
    class SolutionRevisedOnThirdDay {
        public int search(int[] nums, int target) {

            int l = 0;
            int r = nums.length - 1;

            while (l < r) {
                int mid = l + (r - l) / 2;

                if (nums[mid] == target) {
                    return mid;
                }

                if (nums[l] <= nums[mid]) {
                    if (target >= nums[l] && target < nums[mid]) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    if (target <= nums[r] && target > nums[mid]) {
                        l = mid - 1;
                    } else {
                        r = mid + 1;
                    }
                }
            }

            return -1;
        }
    }

    // revised on 12/19/2025
    class SolutionRevisedOnSeventhDay {
        public int search(int[] nums, int target) {

            int l = 0;
            int r = nums.length;

            while (l <= r) {

                int mid = l + (r - l) / 2;

                if (nums[mid] == target) {
                    return target;
                }

                if (nums[l] <= nums[mid]) {
                    if (target >= nums[l] && target < nums[mid]) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    if (target <= nums[r] && target > nums[mid]) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;

                    }
                }
            }
            return -1;
        }
    }

    // revised on 1/2/2026
    class SolutionRevisedOnFourteenDay {
        public int search(int[] nums, int target) {

            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {

                int mid = left + (right - left) / 2;

                if (nums[mid] == target) {
                    return mid;
                }

                if (nums[left] <= nums[mid]) {
                    if (target >= nums[left] && target < nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    if (target <= nums[right] && target > nums[mid]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }

            return -1;
        }
    }

    // revised on 1/31/2026
    class SolutionRevisedOnDayThirty {
        public int search(int[] nums, int target) {

            int l = 0;
            int r = nums.length - 1;

            while (l <= r) {

                int mid = l + (r - l) / 2;

                if (nums[mid] == target) {
                    return mid;
                }

                if (nums[l] <= nums[mid]) {

                    if (target >= nums[l] && target < nums[mid]) {

                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    if (target <= nums[r] && target > nums[mid]) {
                        l = mid + 1;

                    } else {
                        r = mid - 1;
                    }
                }
            }

            return -1;
        }
    }
}
