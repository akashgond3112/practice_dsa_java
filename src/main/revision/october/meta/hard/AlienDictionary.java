package main.revision.october.meta.hard;

import java.util.*;

public class AlienDictionary {

    public class Solution {
        public String foreignDictionary(String[] words) {

            // Adjacency list to represent the character dependency graph.
            // Key: character, Value: set of characters that must come after the key.
            Map<Character, Set<Character>> adj = new HashMap<>();
            // Map to store the in-degree of each character (node).
            // In-degree is the number of incoming edges.
            Map<Character, Integer> inDegree = new HashMap<>();

            // Initialize the graph with all unique characters from the words.
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    // Add each character to the adjacency list with an empty set of dependencies.
                    adj.putIfAbsent(c, new HashSet<>());
                    // Initialize the in-degree of each character to 0.
                    inDegree.putIfAbsent(c, 0);
                }
            }

            // Build the graph by comparing adjacent words to find ordering rules.
            for (int i = 0; i < words.length - 1; i++) {
                String w1 = words[i];
                String w2 = words[i + 1];

                int minLen = Math.min(w1.length(), w2.length());

                // Check for invalid order: e.g., ["abc", "ab"].
                // If a longer word is a prefix of a shorter word, the dictionary is invalid.
                if (w1.length() > w2.length() && w1.startsWith(w2)) {
                    return ""; // Return empty string for invalid input.
                }

                // Find the first differing character between two adjacent words.
                for (int j = 0; j < minLen; j++) {
                    char charWd1 = w1.charAt(j);
                    char charWd2 = w2.charAt(j);

                    // If characters are different, we found an ordering rule.
                    if (charWd1 != charWd2) {
                        // The rule is charWd1 -> charWd2.
                        // Add an edge from charWd1 to charWd2 if it doesn't already exist.
                        if (adj.get(charWd1).add(charWd2)) {
                            // If the edge was added, increment the in-degree of the destination character.
                            inDegree.put(charWd2, inDegree.get(charWd2) + 1);
                        }
                        // Once the first difference is found, the rest of the characters in the words
                        // do not provide any definitive ordering information, so we break.
                        break;
                    }
                }
            }

            // Queue for topological sort (Kahn's algorithm). It will store nodes with an
            // in-degree of 0.
            Queue<Character> q = new LinkedList<>();

            // Add all characters with an in-degree of 0 to the queue.
            // These are the starting points of our sorted order.
            for (char c : inDegree.keySet()) {
                if (inDegree.get(c) == 0) {
                    q.offer(c);
                }
            }

            // StringBuilder to build the result string which represents the character
            // order.
            StringBuilder sb = new StringBuilder();

            // Process the queue until it's empty.
            while (!q.isEmpty()) {
                // Dequeue a character. This character is next in the topological order.
                char c = q.poll();
                // Append it to the result.
                sb.append(c);

                // For each neighbor of the dequeued character...
                for (char neighbor : adj.get(c)) {
                    // ...decrement its in-degree because we have "visited" its prerequisite 'c'.
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);

                    // If a neighbor's in-degree becomes 0, it means all its prerequisites are met.
                    if (inDegree.get(neighbor) == 0) {
                        // Add it to the queue to be processed.
                        q.offer(neighbor);
                    }
                }
            }

            // After the loop, if the result length is not equal to the number of unique
            // characters,
            // it means there was a cycle in the graph, and a valid order is not possible.
            if (sb.length() != inDegree.size()) {
                return ""; // A cycle was detected.
            }

            // Return the topologically sorted order of characters.
            return sb.toString();
        }
    }

    public class SolutionRevisionThirdDay {

        public String foreignDictionary(String[] words) {

            Map<Character, Set<Character>> adj = new HashMap<>();
            Map<Character, Integer> inDegree = new HashMap<>();

            for (String word : words) {
                for (char c : word.toCharArray()) {

                    adj.put(c, new HashSet<>());
                    inDegree.put(c, 0);
                }
            }

            for (int i = 0; i < words.length; i++) {
                String w1 = words[i];
                String w2 = words[i + 1];

                int minLen = Math.min(w1.length(), w2.length());

                if (w1.length() > w2.length() && w1.startsWith(w2)) {
                    return "";
                }

                for (int j = 0; j < minLen; j++) {
                    char charWd1 = w1.charAt(j);
                    char charWd2 = w2.charAt(j);

                    if (charWd1 != charWd2) {
                        if (adj.get(charWd1).add(charWd2)) {
                            inDegree.put(charWd2, inDegree.get(charWd2) + 1);
                        }
                        break;
                    }
                }
            }

            Queue<Character> q = new LinkedList<>();

            for (char c : inDegree.keySet()) {
                if (inDegree.get(c) == 0) {
                    q.offer(c);
                }
            }

            StringBuilder sb = new StringBuilder();

            while (!q.isEmpty()) {

                char c = q.poll();

                sb.append(c);

                for (char neighbour : adj.get(c)) {

                    inDegree.put(neighbour, inDegree.get(neighbour) - 1);

                    if (inDegree.get(neighbour) == 0) {
                        q.offer(neighbour);
                    }
                }
            }

            if (sb.length() != inDegree.size()) {
                return ""; // A cycle was detected.
            }

            return sb.toString();
        }
    }
}
