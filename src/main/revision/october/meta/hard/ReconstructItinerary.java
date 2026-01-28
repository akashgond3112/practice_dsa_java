package main.revision.october.meta.hard;

import java.util.*;

public class ReconstructItinerary {

    class Solution {
        public List<String> findItinerary(List<List<String>> tickets) {
            // Step 1: Flight map banate hain jisme har source ke liye destinations sorted
            // order mein store hote hain.
            Map<String, PriorityQueue<String>> flightMap = new HashMap<>();
            for (List<String> ticket : tickets) {
                flightMap.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
            }

            // Step 2: Itinerary list aur stack initialize karte hain.
            LinkedList<String> itinerary = new LinkedList<>();
            Deque<String> stack = new ArrayDeque<>();
            stack.push("JFK"); // Starting point hamesha "JFK" hota hai.

            // Step 3: DFS traversal karte hain jab tak stack empty na ho jaye.
            while (!stack.isEmpty()) {
                String from = stack.peek(); // Stack ke top se current location lete hain.
                if (flightMap.containsKey(from) && !flightMap.get(from).isEmpty()) {
                    // Agar current location ke liye koi destination bacha hai, toh usse stack mein
                    // push karte hain.
                    stack.push(flightMap.get(from).poll());
                } else {
                    // Agar koi destination nahi bacha, toh current location ko itinerary mein add
                    // karte hain.
                    itinerary.addFirst(stack.pop());
                }
            }

            // Step 4: Final itinerary return karte hain.
            return itinerary;
        }
    }

    // revised on 02/11/2025
    class SolutionRevisionThirdDay {
        public List<String> findItinerary(List<List<String>> tickets) {

            Map<String, PriorityQueue<String>> flightMap = new HashMap<>();

            for (List<String> ticket : tickets) {

                flightMap.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
            }

            LinkedList<String> iternary = new LinkedList<>();
            Deque<String> dq = new ArrayDeque<>();
            dq.offer("JFK");

            while (!dq.isEmpty()) {
                String from = dq.peek();

                if (flightMap.containsKey(from) && !flightMap.get(from).isEmpty()) {
                    dq.push(flightMap.get(from).poll());
                } else {
                    iternary.addFirst(dq.pop());
                }
            }

            return iternary;

        }
    }

    // revised on 11/8/2025
    class SolutionRevisionSeventhDay {
        public List<String> findItinerary(List<List<String>> tickets) {

            Map<String, PriorityQueue<String>> flightMap = new HashMap<>();

            for (List<String> ticket : tickets) {

                flightMap.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
            }

            LinkedList<String> iternary = new LinkedList<>();
            Deque<String> dq = new ArrayDeque<>();
            dq.add("JFK");

            while (!dq.isEmpty()) {

                String from = dq.peek();

                if (flightMap.containsKey(from) && !flightMap.get(from).isEmpty()) {
                    dq.push(flightMap.get(from).poll());
                } else {
                    iternary.addFirst(dq.pop());
                }
            }

            return iternary;
        }
    }

    // revised on 11/22/2025
    class SolutionRevisionFourteenDay {
        public List<String> findItinerary(List<List<String>> tickets) {

            Map<String, PriorityQueue<String>> map = new HashMap<>();

            for (List<String> ticket : tickets) {
                map.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
            }

            LinkedList<String> iternary = new LinkedList<>();
            Deque<String> dq = new ArrayDeque<>();
            dq.add("JFK");

            while (!dq.isEmpty()) {
                String from = dq.poll();

                if (map.containsKey(from) && !map.get(from).isEmpty()) {
                    dq.push(map.get(from).poll());
                } else {
                    iternary.addFirst(dq.pop());
                }
            }

            return iternary;
        }
    }

    // revised on 12/21/2025
    class SolutionRevisionThirtyDay {

        public List<String> findItinerary(List<List<String>> tickets) {

            Map<String, PriorityQueue<String>> flightMap = new HashMap<>();

            for (List<String> ticket : tickets) {
                flightMap.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
            }

            List<String> iternary = new ArrayList<>();
            Deque<String> dq = new ArrayDeque<>();
            dq.add("JFK");

            while (!dq.isEmpty()) {

                String from = dq.peek();

                if (flightMap.containsKey(from) && !flightMap.get(from).isEmpty()) {

                    dq.add(flightMap.get(from).poll());
                } else {
                    iternary.addFirst(dq.pop());
                }
            }

            return iternary;
        }
    }
}
