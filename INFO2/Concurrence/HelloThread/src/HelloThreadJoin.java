import java.util.ArrayList;

public class HelloThreadJoin {
    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 150; i++) {
            int threadNumber = i;
            Runnable runnable = () -> countInThread(threadNumber);
            threads.add(new Thread(runnable));
        }

        threads.forEach(Thread::start);
        threads.forEach((thread) -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new AssertionError();
            }
        });

        System.out.println("Tous les threads se sont terminés");
    }

    private static void countInThread(int threadNumber) {
        for (int i = 0; i < 5000; i++) {
            System.out.println("Thread " + threadNumber + " " + i);
        }
    }
}
