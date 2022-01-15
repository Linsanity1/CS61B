/** A Deque is a list of items of type T. */
public class LinkedListDeque<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        /* Constructors for Node. */
        public Node() {
            prev = null;
            next = null;
        }
        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /* The great sentinel node. */
    private Node sentinel;
    private int size;

    /* Creates an empty deque. */
    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /* Creates a deep copy of 'other'.*/
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        Node ptrCopy = sentinel;
        Node ptrOther = other.sentinel;
        while (ptrOther.next != other.sentinel) {
            ptrOther = ptrOther.next;
            ptrCopy.next = new Node(ptrOther.item, ptrCopy, sentinel);
            ptrCopy = ptrCopy.next;
            size += 1;
        }
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /* Adds an item of type T to the tail of the deque */
    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    /* Returns the number of items in the deque */
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space.
       Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        Node ptr = sentinel;
        while (ptr.next != sentinel) {
            ptr = ptr.next;
            System.out.print(ptr.item + " ");
        }
        System.out.println();
    }

    /* Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (sentinel.next != sentinel) {
            T firstItem = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return firstItem;
        }
        return null;
    }

    /* Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (sentinel.prev != sentinel) {
            T lastItem = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return lastItem;
        }
        return null;
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
       If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        Node ptr = sentinel;
        while (index >= 0) {
            index -= 1;
            ptr = ptr.next;
        }
        return ptr.item;
    }

    /* Same as get, but uses recursion. */
    private T getRecursiveHelp(Node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelp(start.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelp(sentinel.next, index);
    }
}


