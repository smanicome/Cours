import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class PermitSync<V> {
    private final int permits;
    private int currentAccess;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition lockCondition = lock.newCondition();

    public PermitSync(int permits) {
        if(permits < 0)
            throw new IllegalArgumentException();

        this.permits = permits;
    }

    public V safe(Supplier<? extends V> supplier) throws InterruptedException {
        lock.lock();
        try {
            while(currentAccess == permits) {
                lockCondition.await();
            }

            currentAccess++;
            return supplier.get();
        } finally {
            currentAccess--;
            lockCondition.signal();
            lock.unlock();
        }
    }
}