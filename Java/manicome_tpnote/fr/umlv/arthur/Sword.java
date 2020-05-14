package fr.umlv.arthur;

import java.util.Objects;

public class Sword extends Equipment {
    private final String name;
    private final int damage;

    public Sword(String name, int damage) {
        super(name, damage, 0);

        if (name.length() == 0 || damage < 1) {
            throw new IllegalArgumentException();
        }

        this.name = Objects.requireNonNull(name);
        this.damage = Objects.requireNonNull(damage);
    }

	public String name() {
		return name;
	}

	public int damage() {
		return damage;
	}

	@Override
	public String toString() {
		return "Sword [damage=" + damage + ", name=" + name + "]";
	}

	public boolean equals(Object obj) {
        if(!(obj instanceof Sword)) {
            return false;
        }
        Sword swd = (Sword) obj;
		return (name().equals(swd.name())) && (damage() == swd.damage());
	}
}
