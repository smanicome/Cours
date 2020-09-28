package fr.umlv.fight;

public class Robot {
    private final String name;
    private int health;

    public Robot(String name) {
        this.name = name;
        this.health = 10;
    }

    @Override
    public String toString() {
        return "Robot " + name;
    }

    protected boolean rollDice() {
        return true;
    }

    public void fire(Robot target) throws IllegalStateException  {
        if (target.isDead()) {
            throw new IllegalStateException("Target is already dead");
        }

        if (rollDice()) {
            target.hurt();
            System.out.println(target.toString() + " a été touché par le " + this.toString() + " !");
        } else {
            System.out.println(this.toString() + " a raté sont tir !");
        }
        
    }

    public void hurt() {
        this.health -= 2;
    }

    public boolean isDead() {
        return health == 0;
    }
    
}