import java.util.ArrayDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedSafeQueue<V> {
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition lockCondition = lock.newCondition();
    private final ArrayDeque<V> fifo = new ArrayDeque<>();
    private final int capacity;

    public BoundedSafeQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
    }

    public void put(V value) throws InterruptedException {
        lock.lock();
        try {
            while (fifo.size() == capacity) {
                lockCondition.await();
            }
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
            lockCondition.signal();
            return fifo.remove();
        } finally {
            lock.unlock();
        }
    }

    private static void thread_func(BoundedSafeQueue<String> unboundedSafeQueue) {
        while(true) {
            try {
                unboundedSafeQueue.put(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedSafeQueue<String> boundedSafeQueue = new BoundedSafeQueue<>(5);
        int nbThreads = 50;
        Thread[] threads = new Thread[nbThreads];
        for (int i = 0; i < nbThreads; i++) {
            threads[i] = new Thread(() -> thread_func(boundedSafeQueue));
            threads[i].start();
        }

        while(true) {
            boundedSafeQueue.take();
            System.out.println(boundedSafeQueue.fifo.size());
        }
    }
}
