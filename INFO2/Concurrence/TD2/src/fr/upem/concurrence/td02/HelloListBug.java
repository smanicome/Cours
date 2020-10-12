package fr.upem.concurrence.td02;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class HelloListBug {
    public static void main(String[] args) throws InterruptedException {
        var nbThreads = 4;
        var list = new ArrayList<Integer>();
        var threads = new Thread[nbThreads];

        IntStream.range(0, nbThreads).forEach(j -> {
            Runnable runnable = () -> {
                for (var i = 0; i < 5000; i++) {
                    list.add(i);
                }
            };

            threads[j] = new Thread(runnable);
            threads[j].start();
        });

        for (var thread : threads) {
            thread.join();
        }

        System.out.println("Element in list: " + list.size());
    }
}


// En spécifiant la taille lors de l'initialisation de list, list.add se contente d'ajouter la valeur passée en paramètre dans
// la prochaine "case" préalablement allouée. Il y a donc un accès concurrentiel à la ressource, les 4 threads appellent list.add
// avec list.size() = n, donc ils écrivent tous dans la case d'index n.

// Sans spécifier la taille, le programme réagi de la même manière, cela est dû au fait que ArrayList alloue dynamiquement la mémoire
// lorsque la taille n'est pas spécifiée ou est excédée.
// Donc supposons que nous commençions avec une liste vide, les 4 threads appellent list.add simultanément. Donc avec une taille
// initiale à 0, 4 espaces mémoire sont alloués, mais la taille elle reste à 1, donc seul le dernier espace mémoire est considéré.


