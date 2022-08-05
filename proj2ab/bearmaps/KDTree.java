package bearmaps;

import java.util.List;

public class KDTree implements PointSet{
    class Node{
        double xx;
        double yy;
        Node leftChild;
        Node rightChild;
        Node(double x, double y, Node lc, Node rc) {
            xx = x;
            yy = y;
            leftChild = lc;
            rightChild = rc;
        }

        Node(Point p) {
            xx = p.getX();
            yy = p.getY();
            leftChild = null;
            rightChild = null;
        }

        int compareByX(Node node) {
            return xx <= node.xx ? -1 : 1;
        } //

        int compareByY(Node node) {
            return yy <= node.yy ? -1 : 1;
        }

        boolean equal(Node node) {
            return xx == node.xx && yy == node.yy;
        }

        double distance(Point point) {
            double xDistance = xx * xx - point.getX() * point.getX();
            double yDistance = yy * yy - point.getY() * point.getY();
            return xDistance + yDistance;
        }
    }

    Node treeRoot;

    /*
    Two points compare by x when depth is
    two points compare by y when depth is odd
    */


    public KDTree(List<Point> points) {
        for (Point p : points) {
            treeRoot = put(treeRoot, new Node(p), 0);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Node best = new Node(new Point(100000, 100000));
        return null;
    }

    private Node nearest(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        if (n.distance(goal) < best.distance(goal)) {
            best = n;
        }
        best = nearest(n.leftChild, goal, best);
        best = nearest(n.rightChild, goal, best);
        return best;
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
