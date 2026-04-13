package main.revision.march.hard;

import java.util.*;

public class SlidingWindowMaximum {

    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k <= 0)
                return new int[0];
            int n = nums.length;
            if (k > n)
                return new int[0];

            int[] res = new int[n - k + 1];
            Deque<Integer> dq = new ArrayDeque<>(); // stores indices, decreasing by value

            for (int i = 0; i < n; i++) {
                // Remove indices outside the window
                if (!dq.isEmpty() && dq.peekFirst() <= i - k)
                    dq.pollFirst();

                // Maintain decreasing order in deque: remove smaller values from the tail
                while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i])
                    dq.pollLast();

                dq.addLast(i);

                if (i >= k - 1)
                    res[i - k + 1] = nums[dq.peekFirst()];
            }
            return res;
        }
    }
}
