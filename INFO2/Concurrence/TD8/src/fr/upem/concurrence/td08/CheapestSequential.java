package fr.upem.concurrence.td08;

import java.util.Comparator;
import java.util.Optional;

public class CheapestSequential {

    private final String item;
    private final int timeoutMilliPerRequest;

    public CheapestSequential(String item, int timeoutMilliPerRequest) {
        this.item = item;
        this.timeoutMilliPerRequest = timeoutMilliPerRequest;
    }

    /**
     * @return the cheapest price for item if it is sold
     */
    public Optional<Answer> retrieve() throws InterruptedException {
        return Request.ALL_SITES.stream()
            .map(s -> new Request(s, item))
            .map(r -> {
                try {
                    return r.request(timeoutMilliPerRequest);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            })
            .filter(Answer::isSuccessful)
            .min(Comparator.comparingInt(Answer::getPrice));
    }

    public static void main(String[] args) throws InterruptedException {
        var agregator = new CheapestSequential("pikachu", 2_000);
        var answer = agregator.retrieve();
        System.out.println(answer); // Optional[pikachu@darty.fr : 214]
    }
}
