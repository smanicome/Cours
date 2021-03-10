package fr.umlv.conc;

public class BogusSynchronized {
     private boolean stop;
     private final Object lock = new Object();

    public void runCounter() {
        var localCounter = 0;
        for (; ; ) {
            synchronized (lock) {
                if (stop) {
                    break;
                }
                localCounter++;
            }
        }
        System.out.println(localCounter);
    }

    public void stop() {
        synchronized (lock) {
            stop = true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var bogus = new BogusSynchronized();
        var thread = new Thread(bogus::runCounter);
        thread.start();
        Thread.sleep(100);
        bogus.stop();
        thread.join();
    }
}
