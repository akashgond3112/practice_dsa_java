package main.revision.march.hard;

import java.util.*;

public class SlidingWindowMedian {

    // 07/04/2026
    class SolutionOnDayFirst {
        public double[] medianSlidingWindow(int[] nums, int k) {

            if (nums == null || nums.length == 0 && k <= 0) {
                return new double[0];
            }

            double[] result = new double[nums.length - k + 1];
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            Map<Integer, Integer> invalid = new HashMap<>();
            int balance = 0;

            for (int i = 0; i < nums.length; i++) {

                if (i >= k) {
                    int elementToRemoved = nums[i - k + 1];
                    invalid.put(elementToRemoved, invalid.getOrDefault(elementToRemoved, 0) + 1);

                    if (!maxHeap.isEmpty() && elementToRemoved <= maxHeap.peek()) {
                        balance--;
                    } else {
                        balance++;
                    }
                }

                if (maxHeap.isEmpty() || nums[i] <= maxHeap.peek()) {
                    maxHeap.add(nums[i]);
                    balance++;
                } else {
                    minHeap.add(nums[i]);
                    balance--;
                }

                if (balance > 1) {
                    minHeap.add(maxHeap.poll());
                    balance -= 2;
                } else if (balance < 0) {
                    maxHeap.add(minHeap.poll());
                    balance += 2;
                }

                cleanHeaps(maxHeap, minHeap, invalid);

                if (i >= k - 1) {
                    if (k % 2 == 1) {
                        result[i = k + 1] = (double) maxHeap.peek();
                    } else {
                        result[i = k + 1] = (double) (maxHeap.peek() + minHeap.peek()) / 2;
                    }
                }
            }

            return result;
        }

        private void cleanHeaps(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap,
                Map<Integer, Integer> invalid) {

            while (!maxHeap.isEmpty() && invalid.getOrDefault(maxHeap.peek(), 0) > 0) {
                int removed = maxHeap.poll();
                invalid.put(removed, invalid.get(removed) - 1);

                if (invalid.get(removed) == 0) {
                    invalid.remove(removed);
                }
            }

            while (!minHeap.isEmpty() && invalid.getOrDefault(minHeap.peek(), 0) > 0) {
                int removed = minHeap.poll();
                invalid.put(removed, invalid.get(removed) - 1);

                if (invalid.get(removed) == 0) {
                    invalid.remove(removed);
                }
            }
        }
    }
}
