package fr.umlv.arthur;

import java.util.Objects;

public class Bow extends Equipment {
    private final String name;
    private final int damage;
    private final int protection;

    public Bow(String name, int damage, int protection) {
        super(name, damage, protection);

        if (name.length() == 0 || damage < 1 || protection < 1) {
            throw new IllegalArgumentException();
        }

        this.name = Objects.requireNonNull(name);
        this.damage = Objects.requireNonNull(damage);
        this.protection = Objects.requireNonNull(protection);
    }

	public String name() {
		return name;
	}

	public int damage() {
		return damage;
    }
    
    public int protection() {
		return protection;
	}

	@Override
	public String toString() {
		return "Sword [name=" + name + ", damage=" + damage + ", protection=" + protection + "]";
	}

	public boolean equals(Object obj) {
        if(!(obj instanceof Bow)) {
            return false;
        }
        Bow bow = (Bow) obj;
		return (name().equals(bow.name())) && (damage() == bow.damage()) && (protection() == bow.protection());
	}
}
