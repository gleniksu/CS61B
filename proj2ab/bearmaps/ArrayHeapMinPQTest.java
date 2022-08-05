package bearmaps;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Test
    public void testAdd() {
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> n = new NaiveMinPQ<>();
        a.add("a", 2);
        a.add("b", 3);
        a.add("c", 5);
        a.add("d", 1);
        a.add("e", 4);
        n.add("a", 2);
        n.add("b", 3);
        n.add("c", 5);
        n.add("d", 1);
        n.add("e", 4);

    }

    @Test
    public void testGetSmallest() {
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> n = new NaiveMinPQ<>();
        a.add("a", 2);
        a.add("b", 3);
        a.add("c", 5);
        a.add("d", 1);
        a.add("e", 4);
        n.add("a", 2);
        n.add("b", 3);
        n.add("c", 5);
        n.add("d", 1);
        n.add("e", 4);
        assertEquals(a.getSmallest(), n.getSmallest());

    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> n = new NaiveMinPQ<>();
        a.add("a", 2);
        a.add("b", 3);
        a.add("c", 5);
        a.add("d", 1);
        a.add("e", 4);
        n.add("a", 2);
        n.add("b", 3);
        n.add("c", 5);
        n.add("d", 1);
        n.add("e", 4);
        assertEquals(n.removeSmallest(), a.removeSmallest());
        assertEquals(n.getSmallest(), a.getSmallest());
    }

    @Test
    public void testSize() {
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> n = new NaiveMinPQ<>();
        a.add("a", 2);
        a.add("b", 3);
        a.add("c", 5);
        a.add("d", 1);
        a.add("e", 4);
        n.add("a", 2);
        n.add("b", 3);
        n.add("c", 5);
        n.add("d", 1);
        n.add("e", 4);
        assertEquals(n.size(), a.size());
    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
        a.add("a", 2);
        a.add("b", 3);
        a.add("c", 5);
        a.add("d", 1);
        a.add("e", 4);
        assertTrue(a.contains("a"));
        assertTrue(a.contains("b"));
        assertTrue(a.contains("c"));
        assertTrue(a.contains("d"));
        assertTrue(a.contains("e"));
        assertFalse(a.contains("f"));
    }

    @Test
    public void testChangePriority() {
        /*
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> n = new NaiveMinPQ<>();
        a.add("a", 2);
        a.add("b", 3);
        a.add("c", 5);
        a.add("d", 1);
        a.add("e", 4);
        n.add("a", 2);
        n.add("b", 3);
        n.add("c", 5);
        n.add("d", 1);
        n.add("e", 4);
        assertEquals(n.getSmallest(), a.getSmallest());
        a.changePriority("d", 0);
        n.changePriority("d", 0);
        assertEquals(n.getSmallest(), a.getSmallest());
        a.changePriority("e", 0);
        n.changePriority("e", 0);
        assertEquals(n.getSmallest(), a.getSmallest());
        a.changePriority("e", -1);
        n.changePriority("e", -1);
        assertEquals(n.getSmallest(), a.getSmallest());

         */
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);
        minHeap.add(4, 4);
        minHeap.add(5, 0);
        minHeap.add(6, 3);
        minHeap.changePriority(5, 1.5);
        minHeap.changePriority(6, 0);
        assertEquals(6, (int) minHeap.getSmallest());
        assertEquals(6, (int) minHeap.removeSmallest());
        assertEquals(1, (int) minHeap.removeSmallest());
        assertEquals(5, (int) minHeap.getSmallest());
    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ExtrinsicMinPQ<Integer> minHeap = new NaiveMinPQ<>();

        for (int i = 0; i < 5000; i += 1) {
            minHeap.add(i, 100000 - i);
        }

        for (int i = 0; i < 4000; i++) {
            minHeap.removeSmallest();
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 1000.0 +  " seconds.");

        long start2 = System.currentTimeMillis();
        for (int j = 0; j < 10000; j += 1) {
            minHeap.changePriority(StdRandom.uniform(0, minHeap.size()), j);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end2 - start2) / 1000.0 +  " seconds.");
    }
}
