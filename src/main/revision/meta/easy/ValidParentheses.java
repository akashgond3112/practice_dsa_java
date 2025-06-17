/**
 * @author agond
 * @date Jun 17, 2025
 * @time 8:39:20 PM
 */
package main.revision.meta.easy;

import java.util.*;

public class ValidParentheses {
  public boolean isValid(String s) {

    Stack<Character> stack = new Stack<>();

    for (char c : s.toCharArray()) {

      if (c == '(') {
        stack.push(')');
      } else if (c == '{') {
        stack.push('}');
      } else if (c == '[') {
        stack.push(']');
      } else if (stack.isEmpty() || stack.pop() != c) {
        return false;
      }
    }

    return stack.isEmpty();
  }
}
