package main.revision.october.meta.hard;

import java.util.*;

public class WordLadderII {

    class SolutionWithoutTLE {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            // Final answer list banao jisme saari shortest transformation sequences aayengi
            List<List<String>> ans = new ArrayList<>();

            // Reverse graph banao: key = ek word, value = us word tak aane wale words
            // (parents)
            // Ye graph endWord se beginWord ki taraf traverse karne ke liye use hoga
            Map<String, Set<String>> reverse = new HashMap<>();

            // wordList ko HashSet me daalo taaki O(1) lookup mile aur duplicates remove ho
            // jayein
            Set<String> wordSet = new HashSet<>(wordList);

            // beginWord ko wordSet se hata do taaki BFS me cycle na bane (wapas start pe na
            // jaaye)
            wordSet.remove(beginWord);

            // BFS ke liye queue banao — sirf current level ke words store honge (poori path
            // nahi)
            Queue<String> queue = new LinkedList<>();

            // BFS ki pehli layer mein sirf beginWord hai
            queue.add(beginWord);

            // Agla level (next layer) ke naye words track karne ke liye Set
            Set<String> nextLevel = new HashSet<>();

            // Flag: kya endWord BFS mein mil gaya?
            boolean findEnd = false;

            // BFS loop: jab tak queue empty na ho, current layer ke words process karo
            while (!queue.isEmpty()) {

                // Queue se ek word nikalo (current layer ka ek node)
                String word = queue.remove();

                // wordSet ke har word ke saath check karo — kya ye ek valid ladder step hai?
                for (String next : wordSet) {
                    if (isLadder(word, next)) { // sirf ek character different hai toh ladder hai

                        // Reverse graph mein edge daalo: next -> word
                        // Matlab "next" word tak 'word' se aaya ja sakta hai
                        Set<String> reverseLadders = reverse.computeIfAbsent(next, k -> new HashSet<>());
                        reverseLadders.add(word);

                        // Agar next word hi endWord hai toh flag set karo
                        if (endWord.equals(next)) {
                            findEnd = true;
                        }

                        // next word ko agle level mein daalo
                        nextLevel.add(next);
                    }
                }

                // Jab current layer ke saare words process ho jayein (queue empty ho)
                if (queue.isEmpty()) {
                    // Agar endWord mil gaya hai toh aage jaane ki zaroorat nahi — break karo
                    if (findEnd)
                        break;

                    // Nahi mila toh next layer ke words queue mein daalo
                    queue.addAll(nextLevel);

                    // Next layer ke words ko wordSet se hata do taaki wapas visit na ho
                    wordSet.removeAll(nextLevel);

                    // nextLevel clear karo — agli layer ke liye fresh start
                    nextLevel.clear();
                }
            }

            // Agar endWord kabhi reach nahi hua toh empty list return karo
            if (!findEnd)
                return ans;

            // DFS ke liye path banao, endWord se shuru karo (reverse traversal hoga)
            // LinkedHashSet use karo taaki insertion order maintain ho aur duplicates na ho
            Set<String> path = new LinkedHashSet<>();
            path.add(endWord);

            // Reverse graph mein endWord se beginWord tak DFS karo, saari paths collect
            // karo
            findPath(endWord, beginWord, reverse, ans, path);

            return ans;
        }

        // DFS helper: reverse graph mein endWord se beginWord tak saari paths dhundho
        private void findPath(String endWord, String beginWord, Map<String, Set<String>> graph,
                List<List<String>> ans, Set<String> path) {

            // Current word ke parents (is word tak aane wale words) graph se nikalo
            Set<String> next = graph.get(endWord);

            // Agar koi parent nahi hai (beginWord se pehle) toh return karo
            if (next == null)
                return;

            // Har parent word ke liye recursively path explore karo
            for (String word : next) {
                // word ko path mein add karo
                path.add(word);

                if (beginWord.equals(word)) {
                    // Agar beginWord mil gaya toh poori path complete ho gayi
                    // path ko list mein convert karo aur reverse karo (endWord->beginWord tha, ab
                    // beginWord->endWord karo)
                    List<String> shortestPath = new ArrayList<>(path);
                    Collections.reverse(shortestPath);
                    ans.add(shortestPath);
                } else {
                    // Abhi beginWord nahi mila, aur peeche jao
                    findPath(word, beginWord, graph, ans, path);
                }

                // Backtrack: word ko path se hata do taaki dusri branch explore ho sake
                path.remove(word);
            }
        }

        // Helper: kya do words ladder hai? (sirf ek character different hona chahiye)
        private boolean isLadder(String s, String t) {
            // Alag length ke words kabhi ladder nahi ho sakte
            if (s.length() != t.length())
                return false;

            int diffCount = 0; // different characters ki count
            int n = s.length();

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) != t.charAt(i))
                    diffCount++; // ek aur character different mila

                // Agar already 2 se zyada different characters hain toh ye ladder nahi hai
                if (diffCount > 1)
                    return false;
            }

            // Exactly 1 character different hona chahiye
            return diffCount == 1;
        }
    }

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
