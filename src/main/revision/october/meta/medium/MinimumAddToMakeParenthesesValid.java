package main.revision.october.meta.medium;

public class MinimumAddToMakeParenthesesValid {

    public class Solution {
        public int minAddToMakeValid(String s) {

            int opening = 0;
            int closing = 0;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '(') {
                    opening++;
                } else {

                    if (opening > 0 && c == ')') {
                        opening--;
                    } else {
                        closing++;
                    }
                }
            }
            return opening + closing;
        }
    }

    // Revision on 26/10/2025
    public class SolutionRevisionThirdDay {
        public int minAddToMakeValid(String s) {
            int opening = 0;
            int closing = 0;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '(') {
                    opening++;
                } else {

                    if (opening > 0 && c == ')') {
                        opening--;
                    } else {
                        closing++;
                    }
                }
            }
            return opening + closing;
        }
    }

    // Revision on 01/11/2025
    public class SolutionRevisionSeventhDay {
        public int minAddToMakeValid(String s) {
            int opening = 0;
            int closing = 0;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '(') {
                    opening++;
                } else {

                    if (opening > 0 && c == ')') {
                        opening--;
                    } else {
                        closing++;
                    }
                }
            }
            return opening + closing;
        }
    }
}
