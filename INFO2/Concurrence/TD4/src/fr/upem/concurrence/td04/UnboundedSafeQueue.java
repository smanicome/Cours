package fr.upem.concurrence.td04;

import java.util.LinkedList;

public class UnboundedSafeQueue<V> {
    private final LinkedList<V> values = new LinkedList<>();
    private final Object lock = new Object();

    public void add(V value) {
        synchronized (lock) {
            values.add(value);
            lock.notify();
        }
    }

    public V take() throws InterruptedException {
        synchronized (lock) {
            while(values.isEmpty()) {
                lock.wait();
            }

            return values.removeFirst();
        }
    }

    private static void thread_func(UnboundedSafeQueue<String> unboundedSafeQueue) {
        try {
            while(true) {
                unboundedSafeQueue.add(Thread.currentThread().getName());
                Thread.sleep(2_000);
            }
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UnboundedSafeQueue<String> unboundedSafeQueue = new UnboundedSafeQueue<>();
        int nbThreads = 50;
        Thread[] threads = new Thread[nbThreads];
        for (int i = 0; i < nbThreads; i++) {
            threads[i] = new Thread(() -> thread_func(unboundedSafeQueue));
            threads[i].start();
        }

        while(true) {
            System.out.println(unboundedSafeQueue.take());
        }
    }
}
