package fr.umlv.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import static java.util.Objects.requireNonNull;

public class Fifo<E> implements Iterable<E> {
    private int size = 0;
    private int headIndex = 0;
    private int tailIndex = 0;
    private final int capacity;
    private final E[] array;

    @SuppressWarnings("SafeIgnore")
    public Fifo(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("arraySize must be greater than 0");
        }

        this.capacity = capacity;
        array = (E[]) new Object[capacity];
    }

    public void offer(E value) {
        requireNonNull(value);
        if(size == capacity) {
            throw new IllegalStateException("Capacity limit reached");
        }
        array[tailIndex] = value;
        tailIndex = (tailIndex + 1) % capacity;
        size++;
    }

    public E poll() {
        if (size == 0) {
            throw new IllegalStateException("Fifo is empty");
        }

        E value = array[headIndex];
        headIndex = (headIndex + 1) % capacity;
        size--;
        return value;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            var index = (headIndex + i) % capacity;
            stringJoiner.add(array[index].toString());
        }
        return stringJoiner.toString();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int futur = headIndex;
            private int size = Fifo.this.size;

            @Override
            public boolean hasNext() {
                return size > 0;
            }

            public E next() {
                if(size == 0) {
                    throw new NoSuchElementException();
                }
                var value = array[futur];
                futur = ( futur + 1) % capacity;
                size--;
                return value;
            }
        };
    }
}
