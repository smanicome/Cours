package fr.umlv.fight;
import java.util.Random;

public class Fighter extends Robot{
    private final String name;
    private final int seed;

    public Fighter(String name, int seed) {
        super(name);
        this.name = name;
        this.seed = seed;
    }

    @Override
    public String toString() {
        return "Fighter " + name;
    }

    @Override
    protected boolean rollDice() {
        Random random = new Random(this.seed);
        return random.nextBoolean();
    }
}