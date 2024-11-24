package Concept;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

    public static int count(DataStructure.Tree<Integer> root) {
        if (root == null) {
            return 0;
        }
        else if (root.left == null && root.right == null) {
            return 1;
        }

        return 1 + count(root.left) + count(root.right);
    }

    public static int countIterative(DataStructure.Tree<Integer> root) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        Queue<DataStructure.Tree<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            DataStructure.Tree<Integer> node = queue.poll();
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

    public static int height(DataStructure.Tree<Integer> root) {
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
    public static Integer[] printPreorder(DataStructure.Tree<Integer> tr) {
        // assume tr not null
        Stack<DataStructure.Tree<Integer>> st = new Stack<>();
        ArrayList<Integer> al = new ArrayList<>();

        st.push(tr);
        while (!st.isEmpty()) {
            DataStructure.Tree<Integer> top = st.pop();

            if (top != null) {
                al.add(top.value);
                st.push(top.right);
                st.push(top.left);
            }

        }

        return al.toArray(new Integer[0]);
    }

    public static Integer[] printBFS(DataStructure.Tree<Integer> tr) {
        // assume tr not null
        Queue<DataStructure.Tree<Integer>> q = new LinkedList<>();
        ArrayList<Integer> al = new ArrayList<>();
        q.offer(tr);

        while (!q.isEmpty()) {
            DataStructure.Tree<Integer> first = q.poll();

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
    public static String[] printBinaryTree(DataStructure.Tree<Integer> tree) {
        // use bfs to print the tree
        Queue<DataStructure.Tree<Integer>> q = new LinkedList<>();
        ArrayList<String> al = new ArrayList<>();

        q.offer(tree);
        while (!q.isEmpty()) {
            int levelSize = q.size();
            boolean hasNonNullNode = false;

            for (int i = 0; i < levelSize; i++) {
                DataStructure.Tree<Integer> node = q.poll();

                if (node != null) {
                    al.add(node.value.toString());

                    // Enqueue left and right children, even if they are null
                    q.offer(node.left);
                    q.offer(node.right);

                    // Check if there's at least one non-null child
                    if (node.left != null || node.right != null) {
                        hasNonNullNode = true;
                    }
                } else {
                    al.add("∅");
                    // Enqueue null placeholders for left and right to keep tree structure
                    q.offer(null);
                    q.offer(null);
                }
            }

            // If there are no non-null nodes at the next level, stop the traversal
            if (!hasNonNullNode) {
                break;
            }

            al.add("|");
        }

        return al.toArray(String[]::new);

    }
}



