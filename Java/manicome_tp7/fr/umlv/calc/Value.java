package fr.umlv.calc;

public class Value implements Expr {
    private final int value;

    public Value(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value+"";
    }

    @Override
    public int eval() {
        return value;
    }

    @Override
    public void display() {
        System.out.println(toString());
    }
}
