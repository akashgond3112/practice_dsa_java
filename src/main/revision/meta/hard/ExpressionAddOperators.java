/**
 * @author agond
 * @date Jun 11, 2025
 * @time 6:39:38 PM
 */
package main.revision.meta.hard;

import java.util.*;

public class ExpressionAddOperators {

    public List<String> addOperators(String num, int target) {

        List<String> result = new ArrayList<>();
        if (num == null || num.isEmpty()) {
            return result;
        }

        backtrack(result, num, target, 0, "", 0, 0);

        return result;
    }

    private void backtrack(List<String> result, String num, int target, int index, String expr, long evaluated,
            long lastNumber) {

        if (index == num.length()) {
            if (evaluated == target) {
                result.add(expr);
            }
            return;
        }

        for (int i = 0; i < num.length(); i++) {

            if (i > index && num.charAt(i) == '0') {
                break;
            }

            String curChar = num.substring(index, i + 1);
            long curNum = Long.parseLong(curChar);

            if (index == 0) {
                backtrack(result, num, target, i + 1,
                        curChar,
                        curNum,
                        curNum);
            } else {
                // Add
                backtrack(result, num, target, i + 1,
                        expr + '+' + curChar,
                        evaluated + curNum,
                        curNum);
                // sub
                backtrack(result, num, target, i + 1,
                        expr + '-'
                                + curChar,
                        evaluated - curNum,
                        -curNum);

                // mul
                backtrack(result, num, target, i + 1,
                        expr + '*'
                                + curChar,
                        evaluated - lastNumber + (lastNumber * curNum),
                        lastNumber * curNum);
            }

        }
    }

}
