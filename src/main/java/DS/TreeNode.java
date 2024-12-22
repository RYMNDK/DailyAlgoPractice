package DS;

// binary tree
public class TreeNode<T> {
    public TreeNode(T x) {
        value = x;
    }

    public TreeNode(T x, TreeNode<T> l, TreeNode<T> r) {
        this(x);
        this.left = l;
        this.right = r;
    }

    public T value;
    public TreeNode<T> left;
    public TreeNode<T> right;
}
