package fr.umlv.restos;

public abstract class Establishment {
    protected final String name;
    protected final int stars;

    public Establishment(String name, int stars) {
        if(name == null || stars < 0) {
            throw new IllegalArgumentException("Bad parameter");
        }
        this.name = name;
        this.stars = stars;
    }

    public int stars() {
        return stars;
    }
}
