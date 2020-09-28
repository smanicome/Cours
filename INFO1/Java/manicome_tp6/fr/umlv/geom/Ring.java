package fr.umlv.geom;

import java.util.Objects;

public class Ring extends Circle {
    private final Point center;
    private final int radius;
    private final int internalRadius;

    public Ring(Point center, int radius, int internalRadius) {
        super(center, radius);
        
        if (internalRadius > radius) {
            throw new IllegalStateException("Internal radius must be lower than radius");
        }

        this.center = Point.from(Objects.requireNonNull(center));
        this.radius = Objects.requireNonNull(radius);
        this.internalRadius = Objects.requireNonNull(internalRadius);
    }

    @Override
    public String toString() {
        return "Ring [center=" + center + ", radius=" + radius + ", internalRadius=" + internalRadius + " , surface=" + surface() + "]";
    }

    @Override
    public double surface() {
        return ( (Math.PI * Math.pow(radius, 2)) - (Math.PI * Math.pow(internalRadius, 2)) ); 
    }

    @Override
    public boolean contains(Point p) {
        double x2 = Math.pow( (center.getX() - p.getX()), 2 );
        double y2 = Math.pow( (center.getY() - p.getY()), 2 );
        double distance = Math.sqrt( x2 + y2 );

        return (distance >= internalRadius && distance <= radius);
    }
    
    public static boolean contains(Point p, Ring... rings) {
        for (Ring ring : rings) {
            if (ring.contains(p)) {
                return true;
            }
        }

        return false;
    }
}