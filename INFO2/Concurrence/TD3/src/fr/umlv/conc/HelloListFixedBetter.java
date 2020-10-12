package fr.umlv.conc;

import java.util.stream.IntStream;

/**
 * Rappeler quelles doivent être les propriétés de l'objet qui sert de lock.
 * L'objet qui sert de lock doit :
 *  - être immutable, modificateur private et final
 *  - être exclus d'interning (valeur généralisée)
 *  - être un Object, ou en hérite, non null
 *  - utilisé de telle sorte qu'il ne génère pas de blocage
 **/
public class HelloListFixedBetter {

    public static void main(String[] args) throws InterruptedException {
        var nbThreads = 4;
        var threads = new Thread[nbThreads];

        var list = new ThreadSafeList();

        IntStream.range(0, nbThreads).forEach(j -> {
            Runnable runnable = () -> {
                for (var i = 0; i < 5000; i++) {
                    list.add(i);
                }
            };

            threads[j] = new Thread(runnable);
            threads[j].start();
        });

        for (Thread thread : threads) {
            thread.join();
        }

        list.print();
    }
}