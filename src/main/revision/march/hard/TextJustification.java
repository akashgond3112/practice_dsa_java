package main.revision.march.hard;

import java.util.*;

public class TextJustification {

    // 18/04/2026
    class Solution {
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

    // 21/04/2026
    class SolutionRevisedOnDayThird {
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
}
