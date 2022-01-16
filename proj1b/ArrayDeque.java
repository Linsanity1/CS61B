/**
 * ArrayDeque implementing Deque interfaces.
 * @author Luis Lin
 */

/** Invariants:
 * addLast: The next item we want to add, will go into position nextLast
 * addFirst: The first item we want to add, will go into position nextFirst
 * size: The number of items in the list should be size.
 */

public class ArrayDeque<T> implements Deque<T> {
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
        if (nextFirst < nextLast) {
            System.arraycopy(items, nextFirst + 1, a, 0, size);
        } else {
            if (nextFirst + 1 == items.length) {
                System.arraycopy(items, 0, a, 0, size);
            } else {
                System.arraycopy(items, nextFirst + 1, a, 0, items.length - 1 - nextFirst);
                System.arraycopy(items, 0, a, items.length - 1 - nextFirst,
                          size - (items.length - 1 - nextFirst));
            }
        }
        nextFirst = a.length - 1;
        nextLast = size;
        items = a;
    }

    /** Resizes the arrayDeque referring to the relation between size and length */
    private void resizeArray() {
        if (size >= items.length - 3) {
            resize(items.length * 2);
        } else if (size <= items.length / 4) {
            resize(items.length / 2);
        }
    }

    /** Adds an item to the arrayDeque where the nextFirst points. */
    @Override
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
    @Override
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
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            if (nextFirst + 1 + i < items.length) {
                System.out.print(items[nextFirst + 1 + i] + " ");
            } else {
                System.out.print(items[nextFirst + 1 + i - items.length] + " ");
            }
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (nextFirst + 1 > items.length - 1) {
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

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (nextLast - 1 < 0) {
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
    @Override
    public T get(int index) {
        if (nextFirst + 1 + index < items.length) {
            return items[nextFirst + 1 + index];
        } else {
            return items[nextFirst + 1 + index - items.length];
        }
    }

    /** main
    public static void main(String[] args) {
        ArrayDeque A = new ArrayDeque();
        A.addFirst(1);
        A.addFirst(2);
        A.addFirst(3);
        A.addFirst(4);
        A.addLast(0);
        A.addLast(-1);
        A.addLast(-2);
        A.addFirst(5);
        A.addFirst(6);
        int six = (int) A.removeFirst();
        int five = (int) A.removeFirst();
        int negTwo = (int) A.removeLast();
        int negOne = (int) A.removeLast();
        int zero = (int) A.removeLast();
        int four = (int) A.removeFirst();
        int three = (int) A.removeFirst();
        int one = (int) A.removeLast();
        A.addFirst(100);
        A.printDeque();
    }
     */
}
