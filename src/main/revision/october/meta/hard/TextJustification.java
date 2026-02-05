package main.revision.october.meta.hard;

import java.util.*;

public class TextJustification {

    class Solution {
        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> result = new ArrayList<>();
            int i = 0; // i current line ke starting index ko represent karta hai
            int n = words.length;

            // Stack trace ke liye input print karte hain
            System.out.println("Input words: " + Arrays.toString(words));
            System.out.println("maxWidth: " + maxWidth);

            // Har line ko process karne ke liye loop chalayenge
            while (i < n) {
                // Step 1: Current line me kitne words fit ho sakte hain ye determine karte hain
                int j = i; // j current line ke ending index (exclusive) ko represent karta hai
                int lineLength = 0; // Current line ke words ka total length track karta hai

                // Words ko greedily add karte hain jab tak wo line me fit ho rahe hain
                // (j - i) minimum single spaces ko represent karta hai jo words ke beech honge
                while (j < n && lineLength + words[j].length() + (j - i) <= maxWidth) {
                    System.out.printf("Word '%s' index %d par add karne ki koshish (current length: %d)\n", words[j], j,
                            lineLength);
                    lineLength += words[j].length();
                    j++;
                }
                System.out.printf("Line index %d se %d tak (exclusive), lineLength: %d\n", i, j, lineLength);

                StringBuilder sb = new StringBuilder();
                int numWords = j - i; // Current line me total words ka count
                int numSpaces = maxWidth - lineLength; // Spaces ka total count jo fill karna hai

                // Step 2: Agar ye last line ya single-word line hai to left justify karte hain
                if (j == n || numWords == 1) {
                    System.out.println("Line ko left-justify kar rahe hain.");
                    // Words ko ek single space ke saath append karte hain
                    for (int k = i; k < j; k++) {
                        sb.append(words[k]);
                        if (k < j - 1) {
                            sb.append(" ");
                        }
                    }
                    // Line ko maxWidth tak spaces se pad karte hain
                    while (sb.length() < maxWidth) {
                        sb.append(" ");
                    }
                } else {
                    // Step 3: Baaki cases ke liye line ko fully justify karte hain
                    int spacesBetweenWords = numSpaces / (numWords - 1); // Har word ke beech ka average space
                    int extraSpaces = numSpaces % (numWords - 1); // Extra spaces jo distribute karne hain
                    System.out.printf("Line ko fully justify kar rahe hain. spacesBetweenWords: %d, extraSpaces: %d\n",
                            spacesBetweenWords, extraSpaces);

                    for (int k = i; k < j; k++) {
                        sb.append(words[k]); // Word ko append karte hain
                        if (k < j - 1) { // Agar ye last word nahi hai
                            for (int s = 0; s < spacesBetweenWords; s++) {
                                sb.append(" "); // Average spaces ko append karte hain
                            }
                            if (extraSpaces > 0) { // Agar extra spaces bache hain
                                sb.append(" ");
                                extraSpaces--;
                            }
                        }
                    }
                }
                System.out.println("Formatted line: '" + sb.toString() + "'");
                result.add(sb.toString()); // Formatted line ko result me add karte hain
                i = j; // Next line ke liye index update karte hain
            }

