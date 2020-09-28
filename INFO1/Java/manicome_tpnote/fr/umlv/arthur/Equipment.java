package fr.umlv.arthur;

public class Equipment {
    private final String name;
    private final int damage;
    private final int protection;


    public Equipment(String name, int damage, int protection) {
        this.name = name;
        this.damage = damage;
        this.protection = protection;
    }

    @Override
    public String toString() {
        return "Equipment [name=" + name + ", damage=" + damage + ", protection=" + protection + "]";
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Equipment)) {
            return false;
        }

        Equipment eqp = (Equipment) obj;
		return (name().equals(eqp.name())) && (damage == eqp.damage) && (protection == eqp.protection);
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

}