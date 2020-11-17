import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class Sync<V> {
    ReentrantLock lock = new ReentrantLock();

    public boolean inSafe() {
        return lock.isLocked();
    }

    public V safe(Supplier<? extends V> supplier) {
        lock.lock();
        try {
            return supplier.get();
        } finally {
            lock.unlock();
        }
    }
}