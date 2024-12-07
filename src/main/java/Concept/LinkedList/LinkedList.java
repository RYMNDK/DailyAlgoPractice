package Concept.LinkedList;

import java.util.List;
import java.util.Stack;

// linked list
class ListNode<T> {
    T value;
    ListNode<T> next;

    ListNode(T x) {
        this.value = x;
    }

    ListNode(T x, ListNode<T> n) {
        this(x);
        next = n;
    }

}

/* Techniques
 * - Sentinel node: append a Sentinel node in front of head to help with list traversal
 *
 */

//// implement linked list
//class IListNode {
//    Integer value;
//    IListNode next;
//
//    IListNode(int x) {
//        this.value = x;
//    }
//
//    IListNode(int x, IListNode n) {
//        this(x);
//        next = n;
//    }
//
//    public int get(int index) {
//
//    }
//
//    public void insertHead(int val) {
//
//    }
//
//    public void insertTail(int val) {
//
//    }
//
//    public boolean remove(int index) {
//
//    }
//
//    public List<Integer> getValues() {
//
//    }
//
//}

public class LinkedList {
    public static void main(String[] args) {
        ListNode<Integer> n1 = new ListNode<>(1);

        // 1 → 2 → 3 → ∅
        ListNode<Integer> n123 = new ListNode<>(1,
                new ListNode<>(2,
                new ListNode<>(3)));

        printList(R2(makeList(34567)));

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

    // reverse linked list with pointers
    public static ListNode R1(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    // reverse linked list with recursion
    public static ListNode R2(ListNode head) {
        if (head.next == null) {
            return head;
        } else {
            ListNode reversed = R2(head.next);

            System.out.println("head");
            printList(head);
            System.out.println("reversed");
            printList(reversed);

            head.next.next = head;
            head.next = null;
            return reversed;
        }
    }

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

