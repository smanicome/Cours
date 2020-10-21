package fr.umlv.queue;

import java.util.*;

import static java.util.Objects.requireNonNull;

public class ResizeableFifo<E> extends AbstractQueue<E> {
    private int size = 0;
    private int headIndex = 0;
    private int tailIndex = 0;
    private int capacity;
    private E[] array;

    @SuppressWarnings("SafeIgnore")
    public ResizeableFifo(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("arraySize must be greater than 0");
        }

        this.capacity = capacity;
        array = (E[]) new Object[capacity];

    }

    public boolean offer(E value) {
        requireNonNull(value);
        if(size == capacity) {
            throw new IllegalStateException("Capacity limit reached");
        }
        array[tailIndex] = value;
        tailIndex = (tailIndex + 1) % capacity;
        grow();
        return true;
    }

    @SuppressWarnings("SafeIgnore")
    private void grow() {
        size++;
        if(size != capacity)
            return;

        Iterator<E> iterator = iterator();
        var tmp = (E[]) new Object[capacity * 2];
        for (int i = 0; iterator.hasNext(); i++) {
            tmp[i] = iterator.next();
        }

        array = tmp;
        capacity = capacity * 2;
        headIndex = 0;
        tailIndex = size;
    }

    public E poll() {
        if (size == 0) {
            return null;
        }

        E value = array[headIndex];
        headIndex = (headIndex + 1) % capacity;
        size--;
        return value;
    }

    @Override
    public E peek() {
        if(size == 0)
            throw new NoSuchElementException();
        return array[headIndex];
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
        return new Iterator<>() {
            private int futur = headIndex;
            private int size = ResizeableFifo.this.size;

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
