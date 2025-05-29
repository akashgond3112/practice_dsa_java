package main.revision.meta.easy;

/**
 * @author agond
 * @date May 29, 2025
 * @time 7:07:28 PM
 */
public class ValidWordAbbreviation {

    boolean isValidAbbreviation(String word, String abr) {

        int i = 0;
        int j = 0;

        while (i < word.length() && j < abr.length()) {

            char currChar = abr.charAt(j);

            if (Character.isDigit(currChar)) {
                int steps = 0;

                if (currChar == '0') {
                    return false;
                }

                while (j < abr.length() && Character.isDigit(abr.charAt(j))) {
                    int currNumber = abr.charAt(j) - '0';
                    steps = steps * 10 + currNumber;
                    j++;
                }

                i += steps;
            } else {
                if (word.charAt(i) != abr.charAt(j)) {
                    return false;
                }
                i++;
                j++;

            }
        }

        return i == word.length() && j == abr.length();

    }

}
