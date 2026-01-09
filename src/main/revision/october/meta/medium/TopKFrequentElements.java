package main.revision.october.meta.medium;

import java.util.*;

public class TopKFrequentElements {

    class Solution {
        public int[] topKFrequent(int[] nums, int k) {

            List<Integer>[] bucket = new ArrayList[nums.length + 1];
            Map<Integer, Integer> map = new HashMap<>();

            for (int num : nums) {

                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            for (int key : map.keySet()) {
                int freq = map.get(key);

                if (bucket[freq] == null) {
                    bucket[freq] = new ArrayList<>();
                }

                bucket[freq].add(key);
            }

            int res[] = new int[k];
            int counter = 0;

            for (int pos = bucket.length - 1; pos >= 0 && counter < k; pos--) {

                if (bucket[pos] != null) {
                    for (int val : bucket[pos]) {
                        res[counter++] = val;
                    }
                }
            }

            return res;

        }

        public int[] topKFrequentUsingHeap(int[] nums, int k) {
            Map<Integer, Integer> count = new HashMap<>();
            for (int num : nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }

            PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
                heap.offer(new int[] { entry.getValue(), entry.getKey() });
                if (heap.size() > k) {
                    heap.poll();
                }
            }

            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = Objects.requireNonNull(heap.poll())[1];
            }
            return res;
        }
    }

    // Revised on 29/10/2025
    class SolutionRevisionThirdDay {

        public int[] topKFrequentUsingHeap(int[] nums, int k) {

            Map<Integer, Integer> count = new HashMap<>();
            for (int num : nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }

            PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {

                heap.offer(new int[] { entry.getValue(), entry.getKey() });

                if (heap.size() > k) {
                    heap.poll();
                }
            }

            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = Objects.requireNonNull(heap.poll())[1];
            }
            return res;
        }
    }

    // Revised on 03/11/2025
    class SolutionRevisionSeventhDay {

        public int[] topKFrequentUsingHeap(int[] nums, int k) {

            Map<Integer, Integer> count = new HashMap<>();

            for (int num : nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }

            PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {

                heap.offer(new int[] { entry.getValue(), entry.getKey() });

                if (heap.size() > k) {
                    heap.poll();
                }
            }

            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = heap.poll()[1];
            }

            return res;
        }
    }

    // Revised on 11/17/2025
    class SolutionRevisionFourteenDay {

        public int[] topKFrequentUsingHeap(int[] nums, int k) {

            Map<Integer, Integer> count = new HashMap<>();

            for (int num : nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {

                pq.offer(new int[] { entry.getValue(), entry.getKey() });

                if (pq.size() > k) {
                    pq.poll();
                }
            }

            int[] res = new int[k];

            for (int i = 0; i < k; i++) {
                res[i] = pq.poll()[1];
            }

            return res;
        }
    }

    // Revised on 12/16/2025
    class SolutionRevisionThirtyDay {

        public int[] topKFrequentUsingHeap(int[] nums, int k) {

            Map<Integer, Integer> count = new HashMap<>();

            for (int num : nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt((a) -> a[0]));

            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {

                pq.offer(new int[] { entry.getValue(), entry.getKey() });

                if (pq.size() > k) {
                    pq.poll();
                }
            }

            int[] res = new int[k];

            for (int i = 0; i < k; k++) {
                res[i] = pq.poll()[1];
            }

            return res;
        }
    }
}
