/**
 * @author agond
 * @date Jun 14, 2025
 * @time 3:50:39 PM
 */
package main.revision.meta.medium;

import java.util.List;

import main.meta.medium.NestedListWeightSum.NestedInteger;

public class NestedListWeightSum {

    public int depthSum(List<NestedInteger> nestedList) {
        // Validate input
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int depthSum = 0;

        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                // For integer values, multiply by current depth
                depthSum += item.getInteger() * depth;
            } else {
                // For nested lists, increment depth and recurse
                depthSum += dfs(item.getList(), depth + 1);
            }
        }

        return depthSum;
    }

}
