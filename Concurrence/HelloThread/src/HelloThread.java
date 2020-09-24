import java.util.ArrayList;

public class HelloThread {
    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 150; i++) {
            final int threadNumber = i;
            Runnable runnable = () -> countInThread(threadNumber);
            threads.add(new Thread(runnable));
        }

        threads.forEach(Thread::start);
    }

    private static void countInThread(int threadNumber) {
        for (int i = 0; i < 5000; i++) {
            System.out.println("Thread " + threadNumber + " " + i);
        }
    }
}
