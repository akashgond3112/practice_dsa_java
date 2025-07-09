/**
 * @author agond
 * @date Jul 09, 2025
 * @time 8:12:29 PM
 */
package main.revision.meta.medium;

import main.revision.meta.TreeNode;
import java.util.*;

public class CloneGraph {

    public TreeNode cloneGraph(TreeNode node) {

        Map<TreeNode, TreeNode> map = new HashMap<>();

        return dfs(node, map);
    }

    private TreeNode dfs(TreeNode node, Map<TreeNode, TreeNode> map) {
        if (node == null) {

            return null;
        }

        if (map.containsKey(node)) {
            return map.get(node);
        }

        TreeNode copy = new TreeNode(node.val);
        map.put(node, copy);

        for (TreeNode nei : node.neighbors) {
            copy.neighbors.add(dfs(nei, map));
        }

        return copy;
    }
}
