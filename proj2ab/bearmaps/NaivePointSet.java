package bearmaps;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NaivePointSet implements PointSet{

    Set<Point> pointsSet = new HashSet<>();
    public NaivePointSet(List<Point> points) {
        for (Point p: points) {
            pointsSet.add(p);
        }
    }
    @Override
    public Point nearest(double x, double y) {
        Point targetPoint = new Point(x, y);
        Point resultPoint = null;
        double minDistance = Double.MAX_VALUE;
        for (Point p : pointsSet) {
            double distance = Point.distance(p, targetPoint);
            if (minDistance > distance) {
                minDistance = distance;
                resultPoint = p;
            }
        }
        return resultPoint;
    }
}

