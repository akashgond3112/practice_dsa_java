package main.revision.october.meta.medium;

import java.util.List;

public class NestedListWeightSum {

    public abstract static class NestedInteger {
        /**
         * Constructor initializes an empty nested list.
         */
        public NestedInteger() {
        }

        /**
         * Constructor initializes a single integer.
         * 
         * @param value the integer value to be stored
         */
        public NestedInteger(int value) {
        }

        /**
         * Checks if this NestedInteger holds a single integer value.
         * 
         * @return true if holds an integer, false if holds a nested list
         */
        public abstract boolean isInteger();

        /**
         * Returns the integer value if this NestedInteger holds a single integer.
         * 
         * @return the integer value or null if this holds a nested list
         */
        public abstract Integer getInteger();

        /**
         * Sets this NestedInteger to hold a single integer value.
         * 
         * @param value the integer value to be stored
         */
        public abstract void setInteger(int value);

        /**
         * Adds a NestedInteger to the nested list held by this NestedInteger.
         * 
         * @param ni the NestedInteger to be added to the list
         */
        public abstract void add(NestedInteger ni);

        /**
         * Returns the nested list held by this NestedInteger.
         * 
         * @return the nested list or empty list if this holds a single integer
         */
        public abstract List<NestedInteger> getList();
    }

    static class Solution {

        public int depthSum(List<NestedInteger> nestedList) {

            if (nestedList == null || nestedList.isEmpty()) {
                return 0;
            }

            return dfs(nestedList, 1);
        }

        private int dfs(List<NestedInteger> nestedList, int depth) {
            int depthSum = 0;

            for (NestedInteger item : nestedList) {

                if (item.isInteger()) {
                    depthSum += item.getInteger() * depth;
                } else {
                    depthSum += dfs(item.getList(), depth + 1);
                }
            }

            return depthSum;
        }
    }

    // Revise on 27/10/2025
    static class SolutionRevisionThirdDay {

        public int depthSum(List<NestedInteger> nestedList) {

            if (nestedList == null || nestedList.isEmpty()) {
                return 0;
            }

            return dfs(nestedList, 1);
        }

        private int dfs(List<NestedInteger> nestedList, int depth) {
            int depthSum = 0;

            for (NestedInteger item : nestedList) {

                if (item.isInteger()) {
                    depthSum += item.getInteger() * depth;
                } else {
                    depthSum += dfs(item.getList(), depth + 1);
                }
            }

            return depthSum;
        }
    }

    // Revise on 02/11/2025
    static class SolutionRevisionSeventhDay {

        public int depthSum(List<NestedInteger> nestedList) {

            if (nestedList == null || nestedList.isEmpty()) {
                return 0;
            }

            return dfs(nestedList, 1);
        }

        private int dfs(List<NestedInteger> nestedList, int depth) {
            int depthSum = 0;

            for (NestedInteger item : nestedList) {

                if (item.isInteger()) {
                    depthSum += item.getInteger() * depth;
                } else {

                    depthSum += dfs(item.getList(), depth++);
                }
            }

            return depthSum;
        }
    }

}
