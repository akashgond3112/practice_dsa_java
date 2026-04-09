package main.revision.march.hard;

import java.util.*;

public class ReconstructItinerary {

    // 09/04/2026
    class SolutionRevisedOnDayFirst {
        public List<String> findItinerary(List<List<String>> tickets) {

            Map<String, PriorityQueue<String>> map = new HashMap<>();

            for (List<String> ticket : tickets) {
                map.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
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
}
