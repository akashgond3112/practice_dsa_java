package main.revision.october.meta.medium;

public class MinimumAddToMakeParenthesesValid {

    // Yeh Solution class hai jo minimum parentheses add karne ka count batata hai
    public class Solution {
        public int minAddToMakeValid(String s) {

            int opening = 0; // Yeh count karega kitne '(' abhi tak unmatched hain
            int closing = 0; // Yeh count karega kitne ')' extra hain jo match nahi hue

            // Har character ko string me check karenge
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '(') {
                    opening++; // Agar '(' mila toh opening badha do
                } else {
                    // Agar ')' mila aur koi unmatched '(' hai toh match kar do
                    if (opening > 0 && c == ')') {
                        opening--; // Ek '(' match ho gaya, toh opening kam kar do
                    } else {
                        closing++; // Nahi toh yeh extra ')' hai, closing badha do
                    }
                }
            }
            // Total unmatched '(' aur ')' ka sum hi answer hai
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

    // Revision on 11/15/2025
    public class SolutionRevisionFourteenDay {
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

    // Revision on 12/14/2025
    public class SolutionRevisionThirtyDay {
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
