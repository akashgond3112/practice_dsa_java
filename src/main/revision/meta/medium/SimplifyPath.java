/**
 * @author agond
 * @date Jun 10, 2025
 * @time 8:42:12 PM
 */
package main.revision.meta.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {

    public String simplifyPath(String path) {

        Deque<String> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < path.length()) {
            if (path.charAt(i) == '/') {
                i++;
                continue;
            }
            StringBuilder tmp = new StringBuilder();

            while (i < path.length() && path.charAt(i) != '/') {
                tmp.append(path.charAt(i));
                i++;
            }

            String dir = tmp.toString();

            if (dir.equals(".")) {
                // Skip current directory symbol
            } else if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(dir);
            }
        }

        while (!stack.isEmpty()) {
            sb.insert(0, "/" + stack.pop());
        }

        return sb.isEmpty() ? "/" : sb.toString();
    }
}
