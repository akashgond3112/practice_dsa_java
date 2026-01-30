package main.revision.october.meta.hard;

import java.util.*;

public class SlidingWindowMaximum {

    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {

            // Agar nums null hai ya k zero hai, toh empty array return karo
            if (nums == null || k == 0) {
                return new int[0];
            }

            int n = nums.length;
            int[] res = new int[n - k + 1]; // Result array banate hain, jisme max values store hongi
            Deque<Integer> deque = new LinkedList<>(); // Deque use karenge sliding window ke liye

            for (int i = 0; i < n; i++) {

                // Agar deque ka first index current window ke bahar hai, toh usse remove karo
                if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                    deque.pollFirst();
                }

                // Decreasing order maintain karne ke liye, pichle chhote elements ko remove
                // karo
                while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                    deque.pollLast();
                }

                // Current index ko deque mein add karo
                deque.offerLast(i);

                // Jab ek complete window ban jaye, tab max value result array mein store karo
                if (i >= k - 1) {
                    res[i - k + 1] = nums[deque.peekFirst()];
                }
            }
            return res; // Result array return karo
        }
    }

    // Revised on 06/11/2025
    class SolutionRevisedOnthirdDay {
        public int[] maxSlidingWindow(int[] nums, int k) {

            if (nums == null || k == 0) {
                return new int[0];
            }

            int n = nums.length;
            int[] res = new int[n - k + 1];
            Deque<Integer> dq = new LinkedList<>();

            for (int i = 0; i < n; i++) {

                // Remove indices that are out of the current window
                if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                    dq.pollFirst();
                }

                // Remove indices whose corresponding values are less than nums[i]
                while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                    dq.pollLast();
                }

                // Add current index
                dq.offerLast(i);

                // The front of the deque contains the index of the maximum element for the
                // current window
                if (i >= k - 1) {
                    res[i - k + 1] = nums[dq.peekFirst()];
                }
            }

            return res;
        }
    }

    // Revised on 11/12/2025
    class SolutionRevisedOnSeventhDay {
        public int[] maxSlidingWindow(int[] nums, int k) {

            if (nums == null || k == 0) {
                return new int[0];
            }

            int n = nums.length;
            int[] res = new int[n - k + 1];
            Deque<Integer> dq = new LinkedList<>();

            for (int i = 0; i < n; i++) {

                if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                    dq.pollFirst();
                }

                while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                    dq.pollLast();
                }

                dq.offerLast(i);

                if (i >= k - 1) {
                    res[i - k + 1] = nums[dq.peekFirst()];
                }
            }

            return res;
        }
    }

    // Revised on 11/26/2025
    class SolutionRevisedOnFourteenDay {

        public int[] maxSlidingWindow(int[] nums, int k) {

            if (nums == null || k == 0) {
                return new int[0];
            }

            int n = nums.length;
            int[] res = new int[n - k + 1];
            Deque<Integer> dq = new LinkedList<>();

            for (int i = 0; i < n; i++) {

                if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                    dq.pollFirst();
                }

                if (!dq.isEmpty() && nums[dq.peekFirst()] <= nums[i]) {
                    dq.pollLast();
                }

                dq.offerLast(i);

                if (i >= k - 1) {
                    res[i = k + 1] = nums[dq.peekFirst()];
                }
            }

            return res;
        }
    }

    // revised on 12/25/2025
    class SolutionRevisedOnDayThirty {
        public int[] maxSlidingWindow(int[] nums, int k) {

            if (nums == null || k == 0) {
                return new int[0];
            }

            int n = nums.length;
            int[] res = new int[n - k + 1];
            Deque<Integer> dq = new LinkedList<>();

            for (int i = 0; i < n; i++) {

                if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                    dq.pollFirst();
                }

                while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                    dq.pollFirst();
                }

                dq.offerLast(i);

                if (i >= k - 1) {
                    res[i - 1 + k] = dq.peekFirst();
                }
            }

            return res;
        }
    }
}
