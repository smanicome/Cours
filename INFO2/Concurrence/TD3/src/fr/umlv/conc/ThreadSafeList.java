package fr.umlv.conc;

import java.util.ArrayList;
import java.util.Objects;

public class ThreadSafeList {
    private final ArrayList<Integer> list = new ArrayList<>();
    private final Object lock = new Object();

    public void add(Integer integer) {
        Objects.requireNonNull(integer);
        synchronized (lock) {
            list.add(integer);
        }
    }

    public int size() {
        synchronized (lock) {
            return list.size();
        }
    }

    public void print() {
        synchronized (lock) {
            list.forEach(System.out::println);
        }
    }
}
