/**
 * @author agond
 * @date Jun 14, 2025
 * @time 3:18:59 PM
 */
package main.revision.meta.medium;

import java.util.*;

import main.revision.meta.TreeNode;

public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();
            int lastValue = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();
                lastValue = node.val;

                if (node.left != null) {
                    q.offer(node.left);
                } else if (node.right != null) {
                    q.offer(node.right);
                }
            }

            result.add(lastValue);
        }

        return result;
    }

}
