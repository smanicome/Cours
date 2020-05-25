package fr.umlv.data;

import java.util.Objects;

public class LinkedList<T> {
    private Link<T> first;
    private int length;

    public LinkedList() {
        this.first = null;
        this.length = 0;
    }

    public int length() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedList<T> that = (LinkedList<T>) o;
        return length == that.length && first.equals(that.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, length);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Link<T> i = first; i != null; i = i.next()) {
            builder.append(i.getValue().toString());
            builder.append(' ');
        }

        return builder.toString();
    }

    public void add(T value) {
        Objects.requireNonNull(value);
        Link<T> next = new Link<T>(value);

        if (first != null)
            first.setNext(next);
        else
            first = next;

        length += 1;
    }

    public T get(int index) {
        Link<T> link;
        int i;

        if(index >= length) {
            throw new IndexOutOfBoundsException();
        }

        // first count as index 0
        for(i = 1, link = first; i != index && link != null; i++, link = link.next() );

        if (link == null) return null;
        return link.getValue();
    }

    public boolean contains (Object o) {
        Link<T> i;
        for (i = first; i != null && !i.getValue().equals(o); i = i.next());

        return i != null;
    }
}