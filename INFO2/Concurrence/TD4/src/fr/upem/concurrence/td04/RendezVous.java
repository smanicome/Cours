package fr.upem.concurrence.td04;

public class RendezVous<T> {
    private T value;
    private boolean done = false;
    private final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        var rdv = new RendezVous<String>();
        new Thread(
                () -> {
                    try {
                        Thread.sleep(20_000);
                        rdv.set("Message");
                    } catch (InterruptedException e) {
                        throw new AssertionError(e);
                    }
                })
                .start();
        System.out.println(rdv.get());
    }

    private T get() throws InterruptedException {
        synchronized (lock) {
            while(!done) {
                lock.wait(); // then comment this line !
            }
        }
        return value;
    }

    private void set(T nb) {
        synchronized (lock) {
            value = nb;
            done = true;
            lock.notify();
        }
    }

    private static boolean isPrime(long nb) {
        return true;
    }
}
