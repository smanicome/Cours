package Verger;

import java.util.HashMap;

public class Basket {
    private final HashMap<Fruit, Integer> basket;

    public Basket() {
        basket = new HashMap<>();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (var entry : basket.entrySet()) {
            Fruit fruit = (Fruit) entry.getKey();

            builder.append(fruit);
            builder.append(" x ");
            builder.append(entry.getValue());
            builder.append('\n');
        }

        return builder.toString();
    }

    public double getTotal() {
        double sum = 0;
        for (var entry : basket.entrySet()) {
            Fruit fruit = (Fruit) entry.getKey();
            sum += fruit.getPrice();
        }

        return sum;
    }

    public void add(Fruit fruit) {
        add(fruit, 1);
    }

    public void add(Fruit fruit, int quantity) {
        Integer qty;

        qty = basket.get(fruit);

        if (qty == null) {
            basket.put(fruit, quantity);
        } else {
            basket.put(fruit, qty + quantity);
        }
        basket.put(fruit, quantity);
    }
}
