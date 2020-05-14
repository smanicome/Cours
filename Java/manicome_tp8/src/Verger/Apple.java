package Verger;

import java.util.Objects;

public class Apple implements Fruit {
    private final AppleKind appleKind;
    private final int weight;

    public Apple(int weight, AppleKind name) {
        this.appleKind = Objects.requireNonNull(name);
        this.weight = weight;
    }

    public AppleKind getKind() {
        return appleKind;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return appleKind.toString() + " " + weight + " g";
    }

    public double getPrice() {
        return weight / 2.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apple apple = (Apple) o;
        return weight == apple.weight &&
                appleKind.equals(apple.appleKind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appleKind, weight);
    }
}
