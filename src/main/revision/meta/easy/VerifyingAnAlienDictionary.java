/**
 * @author agond
 * @date Jun 17, 2025
 * @time 7:06:18 PM
 */
package main.revision.meta.easy;

public class VerifyingAnAlienDictionary {

    public boolean isAlienSorted(String[] words, String order) {

        int[] orderIndex = new int[26];

        for (int i = 0; i < order.length(); i++) {
            orderIndex[order.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < words.length; i++) {
            String s1 = words[i];
            String s2 = words[i + 1];
            int j = 0;

            for (; j < s1.length(); j++) {

                if (j == s1.length()) {
                    return false;
                }

                if (s1.charAt(j) != s2.charAt(j)) {
                    if (orderIndex[s1.charAt(j) - 'a'] > orderIndex[s2.charAt(j) - 'a']) {
                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }

}
