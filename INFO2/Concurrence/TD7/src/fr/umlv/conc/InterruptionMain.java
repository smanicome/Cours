package fr.umlv.conc;

public class InterruptionMain {
    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("end");
        });
        t.start();
        Thread.sleep(1_000);
        t.interrupt();
    }
}
