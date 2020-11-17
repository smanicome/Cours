package fr.umlv.conc;

public class Exchanger<E> {
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
            if(state == ExchangerState.FINISHED) {
                throw new IllegalStateException();
            }
            E toReturn;
            if(state == ExchangerState.STILL) {
                storedValue = value;
                state = ExchangerState.ONGOING;

                while(state != ExchangerState.FINISHED) {
                    lock.wait();
                }

                return storedValue;
            } else {
                state = ExchangerState.FINISHED;
                toReturn = storedValue;
                storedValue = value;
                lock.notify();

                return toReturn;
            }
        }
    }
}
