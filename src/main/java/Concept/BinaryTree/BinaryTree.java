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

        for (String element : rtf(root)) {
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

    // valid bst 2 - medium backtracking question

    public static int countRecursive(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
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
            count++;
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

    // make array with bfs
    public static Integer[] printBFS(TreeNode<Integer> tr) {
        if (tr == null) {
            return new Integer[0];
        }

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
    // print tree with BFS, level traversal (no placeholder)
    public static void printTreeBFS(TreeNode<Integer> root) {
        System.out.println("Printing tree...");
        if (root == null) {
            System.out.println("Empty tree.");
            return;
        }

        int levelCount = 1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            for (int i = 0; i < levelCount; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.value + " ");

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

            }

            System.out.println();
            levelCount *= 2;
        }
        System.out.println("Printing done.");
    }

    // print bfs with null placeholders
    public static void printLevelOrderWithNulls(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // The idea is to complete each level fully,
        // meaning if we know the level size at the start,
        // we process exactly that many nodes, printing them
        // and enqueueing their children (even if null).

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current == null) {
                    sb.append("∅ ");
                    // Even if we encounter null, to keep structure,
                    // we still add children as null to queue to maintain spacing.
                    queue.offer(null);
                    queue.offer(null);
                } else {
                    sb.append(current.value).append(" ");
                    queue.offer(current.left);
                    queue.offer(current.right);
                }
            }

            // Trim trailing space and print the level
            System.out.println(sb.toString().trim());

            // Check if next level is all nulls; if yes, we can stop
            // Because once you've hit the bottom (or missing children)
            // the following levels will just be null "values".
            boolean allNull = true;
            for (TreeNode node : queue) {
                if (node != null) {
                    allNull = false;
                    break;
                }
            }
            if (allNull) break;
        }
    }

    // array to bst
}