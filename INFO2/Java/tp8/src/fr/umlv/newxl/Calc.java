package fr.umlv.newxl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class Calc {
    @FunctionalInterface
    protected interface Computation {
        double compute(Cell cell);
    }

    public static Computation parse(Cell cell, String expression) {
        var tokens = Arrays.stream(expression.split(" ")).iterator();
        return parse(cell, tokens);
    }

    private static Computation parse(Cell cell, Iterator<String> iterator) {
        if(!iterator.hasNext()) {
            throw new IllegalStateException("expression cannot be parsed");
        }

        String stringValue = iterator.next();

        try {
            var value = Double.parseDouble(stringValue);
            return c -> value;
        } catch (NumberFormatException e) {
            return c -> parse(cell, iterator).compute(cell) + parse(cell, iterator).compute(cell);
        }
    }

    public record Shift(int dx, int dy) { }
    public record Cell(int row, int column) {

        private static Pattern regexPattern = Pattern.compile("[A-Z]\\d{1,2}");

        public Cell {
            if(row < 0 || column < 0)
                throw new IllegalArgumentException();
        }

        @Override
        public String toString() {
            if(column < 26) {
                return Character.toString(column + 'A') + row;
            }
            return "Cell(" + row + "," + column + ')';
        }

        public static Optional<Cell> parse(String model) {
            Objects.requireNonNull(model);

            if(!regexPattern.matcher(model).matches())
                return Optional.empty();

            int column = model.charAt(0) - 65;
            int row = Integer.parseInt(model.substring(1));

            return Optional.of(new Cell(row, column));
        }

        public Shift relativize(Cell other) {
            Objects.requireNonNull(other);
            return new Shift(row - other.row(), column - other.column());
        }

        public Cell resolve(Shift toOther) {
            Objects.requireNonNull(toOther);
            return new Cell(row + toOther.dx(), column + toOther.dy());
        }
    }
}