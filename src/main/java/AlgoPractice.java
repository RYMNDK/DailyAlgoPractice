import java.util.*;

// binary tree
class Tree<T> {
    Tree(T x) {
        value = x;
    }
    T value;
    Tree<T> left;
    Tree<T> right;
}

public class AlgoPractice {

    // useful things because i dont wanna write python/javascript

    // Hello world
    /*
        public class main {
            public static void main(String[] args) {
                System.out.println("Hello World");
            }
        }
     */

    public static void main(String[] args) {
        Tree root = new Tree(1);
        root.left = new Tree(7);
        root.left.left = new Tree(7);
        root.left.right = new Tree(-8);

        String[] result = printBinaryTree(null);

        for (String s: result) {
            System.out.print(s + " ");
        }

    }


    // print binary tree by level and seperated by a | operator
    // has placeholder for null nodes
    // BFS on binary tree
    // can reuse this pattern for level sum
    public static String[] printBinaryTree(Tree<Integer> tree) {
        // use bfs to print the tree
        Queue<Tree<Integer>> q = new LinkedList<>();
        ArrayList<String> al = new ArrayList<>();

        q.offer(tree);
        while (!q.isEmpty()) {
            int levelSize = q.size();
            boolean hasNonNullNode = false;

            for (int i = 0; i < levelSize; i++) {
                Tree<Integer> node = q.poll();

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

    // no direct conversion from List<Integer> to int[]
    public int[] arrayListToStaticPrimitiveArray(List<Integer> result) {
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // convert any dynamic array to static array
    public Integer[] arrayListToStaticArray(List<Integer> result) {
        return result.toArray(Integer[]::new);
    }

    // for debugging
    // print a static array
    public void printIntArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // print an arraylist
    // print a linked list
    // print a binary tree
    // print a stack
    // print a queue
}
