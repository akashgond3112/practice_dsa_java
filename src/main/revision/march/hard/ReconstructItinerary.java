package main.revision.march.hard;

import java.util.*;

public class ReconstructItinerary {

    // 09/04/2026
    class SolutionRevisedOnDayFirst {
        public List<String> findItinerary(List<List<String>> tickets) {

            Map<String, PriorityQueue<String>> map = new HashMap<>();

            for (List<String> ticket : tickets) {
                String from = ticket.get(0);
                String to = ticket.get(1);

                // If airport not in map, create new PriorityQueue
                if (!map.containsKey(from)) {
                    map.put(from, new PriorityQueue<>());
                }

                // Add destination to the queue
                map.get(from).add(to);
            }

            List<String> iternary = new LinkedList<>();
            Stack<String> stack = new Stack<>();

            stack.push("JFK");

            while (!stack.isEmpty()) {

                String from = stack.peek();

                if (map.containsKey(from) && !map.get(from).isEmpty()) {
                    stack.push(map.get(from).poll());
                } else {
                    iternary.add(stack.pop());
                }
            }

            return iternary;
        }
    }

    // 12/04/2026
    class SolutionRevisedOnDayThird {
        public List<String> findItinerary(List<List<String>> tickets) {

            Map<String, PriorityQueue<String>> map = new HashMap<>();

            for (List<String> ticket : tickets) {
                String from = ticket.get(0);
                String to = ticket.get(1);

                // If airport not in map, create new PriorityQueue
                if (!map.containsKey(from)) {
                    map.put(from, new PriorityQueue<>());
                }

                // Add destination to the queue
                map.get(from).add(to);
            }

            List<String> iternary = new LinkedList<>();
            Stack<String> stack = new Stack<>();
            stack.push("JFK");

            while (!stack.isEmpty()) {

                String from = stack.peek();

                if (map.containsKey(from) && !map.get(from).isEmpty()) {
                    stack.push(map.get(from).poll());
                } else {
                    iternary.add(stack.pop());
                }
            }

            return iternary;

        }
    }

    // 18/04/2026
    class SolutionRevisedOnDaySeventh {
        public List<String> findItinerary(List<List<String>> tickets) {

            Map<String, PriorityQueue<String>> map = new HashMap<>();

            for (List<String> ticket : tickets) {
                String from = ticket.get(0);
                String to = ticket.get(1);

                // If airport not in map, create new PriorityQueue
                if (!map.containsKey(from)) {
                    map.put(from, new PriorityQueue<>());
                }

                // Add destination to the queue
                map.get(from).add(to);
            }

            List<String> iternary = new LinkedList<>();
            Stack<String> st = new Stack<>();
            st.push("JFK");

            while (!st.isEmpty()) {

                String from = st.peek();

                if (map.containsKey(from) && !map.get(from).isEmpty()) {
                    st.push(map.get(from).poll());
                } else {
                    iternary.add(st.pop());
                }
            }

            return iternary;
        }
    }
}
