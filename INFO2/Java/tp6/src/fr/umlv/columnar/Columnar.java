package fr.umlv.columnar;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class Columnar {
    private final Column<?>[] columns;
    private final BiFunction<Column<?>, Integer, ?> getter;
    private final IntSupplier computeSize;
    private final Function<Integer, List<?>> computeGetValues;
    private List<Integer> indirections;

    private Columnar(Column<?>[] columns) {
        Objects.requireNonNull(columns);
        if(columns.length == 0) {
            throw new IllegalArgumentException();
        }

        this.columns = Arrays.copyOf(columns, columns.length);
        this.getter = Column::get;
        this.computeSize = () -> columns[0].size();
        this.computeGetValues = (index) -> Arrays.stream(columns)
                .map(column -> column.get(index))
                .collect(Collectors.toUnmodifiableList());
    }

    private Columnar(Column<?>[] columns, List<Integer> indirections) {
        Objects.requireNonNull(columns);
        Objects.requireNonNull(indirections);

        this.columns = columns;
        this.indirections = List.copyOf(indirections);
        this.getter = (col, index) -> col.get(this.indirections.get(index));
        this.computeSize = indirections::size;
        this.computeGetValues = (index) -> Arrays.stream(columns)
            .map(column -> column.get(this.indirections.get(index)))
            .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return Arrays.stream(columns)
            .map(Column::string)
            .collect(Collectors.joining(" | "));
    }

    public static final class Column<T> {
        private final String string;
        private final Class<T> cl;
        private final ArrayList<T> values = new ArrayList<>();
        private int columnarHashCode;

        private Column(String string, Class<T> cl) {
            Objects.requireNonNull(string);
            Objects.requireNonNull(cl);
            this.string = string;
            this.cl = cl;
        }

        @Override
        public String toString() {
            return string + " of " + cl.getName();
        }

        public String string() {
            return string;
        }

        public void attach(int hashcode) {
            if(columnarHashCode != 0)
                throw new IllegalStateException();

            columnarHashCode = hashcode;
        }

        @SuppressWarnings("SafeIgnore")
        public boolean add(Object value) {
            return values.add((T) value);
        }

        public T get(int index) {
            return values.get(index);
        }

        public int size() {
            return values.size();
        }

        public boolean isAttached() {
            return columnarHashCode != 0;
        }
    }

    public static Columnar of(Column<?> ...columns) {
        Objects.requireNonNull(columns);
        if(Arrays.stream(columns).anyMatch(Objects::isNull)) {
            throw new NullPointerException();
        }

        if(Arrays.stream(columns).distinct().count() != columns.length || Arrays.stream(columns).anyMatch(Column::isAttached)) {
            throw new IllegalStateException();
        }

        var columnar = new Columnar(columns);
        Arrays.stream(columns).forEach(column -> column.attach(columnar.hashCode()));
        return columnar;
    }

    public void addValues(Object ...values) {
        Objects.requireNonNull(values);
        if(indirections != null) {
            throw new UnsupportedOperationException();
        }

        if(values.length != columns.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < values.length; i++) {
            if (values[i] != null && values[i].getClass() != columns[i].cl) {
                throw new ClassCastException();
            }
        }

        for (int i = 0; i < values.length; i++) {
            columns[i].add(values[i]);
        }
    }

    public void addValues(List<?> values) {
        Objects.requireNonNull(values);
        if(indirections != null) {
            throw new UnsupportedOperationException();
        }

        addValues(values.toArray());
    }

    @SuppressWarnings("SafeIgnore")
    public <T> T get(Column<? extends T> column, int index) {
        Objects.requireNonNull(column);

        if(indirections == null && column.columnarHashCode != hashCode())
            throw new IllegalStateException("Column is not assigned to this Columnar");

        return (T) getter.apply(column, index);

    }

    public List<?> getValues(int index) {
        return computeGetValues.apply(index);
    }

    public int size() {
        return computeSize.getAsInt();
    }

    public <T> Columnar filter(Column<? extends T> column, Predicate<? super T> condition) {
        Objects.requireNonNull(column);
        Objects.requireNonNull(condition);

        if(this.indirections == null && column.columnarHashCode != hashCode())
            throw new IllegalStateException();

        ArrayList<Integer> indirections = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if(condition.test( get(column, i) ) ) {
                if(this.indirections != null) {
                    indirections.add(this.indirections.get(i));
                } else {
                    indirections.add(i);
                }

            }
        }

        if(this.indirections != null) {
            indirections.retainAll(this.indirections);
        }

        return new Columnar(columns, indirections);
    }

    public static <T> Column<T> column(String string, Class<T> cl) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(cl);
        return new Column<>(string, cl);
    }
}
