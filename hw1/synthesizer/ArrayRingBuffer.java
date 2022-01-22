// Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;

/**
 * ArrayRingBuffer class
 * @author Luis Lin
 * @param <T>
 */
// Make sure to make this class and all of its methods public
// Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> implements Iterable<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // Create new array with capacity elements.
        // first, last, and fillCount should all be set to 0.
        // this.capacity should be set appropriately. Note that the local variable
        // here shadows the field we inherit from AbstractBoundedQueue, so
        // you'll need to use this.capacity to set the capacity.
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[this.capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update last.
        if (fillCount == capacity) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last += 1;
        if (last == capacity) {
            last = 0;
        }
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // Dequeue the first item. Don't forget to decrease fillCount and update first
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        fillCount -= 1;
        T item = rb[first];
        rb[first] = null;
        first += 1;
        if (first == capacity) {
            first = 0;
        }
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // Return the first item. None of your instance variables should change.
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    // When you get to part 5, implement the needed code to support iteration.
    /** Iterator method */
    @Override
    public Iterator<T> iterator() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<T> {
        int wizardPos = first;
        public boolean hasNext() {
            return wizardPos != last;
        }
        public T next() {
            T returnVal = rb[wizardPos];
            wizardPos += 1;
            if (wizardPos == capacity) {
                wizardPos = 0;
            }
            return returnVal;
        }
    }
}
