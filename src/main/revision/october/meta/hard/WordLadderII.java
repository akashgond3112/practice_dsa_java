package main.revision.october.meta.hard;

import java.util.*;

public class WordLadderII {
    /**
     * Time Complexity:
     * - Let L be the length of each word, and N be the number of words in wordList.
     * - For each word in the queue, we try changing each character (L positions) to
     * 26 possible letters.
     * - For each transformation, we check if the new word is in the set (O(1)).
     * - In the worst case, we may generate O(N) words at each level, and each word
     * can be transformed in O(L*26) ways.
     * - Overall, the time complexity is O(N * L * 26) = O(N * L).
     *
     * Space Complexity:
     * - The queue can hold up to O(N) paths, each of length up to O(N).
     * - The set and usedOnLevel lists use O(N) space.
     * - The result list can store all shortest transformation sequences, which in
     * the worst case can be exponential in N.
     * - Overall, the space complexity is O(N^2) in the worst case.
     */
    class Solution {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

            // Step 1: wordList ko HashSet me convert karo fast lookup ke liye
            Set<String> set = new HashSet<>(wordList);

            // Step 2: Queue initialize karo jisme har path ek list of words hoga
            Queue<List<String>> queue = new LinkedList<>();
            List<String> list = new ArrayList<>();
            list.add(beginWord);

            queue.add(list);

            // Step 3: Ek list banao jo current level pe use hue words track karegi
            ArrayList<String> usedOnLevel = new ArrayList<>();
            usedOnLevel.add(beginWord);
            int level = 0;

            // Step 4: Result list initialize karo jisme sabse choti transformation
            // sequences hongi
            List<List<String>> result = new ArrayList<>();

            // Step 5: BFS traversal start karo
            while (!queue.isEmpty()) {

                // Queue se current path uthao
                List<String> current = queue.peek();
                queue.remove();

                // Step 6: Agar naye level pe aa gaye ho toh pichle level ke words set se hata
                // do
                if (current.size() > level) {
                    level++;
                    for (String word : usedOnLevel) {
                        set.remove(word);
                    }
                }

                // Step 7: Current path ka last word nikalo
                String word = current.get(current.size() - 1);

                // Step 8: Agar last word endWord hai toh path result me daal do
                if (word.equals(endWord)) {
                    if (result.isEmpty())
                        result.add(current);
                    else if (result.get(0).size() == current.size()) {
                        result.add(current);
                    }
                    continue;
                }

                // Step 9: Last word ke saare possible transformations banao
                for (int i = 0; i < word.length(); i++) {

                    for (char c = 'a'; c <= 'z'; c++) {
                        char[] replacementChar = word.toCharArray();
                        replacementChar[i] = c;
                        String newWord = new String(replacementChar);

                        // Step 10: Agar transformed word set me hai toh path me add karo
                        if (set.contains(newWord)) {
                            current.add(newWord);

                            // Naya path banao aur queue me daal do
                            ArrayList<String> temp = new ArrayList<>(current);
                            queue.add(temp);

                            // Is level pe word ko used mark karo
                            usedOnLevel.add(newWord);

                            // Backtrack karo, last word hata do
                            current.remove(current.size() - 1);
                        }
                    }
                }

            }
            // Step 11: Result return karo jisme sab shortest transformation sequences hongi
            return result;

        }
    }

    // revised on 11/23/2025
    class SolutionRevisedOnThirdDay {
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

                List<String> current = q.poll();

                if (current.size() > level) {
                    level++;
                    for (String word : usedOnLevel) {
                        set.remove(word);
                    }
                }

                String lastWord = current.get(current.size() - 1);

                if (lastWord.equals(endWord)) {
                    if (result.isEmpty()) {
                        result.add(current);
                    } else if (result.get(0).size() == current.size()) {
                        result.add(current);
                    }
                    continue;
                }

                for (int i = 0; i < lastWord.length(); i++) {

                    for (char c = 'a'; c <= 'z'; c++) {

                        char[] replaceChar = lastWord.toCharArray();
                        replaceChar[i] = c;
                        String newWord = new String(replaceChar);

                        if (set.contains(newWord)) {
                            current.add(newWord);

                            ArrayList<String> tmp = new ArrayList<>(current);
                            q.add(tmp);
                            usedOnLevel.add(newWord);
                            current.remove(current.size() - 1);
                        }
                    }
                }
            }

            return result;

        }
    }

    // revised on 11/29/2025
    class SolutionRevisedOnSeventhDay {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            Set<String> set = new HashSet<>(wordList);

            Queue<List<String>> q = new LinkedList<>();
            List<String> list = new ArrayList<>();
            list.add(beginWord);
            q.add(list);

            List<String> usedOnlevel = new ArrayList<>();
            usedOnlevel.add(beginWord);
            int level = 0;

            List<List<String>> res = new ArrayList<>();

            while (!q.isEmpty()) {

                List<String> curLevelList = q.poll();

                if (curLevelList.size() > level) {
                    level++;
                    for (String word : usedOnlevel) {
                        set.remove(word);
                    }
                }

                String curListLastWord = curLevelList.get(curLevelList.size() - 1);

                if (curListLastWord.equals(endWord)) {
                    if (res.isEmpty()) {
                        res.add(curLevelList);
                    } else if (res.get(0).size() == curLevelList.size()) {
                        res.add(curLevelList);
                    }
                    continue;
                }

                for (int i = 0; i < curListLastWord.length(); i++) {

                    for (char c = 'a'; c < 'z'; c++) {

                        char[] replaceWord = curListLastWord.toCharArray();
                        replaceWord[i] = c;
                        String newWord = new String(replaceWord);

                        if (set.contains(newWord)) {
                            curLevelList.add(newWord);

                            ArrayList<String> tmp = new ArrayList<>(curLevelList);
                            q.add(tmp);
                            usedOnlevel.add(newWord);
                            curLevelList.remove(curLevelList.size() - 1);

                        }
                    }
                }
            }
            return res;
        }
    }

    // revised on 12/13/2025
    class SolutionRevisedOnFourteenDay {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

            Set<String> set = new HashSet<>();

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

                        char[] replace = word.toCharArray();
                        replace[i] = c;

                        String newWord = new String(replace);

                        if (set.contains(newWord)) {

                            cur.add(newWord);
                            ArrayList<String> temp = new ArrayList<>(cur);
                            q.add(temp);

                            usedOnLevel.add(newWord);
                            cur.remove(cur.size() - 1);

                        }
                    }
                }
            }
            return result;

        }

    }

    // revised on 1/10/2026
    class SolutionRevisedOnDayThirty {

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

                String currentWord = cur.getLast();

                if (currentWord.equals(endWord)) {
                    if (result.isEmpty()) {
                        result.add(cur);
                    } else if (result.getFirst().size() == cur.size()) {
                        result.add(cur);
                    }
                    continue;
                }

                for (int i = 0; i < currentWord.length(); i++) {

                    for (char c = 'a'; c <= 'z'; c++) {

                        char[] replacementChar = currentWord.toCharArray();
                        replacementChar[i] = c;

                        String newWord = new String(replacementChar);

                        if (set.contains(newWord)) {
                            cur.add(newWord);

                            ArrayList<String> tmp = new ArrayList<>(cur);
                            q.add(tmp);

                            usedOnLevel.add(newWord);

                            cur.remove(cur.getLast());
                        }
                    }
                }
            }

            return result;

        }
    }
}
