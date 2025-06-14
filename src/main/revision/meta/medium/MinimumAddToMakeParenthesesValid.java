/**
 * @author agond
 * @date Jun 14, 2025
 * @time 3:41:25 PM
 */
package main.revision.meta.medium;

public class MinimumAddToMakeParenthesesValid {

    public int minAddToMakeValid(String s) {

        int opening = 0;
        int closing = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c == '(') {
                opening++;
            } else if (opening > 0) {
                opening--;
            } else {
                closing++;
            }
        }

        return opening + closing;
    }

}
