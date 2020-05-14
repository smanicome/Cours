package fr.umlv.calc;

import java.util.Iterator;

public interface Expr {

    public int eval();

    public void display();

    public static Expr parse(Iterator<String> iterator) {
        String stringValue = iterator.next();

        try {

            int value = Integer.parseInt(stringValue);
            return new Value(value);

        } catch (NumberFormatException e) {
            Expr expr = null;

            switch (stringValue) {
                case "+" -> expr = new Add(parse(iterator), parse(iterator));
                case "-" -> expr = new Sub(parse(iterator), parse(iterator));
                default -> expr = null;
            };

            return expr;
        }
    }
}
