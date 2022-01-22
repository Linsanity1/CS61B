package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug and Luis Lin
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer(10);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(1.0);
        arb.enqueue(2.0);
        arb.enqueue(3.0);
        assertEquals((Double) 1.0, (Double) arb.dequeue());
        Double sum = 0.0;
        for (Double d : arb) {
            sum += d;
        }
        assertEquals((Double) 5.0, sum);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
