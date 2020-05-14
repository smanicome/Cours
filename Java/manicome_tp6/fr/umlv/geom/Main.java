package fr.umlv.geom;

public class Main {
    public static void main(String[] args) {
        var point = new Point(1, 2);
        var circle = new Circle(point, 2);
        System.out.println(circle);
        var ring = new Ring(point, 2, 1);
        System.out.println(ring);
    }
}