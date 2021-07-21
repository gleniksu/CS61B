import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class TestArrayDequeGold {
    @Test
    public void testAddMethod() {
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();

        /**
         * Test addLast.
         */
        for (int i = 0; i < 10; i++) {
            int random = StdRandom.uniform(100);
            ads.addLast(random);
            sad.addLast(random);
        }

        for (int i = 0; i < 10; i++) {
            int actual = sad.get(i);
            int expected = ads.get(i);
            assertEquals("Oh noooo!\nThis is bad in addLast():\n    random number " + actual
            + " not equal to " + expected + "!", expected, actual);
        }

        /**
         * Test addFirst.
         */
        for (int i = 0; i < 10; i++) {
            int random = StdRandom.uniform(100);
            ads.addFirst(random);
            sad.addFirst(random);
        }

        for (int i = 0; i < 10; i++) {
            int actual = sad.get(i);
            int expected = ads.get(i);
            assertEquals("Oh noooo!\nThis is bad in addFirst():\n   random number " + actual
            + " not equal to " + expected + "!", expected, actual);
        }

        /**
         * Test RemoveFirst
         */
        List<Integer> actualList = new ArrayList<>();
        List<Integer> expectedList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            actualList.add(sad.removeFirst());
            expectedList.add(ads.removeFirst());
        }
        for (int i = 0; i < 10; i++) {
            int actual = sad.get(i);
            int expected = ads.get(i);
            assertEquals();
        }
        for (int i = 0; i < 10; i++) {
            int actual = actualList.get(i);
            int expected = expectedList.get(i);
            assertEquals();
        }

        /**
         * Test RemoveLast
         */
        actualList.clear();
        expectedList.clear();
        for (int i = 0; i < 10; i++) {
            actualList.add(sad.removeLast());
            expectedList.add(ads.removeLast());
        }

        int actual = sad.size();
        int expected = ads.size();
        assertEquals();

        for (int i = 0; i < 10; i++) {
            actual = actualList.get(i);
            expected = expectedList.get(i);
            assertEquals();
        }
    }
}
