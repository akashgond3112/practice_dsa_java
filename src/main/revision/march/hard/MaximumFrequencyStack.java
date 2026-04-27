package main.revision.march.hard;

import java.util.*;

public class MaximumFrequencyStack {

    public class Solution {

        class Tuple implements Comparable<Tuple> {
            Integer val;
            Integer freq;
            Integer order;

            Tuple(Integer val, Integer freq, Integer order) {

                this.val = val;
                this.freq = freq;
                this.order = order;
            }

            @Override
            public int compareTo(Tuple other) {

                int curFreq = other.freq.compareTo(this.freq);

                return freq != 0 ? curFreq : other.order.compareTo(this.order);
            }
        }

        class FreqStack {
            Map<Integer, Integer> map;
            PriorityQueue<Tuple> heap;
            int order;

            public FreqStack() {
                this.map = new HashMap<>();
                this.heap = new PriorityQueue<>();
                this.order = 0;
            }

            public void push(int val) {

                map.put(val, map.getOrDefault(val, 0) + 1);

                heap.add(new Tuple(val, map.get(val), ++order));
            }

            public int pop() {

                Tuple tuple = heap.poll();
                int val = tuple.val;

                map.put(val, map.get(val) - 1);
                return val;
            }
        }
    }
}
