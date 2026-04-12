package main.revision.march.hard;

import java.util.*;

public class ExpressionAddOperators {

    // 20/03/2026
    class SolutionOnDayFirst {
        public List<String> addOperators(String num, int target) {

            List<String> result = new ArrayList<>();

            if (num == null || num.isEmpty()) {
                return result;
            }

            dfs(result, num, target, 0, "", 0, 0);

            return result;
        }

        private void dfs(List<String> result, String num, int target, int index, String expr, long eval, long lastNum) {

            if (index == num.length()) {
                if (eval == target) {
                    result.add(expr);
                }
                return;
            }

            for (int i = index; i < num.length(); i++) {

                if (i > index && num.charAt(index) == '0') {
                    break;
                }

                String current = num.substring(index, i + 1);
                long currentNum = Long.parseLong(current);

                if (index == 0) {
                    dfs(result, num, target, i + 1, current, currentNum, currentNum);
                } else {

                    dfs(result, num, target, i + 1, (expr + "+" + current), eval + currentNum, currentNum);

                    dfs(result, num, target, i + 1, (expr + "-" + current), eval - currentNum, -currentNum);

                    dfs(result, num, target, i + 1, (expr + "*" + current),
                            (eval - lastNum + (lastNum * currentNum)),
                            (lastNum * currentNum));
                }
            }
        }
    }

    // 23/03/2026
    class SolutionRevisedOnDayThird {
        public List<String> addOperators(String num, int target) {

            List<String> result = new ArrayList<>();

            if (num == null || num.isEmpty()) {
                return result;
            }

            dfs(result, num, target, 0, "", 0, 0);

            return result;

        }

        private void dfs(List<String> result, String num, int target, int index, String expr, long eval, long lastNum) {

            if (index == num.length()) {
                if (eval == target) {
                    result.add(expr);
                }
                return;
            }

            for (int i = index; i < num.length(); i++) {

                if (i > index && num.charAt(index) == '0') {
                    break;
                }
                String current = num.substring(index, i + 1);
                long currentNum = Long.parseLong(current);

                if (index == 0) {
                    dfs(result, num, target, i + 1, current, currentNum, currentNum);
                } else {

                    dfs(result, num, target, i + 1, (expr + "+" + current), eval + currentNum, currentNum);
                    dfs(result, num, target, i + 1, (expr + "-" + current), eval - currentNum, -currentNum);

                    dfs(result, num, target, i + 1,
                            (expr + "-" + current),
                            (eval - lastNum + (lastNum * currentNum)),
                            lastNum * currentNum);

                }

            }

        }
    }

    // 30/03/2026
    class SolutionRevisedOnDaySeventh {
        public List<String> addOperators(String num, int target) {

            List<String> result = new ArrayList<>();

            if (num == null || num.isEmpty()) {
                return result;
            }

            dfs(result, num, target, 0, "", 0, 0);

            return result;
        }

        private void dfs(List<String> result, String num, int target, int index, String expr, long eval, long lastNum) {

            if (index == num.length()) {
                if (eval == target) {
                    result.add(expr);
                }

                return;
            }

            for (int i = index; i < num.length(); i++) {

                if (i > index && num.charAt(index) == 0) {
                    break;
                }

                String current = num.substring(index, i + 1);
                long currentNum = Long.parseLong(current);

                if (index == 0) {
                    dfs(result, num, target, i + 1, current, currentNum, currentNum);
                } else {

                    dfs(result, num, target, i + 1, (expr + "+" + current), eval + currentNum, currentNum);
                    dfs(result, num, target, i + 1, (expr + "-" + current), eval - currentNum, -currentNum);
                    dfs(result, num, target, i + 1, (expr + "*" + current),
                            (eval - lastNum + (lastNum * currentNum)),
                            lastNum * currentNum);

                }
            }
        }
    }

    // 30/03/2026
    class SolutionRevisedOnDayFifth {
        public List<String> addOperators(String num, int target) {

            List<String> result = new ArrayList<>();

            if (num == null || num.isEmpty()) {
                return result;
            }

            dfs(num, target, result, "", 0, 0, 0);

            return result;
        }

        private void dfs(String num, int target, List<String> result, String expr, int index, long evaluated,
                long lastNumber) {

            if (index == num.length()) {
                if (evaluated == target) {
                    result.add(expr);
                }
                return;
            }

            for (int i = index; i < num.length(); i++) {

                if (i > index && num.charAt(i) == '0')
                    break;

                String current = num.substring(index, i + 1);
                long currentNum = Long.parseLong(current);

                if (index == 0) {
                    dfs(num, target, result, current, index + 1, currentNum, currentNum);
                } else {
                    dfs(num, target, result, (expr + "+" + current), i + 1, evaluated + currentNum, currentNum);
                    dfs(num, target, result, (expr + "-" + current), i + 1, evaluated - currentNum, -currentNum);
                    dfs(num, target, result, (expr + "*" + current), i + 1,
                            evaluated - lastNumber + (lastNumber * currentNum),
                            lastNumber * currentNum);

                }
            }
        }
    }
}
