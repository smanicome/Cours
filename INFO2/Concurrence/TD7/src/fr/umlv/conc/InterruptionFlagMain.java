package fr.umlv.conc;

public class InterruptionFlagMain {
    private static int slow() {
        var result = 1;
        for (var i = 0; i < 1_000_000; i++) {
            if(Thread.interrupted()) {
                Thread.currentThread().interrupt();
                return result;
            }

            result += (result * 7) % 513;
        }
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(() -> {
            var forNothing = 0;
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(1_000);
                    forNothing += slow();
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println(forNothing);
        });
        t.start();
        Thread.sleep(1_000);
        t.interrupt();
    }
}
