import java.util.ArrayDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UnboundedSafeQueue<V> {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition lockCondition = lock.newCondition();
    private final ArrayDeque<V> fifo = new ArrayDeque<>();

    public void add(V value) {
        lock.lock();

        try {
            fifo.add(value);
            lockCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public V take() throws InterruptedException {
        lock.lock();
        try {
            while (fifo.isEmpty()) {
                lockCondition.await();
            }
            return fifo.remove();
        } finally {
            lock.unlock();
        }
    }

    private static void thread_func(UnboundedSafeQueue<String> unboundedSafeQueue) {
        while(true) {
            unboundedSafeQueue.add(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UnboundedSafeQueue<String> unboundedSafeQueue = new UnboundedSafeQueue<>();
        int nbThreads = 50;
        Thread[] threads = new Thread[nbThreads];
        for (int i = 0; i < nbThreads; i++) {
            threads[i] = new Thread(() -> thread_func(unboundedSafeQueue));
            threads[i].start();
        }

        while(true) {
            System.out.println(unboundedSafeQueue.take());
        }
    }
}