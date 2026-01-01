package main.revision.october.meta.easy;

public class GoatLatin {

    // Yeh class ek solution ko represent karti hai jo ek sentence ko "Goat Latin"
    // mein convert karti hai.
    class Solution {
        // Yeh method ek sentence ko "Goat Latin" mein convert karta hai.
        public String toGoatLatin(String sentence) {
            // Agar sentence null ya empty hai, toh empty string return karo.
            if (sentence == null || sentence.isEmpty()) {
                return "";
            }

            // Sentence ko words mein split karte hain.
            String[] words = sentence.split(" ");
            StringBuilder result = new StringBuilder();
            String vowels = "aeiouAEIOU"; // Vowels ki list.

            // Har word ke liye loop chalao.
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                // Agar word ka pehla character vowel hai, toh word ko as it is add karo.
                if (vowels.indexOf(word.charAt(0)) != -1) {
                    result.append(word);
                } else {
                    // Agar pehla character vowel nahi hai, toh pehla character ko end mein le jao.
                    result.append(word.substring(1)).append(word.charAt(0));
                }
                result.append("ma"); // "ma" ko har word ke end mein add karo.

                // Word ke position ke hisaab se "a" add karo.
                for (int j = 0; j <= i; j++) {
                    result.append("a");
                }

                // Agar yeh last word nahi hai, toh space add karo.
                if (i < words.length - 1) {
                    result.append(" ");
                }
            }
            // Final result ko string mein convert karke return karo.
            return result.toString();
        }
    }

    // revised on 12/26/2025
    class SolutionRevisedOnThirdDay {
        public String toGoatLatin(String sentence) {

            if (sentence == null || sentence.isEmpty()) {
                return "";
            }

            String[] words = sentence.split(" ");
            StringBuilder result = new StringBuilder();
            String vowels = "aeiouAEIOU";

            for (int i = 0; i < words.length; i++) {
                String word = words[i];

                if (vowels.indexOf(word.charAt(0)) != -1) {
                    result.append(word);
                } else {
                    result.append(word.substring(1)).append(word.charAt(0));
                }

                result.append("ma");

                for (int j = 0; j <= i; j++) {
                    result.append('a');
                }

                if (i < words.length - 1) {
                    result.append(" ");
                }
            }

            return result.toString();
        }
    }

    // revised on 1/1/2026
    class SolutionRevisedOnSeventhDay {
        public String toGoatLatin(String sentence) {

            if (sentence == null || sentence.isEmpty()) {
                return "";
            }

            String[] words = sentence.split(" ");
            StringBuilder result = new StringBuilder();
            String vowels = "aeiouAEIOU";

            for (int i = 0; i < words.length; i++) {
                String word = words[i];

                if (vowels.indexOf(word.charAt(0)) != -1) {
                    result.append(word);
                } else {
                    result.append(word.substring(1)).append(word.charAt(0));
                }

                result.append("ma");

                for (int j = 0; j <= 1; j++) {
                    result.append("a");
                }

                if (i < words.length - 1) {
                    result.append(" ");
                }
            }

            return result.toString();
        }
    }
}
