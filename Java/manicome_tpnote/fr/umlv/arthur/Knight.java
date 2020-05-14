package fr.umlv.arthur;

import java.util.List;
import java.util.Objects;

public class Knight {
    private final String name;
    private final List<Equipment> equipments;
    private Heroicity heroicity;

    public Knight(String name, Equipment...equipments) {
        if (name.length() == 0 || equipments.length == 0) {
            throw new IllegalArgumentException();
        }

        this.name = Objects.requireNonNull(name);
        this.equipments = List.of(Objects.requireNonNull(equipments));
        this.heroicity = new Heroicity(1);

        boolean hasSwd = false;
        for (Equipment equipment : equipments) {
            Objects.requireNonNull(equipment);

            if (equipment instanceof Sword) {
                hasSwd = true;
            }
        }

        if (!hasSwd) {
            throw new IllegalArgumentException();
        }
    }

    public String name() {
		return name;
	}

	public int damage() {
        int damage = 0;
        for (Equipment equipment : equipments) {
            damage += equipment.damage();
        }

		return heroicity.applyHeroicityDamage(damage);
    }

    public int protection() {
        int protection = 0;
        for (Equipment equipment : equipments) {
            protection += equipment.protection();
        }

		return heroicity.applyHeroicityProtection(protection);
    }
    
    public boolean isBetterThan(Knight opponent) {
        int damage = damage() - Objects.requireNonNull(opponent).damage();
        
        if (damage == 0) {
            return protection() > Objects.requireNonNull(opponent).protection();
        }

        return damage > 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( name );
        builder.append( " damage: " );
        builder.append( damage() );
        builder.append( " protection: " );
        builder.append( protection() );
        builder.append( "\n" );
        builder.append( "  " );

        builder.append("[");
        for (Equipment equipment : equipments) {
            builder.append(equipment.name());
            builder.append(", ");
        }
        builder.delete(builder.length() - 2, builder.length());
        builder.append("]");

        return builder.toString();
    }

    public Heroicity getHeroicity() {
        return heroicity;
    }

    public void setHeroicity(Heroicity heroicity) {
        this.heroicity = Objects.requireNonNull(heroicity);
    }

    public boolean isHeroic() {
        return heroicity.isHeroic();
    }
}