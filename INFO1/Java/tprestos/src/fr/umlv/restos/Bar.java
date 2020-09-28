package fr.umlv.restos;

import java.util.Objects;

public class Bar extends Establishment {
    private final boolean afterEight;

    public Bar(String name, boolean afterEight, int stars) {
        super(name, stars);
        this.afterEight = afterEight;
    }

    public Bar(String name, boolean afterEight) {
        this(name, afterEight, 0);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(name);
        for(int i=0; i < stars; i++) { s.append("*"); }

        s.append(" (");
        if (!afterEight) {
            s.append("not ");
        }
        s.append("open after eight");
        s.append(")");
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Bar)) {
            return false;
        }

        Bar r = (Bar) o;
        return name.equals(r.name) && afterEight == r.afterEight && stars == r.stars;
    }

    @Override
    public int hashCode() {
        return Objects.hash(afterEight);
    }
}
