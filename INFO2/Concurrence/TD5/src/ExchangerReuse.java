public class ExchangerReuse<E> {
    private final Object lock = new Object();
    public enum ExchangerState {
        STILL,
        ONGOING,
        FINISHED
    }

    private ExchangerState state = ExchangerState.STILL;
    private E storedValue;

    public E exchange(E value) throws InterruptedException {
        synchronized (lock) {
            while(state == ExchangerState.FINISHED) {
                lock.wait();
            }

            E toReturn;
            if(state == ExchangerReuse.ExchangerState.STILL) {
                storedValue = value;
                state = ExchangerReuse.ExchangerState.ONGOING;

                while(state != ExchangerReuse.ExchangerState.FINISHED) {
                    lock.wait();
                }

                state = ExchangerState.STILL;
                lock.notifyAll();
                return storedValue;
            } else {
                state = ExchangerReuse.ExchangerState.FINISHED;
                toReturn = storedValue;
                storedValue = value;
                lock.notify();

                return toReturn;
            }
        }
    }

}
