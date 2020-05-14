package Verger;

import java.util.Objects;

public class Pear implements Fruit {
    private final int juice;

    public Pear(int juice) {
        if (juice < 0 || juice > 9) {
            throw new IllegalArgumentException("Juice must be between 0 and 9");
        }

        this.juice = juice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pear pear = (Pear) o;
        return juice == pear.juice;
    }

    @Override
    public String toString() {
        return "Pear " + juice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(juice);
    }

    public int getJuice() {
        return juice;
    }

    @Override
    public double getPrice() {
        return juice * 3;
    }
}
