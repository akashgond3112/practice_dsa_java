package main.revision.october.meta.hard;

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

            // Step 1: Ek map banayein jisme har shabd ka reverse aur uska index store hoga.
            // Isse hume O(1) time mein kisi bhi shabd ka reverse dhundhne mein madad
            // milegi.
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                map.put(new StringBuilder(words[i]).reverse().toString(), i);
            }

            // Step 2: Result list banayein jisme valid pairs ke indices store honge.
            List<List<Integer>> result = new ArrayList<>();

            // Step 3: Har ek shabd ke liye iterate karein.
            for (int i = 0; i < words.length; i++) {
                String currentWord = words[i];
                // Step 4: Har shabd ko do hisson (prefix aur suffix) mein baatein.
                // 'j' split point hai.
                for (int j = 0; j <= currentWord.length(); j++) {
                    String prefix = currentWord.substring(0, j);
                    String suffix = currentWord.substring(j);

                    // Case 1: words[i] + words[k]
                    // Agar prefix ka reverse map mein मौजूद hai aur suffix ek palindrome hai.
                    // Iska matlab hai ki hume ek shabd (words[k]) mila hai jo prefix ka reverse
                    // hai.
                    // To words[i] + words[k] = (prefix + suffix) + reverse(prefix).
                    // Agar suffix palindrome hai, to poora string palindrome hoga.
                    if (map.containsKey(prefix) && isPalindrome(suffix)) {
                        int k = map.get(prefix);
                        // Yeh check karein ki hum shabd ko khud se pair nahi kar rahe hain.
                        if (k != i) {
                            result.add(Arrays.asList(i, k));
                        }
                    }

                    // Case 2: words[k] + words[i]
                    // Agar suffix ka reverse map mein मौजूद hai aur prefix ek palindrome hai.
                    // Iska matlab hai ki hume ek shabd (words[k]) mila hai jo suffix ka reverse
                    // hai.
                    // To words[k] + words[i] = reverse(suffix) + (prefix + suffix).
                    // Agar prefix palindrome hai, to poora string palindrome hoga.
                    // Yeh bhi check karein ki suffix empty na ho, kyunki empty string ka case
                    // pehle hi handle ho chuka hai (jab j = currentWord.length()).
                    if (!suffix.isEmpty() && map.containsKey(suffix) && isPalindrome(prefix)) {
                        int k = map.get(suffix);
                        // Yeh check karein ki hum shabd ko khud se pair nahi kar rahe hain.
                        if (k != i) {
                            result.add(Arrays.asList(k, i));
                        }
                    }
                }
            }

            // Step 5: Sabhi valid pairs ki list return karein.
            return result;
        }
    }

    // revised on 12/13/2025
    class SolutionRevisedOnThirdDay {
        public List<List<Integer>> palindromePairs(String[] words) {

            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < words.length; i++) {
                map.put(new StringBuilder(words[i]).reverse().toString(), i);
            }

            List<List<Integer>> result = new ArrayList<>();

            for (int i = 0; i < words.length; i++) {
                String currentWord = words[i];

                for (int j = 0; j < currentWord.length(); j++) {
                    String prefix = currentWord.substring(0, j);
                    String sufix = currentWord.substring(j);

                    if (map.containsKey(prefix) && isPalindrome(sufix)) {
                        int k = map.get(prefix);

                        if (k != i)
                            result.add(Arrays.asList(i, k));
                    }

                    if (!sufix.isEmpty() && map.containsKey(sufix) && isPalindrome(prefix)) {
                        int k = map.get(sufix);

                        if (k != i)
                            result.add(Arrays.asList(k, i));
                    }
                }
            }
            return result;
        }
    }

    // revised on 12/13/2025
    class SolutionRevisedOnSeventhDay {
        public List<List<Integer>> palindromePairs(String[] words) {
            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < words.length; i++) {
                map.put(new StringBuilder(words[i]).reverse().toString(), i);
            }

            List<List<Integer>> result = new ArrayList<>();

            for (int i = 0; i < words.length; i++) {

                String currentWord = words[i];

                for (int j = 0; j < currentWord.length(); j++) {
                    String prefix = currentWord.substring(0, j);
                    String sufix = currentWord.substring(j);

                    if (map.containsKey(prefix) && isPalindrome(sufix)) {
                        int k = map.get(prefix);

                        if (k != i) {
                            result.add(Arrays.asList(i, k));
                        }
                    }

                    if (!sufix.isEmpty() && map.containsKey(sufix) && isPalindrome(prefix)) {
                        int k = map.get(sufix);

                        if (k != i) {
                            result.add(Arrays.asList(i, k));
                        }
                    }
                }
            }

            return result;
        }
    }

    // revised on 1/2/2026
    class SolutionRevisedOnFourteenDay {
        public List<List<Integer>> palindromePairs(String[] words) {

            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                map.put(new StringBuilder(word).reverse().toString(), i);
            }

            List<List<Integer>> result = new ArrayList<>();

            for (int i = 0; i < words.length; i++) {
                String word = words[i];

                for (int j = 0; j < word.length(); j++) {
                    String prefix = word.substring(0, j);
                    String sufix = word.substring(j);

                    if (map.containsKey(prefix) && isPalindrome(sufix)) {
                        int k = map.get(prefix);

                        if (k != i) {
                            result.add(Arrays.asList(i, k));
                        }
                    }

                    if (!sufix.isEmpty() && map.containsKey(sufix) && isPalindrome(prefix)) {
                        int k = map.get(sufix);

                        if (k != i) {
                            result.add(Arrays.asList(i, k));
                        }
                    }
                }
            }
            return result;
        }
    }

    // revised on 1/31/2026
    class SolutionRevisedOnDayThirty {
        public List<List<Integer>> palindromePairs(String[] words) {

            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < words.length; i++) {
                map.put(new StringBuilder(words[i]).reverse().toString(), i);
            }

            List<List<Integer>> result = new ArrayList<>();

            for (int i = 0; i < words.length; i++) {
                String currentWord = words[i];

                for (int j = 0; j < currentWord.length(); j++) {
                    String prefix = currentWord.substring(0, j);
                    String suffix = currentWord.substring(j);

                    if (map.containsKey(prefix) && isPalindrome(suffix)) {

                        int k = map.get(prefix);

                        if (k != i) {
                            result.add(Arrays.asList(i, k));
                        }
                    } else if (!suffix.isEmpty() && map.containsKey(suffix) && isPalindrome(prefix)) {
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
