package main.revision.october.meta.hard;

import java.util.*;

public class MaximumFrequencyStack {

    public class Solution {

        // Tuple class ka use ek element ke value, frequency aur order ko store karne ke
        // liye hota hai
        class Tuple implements Comparable<Tuple> {

            Integer val; // Element ki value
            Integer freq; // Element ki frequency
            Integer order; // Element ka order (push karte waqt)

            // Constructor jo Tuple object banata hai
            Tuple(Integer val, Integer freq, Integer order) {
                this.val = val;
                this.freq = freq;
                this.order = order;
            }

            // compareTo method ka use heap ke elements ko compare karne ke liye hota hai
            public int compareTo(Tuple other) {
                // Pehle frequency ke basis par compare karo, agar frequency same hai to order
                // ke basis par
                int freqCompare = other.freq.compareTo(this.freq);
                return freqCompare != 0 ? freqCompare : other.order.compareTo(this.order);
            }
        }

        class FreqStack {

            Map<Integer, Integer> map; // Har element ki frequency store karne ke liye
            PriorityQueue<Tuple> heap; // Max-heap jo elements ko frequency aur order ke basis par store karega
            int order; // Elements ke push hone ka order track karne ke liye

            // Constructor jo map, heap aur order initialize karta hai
            public FreqStack() {
                this.map = new HashMap<>();
                this.heap = new PriorityQueue<>();
                this.order = 0;
            }

            // push method ek element ko stack mein daalne ke liye
            public void push(int val) {
                // Element ki frequency ko increment karo
                map.put(val, map.getOrDefault(val, 0) + 1);
                // Heap mein element ko frequency aur order ke saath add karo
                heap.add(new Tuple(val, map.get(val), ++order));
            }

            // pop method stack se sabse zyada frequent element ko nikalne ke liye
            public int pop() {
                // Heap se top element ko nikal lo
                Tuple tuple = heap.poll();
                int val = tuple.val;
                // Map mein us element ki frequency ko decrement karo
                map.put(val, map.get(val) - 1);
                return val; // Element ki value return karo
            }
        }

    }

    // Revised on 12/9/2025
    public class SolutionRevisionOnThirdDay {

        class Tuple implements Comparable<Tuple> {

            Integer val;
            Integer freq;
            Integer order;

            Tuple(Integer val, Integer freq, Integer order) {
                this.val = val;
                this.freq = freq;
                this.order = order;
            }

            public int compareTo(Tuple other) {

                int freqCompare = other.freq.compareTo(this.freq);
                return freqCompare != 0 ? freqCompare : other.order.compareTo(this.order);
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
                while (!heap.isEmpty()) {
                    Tuple tuple = heap.poll();
                    int val = tuple.val;
                    map.put(val, map.get(val) - 1);
                    return val;
                }

                return 0;
            }
        }
    }

    // Revised on 12/9/2025
    public class SolutionRevisionOnSeventhDay {

        class Tuple implements Comparable<Tuple> {
            Integer val;
            Integer freq;
            Integer order;

            Tuple(Integer val, Integer freq, Integer order) {
                this.val = val;
                this.freq = freq;
                this.order = order;
            }

            public int compareTo(Tuple other) {

                int freqCompare = other.freq.compareTo(this.freq);
                return freqCompare != 0 ? freqCompare : other.order.compareTo(this.order);
            }
        }

        class FreqStack {

            Map<Integer, Integer> map; // To store frequency of each element
            PriorityQueue<Tuple> heap; // Max-heap to store elements based on frequency and order
            int order; // To maintain the order of elements pushed

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

    // Revised on 12/9/2025
    public class SolutionRevisionOnFourteenDay {

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
                int freqCompare = other.freq.compareTo(this.freq);
                return freqCompare != 0 ? freqCompare : other.order.compareTo(this.order);
            }
        }

        class FreqStack {
            Map<Integer, Integer> map;
            PriorityQueue<Tuple> pq;
            int order;

            public FreqStack() {
                this.map = new HashMap<>();
                this.pq = new PriorityQueue<>();

            }

            public void push(int val) {

                map.put(val, map.getOrDefault(val, 0) + 1);
                pq.offer(new Tuple(val, map.get(val), ++order));
            }

            public int pop() {
                Tuple tuple = pq.poll();
                int val = tuple.val;

                map.put(val, map.getOrDefault(val, 0) - 1);
                return val;
            }
        }

    }
}
