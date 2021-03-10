package fr.umlv.conc;

public class Counter {
    private int counter;

    public int nextInt() {
        return counter++;
    }

    public static void main(String[] args) throws InterruptedException {
        var threads = new Thread[5];
        var counter = new Counter();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 100_000; j++) {
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
