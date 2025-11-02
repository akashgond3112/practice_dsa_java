package main.revision.october.meta;

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
