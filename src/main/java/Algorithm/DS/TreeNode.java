package Algorithm.DS;

// binary tree
public class TreeNode {
    public TreeNode(int x) {
        value = x;
    }

    public TreeNode(int x, TreeNode l, TreeNode r) {
        this(x);
        this.left = l;
        this.right = r;
    }

    public int value;
    public TreeNode left;
    public TreeNode right;
}
