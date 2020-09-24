import java.util.ArrayList;

public class TurtleRace {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("On your mark!");
        Thread.sleep(30_000);
        System.out.println("Go!");
        int[] times = {25_000, 10_000, 20_000, 5_000, 50_000, 60_000};

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < times.length; i++) {
            int index = i;

            Runnable runnable = () -> {
                try {
                    Thread.sleep(times[index]);
                    System.out.println("Turtle " + index + " has arrived...");
                } catch (InterruptedException e) {
                    throw new AssertionError();
                }
            };
            threads.add(new Thread(runnable));
        }

        threads.forEach(Thread::start);
    }
}