            return result; // Final justified text return karte hain
        }
    }

    class SolutionRevisonOnthirdDay {
        public List<String> fullJustify(String[] words, int maxWidth) {

            List<String> result = new ArrayList<>();
            int i = 0;
            int n = words.length;

            while (i < n) {

                int j = i;
                int lineLength = 0;

                while (j < n && lineLength + words[j].length() + (j - i) <= maxWidth) {
                    lineLength += words[j].length();
                    j++;
                }

                StringBuilder sb = new StringBuilder();
                int numWords = j - i;
                int numSpaces = maxWidth - lineLength;

                if (j == n || numWords == 1) {

                    for (int k = i; k < j; k++) {
                        sb.append(words[k]);

                        if (k < j - 1) {
                            sb.append(" ");
                        }
                    }

                    while (sb.length() < maxWidth) {
                        sb.append(" ");
                    }
                } else {
                    int spacesBetweenWords = numSpaces / (numWords - 1);
                    int extraSpaces = numSpaces % (numWords - 1);

                    for (int k = i; k < j; k++) {
                        sb.append(words[k]);

                        if (k < j - 1) {

                            for (int s = 0; s < spacesBetweenWords; s++) {
                                sb.append(" ");
                            }

                            if (extraSpaces > 0) {
                                sb.append(" ");
                                extraSpaces--;
                            }
                        }
                    }
                }

                result.add(sb.toString());
                i = j;
            }

            return result;
        }
    }

    // revised on 12/1/2025
    class SolutionRevisonOnSeventhDay {
        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> result = new ArrayList<>();
            int i = 0;
            int n = words.length;

            while (i < n) {

                int j = i;
                int lineLength = 0;

                while (j < n && lineLength + words[i].length() + (j - 1) <= maxWidth) {
                    lineLength += words[j].length();
                    j++;
                }

                StringBuilder sb = new StringBuilder();
                int numWords = j - i;
                int numSpaces = maxWidth - lineLength;

                if (j == n && numWords == 1) {

                    for (int k = 0; k < j; k++) {
                        sb.append(words[k]);

                        if (k < j - 1) {
                            sb.append(" ");
                        }
                    }

                    while (sb.length() < maxWidth) {
                        sb.append(" ");
                    }
                } else {
                    int spacesbetweenWords = numSpaces / (numWords - 1);
                    int extraSpaces = numSpaces & (numWords - 1);

                    for (int k = 0; k < j; k++) {
                        for (int s = 0; s < spacesbetweenWords; s++) {
                            sb.append(" ");
                        }

                        if (extraSpaces > 0) {
                            sb.append(" ");
                            extraSpaces--;
                        }
                    }
                }
                result.add(sb.toString());
                i = j;

            }

            return result;
        }
    }

    // revised on 12/15/2025
    class SolutionRevisonOnFourteenDay {
        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> result = new ArrayList<>();
            int i = 0;
            int n = words.length;

            while (i < n) {
                int j = i;
                int lineLength = 0;

                while (j < n && lineLength + words[j].length() + (j - i) <= maxWidth) {
                    lineLength += words[j].length();
                    j++;
                }

                StringBuilder sb = new StringBuilder();
                int numWords = j - i;
                int numSpaces = maxWidth - lineLength;

                if (j == n || numWords == 1) {

                    for (int k = i; k < j; k++) {
                        sb.append(words[k]);
                        if (k < j - 1) {
                            sb.append(" ");
                        }
                    }

                    while (sb.length() < maxWidth) {
                        sb.append(" ");
                    }
                } else {
                    int spacesBetweenWords = numSpaces / (numWords - 1);
                    int extraSpaces = numSpaces % (numWords - 1);

                    for (int k = i; k < j; k++) {
                        sb.append(words[k]);

                        if (k < j - 1) {
                            for (int s = 0; s < spacesBetweenWords; s++) {
                                sb.append(" ");
                            }

                            if (extraSpaces > 0) {
                                sb.append(" ");
                                extraSpaces--;
                            }
                        }
                    }
                }

                result.add(sb.toString());
                i = j;
            }

            return result;
        }
    }

    // revised on 1/13/2026
    class SolutionRevisedOnDayThirty {
        public List<String> fullJustify(String[] words, int maxWidth) {

            List<String> result = new ArrayList<>();
            int i = 0;
            int n = words.length;

            while (i < n) {
                int j = i;
                int lineLength = 0;

                while (j < n && lineLength + words[j].length() + (j - 1) <= maxWidth) {
                    lineLength += words[j].length();
                    j++;
                }

                StringBuilder sb = new StringBuilder();
                int numWords = j - 1;
                int numSpaces = maxWidth - lineLength;

                if (j == n && numWords == 1) {
                    for (int k = i; k < j; k++) {
                        sb.append(words[k]);

                        if (k < j - 1) {
                            sb.append(" ");
                        }
                    }
                    while (sb.length() < maxWidth) {
                        sb.append(" ");
                    }
                } else {
                    int spacesBetweenWords = numSpaces / (numWords - 1);
                    int extraSpaces = numSpaces % (numWords - 1);
                    for (int k = i; k < j; k++) {
                        sb.append(words[k]);

                        if (k < j - 1) {
                            for (int s = 0; s < spacesBetweenWords; s++) {
                                sb.append(" ");
                            }

                            if (extraSpaces > 0) {
                                sb.append(" ");
                                extraSpaces--;
                            }
                        }
                    }
                }
                result.add(sb.toString());
                i = j;
            }
            return result;
        }
    }

    // Sample stack trace for the example input
    public static void main(String[] args) {
        Solution sol = new TextJustification().new Solution();
        String[] words = { "This", "is", "an", "example", "of", "text", "justification." };
        int maxWidth = 16;
        List<String> output = sol.fullJustify(words, maxWidth);
        for (String line : output) {
            System.out.println("\"" + line + "\"");
        }
    }
}
