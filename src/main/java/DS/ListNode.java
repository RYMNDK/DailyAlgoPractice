package DS;

// linked list
public class ListNode<T> {
    public T value;
    public ListNode<T> next;

    public ListNode(T x) {
        this.value = x;
    }

    public ListNode(T x, ListNode<T> n) {
        this(x);
        next = n;
    }

}
