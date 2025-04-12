package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> x = new ArrayRingBuffer<Double>(10);
        x.enqueue(33.1); 
        x.enqueue(44.8); 
        x.enqueue(62.3); 
        x.enqueue(-3.4); 
        assertTrue(x.dequeue()==33.1);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
