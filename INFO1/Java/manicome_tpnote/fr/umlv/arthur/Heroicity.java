package fr.umlv.arthur;

public class Heroicity {
    private final int heroicityDamage;
    private final int heroicityProtection;


    public Heroicity(int heroicityDamage, int heroicityProtection) {
        this.heroicityDamage = heroicityDamage;
        this.heroicityProtection = heroicityProtection;
    }
    

    public Heroicity(int heroicity) {
        this(heroicity, heroicity);
    }

    public int applyHeroicityDamage(int value) {
        return value * heroicityDamage;
    }

    public int applyHeroicityProtection(int value) {
        return value * heroicityProtection;
    }

    public boolean isHeroic() {
        return heroicityDamage > 1 || heroicityProtection > 1;
    }
}