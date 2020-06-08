package fr.umlv.rank;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Ranker<E> {
    private final HashMap<E, List<Review>> reviews;

    public Ranker() {
        this.reviews = new HashMap<>();
    }

    public void addEvaluation(E item, int score, String comment) {
        if (item == null || score < 0 || score > 5 || comment == null) {
            throw new IllegalArgumentException("Bad arguments");
        }

        List<Review> list = reviews.get(item);

        if (list == null) {
            list = new ArrayList<>();
            reviews.put(Objects.requireNonNull(item), list);
        }

        list.add(new Review(comment, score));

        /*
        * 3.3 La liste est triée à chaque insertion, on évite de la trier à chaque affichage
        */
        list.sort((a, b) -> {
            if (a.getValue() > b.getValue()) {
                return -1;
            } else if (a.getValue() == b.getValue()) {
                return 0;
            } else {
                return 1;
            }
        });
    }

    public void addEvaluation(E item, int score) {
        if (item == null || score < 0 || score > 5) {
            throw new IllegalArgumentException("Bad arguments");
        }

        addEvaluation(item, score, "");
    }

    public double averageScoreFor(E item) {
        Objects.requireNonNull(item);
        double score = 0.0;

        List<Review> list = reviews.get(item);

        if (list != null) {
            double sum = list.stream()
                    .mapToInt(Review::getValue)
                    .reduce(Integer::sum)
                    .getAsInt();

            score = sum / list.size();
        }

        return score;
    }

    public void printCommentsForItemWithScoreBetterOrEqualsThan(int score) {
        printCommentsForItemWithScoreBetterOrEqualsThan(elt -> elt.getValue() >= score);
    }

    public void printCommentsForItemWithScoreBetterOrEqualsThan(Predicate<Review> predicate) {
        reviews.forEach((k, v) -> {
            Stream<Review> streamV = v.stream();
            streamV = streamV.filter(predicate);

            System.out.println(k);
            streamV.forEach(System.out::println);
            System.out.println();
        });
    }
}
