package fr.umlv.restos;

import java.util.Objects;

public class Resto extends Establishment {
    private final int covers;

    public Resto(String name, int covers, int stars) {
        super(name, stars);
        if(covers < 0) {
            throw new IllegalArgumentException("Bad parameter");
        }

        this.covers = covers;
    }
    public Resto(String name, int covers) {
        this(name, covers, 0);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(name);
        for(int i=0; i<stars; i++) { s.append("*"); }

        s.append(" (");
        s.append(covers);
        s.append(")");
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Resto)) {
            return false;
        }

        Resto r = (Resto) o;
        return name.equals(r.name) && covers == r.covers && stars == r.stars;
    }

    @Override
    public int hashCode() {
        return Objects.hash(covers);
    }
}
