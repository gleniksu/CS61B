package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        int expected = 10;
        assertEquals(expected, arb.capacity());// Test the method capacity.
        arb.enqueue(new Integer(1));
        arb.enqueue(new Integer(2));
        ;
        expected = 2;
        assertEquals(expected, arb.fillCount());
    }

    @Test
    public void testEnqueue() {

        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(4);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.dequeue();
        arb.enqueue(5);
        Integer expected = new Integer(2);
        assertEquals(expected, arb.peek());
        assertTrue(arb.isFull());
        assertEquals(expected, arb.dequeue());
    }

}
