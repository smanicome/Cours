package fr.umlv.seq;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Seq2<T> implements Iterable<T> {
    private final Object[] values;
    private final Function<Object, T> mapping;

    private Seq2(List<Object> values, Function<Object, T> mapping) {
        this.values = values.toArray();
        this.mapping = mapping;
    }

    @Override
    public String toString() {
        return Arrays.stream(values)
                .map(mapping)
                .map(Objects::toString)
                .collect(Collectors.joining(", ", "<", ">"));
    }

    public T get(int index) {
        if(index < 0 || index > values.length - 1) {
            throw new IndexOutOfBoundsException();
        }

        return mapping.apply(values[index]);
    }

    @SuppressWarnings("SafeIgnore")
    public static <E> Seq2<E> from(List<? extends  E> values) {
        return new Seq2<>(List.copyOf(values), e -> (E) e);
    }

    public void forEach(Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        Arrays.stream(values).map(mapping).forEach(consumer);
    }

    @SafeVarargs
    public static <E> Seq2<E> of(E ...values) {
        return Seq2.from(List.of(values));
    }

    public <E> Seq2<E> map(Function<? super T, ? extends E> function) {
        Objects.requireNonNull(function);
        return new Seq2<>(List.of(values), e -> function.apply(mapping.apply(e)));
    }

    public Optional<T> findFirst() {
        if(values.length == 0) {
            return Optional.empty();
        }

        return Optional.of(mapping.apply(values[0]));
    }

    public int size() {
        return values.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final int size = values.length;
            private int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if(!hasNext())
                    throw new NoSuchElementException();

                var value = get(index);
                index++;
                return value;
            }
        };
    }

    public Stream<T> stream() {
        var mappedValues = Arrays.stream(values).map(mapping).toArray();
        var characteristics = Spliterator.IMMUTABLE | Spliterator.ORDERED | Spliterator.NONNULL;
        return StreamSupport.stream(Spliterators.spliterator(mappedValues, characteristics), false);
    }
}
