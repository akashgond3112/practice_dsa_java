package main.revision.october.meta.medium;

public class CuttingRibbons {

    // Yeh Solution class hai jo maximum ribbon length nikalta hai
    public static class Solution {

        // getMaxLength function maximum length find karta hai jisse k ribbons cut ho
        // sake
        public static int getMaxLength(int[] nums, int k) {

            int maxLength = 0;
            // Sare ribbons ki total length nikal rahe hain
            for (int num : nums) {
                maxLength += num;
            }

            int left = 1; // Minimum possible length
            int right = maxLength; // Maximum possible length

            // Binary search use kar rahe hain best length find karne ke liye
            while (left <= right) {

                int mid = left + (right - left) / 2;

                // Agar mid length se kya usse zyada ribbons cut ho sakte hain toh left ko badha
                // do
                if (cutRibbons(nums, k, mid)) {
                    left = mid + 1;
                } else {
                    // Nahi toh right ko kam kar do
                    right = mid - 1;
                }
            }

            // Final answer right mein hoga
            return right;
        }

        // Yeh function check karta hai ki given length se k ribbons cut ho sakte hain
        // ya nahi
        private static boolean cutRibbons(int[] nums, int k, int mid) {
            int count = 0;

            // Har ribbon ko mid length mein cut kar ke count badha rahe hain
            for (int num : nums) {
                count += num / mid;
            }

            // Agar count k ya usse zyada hai toh true return karo
            return count >= k;
        }
    }

    // revision on 12/8/2025
    public static class SolutionRevisedOnThirdDay {

        public static int getMaxLength(int[] nums, int k) {

            int maxLength = 0;
            for (int num : nums) {
                maxLength += num;
            }

            int left = 1;
            int right = maxLength;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (cutRibbons(nums, k, mid)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return right;
        }

        private static boolean cutRibbons(int[] nums, int k, int mid) {

            int count = 0;
            for (int num : nums) {
                count += num / mid;
            }

            return count >= k;
        }
    }

    // revision on 12/14/2025
    public static class SolutionRevisedOnSeventhDay {

        public static int getMaxLength(int[] nums, int k) {

            int maxLength = 0;
            for (int num : nums) {
                maxLength += num;
            }

            int left = 1;
            int right = maxLength;

            while (left <= right) {

                int mid = left + (right - left) / 2;

                if (cutRibbons(nums, k, mid)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return right;
        }

        private static boolean cutRibbons(int[] nums, int k, int mid) {
            int count = 0;

            for (int num : nums) {
                count += num / mid;
            }

            return count >= k;
        }
    }

    // revision on 12/28/2025
    public static class SolutionRevisedOnFourteenDay {

        public static int getMaxLength(int[] nums, int k) {

            int left = 1;
            int right = 0;

            for (int num : nums) {
                right += num;
            }

            while (left <= right) {

                int mid = (right - left) + left / 2;

                if (cutRibbons(nums, k, mid)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return right;
        }

        private static boolean cutRibbons(int[] nums, int k, int mid) {
            int count = 0;

            for (int num : nums) {
                count += num / mid;
            }

            return count >= k;
        }
    }
}
