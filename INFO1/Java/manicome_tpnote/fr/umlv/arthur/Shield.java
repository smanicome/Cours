package fr.umlv.arthur;

import java.util.Objects;

public class Shield extends Equipment {
    private final String name;
    private final int protection;

    public Shield(String name, int protection) {
        super(name, 0, protection);

        if (name.length() == 0 || protection < 1) {
            throw new IllegalArgumentException();
        }

        this.name = Objects.requireNonNull(name);
        this.protection = Objects.requireNonNull(protection);
    }

    public String name() {
        return name;
    }

    public int protection() {
        return protection;
    }

    @Override
    public String toString() {
        return "Shield [name=" + name + ", protection=" + protection + "]";
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Shield)) {
            return false;
        }
        Shield shd = (Shield) obj;
		return (name().equals(shd.name())) && (protection == shd.protection());
	}
}