package fr.upem.concurrence.td08;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;

public class CheapestTest {
    private final String product;
    private final int time;

    public CheapestTest(String product, int time) {
        Objects.requireNonNull(product);
        if(time < 0) {
            throw new IllegalArgumentException();
        }

        this.product = product;
        this.time = time;
    }

    public Optional<Answer> retrieve() throws InterruptedException {
        var threads = new ArrayList<Thread>();
        var resultQueue = new ArrayBlockingQueue<Answer>(Request.ALL_SITES.size());

        Request.ALL_SITES.forEach(site -> {
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
        });

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

        return cheapest == null ? Optional.empty() : Optional.of(cheapest);
    }

    public static void main(String[] args) throws InterruptedException {
        var agregator = new CheapestTest("tortank", 5_000);
        var answer = agregator.retrieve();
        System.out.println(answer); // Optional[tortank@... : ...]
    }
}
