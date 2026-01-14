package main.revision.october.meta.medium;

import java.util.*;

public class WordLadderI {

    static class Pair {
        String word;
        int dist;

        public Pair(String word, int dist) {
            this.word = word;
            this.dist = dist;
        }
    }

    class Solution {

        /**
         * Returns the length of the shortest transformation sequence from beginWord to
         * endWord,
         * or 0 if no such sequence exists. The return value 0 indicates that no
         * transformation
         * sequence is possible according to the problem's convention.
         */
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) {
                return 0;
            }

            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(beginWord, 1));
            Set<String> visited = new HashSet<>(wordList);
            visited.remove(beginWord);

            while (!queue.isEmpty()) {
                Pair pair = queue.poll();
                String word = pair.word;
                int dist = pair.dist;
                if (word.equals(endWord)) {
                    return dist;
                }

                for (int i = 0; i < word.length(); i++) {
                    char[] replace = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == word.charAt(i)) {
                            continue;
                        }
                        replace[i] = c;
                        String newWord = new String(replace);
                        if (visited.contains(newWord)) {
                            visited.remove(newWord);
                            queue.add(new Pair(newWord, dist + 1));
                        }
                    }
                }
            }
            return 0;

        }
    }

    // Revised on 26/10/2025
    class SolutionRevisionOnThirdDay {

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            if (!wordList.contains(endWord)) {
                return 0;
            }

            Queue<Pair> q = new LinkedList<>();
            q.offer(new Pair(beginWord, 1));

            Set<String> visited = new HashSet<>(wordList);
            visited.remove(beginWord);

            while (!q.isEmpty()) {

                Pair pair = q.poll();
                String word = pair.word;
                int dist = pair.dist;

                if (word.equals(endWord)) {
                    return dist;
                }

                for (int i = 0; i < word.length(); i++) {
                    // [h, i , t]
                    char[] replace = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {

                        if (c == word.charAt(i)) {
                            continue;
                        }

                        replace[i] = c;

                        String newWord = new String(replace);

                        if (visited.contains(newWord)) {
                            visited.remove(newWord);
                            q.offer(new Pair(newWord, dist + 1));
                        }
                    }
                }
            }

            return 0;

        }
    }

    // Revised on 01/11/2025
    class SolutionRevisionOnSeventhDay {

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            if (!wordList.contains(endWord)) {
                return 0;
            }

            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(beginWord, 1));

            Set<String> visited = new HashSet<>(wordList);
            visited.remove(beginWord);

            while (!queue.isEmpty()) {

                Pair pair = queue.poll();
                String word = pair.word;
                int dist = pair.dist;

                if (word.equals(endWord)) {
                    return dist;
                }

                for (int i = 0; i < word.length(); i++) {

                    char[] replace = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {

                        if (c == word.charAt(i)) {
                            continue;
                        }

                        replace[i] = c;

                        String newWord = new String(replace);

                        if (visited.contains(newWord)) {
                            visited.remove(newWord);
                            queue.offer(new Pair(newWord, dist + 1));
                        }
                    }
                }
            }

            return 0;
        }
    }

    // Revised on 11/15/2025
    class SolutionRevisionOnFourteenDay {

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            if (!wordList.contains(endWord)) {
                return 0;
            }

            Queue<Pair> q = new LinkedList<>();
            q.add(new Pair(beginWord, 1));

            Set<String> visited = new HashSet<>(wordList);
            visited.remove(beginWord);

            while (!q.isEmpty()) {

                Pair pair = q.poll();
                String word = pair.word;
                int dist = pair.dist;

                if (word.equals(endWord)) {
                    return dist;
                }

                for (int i = 0; i < word.length(); i++) {

                    char[] replace = word.toCharArray();

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == word.charAt(i))
                            continue;

                        replace[i] = c;
                        String newWord = new String(replace);

                        if (visited.contains(newWord)) {
                            visited.remove(newWord);
                            q.add(new Pair(newWord, dist + 1));
                        }
                    }
                }
            }
            return 0;
        }
    }

    // Revised on 12/14/2025
    class SolutionRevisionOnThirtyDay {

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            if (!wordList.contains(endWord)) {
                return 0;
            }

            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(beginWord, 1));

            Set<String> visited = new HashSet<>(wordList);
            visited.remove(beginWord);

            while (!queue.isEmpty()) {

                Pair pair = queue.poll();

                String word = pair.word;
                int dist = pair.dist;

                if (word.equals(endWord)) {
                    return dist;
                }

                for (int i = 0; i < word.length(); i++) {
                    char[] replace = word.toCharArray();

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == word.charAt(i)) {
                            continue;
                        }

                        replace[i] = c;
                        String newWord = new String(replace);

                        if (visited.contains(newWord)) {
                            visited.remove(newWord);
                            queue.add(new Pair(newWord, dist + 1));
                        }
                    }
                }
            }
            return 0;
        }
    }
}
