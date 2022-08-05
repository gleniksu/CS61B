import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class TestSortAlgs {
    private Queue generateDoubleQueue(int n) {
        Queue<Double> queue = new Queue<>();
        Random rand = new Random(123);
        for (int i = 0; i < n; i++) {
            queue.enqueue(rand.nextDouble());
        }
        return queue;
    }

    @Test
    public void testQuickSort() {
        for (int i = 1; i < 1000; i++) {
            Queue<Double> queue = generateDoubleQueue(i);
            Queue<Double> res = QuickSort.quickSort(queue);
            assertTrue(isSorted(res));
        }
    }

    @Test
    public void testMergeSort() {
        for (int i = 1; i < 1000; i++) {
            Queue<Double> queue = generateDoubleQueue(i);
            Queue<Double> res = MergeSort.mergeSort(queue);
            assertTrue(isSorted(res));
        }
    }

    /*@Test
    public void testIsSorted() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("Joe");
        queue.enqueue("Omar");
        queue.enqueue("Itai");
        assertTrue(isSorted(queue));
    }*/

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
