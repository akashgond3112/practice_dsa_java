package main.revision.march.hard;

import java.util.*;

public class WordLadderII {

    class Solution {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

            Set<String> set = new HashSet<>(wordList);

            Queue<List<String>> queue = new LinkedList<>();
            List<String> list = new ArrayList<>();
            list.add(beginWord);
            queue.add(list);

            List<String> useOnLevel = new ArrayList<>();
            useOnLevel.add(beginWord);
            int level = 0;

            List<List<String>> result = new ArrayList<>();

            while (!queue.isEmpty()) {

                List<String> cur = queue.poll();

                if (cur.size() > level) {
                    level++;
                    for (String word : useOnLevel) {
                        set.remove(word);
                    }
                }

                String word = cur.get(cur.size() - 1);

                if (word.equals(endWord)) {
                    if (result.isEmpty()) {
                        result.add(cur);
                    } else if (result.get(0).size() == cur.size()) {
                        result.add(cur);
                    }
                    continue;
                }

                for (int i = 0; i < word.length(); i++) {

                    for (char c = 'a'; c <= 'z'; c++) {
                        char[] chars = word.toCharArray();
                        chars[i] = c;
                        String newWord = new String(chars);

                        if (set.contains(newWord)) {
                            cur.add(newWord);

                            queue.add(new ArrayList<>(cur));

                            useOnLevel.add(newWord);

                            cur.remove(cur.getLast());
                        }
                    }
                }
            }

            return result;
        }
    }

    // 19/04/2026
    class SolutionRevisedOnDayThird {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

            Set<String> set = new HashSet<>(wordList);

            Queue<List<String>> q = new LinkedList<>();
            List<String> list = new ArrayList<>();
            list.add(beginWord);
            q.add(list);

            List<String> usedOnLevel = new ArrayList<>();
            usedOnLevel.add(beginWord);
            int level = 0;

            List<List<String>> result = new ArrayList<>();

            while (!q.isEmpty()) {

                List<String> cur = q.poll();

                if (cur.size() > level) {
                    level++;
                    for (String word : usedOnLevel) {
                        set.remove(word);
                    }
                }

                String lastWord = cur.getLast();

                if (lastWord.equals(endWord)) {
                    if (result.isEmpty()) {
                        result.add(cur);
                    } else if (result.get(0).size() == cur.size()) {
                        result.add(cur);
                    }
                    continue;
                }

                for (int i = 0; i < lastWord.length(); i++) {

                    for (char c = 'a'; c <= 'z'; c++) {
                        char[] replaceChars = lastWord.toCharArray();
                        replaceChars[i] = c;

                        String newWord = new String(replaceChars);

                        if (set.contains(newWord)) {
                            cur.add(newWord);

                            List<String> tmp = new ArrayList<>(cur);
                            q.add(tmp);

                            usedOnLevel.add(newWord);
                            cur.removeLast();
                        }
                    }
                }
            }

            return result;
        }
    }
}
