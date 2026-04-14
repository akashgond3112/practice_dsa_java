package main.revision.march.hard;

import java.util.*;

public class WordLadderI {

    public static class Pair {
        String word;
        int dist;

        Pair(String word, int dist) {
            this.word = word;
            this.dist = dist;
        }
    }

    // 01/04/2026
    class SolutionOnDayFirst {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            if (!wordList.contains(beginWord)) {
                return 0;
            }

            Queue<Pair> q = new LinkedList<>();
            q.add(new Pair(beginWord, 1));
            Set<String> visited = new HashSet<>(wordList);
            visited.remove(beginWord);

            while (!q.isEmpty()) {
                Pair p = q.poll();
                String curWord = p.word;
                int curDist = p.dist;

                if (curWord.equals(endWord)) {
                    return curDist;
                }

                for (int i = 0; i < curWord.length(); i++) {
                    char[] charWord = curWord.toCharArray();

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == curWord.charAt(i)) {
                            continue;
                        }

                        charWord[i] = c;
                        String newWord = new String(charWord);

                        if (visited.contains(newWord)) {
                            visited.remove(newWord);
                            q.add(new Pair(newWord, curDist + 1));
                        }
                    }
                }
            }

            return 0;
        }
    }

    // 06/04/2026
    class SolutionOnDayThird {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            if (!wordList.contains(beginWord)) {
                return 0;
            }

            Queue<Pair> q = new LinkedList<>();
            q.add(new Pair(beginWord, 0));

            Set<String> visited = new HashSet<>(wordList);
            visited.remove(beginWord);

            while (!q.isEmpty()) {
                Pair p = q.poll();
                String curWord = p.word;
                int curDist = p.dist;

                if (curWord.equals(endWord)) {
                    return curDist;
                }

                for (int i = 0; i < curWord.length(); i++) {

                    char[] charWord = curWord.toCharArray();

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == curWord.charAt(i)) {
                            continue;
                        }

                        charWord[i] = c;
                        String newWord = new String(charWord);

                        if (visited.contains(newWord)) {
                            visited.remove(newWord);
                            q.add(new Pair(newWord, curDist + 1));
                        }
                    }
                }
            }

            return 0;
        }
    }

    // 10/04/2026
    class SolutionOnDayFive {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            if (!wordList.contains(beginWord)) {
                return 0;
            }

            Queue<Pair> q = new LinkedList<>();
            q.add(new Pair(beginWord, 0));
            Set<String> visited = new HashSet<>(wordList);
            visited.remove(beginWord);

            while (!q.isEmpty()) {

                Pair pair = q.poll();
                String curWord = pair.word;
                int curDist = pair.dist;

                if (curWord.equals(endWord)) {
                    return curDist;
                }

                for (int i = 0; i < curWord.length(); i++) {

                    char[] chars = curWord.toCharArray();

                    for (char c = 'a'; c <= 'z'; c++) {

                        if (c == curWord.charAt(i)) {
                            continue;
                        }
                        chars[i] = c;
                        String newWord = new String(chars);

                        if (visited.contains(newWord)) {
                            visited.remove(newWord);
                            q.add(new Pair(newWord, curDist + 1));
                        }

                    }
                }
            }

            return 0;
        }
    }
}
