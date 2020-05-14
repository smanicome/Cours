package fr.umlv.geom;

import java.util.Objects;

public class Circle {
    private final Point center;
    private final int radius;

    public Circle(Point center, int radius) {
        this.center = Point.from(Objects.requireNonNull(center));
        this.radius = Objects.requireNonNull(radius);
    }

    @Override
    public String toString() {
        return "Cercle [center=" + center + ", radius=" + radius + ", surface=" + surface() +"]";
    }

    public void translate(int dx, int dy) {
        center.translate(dx, dy);
    }

    public Point getCenter() {
        return Point.from(center);
    }

    public double surface() {
        return Math.PI * Math.pow(radius, 2); 
    }

    public boolean contains(Point p) {
        double x2 = Math.pow( (center.getX() - p.getX()), 2 );
        double y2 = Math.pow( (center.getY() - p.getY()), 2 );
        double distance = Math.sqrt( x2 + y2 );

        return (distance >= 0 && distance <= radius);
    }

    public static boolean contains(Point p, Circle... circles) {
        for (Circle circle : circles) {
            if (circle.contains(p)) {
                return true;
            }
        }

        return false;
    }
}