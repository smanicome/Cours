package fr.umlv.geom;

import java.util.Objects;

public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = Objects.requireNonNull(x);
		this.y = Objects.requireNonNull(y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "(" + x + ',' + y + ')';
	}
	
	public void translate(int dx, int dy) {
	  x += dx;
	  y += dy;
	}

	public static Point from(Point p) {
		Point copy;
		copy = new Point(p.getX(), p.getY());
		return copy;
	}
}
