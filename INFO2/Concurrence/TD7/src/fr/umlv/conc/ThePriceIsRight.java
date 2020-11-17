package fr.umlv.conc;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThePriceIsRight {
    private final HashMap<Long, Integer> answers = new HashMap<>();
    private final int realPrice;
    private int contestantNumber;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition lockCondition = lock.newCondition();
    private boolean finished = false;

    public ThePriceIsRight(int price, int contestantNumber) {
        if(price < 0 || contestantNumber < 0) {
            throw new IllegalArgumentException();
        }

        this.realPrice = price;
        this.contestantNumber = contestantNumber;
    }

    private int distance(int price) {
        return Math.abs(price - realPrice);
    }

    public boolean propose(int value) {
        if(value < 0 || answers.size() == contestantNumber || finished)
            return false;

        var threadId = Thread.currentThread().getId();
        lock.lock();

        try {
            if(answers.containsValue(value)) {
                contestantNumber--;
                lockCondition.signalAll();
                return false;
            }

            answers.put(threadId, value);

            if(answers.size() == contestantNumber) {
                finished = true;
                lockCondition.signalAll();
            } else {
                while(!finished && answers.size() < contestantNumber) {
                    lockCondition.await();
                }
            }

            var winner = answers.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue(Comparator.comparingInt(this::distance)))
                .orElseThrow();

            return threadId == winner.getKey();
        } catch (InterruptedException e) {
            answers.remove(threadId);
            finished = true;
            lockCondition.signalAll();
            return false;
        } finally {
            lock.unlock();
        }
    }
}
