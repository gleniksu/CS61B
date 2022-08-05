package bearmaps;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;


import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KDTreeTest {
    /*Random rand = new Random(500);
    @Test
    public void testNaivePointSet() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        List<Point> lists = new ArrayList<>();
        lists.add(p1);
        lists.add(p2);
        lists.add(p3);
        NaivePointSet nn = new NaivePointSet(lists);
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println("ret.getX()" + ret.getX());; // evaluates to 3.3
        System.out.println(("ret.getY()" + ret.getY())); // evaluates to 4.4
    }

    // Simple test
    @Test
    public void testSimpleNearest() {
        Point a = new Point(2, 3);
        Point b = new Point(4, 2);
        Point c = new Point(4, 5);
        Point d = new Point (3, 3);
        Point e = new Point (1, 5);
        Point f = new Point (4, 4);
        Point p7 = new Point (4, 5);

        List<Point> lists = new ArrayList<>();
        lists.add(a);
        lists.add(b);
        lists.add(c);
        lists.add(d);
        lists.add(e);
        lists.add(f);
        OtherKDTree kdt = new OtherKDTree(lists);
        NaivePointSet np = new NaivePointSet(lists);
        kdt.nearest(0, 7);
        System.out.println(kdt.nearest(0, 7));
        assertTrue(kdt.nearest(0, 7).equals(new Point(1, 5)));
        assertEquals(kdt.nearest(0, 7), np.nearest(0, 7));
    }

    private Point randomPoint() {
        double x = rand.nextDouble();
        double y = rand.nextDouble();
        return new Point(x, y);
    }

    private List<Point> randomPoints(int N) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            points.add(randomPoint());
        }
        return points;
    }


    private void testWithPointsAndQueries(int pointCount, int queriesCount) {
        List<Point> points = randomPoints(pointCount);
        List<Point> queries = randomPoints(queriesCount);
        NaivePointSet nps = new NaivePointSet(points);
        OtherKDTree okdt = new OtherKDTree(points);

        for (Point p : queries) {
            Point exp = nps.nearest(p.getX(), p.getY());
            Point act = okdt.nearest(p.getX(), p.getY());
            assertEquals(exp, act);
        }
    }

    @Test
    public void testWith100000And10000Queries() {
        int pointCount = 100000;
        int queriesCount = 10000;
        testWithPointsAndQueries(pointCount, queriesCount);
    }


    // Random test
    @Test
    public void testRandomNearest() {
        List<Point> lists = new ArrayList<>();
        int n = StdRandom.uniform(1000, 10000);
        for (int i = 0; i < 100000; i++) {
            double x = StdRandom.uniform((double) 0, (double) 10000);
            double y = StdRandom.uniform((double) 0, (double) 10000);
            lists.add(new Point(x, y));
        }

        OtherKDTree kdt = new OtherKDTree(lists);
        NaivePointSet np = new NaivePointSet(lists);
        n = StdRandom.uniform(100, 1000);
        for (int i = 0; i < n; i++) {
            double x = StdRandom.uniform((double) 0, (double) 10000);
            double y = StdRandom.uniform((double) 0, (double) 10000);
            long startTime = System.currentTimeMillis();
            Point npPoint = np.nearest(x, y);
            long endTime = System.currentTimeMillis();
            long result = endTime - startTime;
            System.out.println("NaiveMinPQ version nearest: " + result);
            startTime = System.currentTimeMillis();
            Point kdtPoint = kdt.nearest(x, y);
            endTime = System.currentTimeMillis();
            result = endTime - startTime;
            System.out.println("KDTree version nearest: " + result);
            assertEquals(npPoint, kdtPoint);
        }

    }

    @Test
    public void testEfficiency() {
        int pointCount = 100000;
        int queriesCount = 10000;
        List<Point> points = randomPoints(pointCount);
        List<Point> queries = randomPoints(queriesCount);
        NaivePointSet nps = new NaivePointSet(points);
        OtherKDTree okdt = new OtherKDTree(points);

        System.out.print("NaivePointSet Spend Time: ");
        long startTime = System.currentTimeMillis();
        for (Point p : queries) {
            Point exp = nps.nearest(p.getX(), p.getY());
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0 + "s");

        System.out.print("KDTree Spend Time: ");
        startTime = System.currentTimeMillis();
        for (Point p : queries) {
            Point exp = okdt.nearest(p.getX(), p.getY());
        }
        endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0 + "s");

    }*/


    private static Random r = new Random(123);

    @Test
    public void testCorrectness() {
        List<Point> points = randomPoints(100000);

        NaivePointSet nps = new NaivePointSet(points);
        OtherKDTree kd = new OtherKDTree(points);

        List<Point> goals = randomPoints(10000);

        for (Point goal : goals) {
            Point expected = nps.nearest(goal.getX(), goal.getY());
            Point actual = kd.nearest(goal.getX(), goal.getY());
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testEfficiency() {
        List<Point> randomPoints = randomPoints(100000);

        OtherKDTree kd = new OtherKDTree(randomPoints);
        NaivePointSet nps = new NaivePointSet(randomPoints);

        List<Point> queryPoints = randomPoints(10000);

        long start = System.currentTimeMillis();
        for (Point p : queryPoints) {
            nps.nearest(p.getX(), p.getY());
        }
        long end = System.currentTimeMillis();
        System.out.println("NaivePointSet: " + (end - start) / 1000.0 + " seconds");

        start = System.currentTimeMillis();
        for (Point p : queryPoints) {
            kd.nearest(p.getX(), p.getY());
        }
        end = System.currentTimeMillis();
        System.out.println("KDTree: " + (end - start) / 1000.0 + " seconds");
    }

    private Point randomPoint() {
        return new Point(r.nextDouble(), r.nextDouble());
    }

    private List<Point> randomPoints(int N) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i += 1) {
            points.add(randomPoint());
        }
        return points;
    }

}
