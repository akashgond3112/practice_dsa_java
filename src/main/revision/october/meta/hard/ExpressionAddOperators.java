package main.revision.october.meta.hard;

import java.util.*;

public class ExpressionAddOperators {
    /**
     * Generates all possible expressions by adding '+', '-', or '*' operators
     * between the digits of the input string
     * such that the resulting expression evaluates to the target value.
     *
     * @param num    the string containing only digits
     * @param target the target value to achieve with the expressions
     * @return a list of valid expressions that evaluate to the target value
     */
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.isEmpty()) {
            return res;
        }

        dfs(res, num, target, 0, "", 0, 0);

        return res;
    }

    /**
     * Performs depth-first search to find all expressions that evaluate to the
     * target.
     *
     * @param result     List to store valid expressions.
     * @param num        The numeric string to process.
     * @param target     The target value to reach.
     * @param index      Current index in the numeric string.
     * @param expr       Current expression being built.
     * @param evaluated  Current evaluated value of the expression.
     * @param lastNumber Last number used in the expression (for multiplication).
     */
    private void dfs(List<String> result, String num, int target, int index, String expr, long evaluated,
            long lastNumber) {

        if (index == num.length()) {
            if (evaluated == target) {
                result.add(expr);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {

            // Skip numbers with leading zeros (length > 1 and starts with '0')
            if (i > index && num.charAt(index) == '0')
                break;

            String current = num.substring(index, i + 1);
            long currentNum = Long.parseLong(current);

            if (index == 0) {
                dfs(result, num, target, i + 1, current, currentNum, currentNum);
            } else {
                dfs(result, num, target, i + 1, (expr + "+" + current), evaluated + currentNum, currentNum);

                dfs(result, num, target, i + 1, (expr + "-" + current), evaluated - currentNum, -currentNum);

                dfs(result, num, target, i + 1, (expr + "*" + current),
                        (evaluated - lastNumber + (lastNumber * currentNum)),
                        (lastNumber * currentNum));
            }
        }
    }

    // Revision on 20/10/2025
    private void dfsRevisionSeventhDay(List<String> result, String num, int target, int index, String expr,
            long evaluated, long lastNumber) {

        if (index == num.length()) {

            if (evaluated == target) {
                result.add(expr);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {

            // Skip numbers with leading zeros (length > 1 and starts with '0')
            if (i > index && num.charAt(index) == '0')
                break;

            String current = num.substring(index, i + 1);
            long currentNum = Long.parseLong(current);

            if (index == 0) {
                dfs(result, num, target, index + 1, current, currentNum, currentNum);
            } else {
                dfs(result, num, target, index + 1, (expr + "+" + current), evaluated + currentNum, currentNum);

                dfs(result, num, target, index + 1, (expr + "-" + current), evaluated - currentNum, -currentNum);

                dfs(result, num, target, index + 1, (expr + "*" + current),
                        (evaluated - lastNumber + (lastNumber * currentNum)),
                        (lastNumber * currentNum));
            }
        }
    }

    // Revision on 03/11/2025
    private void dfsRevisionFourteenDay(List<String> result, String num, int target, int index, String expr,
            long evaluated, long lastNumber) {

        if (index == num.length()) {

            if (evaluated == target) {
                result.add(expr);
            }

            return;
        }

        for (int i = index; i < num.length(); i++) {

            if (i > index && num.charAt(i) == '0') {
                break;
            }

            String current = num.substring(index, i + 1);
            long currentNum = Long.parseLong(current);

            if (i == 0) {
                dfs(result, num, target, index + 1, current, currentNum, currentNum);
            } else {
                dfs(result, num, target, index + 1, (expr + '+' + current), currentNum, currentNum);

                dfs(result, num, target, index + 1, (expr + '-' + current), currentNum, -currentNum);

                dfs(result, num, target, index + 1, (expr + '*' + current),
                        (evaluated - lastNumber + (lastNumber * currentNum)),
                        (lastNumber * currentNum));
            }
        }
    }

    // Revision on 12/2/2025
    private void dfsRevisionThirtyDay(List<String> result, String num, int target, int index, String expr,
            long evaluated, long lastNumber) {

        if (index == num.length()) {
            if (evaluated == target) {
                result.add(expr);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {

            if (i > index && num.charAt(i) == '0') {
                break;
            }

            String current = num.substring(index, i + 1);
            long currentNum = Long.parseLong(current);

            if (i == 0) {
                dfs(result, num, target, index + 1, current, currentNum, currentNum);
            } else {
                dfs(result, num, target, index + 1, (expr + '+' + current), currentNum, currentNum);
                dfs(result, num, target, index + 1, (expr + '-' + current), currentNum, -currentNum);
                dfs(result, num, target, index + 1, (expr + '*' + current),
                        (evaluated - lastNumber + (lastNumber * currentNum)),
                        (lastNumber * currentNum));
            }
        }
    }
}