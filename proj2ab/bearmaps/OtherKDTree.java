package bearmaps;

import com.sun.scenario.effect.Brightpass;

import java.util.List;

public class OtherKDTree implements PointSet{
    class Node{
        Point point;
        Node leftChild;
        Node rightChild;
        Node(double x, double y, Node lc, Node rc) {
            point = new Point(x, y);
            leftChild = lc;
            rightChild = rc;
        }

        Node(Point p) {
            point = p;
            leftChild = null;
            rightChild = null;
        }

        int compareByX(Node node) {
            return point.getX() <= node.point.getX() ? -1 : 1;
        } //

        int compareByY(Node node) {
            return point.getY() <= node.point.getY() ? -1 : 1;
        }

        boolean equal(Node node) {
            return point.getX() == node.point.getX() && point.getY() == node.point.getY();
        }

        double distance(Point p) {
            return Point.distance(point,p);
        }
    }

    Node treeRoot;

    /*
    Two points compare by x when depth is
    two points compare by y when depth is odd
    */


    public OtherKDTree(List<Point> points) {
        for (Point p : points) {
            treeRoot = put(treeRoot, new Node(p), 0);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        return nearest(treeRoot, new Point(x, y), treeRoot, 0).point;
    }

    private Node nearest(Node n, Point goal, Node best, int level) {
        if (n == null) {
            return best;
        }
        if (n.distance(goal) < best.distance(goal)) {
            best = n;
        }
        Node goodSide, badSide;
        double cmpVal = axisCompare(level, n.point, goal);

        if (cmpVal > 0) {
            goodSide = n.leftChild;
            badSide = n.rightChild;
        } else {
            goodSide = n.rightChild;
            badSide = n.leftChild;
        }
        best = nearest(goodSide, goal, best, level + 1);
        if (cmpVal * cmpVal < Point.distance(best.point, goal)) {
            best = nearest(badSide, goal, best, level + 1);
        }
        return best;
    }

    private double axisCompare(int level, Point nodePoint, Point goalPoint) {
        double result = 0;
        if (level % 2 == 0) {
            result = nodePoint.getX() - goalPoint.getX();
        } else {
            result = nodePoint.getY() - goalPoint.getY();
        }
        return result;
    }

    /**
     * Helper functions
     */

    private Node put(Node root, Node node, int depth) {
        if (root == null) {
            return node;
        }
        if (root.equal(node)) {
            return root;
        }
        if (depth % 2 == 0) {
            if (root.compareByX(node) == 1) { //root.x > node.x
                root.leftChild = put(root.leftChild, node, depth + 1);
            } else { //root.x =< node.x
                root.rightChild = put(root.rightChild, node, depth + 1);
            }
        } else {
            if (root.compareByY(node) == 1) { //root.y > node.y
                root.leftChild = put(root.leftChild, node, depth + 1);
            } else { //root.y <= node.y
                root.rightChild = put(root.rightChild, node, depth + 1);
            }
        }
        return root;
    }
}
