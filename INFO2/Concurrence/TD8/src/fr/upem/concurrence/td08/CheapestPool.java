package fr.upem.concurrence.td08;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class CheapestPool {
    private final String product;
    private final int time;
    private final int workersNumber;

    public CheapestPool(String product, int time, int workersNumber) {
        Objects.requireNonNull(product);
        if(time < 0 || workersNumber < 0) {
            throw new IllegalArgumentException();
        }

        this.product = product;
        this.time = time;
        this.workersNumber = workersNumber;
    }

    public Optional<Answer> retrieve() throws InterruptedException {
        var threads = new ArrayList<Thread>();
        var siteQueue = new LinkedBlockingDeque<>(Request.ALL_SITES);
        var resultQueue = new ArrayBlockingQueue<Answer>(Request.ALL_SITES.size());

        for (int j = 0; j < workersNumber; j++) {
            while(!siteQueue.isEmpty()) {
                String site = siteQueue.take();
                var t = new Thread(() -> {
                    try {
                        var result = new Request(site, product).request(time);
                        resultQueue.put(result);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });

                t.start();
                threads.add(t);
            }
        }

        Optional<Answer> result = Optional.empty();
        Answer cheapest = null;

        for (int i = 0; i < Request.ALL_SITES.size(); i++) {
            var answer = resultQueue.take();
            if(answer.isSuccessful()) {
                if(cheapest == null) {
                    cheapest = answer;
                } else if(answer.compareTo(cheapest) < 0) {
                    cheapest = answer;
                }
            }
        }

        threads.forEach(Thread::interrupt);

        return cheapest == null ? Optional.empty() : Optional.of(cheapest);
    }

    public static void main(String[] args) throws InterruptedException {
        var agregator = new CheapestPool("tortank", 5_000, 10);
        var answer = agregator.retrieve();
        System.out.println(answer); // Optional[tortank@... : ...]
    }
}
