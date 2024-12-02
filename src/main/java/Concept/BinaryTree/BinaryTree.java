package Concept.BinaryTree;

import com.sun.source.tree.Tree;

import java.util.*;

import static Concept.DataStructure.*;

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
        TreeNode tr = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)),  new TreeNode(5, new TreeNode(6), new TreeNode(7)));
        printTree(tr);


    }

    // root to leaf - easy backtracking question
    public static List<String> rtf(TreeNode root) {
        Stack<TreeNode> st = new Stack();
        String base = "";
        List<String> results = new ArrayList<>();

        st.push(root);
        while (!st.isEmpty()) {
            TreeNode node = st.pop();
            base += node.value;

            if (node.left == null && node.right == null) {

            } else {
                if (node.right != null) {
                    st.push(node.right);
                }
                if (node.left != null) {
                    st.push(node.left);
                }
            }

            base += "->";
        }

        return results;
    }

//    class Solution {
//        public List<String> binaryTreePaths(TreeNode root) {
//            LinkedList<String> paths = new LinkedList();
//            if (root == null)
//                return paths;
//
//            LinkedList<TreeNode> node_stack = new LinkedList();
//            LinkedList<String> path_stack = new LinkedList();
//            node_stack.add(root);
//            path_stack.add(Integer.toString(root.val));
//            TreeNode node;
//            String path;
//
//            while ( !node_stack.isEmpty() ) {
//                node = node_stack.pollLast();
//                path = path_stack.pollLast();
//                if ((node.left == null) && (node.right == null))
//                    paths.add(path);
//                if (node.left != null) {
//                    node_stack.add(node.left);
//                    path_stack.add(path + "->" + Integer.toString(node.left.val));
//                }
//                if (node.right != null) {
//                    node_stack.add(node.right);
//                    path_stack.add(path + "->" + Integer.toString(node.right.val));
//                }
//            }
//            return paths;
//        }
//    }

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

    // print binary tree by level and seperated by a | operator
    // has placeholder for null nodes
    // BFS on binary tree
    // can reuse this pattern for level sum
    //    public static String[] printBinaryTree(Tree<Integer> tree) {
    //        // check if your console can print ∅ and replace if required
    //        System.out.println("======================== Printing Tree ========================");
    //        // use bfs to print the tree
    //        Queue<Tree<Integer>> q = new LinkedList<>();
    //        ArrayList<String> al = new ArrayList<>();
    //
    //        q.offer(tree);
    //        while (!q.isEmpty()) {
    //            int levelSize = q.size();
    //            boolean hasNonNullNode = false;
    //
    //            for (int i = 0; i < levelSize; i++) {
    //                Tree<Integer> node = q.poll();
    //
    //                if (node != null) {
    //                    al.add(node.value.toString());
    //
    //                    // Enqueue left and right children, even if they are null
    //                    q.offer(node.left);
    //                    q.offer(node.right);
    //
    //                    // Check if there's at least one non-null child
    //                    if (node.left != null || node.right != null) {
    //                        hasNonNullNode = true;
    //                    }
    //                } else {
    //                    al.add("∅");
    //                    // Enqueue null placeholders for left and right to keep tree structure
    //                    q.offer(null);
    //                    q.offer(null);
    //                }
    //            }
    //
    //            // If there are no non-null nodes at the next level, stop the traversal
    //            if (!hasNonNullNode) {
    //                break;
    //            }
    //
    //            al.add("|");
    //        }
    //
    //        return al.toArray(String[]::new);
    //        System.out.println("======================== Printing Done ========================");
    //    }


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



