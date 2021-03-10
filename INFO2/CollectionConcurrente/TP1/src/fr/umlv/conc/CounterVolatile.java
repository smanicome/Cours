package fr.umlv.conc;

public class CounterVolatile {
    private volatile int counter;

    public int nextInt() {
        return counter++;
    }

    public static void main(String[] args) throws InterruptedException {
        var threads = new Thread[5];
        var counter = new CounterVolatile();
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

        System.out.println(counter.counter);
    }
}
