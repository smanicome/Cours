package fr.umlv.newxl;

import java.util.*;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

public class Calc {

    private static final Map<String, BiFunction<Double, Double, Double>> operations = Map.of(
        "+", (Double a, Double b) -> a + b,
        "-", (Double a, Double b) -> a - b,
        "*", (Double a, Double b) -> a * b,
        "/", (Double a, Double b) -> a / b
    );

    private final HashMap<Cell, Computation> computations = new HashMap<>();

    @FunctionalInterface
    protected interface Computation {
        double compute(Cell cell);
    }

    protected Computation parse(Cell cell, String expression) {
        var tokens = Arrays.stream(expression.split(" ")).iterator();
        return parse(cell, tokens);
    }

    private Computation parse(Cell cell, Iterator<String> iterator) {
        Objects.requireNonNull(cell);
        Objects.requireNonNull(iterator);

        String stringValue;
        try {
            stringValue = iterator.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        var optionalCell = Cell.parse(stringValue);
        if(optionalCell.isPresent()) {
            var otherCell = optionalCell.get();
            var shift = otherCell.relativize(cell);
            return c -> eval(c.resolve(shift)).orElse(0.0);
        }

        try {
            var value = Double.parseDouble(stringValue);
            return c -> value;
        } catch (NumberFormatException e) {
            var left = parse(cell, iterator).compute(cell);
            var right = parse(cell, iterator).compute(cell);

            return c -> operations.get(stringValue).apply(left, right);
        }
    }

    public void set(Cell cell, String value) {
        Objects.requireNonNull(cell);
        Objects.requireNonNull(value);
        computations.put(cell, parse(cell, value));
    }

    private Cell moveAccordingToSpanDirection(Cell cell, SpanDirection spanDirection) {
        Objects.requireNonNull(cell);
        Objects.requireNonNull(spanDirection);

        return switch (spanDirection) {
            case EAST -> new Cell(cell.row, cell.column + 1);
            case WEST -> new Cell(cell.row, cell.column - 1);
            case NORTH -> new Cell(cell.row - 1, cell.column);
            case SOUTH -> new Cell(cell.row + 1, cell.column);
        };
    }

    public void setAll(Cell receivingCell, SpanDirection direction, int length, String cellLabelToCopyFrom) {
        Objects.requireNonNull(receivingCell);
        Objects.requireNonNull(direction);
        Objects.requireNonNull(cellLabelToCopyFrom);
        if(length <= 0) {
            throw new IllegalArgumentException();
        }


        var cellToCopyFrom = Cell.parse(cellLabelToCopyFrom).orElseThrow();
        for (int i = 0; i < length; i++) {
            computations.put(receivingCell, computations.get(cellToCopyFrom));
            if(i < length - 1) {
                receivingCell = moveAccordingToSpanDirection(receivingCell, direction);
                cellToCopyFrom = moveAccordingToSpanDirection(cellToCopyFrom, direction);
            }
        }
    }

    public Optional<Double> eval(Cell cell) {
        Objects.requireNonNull(cell);
        var computation = computations.get(cell);
        return computation != null ? Optional.of(computation.compute(cell)) : Optional.empty();
    }

    public record Shift(int dx, int dy) { }
    public record Cell(int row, int column) {
        private static Pattern regexPattern = Pattern.compile("[A-Z]\\d+");

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