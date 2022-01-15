/** Array deque class. */

/** Invariants:
 * addLast: The next item we want to add, will go into position nextLast
 * addFirst: The first item we want to add, will go into position nextFirst
 * size: The number of items in the list should be size.
 */

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Constructor */
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[15]; // array length less than 16
        nextFirst = 0;
        nextLast = 1;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (nextFirst <= nextLast) {
            System.arraycopy(items, nextFirst + 1, a, nextFirst + 1, size);
        }
        else {
            System.arraycopy(items, nextFirst + 1, a, nextFirst + 1, items.length - nextFirst);
            System.arraycopy(items, 0, a, 0, size - (items.length - nextFirst));
        }
        items = a;
    }

    /** Resizes the arrayDeque referring to the relation between size and length */
    private void resizeArray() {
        if (size == items.length) {
            resize(size * 2);
        } else if (size <= items.length / 4) {
            resize(size / 2);
        }
    }

    /** Adds an item to the arrayDeque where the nextFirst points. */
    public void addFirst(T item) {
        items[nextFirst] = item;
        size++;
        nextFirst--;
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
        resizeArray();
    }

    /** Adds an item to the arrayDeque where the nextLast points. */
    public void addLast(T item) {
        items[nextLast] = item;
        size++;
        nextLast++;
        if (nextLast >= items.length) {
            nextLast = 0;
        }
        resizeArray();
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            if (nextFirst + 1 + i < items.length) {
                System.out.print(items[nextFirst + 1 + i] + " ");
            }
            else {
                System.out.print(items[nextFirst + 1 + i - items.length] + " ");
            }
            System.out.println();
        }
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (items[nextFirst + 1] == null) {
            return null;
        }
        if (nextFirst + 1 == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
        T rmItem = items[nextFirst];
        items[nextFirst] = null;
        size--;
        resizeArray();
        return rmItem;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (items[nextLast - 1] == null) {
            return null;
        }
        if (nextLast - 1 == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast--;
        }
        T rmItem = items[nextLast];
        items[nextLast] = null;
        size--;
        resizeArray();
        return rmItem;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (nextFirst + 1 + index < items.length) {
            return items[nextFirst + 1 + index];
        } else {
            return items[nextFirst + 1 + index - items.length];
        }
    }
}
