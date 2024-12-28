package Algorithm.Concept.Other;

import java.util.HashMap;
import java.util.Map;

class DoublyLinkedList {
    int key;
    String val;
    DoublyLinkedList next;
    DoublyLinkedList prev;

    public DoublyLinkedList(int key, String val) {
        this.key = key;
        this.val = val;
    }

}

class LFU {
    int capacity;
    HashMap<Integer, DoublyLinkedList> cache;
    HashMap<Integer, DoublyLinkedList> bookmark;
    HashMap<Integer, Integer> frequency;
    DoublyLinkedList head;
    DoublyLinkedList tail;


    public LFU(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        bookmark = new HashMap<>();
        frequency = new HashMap<>();
        head = new DoublyLinkedList(-1, "HEAD");
        tail = new DoublyLinkedList(-1, "TAIL");
        head.next = tail;
        tail.prev = head;


    }

    public String get(int key) {
        if (!cache.containsKey(key)) {
            return "-1";
        }
        else {
            // modify order
            // add one to frequency
            // remove(cache.get(key));
            // addToTail(cache.get(key)); // add this before bookmark
            return cache.get(key).val;
        }
    }

    public void put(int key, String val) {
        // If a node with this key already exists, remove it from the
        // linked list.
        if (cache.containsKey(key)) {
            cache.get(key).val = val;
        }

        DoublyLinkedList node = new DoublyLinkedList(key, val);
        cache.put(key, node);
        // Remove the least recently used node from the cache if adding
        // this new node will result in an overflow.
//        if (cache.size() > capacity) {
//            cache.remove(key);
//            remove(head.next);
//        }

//        // add to last of n + 1
//        if (bookmark.containsKey(2)) {
//            // add to last of 2 (1)
//            addToPrev(node, bookmark.get(1));
//        } else {
//            // add to last of tail (1)
//            bookmark.put(2, node);
//            addToPrev(node, tail);
//        }
    }

    // After insert: target.prev <-> node <-> target
    private void addToPrev(DoublyLinkedList node, DoublyLinkedList target) {
        DoublyLinkedList prevNode = target.prev;
        node.prev = prevNode;
        node.next = tail;
        prevNode.next = node;
        tail.prev = node;
    }

    // pass in tail for this one, merge this with prev function
    private void addToTail(DoublyLinkedList node) {
        DoublyLinkedList prevNode = tail.prev;
        node.prev = prevNode;
        node.next = tail;
        prevNode.next = node;
        tail.prev = node;
    }

    private void remove(DoublyLinkedList node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // For testing
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Print Capacity
        sb.append("Size: ")
                .append(cache.size()).append("/").append(capacity).append(" ")
                .append((cache.size() == capacity ? "FULL!" : "not full."))
                .append("\n");

        // Print least used, least accessed key
        sb.append("Least used Key (").append(head.next.key).append(", ").append(head.next.val).append(")\n");

        // Print HashMap entries for (K,V)
        sb.append("Cache Entries:\n");
        for (Map.Entry<Integer, DoublyLinkedList> entry : cache.entrySet()) {
            sb.append("Key: ").append(entry.getKey()).append(", Value: ")
            .append("(").append(entry.getValue().key).append(", ").append(entry.getValue().val).append(")\n");
        }

        // Print HashMap entries for bookmark
        sb.append("Bookmark Entries:\n");
        for (Map.Entry<Integer, DoublyLinkedList> entry : bookmark.entrySet()) {
            sb.append("Key: ").append(entry.getKey()).append(", Value: ")
                    .append("(").append(entry.getValue().key).append(", ").append(entry.getValue().val).append(")\n");
        }

        // Print HashMap entries for Frequency
        sb.append("Frequency Entries:\n");
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            sb.append("Key: ").append(entry.getKey()).append(", Value: ")
                    .append("(").append(entry.getValue()).append(", ").append(entry.getValue()).append(")\n");
        }

        // Print Doubly Linked List including sentinel nodes
        sb.append("Doubly Linked List: \n");
        DoublyLinkedList current = head;
        while (current != null) {
            sb.append("(").append(current.key).append(", ").append(current.val).append(")");
            if (current.next != null) {
                sb.append(" <-> ");
            }
            current = current.next;
        }

        return sb.toString();
    }
}

public class LFURunner {
    // Code runner
    public static void main(String[] args) {
        LFU lfu = new LFU(3);

        lfu.put(1, "10");
        lfu.put(2, "20");
        lfu.put(3, "30");
        System.out.println(lfu);
    }
}
