package main.revision.october.meta.easy;

import java.util.*;

public class VerifyingAnAlienDictionary {

    class Solution {
        public boolean isAlienSorted(String[] words, String order) {

            Map<Character, Integer> map = new HashMap<>();

            for (int i = 0; i < order.length(); i++) {
                map.put(order.charAt(i), i);
            }

            for (int j = 0; j < words.length - 1; j++) {
                String word1 = words[j];
                String word2 = words[j + 1];

                int minLength = Math.min(word1.length(), word2.length());
                boolean differenceFound = false;

                for (int k = 0; k < minLength; k++) {
                    char char1 = word1.charAt(k);
                    char char2 = word2.charAt(k);

                    if (char1 != char2) {
                        if (map.get(char1) > map.get(char2)) {
                            return false;
                        }
                        differenceFound = true;
                        break;
                    }
                }

                if (!differenceFound && word1.length() > word2.length()) {
                    return false;
                }
            }
            return true;
        }
    }

    // revised on 28/10/2025
    class SolutionRevisionThirdDay {
        public boolean isAlienSorted(String[] words, String order) {

            Map<Character, Integer> map = new HashMap<>();

            for (int i = 0; i < order.length(); i++) {
                map.put(order.charAt(i), i);
            }

            for (int j = 0; j < words.length - 1; j++) {

                String word1 = words[j];
                String word2 = words[j + 1];

                int minLength = Math.min(word1.length(), word2.length());
                boolean differenceFound = false;

                for (int k = 0; k < minLength; k++) {

                    char c1 = word1.charAt(k);
                    char c2 = word2.charAt(k);

                    if (c1 != c2) {
                        if (map.get(c1) > map.get(c2)) {
                            return false;
                        }
                        differenceFound = true;
                        break;

                    }
                }

                if (!differenceFound && word1.length() > word2.length()) {
                    return false;
                }
            }

            return true;
        }
    }

    // revised on 04/11/2025
    class SolutionRevisionSeventhDay {
        public boolean isAlienSorted(String[] words, String order) {

            Map<Character, Integer> map = new HashMap<>();

            for (int i = 0; i < order.length(); i++) {
                map.put(order.charAt(i), i);
            }

            for (int j = 0; j < words.length; j++) {

                String word1 = words[j];
                String word2 = words[j + 1];

                int minLength = Math.min(word1.length(), word2.length());

                boolean differenceFound = false;

                for (int k = 0; k < minLength; k++) {

                    char c1 = word1.charAt(k);
                    char c2 = word2.charAt(k);

                    if (c1 != c2) {
                        if (map.get(c1) > map.get(c2))
                            return false;
                    }
                    differenceFound = true;
                    break;

                }

                if (!differenceFound && word1.length() > word2.length()) {
                    return false;
                }
            }

            return true;
        }
    }
}
