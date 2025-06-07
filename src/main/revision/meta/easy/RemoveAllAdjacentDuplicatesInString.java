/**
 * @author agond
 * @date Jun 06, 2025
 * @time 7:41:22 PM
 */
package main.revision.meta.easy;

public class RemoveAllAdjacentDuplicatesInString {

    public String removeDuplicates(String s) {
        StringBuilder stack = new StringBuilder();
        for (char c : s.toCharArray()) {
            int len = stack.length();
            if (!stack.isEmpty() && stack.charAt(len - 1) == c) {
                stack.deleteCharAt(len - 1); // Remove duplicate
            } else {
                stack.append(c); // Push to stack
            }
        }
        return stack.toString();
    }

}
