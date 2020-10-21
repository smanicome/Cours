package fr.upem.concurrence.td04;

import java.util.LinkedList;

public class BoundedSafeQueue<V> {
    private final LinkedList<V> values = new LinkedList<>();
    private final Object lock = new Object();
    private final int max;

    public BoundedSafeQueue(int max) {
        if(max < 0)
            throw new IllegalArgumentException("max must be 0 or above");
        this.max = max;
    }

    public void put(V value) throws InterruptedException {
        synchronized (lock) {
            while(values.size() == max) {
                lock.wait();
            }
            values.add(value);
            lock.notify();
        }
    }

    public V take() throws InterruptedException {
        synchronized (lock) {
            while(values.isEmpty()) {
                lock.wait();
            }

            lock.notify();
            return values.removeFirst();
        }
    }

    private static void thread_func(BoundedSafeQueue<String> unboundedSafeQueue) {
        while(true) {
            try {
                unboundedSafeQueue.put(Thread.currentThread().getName());
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedSafeQueue<String> boundedSafeQueue = new BoundedSafeQueue<>(5);
        int nbThreads = 50;
        Thread[] threads = new Thread[nbThreads];
        for (int i = 0; i < nbThreads; i++) {
            threads[i] = new Thread(() -> thread_func(boundedSafeQueue));
            threads[i].start();
        }

        while(true) {
            boundedSafeQueue.take();
            System.out.println(boundedSafeQueue.values.size());
        }
    }
}
