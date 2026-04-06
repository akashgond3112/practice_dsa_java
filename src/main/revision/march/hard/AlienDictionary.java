package main.revision.march.hard;

import java.util.*;

public class AlienDictionary {

    // 26/03/2026
    public class SolutionOnDayFirst {
        public String foreignDictionary(String[] words) {

            Map<Character, Set<Character>> adj = new HashMap<>();

            Map<Character, Integer> indegree = new HashMap<>();

            for (String word : words) {
                for (char c : word.toCharArray()) {
                    adj.putIfAbsent(c, new HashSet<>());
                    indegree.put(c, 0);
                }
            }

            for (int i = 0; i < words.length - 1; i++) {

                String w1 = words[i];
                String w2 = words[i + 1];

                int min = Math.min(w1.length(), w2.length());

                if (w1.length() > w2.length() && w1.startsWith(w2)) {
                    return "";
                }

                for (int j = 0; j < min; j++) {
                    char c1 = w1.charAt(j);
                    char c2 = w2.charAt(j);

                    if (c1 != c2) {
                        if (adj.get(c1).add(c2)) {
                            indegree.put(c2, indegree.get(c2) + 1);
                        }
                        break;
                    }
                }
            }

            Queue<Character> q = new LinkedList<>();

            for (char c : indegree.keySet()) {
                if (indegree.get(c) == 0) {
                    q.offer(c);
                }
            }

            StringBuilder sb = new StringBuilder();

            while (!q.isEmpty()) {

                char c = q.poll();

                sb.append(c);

                for (char nei : adj.get(c)) {

                    indegree.put(nei, indegree.get(nei) - 1);

                    if (indegree.get(nei) == 0) {
                        q.offer(nei);
                    }
                }
            }

            if (sb.length() != indegree.size()) {
                return "";
            }

            return sb.toString();
        }
    }

    // 29/03/2026
    public class SolutionRevisedOnDayThird {
        public String foreignDictionary(String[] words) {

            Map<Character, Set<Character>> adj = new HashMap<>();
            Map<Character, Integer> indegree = new HashMap<>();

            for (String word : words) {
                for (char c : word.toCharArray()) {
                    adj.putIfAbsent(c, new HashSet<>());
                    indegree.put(c, 0);
                }
            }

            for (int i = 0; i < words.length; i++) {

                String w1 = words[i];
                String w2 = words[i + 1];

                if (w1.length() > w2.length() && w1.startsWith(w2)) {
                    return "";
                }

                int min = Math.min(w1.length(), w2.length());

                for (int j = 0; j < min; j++) {

                    char c1 = w1.charAt(j);
                    char c2 = w2.charAt(j);

                    if (c1 != c2) {

                        if (adj.get(c1).add(c2)) {
                            indegree.put(c2, indegree.get(c2) + 1);
                        }
                        break;
                    }
                }
            }

            Queue<Character> q = new LinkedList<>();

            for (char c : indegree.keySet()) {
                if (indegree.get(c) == 0) {
                    q.offer(c);
                }
            }

            StringBuilder sb = new StringBuilder();

            while (!q.isEmpty()) {

                char c = q.poll();

                sb.append(c);

                for (char nei : adj.get(c)) {

                    indegree.put(nei, indegree.get(nei) - 1);

                    if (indegree.get(nei) == 0) {
                        q.offer(nei);
                    }
                }
            }

            if (sb.length() != indegree.size()) {
                return "";
            }

            return sb.toString();
        }
    }

    // 04/04/2026
    public class SolutionRevisedOnDayFifth {
        public String foreignDictionary(String[] words) {

            Map<Character, Set<Character>> adj = new HashMap<>();
            Map<Character, Integer> indegree = new HashMap<>();

            for (String word : words) {
                for (char c : word.toCharArray()) {
                    adj.putIfAbsent(c, new HashSet<>());
                    indegree.putIfAbsent(c, 0);
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

                    char c1 = w1.charAt(j);
                    char c2 = w2.charAt(j);

                    if (c1 != c2) {

                        if (adj.get(c1).add(c2)) {
                            indegree.put(c2, indegree.get(c2) + 1);
                        }

                        break;
                    }
                }
            }

            Queue<Character> q = new LinkedList<>();

            for (char c : indegree.keySet()) {

                if (indegree.get(c) == 0) {
                    q.offer(c);
                }
            }

            StringBuilder sb = new StringBuilder();

            while (!q.isEmpty()) {

                char c = q.poll();

                sb.append(c);

                for (char nei : adj.get(c)) {
                    indegree.put(nei, indegree.get(nei) - 1);

                    if (indegree.get(nei) == 0) {
                        q.offer(nei);
                    }
                }
            }

            if (sb.length() != q.size()) {
                return "";
            }

            return sb.toString();
        }
    }
}
