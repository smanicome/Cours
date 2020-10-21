package fr.umlv.conc;

import java.util.Random;

public class RendezVous<T> {
    private T value;
    private final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        var nbThreads = 4;
        var rdv = new RendezVous<Long>();
        for (var i = 0; i < nbThreads; i++) {
            var fi = i;
            var thread = new Thread(() -> {
                var random = new Random();
                for (;;) {
                    var nb = 10_000_000_000L + (random.nextLong() % 10_000_000_000L);
                    if (isPrime(nb)) {
                        rdv.set(nb);
                        System.out.println("A prime number was found in thread " + fi);
                        return;
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }
        Long prime = rdv.get();
        System.out.println("I found a large prime number : " + prime);
    }

    private T get() throws InterruptedException {
        synchronized (lock) {
            while(value == null) {
                Thread.sleep(1);  // then comment this line !
            }
        }
        return value;
    }

    private void set(T nb) {
        synchronized (lock) {
            value = nb;
        }
    }

    private static boolean isPrime(long nb) {
        return true;
    }
}
