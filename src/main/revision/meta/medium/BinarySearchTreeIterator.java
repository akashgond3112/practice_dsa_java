/**
 * @author agond
 * @date Jul 06, 2025
 * @time 9:06:17 AM
 */
package main.revision.meta.medium;

import java.util.Stack;
import main.revision.meta.TreeNode;

public class BinarySearchTreeIterator {

    Stack<TreeNode> stack = new Stack<>();

    public BinarySearchTreeIterator(TreeNode root) {
        pushAll(root);
    }

    public int next() {
        TreeNode node = stack.pop();
        pushAll(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushAll(TreeNode root) {
        for (; root != null; stack.push(root), root = root.left) {
        }
    }
}
