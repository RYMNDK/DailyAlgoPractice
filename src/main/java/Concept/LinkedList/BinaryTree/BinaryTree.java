package Concept.LinkedList.BinaryTree;

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
        TreeNode<Integer> bst = new TreeNode<Integer>(5);
        insertBST(bst, new TreeNode<Integer>(2));
        insertBST(bst, new TreeNode<Integer>(7));
        insertBST(bst, new TreeNode<Integer>(1));
        insertBST(bst, new TreeNode<Integer>(3));
        insertBST(bst, new TreeNode<Integer>(4));
        insertBST(bst, new TreeNode<Integer>(6));
        insertBST(bst, new TreeNode<Integer>(8));

        printLevelOrderWithNulls(bst);

        int[] result = binaryTreeFC(bst, 6.5);
        System.out.println(Arrays.toString(result));
    }

    // validate BST


    // floor and ceil of binary tree
    public static int[] binaryTreeFC(TreeNode<Integer> root, double target) {
        int[] result = new int[2];
        result[0] = Integer.MIN_VALUE;
        result[1] = Integer.MAX_VALUE;
        binaryTreeFCHelper(root, target, result);
        return result;
    }

    public static void binaryTreeFCHelper(TreeNode<Integer> root, double target, int[] FC) {
        if (root != null) {
            System.out.println("cur_value: " + root.value + " target: " + target);
            if (root.value == target) {
                FC[0] = root.value;
                FC[1] = root.value;
            }
            else if (root.value < target) {
                FC[0] = root.value;
                binaryTreeFCHelper(root.right, target, FC);
            } else {
                FC[1] = root.value;
                binaryTreeFCHelper(root.left, target, FC);
            }
        }
    }

    // deepest node (dfs)
    public static TreeNode deepestNode(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        List<TreeNode<Integer>> results = new ArrayList<>();
        deepestNodeHelper(root, results);
        return results.getFirst();
    }

    private static int deepestNodeHelper(TreeNode<Integer> root, List<TreeNode<Integer>> result) {
        if (root == null) {
            result.clear();
            return 0;
        }
        List<TreeNode<Integer>> leftDeepestNodes = new ArrayList<>();
        List<TreeNode<Integer>> rightDeepestNodes = new ArrayList<>();

        int leftMaxHeight = deepestNodeHelper(root.left, leftDeepestNodes);
        int rightMaxHeight = deepestNodeHelper(root.right, rightDeepestNodes);

        if (leftMaxHeight == rightMaxHeight) {
            if (leftMaxHeight == 0) {
                result.add(root);
                return 1;
            } else {
                // take left over right
                result.addAll(leftDeepestNodes);
                result.addAll(rightDeepestNodes);
                return  leftMaxHeight + 1;
            }
        }
        else if (leftMaxHeight > rightMaxHeight) {
            result.addAll(leftDeepestNodes);
            return leftMaxHeight + 1;
        }
        else {
            result.addAll(rightDeepestNodes);
            return rightMaxHeight + 1;
        }
    }

    // deepest node (bfs)
    public static TreeNode deepestNodeQueue(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode<Integer>> nextLevel = new LinkedList<>();
        nextLevel.offer(root);
        while (!nextLevel.isEmpty()) {
            int levelSize = nextLevel.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode<Integer> node = nextLevel.poll();
                if (node.left != null) {
                    nextLevel.offer(node.left);
                }
                if (node.right != null) {
                    nextLevel.offer(node.right);
                }
                // last node in the bfs traversal is the furthest
                if (nextLevel.isEmpty()) {
                    return node;
                }
            }
        }
        // will not be reached
        return null;
    }

    // todo:  116. Populating Next Right Pointers in Each Node
    // this one uses a unique binary tree structure i will print it instead

    // todo:  623. Add One Row to Tree

    // todo: take a sorted list convert it to balanced bst (target code time about 3-5 min)

    // Root to leaf - easy backtracking question
    public static List<String> rootToLeaf(TreeNode root) {
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

    // Unique BST2 - medium backtracking, return all trees
    public static List<TreeNode<Integer>> UniqueBST2(int nums) {
        //List<TreeNode<Integer>> allResults = new ArrayList<>();
        if (nums == 0) return new ArrayList<>();
        return uBST2bt(1, nums, new HashMap<>());
    }

    private static List<TreeNode<Integer>> uBST2bt(int from, int to, HashMap<String, List<TreeNode<Integer>>> memo) {
        // check memo
        String key = from+"->"+to;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        List<TreeNode<Integer>> allLocalResults = new ArrayList<>();
        // to is never equal to from
        if (to < from) {
            allLocalResults.add(null);
            return allLocalResults;
        } else {
            for (int i = from; i <= to; i++) {
                List<TreeNode<Integer>> localLeft = uBST2bt(from, i - 1, memo);
                List<TreeNode<Integer>> localRight = uBST2bt(i + 1, to, memo);
                // get all combination of left and right and attach the current node on top
                for (TreeNode<Integer> left : localLeft) {
                    for (TreeNode<Integer> right : localRight) {
                        TreeNode<Integer> root = new TreeNode<>(i);
                        root.left = left;
                        root.right = right;
                        allLocalResults.add(root);
                    }
                }
            }
        }
        // update memo
        memo.put(key, allLocalResults);
        return allLocalResults;
    }

    private static TreeNode<Integer> deepCopy(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        TreeNode<Integer> curNode = new TreeNode<>(root.value);
        curNode.left = deepCopy(root.left);
        curNode.right = deepCopy(root.right);
        return curNode;
    }

    // values are unique (no equals)
    private static TreeNode<Integer> insertBST(TreeNode<Integer> root, TreeNode<Integer> node) {
        if (root == null) {
            return node;
        }

        if (node.value < root.value) {
            root.left = insertBST(root.left, node);
        } else {
            root.right = insertBST(root.right, node);
        }
        return root;
    }

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

    // todo: postorder, can do without a stack (left right node)

    // todo: inorder, can do without a stack (left node right)

    // preorder, can do without a stack (node left right)
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

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode<Integer> node = queue.poll();
                System.out.print(node.value + " ");

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

            }

            System.out.println();
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

}
