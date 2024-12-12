package Concept.BinaryTree;

import com.sun.source.tree.Tree;

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


        TreeNode<Integer> node7 = new TreeNode<>(7);
        TreeNode<Integer> node5 = new TreeNode<>(5, node7, null);
        TreeNode<Integer> node4 = new TreeNode<>(4);
        TreeNode<Integer> node2 = new TreeNode<>(2, node4, node5);
        TreeNode<Integer> node6 = new TreeNode<>(6);
        TreeNode<Integer> node3 = new TreeNode<>(3, null, node6);
        TreeNode<Integer> root = new TreeNode<>(1, node2, node3);

        // long startTime = System.nanoTime();

        TreeNode deepest = deepestNodeQueue(root);

        // long endTime = System.nanoTime();

        System.out.println("Deepest Node Value: " + (deepest != null ? deepest.value : "null"));


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

    // 116. Populating Next Right Pointers in Each Node
    // this one uses a unique binary tree structure i will print it instead

    // 623. Add One Row to Tree

    // take a sorted list convert it to balanced bst (target code time about 3-5 min)

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

    // postorder, can do without a stack (left right node)

    // inorder, can do without a stack (left node right)

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

    // sorted array to bst
}
