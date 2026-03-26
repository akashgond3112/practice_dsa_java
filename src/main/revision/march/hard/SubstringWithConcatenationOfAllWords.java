package main.revision.march.hard;

import java.util.*;

public class SubstringWithConcatenationOfAllWords {

    class Solution {
        public List<Integer> findSubstring(String s, String[] words) {

            Map<String, Integer> map = new HashMap<>();

            for (String word : words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }

            int len = s.length();
            int n = words.length;
            if (n == 0)
                return new ArrayList<>();
            int wordSize = words[0].length();
            int windowSize = wordSize * n;

            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < wordSize; i++) {
                int start = i;

                while (start + windowSize <= len) {

                    Map<String, Integer> cur = new HashMap<>(map);
                    String curWord;
                    boolean matched = true;
                    for (int j = 0; j < n; j++) {
                        int begin = start + j * wordSize;
                        int end = begin + wordSize;
                        curWord = s.substring(begin, end);
                        if (!cur.containsKey(curWord) || cur.get(curWord) == 0) {
                            matched = false;
                            break;
                        }
                        cur.put(curWord, cur.get(curWord) - 1);

                    }

                    if (matched) {
                        result.add(start);
                    }

                    start += wordSize;
                }
            }

            return result;
        }
    }
}
