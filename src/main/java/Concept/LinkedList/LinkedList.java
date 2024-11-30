package Concept.LinkedList;

import java.util.Stack;

// linked list
class ListNode<T> {
    ListNode(T x) {
        this(x, null);
    }

    ListNode(T x, ListNode<T> n) {
        value = x;
        next = n;
    }
    T value;
    ListNode<T> next;

}

/* Techniques
 * - Sentinel node: append a Sentinel node in front of head to help with list traversal
 *
 */

public class LinkedList {
    public static void main(String[] args) {
        ListNode<Integer> n1 = new ListNode<>(1);

        // 1 → 2 → 3 → ∅
        ListNode<Integer> n123 = new ListNode<>(1,
                new ListNode<>(2,
                new ListNode<>(3)));

        printList(reverseWithoutArray(makeList(34567)));

    }

    // check if your console can print → and ∅ and replace if required
    public static void printList(ListNode head) {
        System.out.println("======================== Printing List ========================");
        ListNode pointer = head;
        while (pointer != null) {
            System.out.print(pointer.value + " → ");
            pointer = pointer.next;
        }
        System.out.println("∅");
        System.out.println("======================== Printing Done ========================");
    }

    // convert a number to linked list
    public static ListNode<Integer> makeList(long number) {
        ListNode<Integer> sentinel = new ListNode<>(-1);
        ListNode<Integer> pointer = sentinel.next;
        while (number > 0) {
            ListNode<Integer> newNode = new ListNode(number % 10, pointer);
            sentinel.next = newNode;
            pointer = newNode;
            number /= 10;
        }

        return sentinel.next;
    }

    // reverse linked with pointers, recursion

    // reverse, no array allowed.
    // random access data structure not allowed (or you can just read array from back)
    public static ListNode reverseWithoutArray(ListNode head){
        Stack<ListNode> st = new Stack<>();
        ListNode sentinel = new ListNode(-1);
        ListNode pointer = head;
        while (pointer != null) {
            st.push(pointer);
            pointer = pointer.next;
        }
        pointer = sentinel;
        while (!st.isEmpty()) {
            pointer.next = st.pop();
            pointer = pointer.next;

        }
        pointer.next = null;
        return sentinel.next;
    }
}


// todo:
// recent access list
// use chunks if larger more data size
// LRU

// reverse a linked list Recursive, iterative and iterative with extra space

// merge 2 sorted linked list

