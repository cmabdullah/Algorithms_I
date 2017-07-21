import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private static final boolean HORIZONTAL = true;
    private static final boolean VERTICAL = false;

    private class Node {
        private Point2D point;
        private boolean divideBy;
        private int size;
        private Node left;
        private Node right;

        public Node(Point2D point, boolean divideBy) {
            this.point = point;
            this.divideBy = divideBy;
            size = 1;
            left = null;
            right = null;
        }
    }

    private Node root;
    private Point2D nearestPoint;
    private double minDistance;

    public KdTree() {
        // construct an empty set of points
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException();
        root = insert(root, p, VERTICAL);
    }

    private Node insert(Node x, Point2D p, boolean divideBy) {
        if (x == null) return new Node(p, divideBy);
        if (p.compareTo(x.point) == 0) return x;

        double cmp;
        if (divideBy == VERTICAL)
            cmp = p.x() - x.point.x();
        else
            cmp = p.y() - x.point.y();

        if (cmp < 0)
            x.left = insert(x.left, p, !divideBy);
        else
            x.right = insert(x.right, p, !divideBy);

        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException();
        Node x = contains(root, p);
        return x != null;
    }

    private Node contains(Node x, Point2D p) {
        if (x == null) return null;

        if (p.compareTo(x.point) == 0) return x;

        double cmp;
        if (x.divideBy == VERTICAL)
            cmp = p.x() - x.point.x();
        else
            cmp = p.y() - x.point.y();

        if (cmp < 0)
            return contains(x.left, p);
        else
            return contains(x.right, p);
    }

    public void draw() {
        draw(root);
    }

    private void draw(Node x) {
        if (x == null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        x.point.draw();
        if (x.divideBy == VERTICAL) {
            StdDraw.setPenColor(StdDraw.BOOK_BLUE);
            StdDraw.line(x.point.x(), 0, x.point.x(), 1);
        } else {
            StdDraw.setPenColor(StdDraw.BOOK_RED);
            StdDraw.line(0, x.point.y(), 1, x.point.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException();

        ArrayList<Point2D> points = new ArrayList<>();
        range(root, rect, points);
        return points;
    }

    private void range(Node x, RectHV rect, ArrayList<Point2D> points) {
        if (x == null) return;
        if (rect.contains(x.point))
            points.add(x.point);
        if (x.divideBy == VERTICAL) {
            if (rect.xmax() < x.point.x())
                range(x.left, rect, points);
            else if (rect.xmin() >= x.point.x())
                range(x.right, rect, points);
            else {
                range(x.left, rect, points);
                range(x.right, rect, points);
            }
        } else {
            if (rect.ymax() < x.point.y())
                range(x.left, rect, points);
            else if (rect.ymin() >= x.point.y())
                range(x.right, rect, points);
            else {
                range(x.left, rect, points);
                range(x.right, rect, points);
            }
        }
    }

    public Point2D nearest(Point2D p) {

        if (p == null) throw new NullPointerException();
        nearestPoint = null;
        minDistance = Double.POSITIVE_INFINITY;
        nearest(root, p);
        return nearestPoint;
    }

    private void nearest(Node x, Point2D p) {
        if (x == null) return;

        double distance = p.distanceTo(x.point);
        if (distance < minDistance) {
            minDistance = distance;
            nearestPoint = x.point;
        }

        if (x.divideBy == VERTICAL) {
            if (p.x() < x.point.x()) {
                nearest(x.left, p);
                if (minDistance >= x.point.x() - p.x())
                    nearest(x.right, p);
            } else {
                nearest(x.right, p);
                if (minDistance >= p.x() - x.point.x())
                    nearest(x.left, p);
            }
        } else {
            if (p.y() < x.point.y()) {
                nearest(x.left, p);
                if (minDistance >= x.point.y() - p.y())
                    nearest(x.right, p);
            } else {
                nearest(x.right, p);
                if (minDistance >= p.y() - x.point.y())
                    nearest(x.left, p);
            }
        }
    }

    public static void main(String[] args) {
        
    }
}
