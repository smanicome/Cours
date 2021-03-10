package fr.umlv.conc;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterAtomic {
    private final AtomicInteger counter = new AtomicInteger();

    public int nextInt() {
        var value = counter.get();
        while(!counter.compareAndSet(value, value+1)) {
            value = counter.get();
        }
        return value + 1;
    }

    public static void main(String[] args) throws InterruptedException {
        var threads = new Thread[5];
        var counter = new CounterAtomic();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100_000; j++) {
                    counter.nextInt();
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(counter.counter.get());
    }
}
