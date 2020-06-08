package fr.umlv.restos;

public class Restaurant {
    private final String name;
    private final int covers;
    private final int stars;

    public Restaurant(String name, int covers, int stars) {
        if(name == null || covers < 0 || stars < 0) {
            throw new IllegalArgumentException("Bad parameter");
        }
        this.name = name;
        this.covers = covers;
        this.stars = stars;
    }
    public Restaurant(String name, int covers) {
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
        if (!(o instanceof Restaurant)) {
            throw new IllegalArgumentException("Argument must be a Restaurant");
        }

        Restaurant r = (Restaurant) o;
        return name.equals(r.name) && covers == r.covers && stars == r.stars;
    }
    public static void main(String[] args) {
        Restaurant r1 = new Restaurant("Le p'tit creux", 25);
        Restaurant r2 = new Restaurant("Le p'tit creux", 25, 0);
        System.out.println(r2);  // affiche "Le p'tit creux (25)"
        Restaurant r3 = new Restaurant("Le Pré Catlan", 50, 3);
        System.out.println(r3);  // affiche "Le Pré Catlan*** (50)"
        System.out.println(r1.equals(r2)); // affiche "true"
        System.out.println(r1.equals(r3)); // affiche "false"
    }
}
