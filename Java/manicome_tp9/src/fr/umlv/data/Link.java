package fr.umlv.data;

import java.util.Objects;

class Link<T> {
    private final T value;
    private Link<T> next;

    Link(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    Link<T> next() {
        if(next == null) {
            throw new IllegalStateException("There is no next, use hasNext method ahead");
        } else {
            return next;
        }
    }

    boolean hasNext() {
        return this.next != null;
    }

    @Override
    public String toString() {
        return "Link{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;
        Link<T> link = (Link<T>) o;
        return value.equals(link.value) && next.equals(link.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, next);
    }

    void setNext(Link<T> next) {
        if(next == null) {
            throw new IllegalArgumentException("setNext must not be null");
        }

        if (this.next != null) {
            this.next.setNext(next);
        } else {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Link a = new Link(13);
        Link b = new Link(144);
    }
}