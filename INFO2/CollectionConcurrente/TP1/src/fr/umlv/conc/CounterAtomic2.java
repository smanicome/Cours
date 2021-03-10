package fr.umlv.conc;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterAtomic2 {
    private final AtomicInteger counter = new AtomicInteger();

    public int nextInt() {
        return counter.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        var threads = new Thread[5];
        var counter = new CounterAtomic2();
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
