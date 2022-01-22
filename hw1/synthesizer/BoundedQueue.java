package synthesizer;
import java.util.Iterator;

/**
 * Bounded queue interface
 * @author Luis Lin
 * @param <T>
 */

public interface BoundedQueue<T> extends Iterable<T> {
    int capacity(); // return size of the buffer
    int fillCount(); //return number of items currently in the buffer
    void enqueue(T x); // add item x to the end
    T dequeue(); // delete and return item from the front
    T peek(); // return but not delete from the front

    Iterator<T> iterator();

    /** Is the buffer empty */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /** Is the buffer full */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
