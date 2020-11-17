package fr.umlv.seq;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Seq<T> implements Iterable<T> {
    private final List<Object> values;
    private final Function<Object, T> mapping;

    private Seq(List<Object> values, Function<Object, T> mapping) {
        this.values = values;
        this.mapping = mapping;
    }

    @Override
    public String toString() {
        return values.stream()
            .map(mapping)
            .map(Objects::toString)
            .collect(Collectors.joining(", ", "<", ">"));
    }

    public T get(int index) {
        if(index < 0 || index > values.size() - 1) {
            throw new IndexOutOfBoundsException();
        }

        return mapping.apply(values.get(index));
    }

    @SuppressWarnings("SafeIgnore")
    public static <E> Seq<E> from(List<? extends  E> values) {
        return new Seq<>(List.copyOf(values), e -> (E) e);
    }

    public void forEach(Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        values.stream().map(mapping).forEach(consumer);
    }

    @SafeVarargs
    public static <E> Seq<E> of(E ...values) {
        return Seq.from(List.of(values));
    }

    public <E> Seq<E> map(Function<? super T, ? extends E> function) {
        Objects.requireNonNull(function);
        return new Seq<>(values, e -> function.apply(mapping.apply(e)));
    }

    public Optional<T> findFirst() {
        if(values.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(mapping.apply(values.get(0)));
    }

    public int size() {
        return values.size();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final int size = values.size();
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
        var mappedList = values.stream().map(mapping).toArray();
        var characteristics = Spliterator.IMMUTABLE | Spliterator.ORDERED | Spliterator.NONNULL;
        return StreamSupport.stream(Spliterators.spliterator(mappedList, characteristics), false);
    }
}
