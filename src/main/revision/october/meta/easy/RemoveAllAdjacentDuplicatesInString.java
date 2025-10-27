package main.revision.october.meta.easy;

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

    // Revision on 20/10/2025
    public String removeDuplicatesRevisionThirdDay(String s) {
        StringBuilder stack = new StringBuilder();

        for (char c : s.toCharArray()) {
            int len = stack.length();

            if (!stack.isEmpty() && stack.charAt(len - 1) == c) {
                stack.deleteCharAt(len - 1);
            } else
                stack.append(c);
        }

        return stack.toString();
    }

    // Revision on 26/10/2025
    public String removeDuplicatesRevisionSeventhdDay(String s) {

        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {

            int len = sb.length();

            if (!sb.isEmpty() && sb.charAt(len - 1) == c) {
                sb.deleteCharAt(len - 1);
            } else {
                sb.append(c);
            }

        }

        return sb.toString();
    }
}
