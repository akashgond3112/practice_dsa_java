package main.revision.october.meta.medium;

import java.util.*;

public class KthLargestElementInAnArray {

    /**
     * Finds the kth largest element in an array using a min-heap.
     * Time Complexity: O(n log k), where n is the length of nums.
     * Constraints: 1 <= k <= nums.length
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || k < 1 || k > nums.length) {
            throw new IllegalArgumentException("k must be between 1 and the length of nums");
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(k);

        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {

            if (nums[i] > pq.peek()) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }

        return pq.peek();
    }

    // Revised on 18/10/2025
    public int findKthLargestRevisionDayThird(int[] nums, int k) {

        if (nums == null || k < 1 || k > nums.length) {
            return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(k);

        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }

        for (int j = k; j < nums.length; j++) {

            if (nums[j] > pq.peek()) {
                pq.poll();
                pq.offer(nums[j]);
            }
        }

        return pq.peek();
    }

    // Revised on 18/10/2025
    public int findKthLargestRevisionDaySeventh(int[] nums, int k) {

        if (nums == null || k < 1 || k > nums.length) {
            return 0;
        }

        PriorityQueue<Integer> q = new PriorityQueue<>(k);

        for (int i = 0; i < k; i++) {
            q.offer(nums[i]);
        }

        for (int j = k; j < nums.length; j++) {

            if (nums[j] > q.peek()) {
                q.poll();
                q.offer(nums[j]);
            }
        }

        return q.peek();
    }

    // Revised on 11/7/2025
    public int findKthLargestRevisionDayFourteen(int[] nums, int k) {

        if (nums == null || k < 1 || k > nums.length) {
            return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }

        for (int j = k; j < nums.length; j++) {

            if (nums[j] > pq.peek()) {
                pq.poll();
                pq.offer(nums[j]);
            }
        }

        return pq.peek();
    }

    // Revised on 12/6/2025
    public int findKthLargestRevisionDayThirty(int[] nums, int k) {

        if (nums == null || k < 1 || k > nums.length) {
            return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }

        for (int j = k; j < nums.length; j++) {

            if (nums[j] > pq.peek()) {
                pq.poll();
                pq.offer(nums[j]);
            }
        }

        return pq.peek();
    }
}
