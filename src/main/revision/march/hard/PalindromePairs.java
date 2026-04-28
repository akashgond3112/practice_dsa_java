package main.revision.march.hard;

import java.util.*;

public class PalindromePairs {

    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;

    }

    class Solution {
        public List<List<Integer>> palindromePairs(String[] words) {

            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < words.length; i++) {
                map.put(new StringBuilder(words[i]).reverse().toString(), i);
            }

            List<List<Integer>> result = new ArrayList<>();

            for (int i = 0; i < words.length; i++) {

                String currentWord = words[i];

                for (int j = 0; j <= currentWord.length(); j++) {
                    String prefix = currentWord.substring(0, j);
                    String suffix = currentWord.substring(j);

                    if (map.containsKey(prefix) && isPalindrome(suffix)) {
                        int k = map.get(prefix);

                        if (k != i) {
                            result.add(Arrays.asList(i, k));
                        }
                    }

                    if (!suffix.isEmpty() && map.containsKey(suffix) && isPalindrome(prefix)) {
                        int k = map.get(suffix);

                        if (k != i) {
                            result.add(Arrays.asList(i, k));
                        }
                    }
                }
            }

            return result;
        }
    }
}
