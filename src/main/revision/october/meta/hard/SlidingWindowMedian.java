package main.revision.october.meta.hard;

import java.util.*;

public class SlidingWindowMedian {

    class Solution {
        public double[] medianSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k <= 0) {
                return new double[0];
            }

            double[] result = new double[nums.length - k + 1];
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            Map<Integer, Integer> invalid = new HashMap<>();
            int balance = 0; // Track the balance: positive means maxHeap has more valid elements

            for (int i = 0; i < nums.length; i++) {
                // Remove outgoing element (if window is full)
                if (i >= k) {
                    int elementToRemove = nums[i - k];
                    invalid.put(elementToRemove, invalid.getOrDefault(elementToRemove, 0) + 1);

                    // Update balance based on which heap the element conceptually belongs to
                    if (elementToRemove <= maxHeap.peek()) {
                        balance--;
                    } else {
                        balance++;
                    }
                }

                // Add incoming element
                if (maxHeap.isEmpty() || nums[i] <= maxHeap.peek()) {
                    maxHeap.add(nums[i]);
                    balance++;
                } else {
                    minHeap.add(nums[i]);
                    balance--;
                }

                // Rebalance heaps based on balance counter
                if (balance > 1) {
                    minHeap.add(maxHeap.poll());
                    balance -= 2;
                } else if (balance < 0) {
                    maxHeap.add(minHeap.poll());
                    balance += 2;
                }

                // Clean invalid elements from heap tops
                cleanHeaps(maxHeap, minHeap, invalid);

                // Calculate median when window is full
                if (i >= k - 1) {
                    if (k % 2 == 1) {
                        result[i - k + 1] = (double) maxHeap.peek();
                    } else {
                        result[i - k + 1] = ((double) maxHeap.peek() + (double) minHeap.peek()) / 2.0;
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

    // revised on 10/28/2025
    class SolutionRevisionThirdDay {
        Queue<Integer> minHeap;
        Queue<Integer> maxHeap;

        public SolutionRevisionThirdDay() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        }

        private void add(int num) {
            // add element to the correct heap
            if (maxHeap.isEmpty() || maxHeap.peek() > num) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
            // balance heap
            balance();
        }

        private void balance() {
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }

        private void remove(int num) {
            // remove element from the correct heap
            if (num <= maxHeap.peek()) {
                maxHeap.remove(num);
            } else {
                minHeap.remove(num);
            }
            // balance heap
            balance();
        }

        private double findMedian() {
            if (maxHeap.size() > minHeap.size())
                return maxHeap.peek();
            if (minHeap.size() > maxHeap.size())
                return minHeap.peek();
            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        }

        public double[] medianSlidingWindow(int[] nums, int k) {
            int start = 0;
            double[] result = new double[nums.length - k + 1];

            for (int end = 0; end < nums.length; end++) {
                add(nums[end]);
                int size = (end - start + 1);
                if (size == k) {
                    result[start] = findMedian();
                    remove(nums[start]); // remove the start number as we move forward the next window size
                    start++; // move start window ahead
                }
            }

            return result;
        }
    }

    // revised on 04/11/2025
    class SolutionRevisionSeventhDay {

        Queue<Integer> minHeap;
        Queue<Integer> maxHeap;

        SolutionRevisionSeventhDay() {
            this.minHeap = new PriorityQueue<>();
            this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        }

        private void add(int num) {

            if (maxHeap.isEmpty() || maxHeap.peek() > num) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }

            balance();
        }

        public void remove(int num) {

            if (num < maxHeap.peek()) {
                maxHeap.remove(num);
            } else {
                minHeap.remove(num);
            }

            balance();
        }

        public void balance() {
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }

        double findMedian() {

            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }

            if (minHeap.size() > maxHeap.size()) {
                return minHeap.peek();
            }

            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        }

        public double[] medianSlidingWindow(int[] nums, int k) {

            int start = 0;
            double[] result = new double[nums.length - k + 1];

            for (int end = 0; end < nums.length; end++) {
                add(nums[end]);
                int size = end - start + 1;

                if (size == k) {
                    result[start] = findMedian();

                    remove(nums[start]);
                    start++;
                }
            }

            return result;
        }
    }

    // revised on 11/17/2025
    class SolutionRevisionFourteenDay {

        Queue<Integer> minHeap;
        Queue<Integer> maxHeap;

        SolutionRevisionFourteenDay() {
            this.minHeap = new PriorityQueue<>();
            this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        }

        private void add(int num) {

            if (maxHeap.isEmpty() && maxHeap.peek() > num) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
            balance();
        }

        private void remove(int num) {

            if (num <= maxHeap.peek()) {
                maxHeap.remove(num);
            } else {
                minHeap.remove(num);
            }

            balance();
        }

        public void balance() {

            if (maxHeap.size() > minHeap.size() - 1) {
                minHeap.add(maxHeap.poll());
            } else {
                maxHeap.add(minHeap.poll());
            }
        }

        private double findMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }
            if (minHeap.size() > maxHeap.size())
                return minHeap.peek();

            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        }

        public double[] medianSlidingWindow(int[] nums, int k) {

            int start = 0;
            double[] result = new double[nums.length - k + 1];

            for (int end = 0; end < nums.length; end++) {

                add(nums[end]);

                int size = (end - start + 1);

                if (size == k) {

                    result[start] = findMedian();
                    remove(nums[start]);
                    start++;
                }
            }

            return result;
        }

    }

    // revised on 12/16/2025
    class SolutionRevisionThirtyDay {

        Queue<Integer> minHeap;
        Queue<Integer> maxHeap;

        SolutionRevisionThirtyDay() {
            this.minHeap = new PriorityQueue<>();
            this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        }

        public void add(int num) {

            if (maxHeap.isEmpty() || maxHeap.peek() > num) {
                maxHeap.add(num);
            } else {
                maxHeap.add(num);
            }

            balance();
        }

        public void balance() {

            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }

        private void remove(int num) {

            if (num <= maxHeap.peek()) {
                maxHeap.remove(num);
            } else {
                minHeap.remove(num);
            }

            balance();
        }

        private double findMedian() {

            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }

            if (minHeap.size() > maxHeap.size()) {
                return minHeap.peek();
            }

            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        }

        public double[] medianSlidingWindow(int[] nums, int k) {
            int start = 0;
            double[] result = new double[nums.length - k + 1];

            for (int end = 0; end < nums.length; end++) {
                add(nums[end]);
                int size = end - (start + 1);

                if (size == k) {
                    result[start] = findMedian();
                    remove(nums[start]);
                    start++;
                }
            }

            return result;
        }
    }
}
