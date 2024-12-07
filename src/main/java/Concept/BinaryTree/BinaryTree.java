package Concept.BinaryTree;

import java.util.*;

// binary tree
class TreeNode<T> {
    TreeNode(T x) {
        value = x;
    }

    TreeNode(T x, TreeNode<T> l, TreeNode<T> r) {
        this(x);
        this.left = l;
        this.right = r;
    }

    T value;
    TreeNode<T> left;
    TreeNode<T> right;
}

public class BinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);

        for (String element: rtf(root)) {
            System.out.println(element);
        }

    }

    // root to leaf - easy backtracking question
    public static List<String> rtf(TreeNode root) {
        List<String> results = new ArrayList<>();
        if (root == null) {
            return results;
        }

        Stack<TreeNode> st = new Stack();
        Stack<String> basePath = new Stack();

        st.push(root);
        basePath.push("" + root.value);

        while (!st.isEmpty()) {
            TreeNode node = st.pop();
            String path = basePath.pop();

            if (node.left == null && node.right == null) {
                results.add(path);
            } else {
                if (node.right != null) {
                    st.push(node.right);
                    basePath.push(path + "->" + node.right.value);
                }
                if (node.left != null) {
                    st.push(node.left);
                    basePath.push(path + "->" + node.left.value);
                }
            }
        }

        return results;
    }

    public static int countRecursive(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        else if (root.left == null && root.right == null) {
            return 1;
        }

        return 1 + countRecursive(root.left) + countRecursive(root.right);
    }

    public static int countIterative(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<Integer> node = queue.poll();
            count ++;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return count;
    }

    public static int height(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        return Math.max(height(root.left), height(root.right)) + 1;
    }

    //    // postorder, can do without a stack
    //    public static Integer[] print(Tree<Integer> tr) {
    //        // assume tr not null
    //        Stack<Tree<Integer>> st = new Stack<>();
    //        ArrayList<Integer> al = new ArrayList<>();
    //
    //        st.push(tr);
    //
    //        while (!st.isEmpty()) {
    //            Tree<Integer> top = st.pop();
    //            if (top != null) {
    //                System.out.println("peek " + top.value);
    //                // if top is a leaf dont push null into stack
    //                if (top.right != null) {
    //                    System.out.println("push " + top.right.value);
    //                    st.push(top.right);
    //                }
    //
    //                if (top.left != null) {
    //                    System.out.println("push " + top.left.value);
    //                    st.push(top.left);
    //                }
    //
    //                if (top.right == null && top.left == null) {
    //                    System.out.println("print " + top.value);
    //                    al.add(top.value);
    //                }
    //            }
    //
    //        }
    //
    //        return al.toArray(al.toArray(new Integer[0]));
    //    }

    //    // inorder, can do without a stack
    //    public static Integer[] printInorder(Tree<Integer> tr) {
    //        // assume tr not null
    //        Stack<Tree<Integer>> st = new Stack<>();
    //        ArrayList<Integer> al = new ArrayList<>();
    //
    //        return al.toArray(al.toArray(new Integer[0]));
    //    }

    // preorder, can do without a stack (recursive)
    public static Integer[] printPreorder(TreeNode<Integer> tr) {
        // assume tr not null
        Stack<TreeNode<Integer>> st = new Stack<>();
        ArrayList<Integer> al = new ArrayList<>();

        st.push(tr);
        while (!st.isEmpty()) {
            TreeNode<Integer> top = st.pop();

            if (top != null) {
                al.add(top.value);
                st.push(top.right);
                st.push(top.left);
            }

        }

        return al.toArray(new Integer[0]);
    }

    public static Integer[] printBFS(TreeNode<Integer> tr) {
        // assume tr not null
        Queue<TreeNode<Integer>> q = new LinkedList<>();
        ArrayList<Integer> al = new ArrayList<>();
        q.offer(tr);

        while (!q.isEmpty()) {
            TreeNode<Integer> first = q.poll();

            if (first != null) {
                al.add(first.value);

                q.offer(first.left);
                q.offer(first.right);
            }
        }

        return al.toArray(al.toArray(new Integer[0]));
    }

    // binary level traversal (with bfs)

    // print binary tree in a visually friendly way
    public static void printTree(TreeNode<Integer> tree) {
        System.out.println("======================== Printing Tree ========================");
        if (tree == null) {
            System.out.println("Tree is empty");
            System.out.println("======================== Printing Done ========================");
            return;
        }

        // Compute the maximum depth of the tree
        int maxDepth = getMaxDepth(tree);

        // Use BFS to print the tree
        Queue<TreeNode<Integer>> q = new LinkedList<>();
        q.offer(tree);

        int level = 0;
        while (!q.isEmpty() && level < maxDepth) {
            int levelSize = q.size();
            // Calculate the number of spaces before the first node
            int spaces = (int) Math.pow(2, maxDepth - level - 1) - 1;
            // Calculate the number of spaces between nodes
            int betweenSpaces = (int) Math.pow(2, maxDepth - level) - 1;

            // Print leading spaces
            printSpaces(spaces);

            List<TreeNode<Integer>> nextLevelNodes = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode<Integer> node = q.poll();

                // Print node value or ∅ for null
                if (node != null) {
                    System.out.print(node.value);
                    nextLevelNodes.add(node.left);
                    nextLevelNodes.add(node.right);
                } else {
                    System.out.print("∅");
                    nextLevelNodes.add(null);
                    nextLevelNodes.add(null);
                }

                // Print spaces between nodes
                if (i < levelSize - 1) {
                    printSpaces(betweenSpaces);
                }
            }
            System.out.println();

            // Enqueue nodes for the next level
            for (TreeNode<Integer> nextNode : nextLevelNodes) {
                q.offer(nextNode);
            }

            level++;
        }

        System.out.println("======================== Printing Done ========================");
    }

    private static void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print("  ");
        }
    }

    private static int getMaxDepth(TreeNode<Integer> node) {
        if (node == null) {
            return 0;
        } else {
            int leftDepth = getMaxDepth(node.left);
            int rightDepth = getMaxDepth(node.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

}



