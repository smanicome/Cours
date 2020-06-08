package fr.umlv.restos.main;

import fr.umlv.rank.Ranker;
import fr.umlv.restos.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Establishment r1 = new Resto("Le p'tit creux", 25);
        Establishment r2 = new Resto("Le p'tit creux", 25, 0);
        System.out.println(r2);  // display "Le p'tit creux (25)"
        Establishment r3 = new Resto("Le Pré Catlan", 50, 3);
        System.out.println(r3);  // display "Le Pré Catlan*** (50)"
        System.out.println(r1.equals(r2)); // display "true"
        System.out.println(r1.equals(r3)); // display "false"

        System.out.println();

        Establishment b1 = new Bar("La soif", false);
        Establishment b2 = new Bar("Jusqu'à l'aube", true, 2);
        System.out.println(b1); // display "La soif (not open after eight)"
        System.out.println(b2); // display "Jusqu'à l'aube** (open after eight)"

        System.out.println(r3.stars() - b2.stars()); // display "1"

        System.out.println();

        List<Establishment> list = new ArrayList<Establishment>();
        list.add(r1);
        list.add(r3);
        list.add(b1);
        list.add(b2);
        System.out.println(list.contains(new Bar("La soif", false, 0))); // display "true"
        System.out.println(list.contains(new Bar("La soif", true, 0)));  // display "false"
        System.out.println(list.contains(new Resto("La soif", 10)));     // display "false"

        System.out.println();

        printEstablishmentsWithGivenStarNumber(list, 2);
        // display "Jusqu'à l'aube** (open after eight)"
        printEstablishmentsWithGivenStarNumber(list, 0);
        // display "Le p'tit creux (25)"
        //         "La soif (not open after eight)"

        System.out.println();

        System.out.println(list);
        list.sort( (a, b) -> {
            if (a.stars() > b.stars()) {
                return -1;
            } else if (a.stars() == b.stars()) {
                return 0;
            } else {
                return 1;
            }
        });
        System.out.println(list);
        /*
        * Pour filtrer de cette manière, je diviserais la liste en fonction des étoiles, puis trier chacune
        * des listes obtenues dans l'orde lexicographiques pour ensuite les rassembler dans l'ordre décroissant
        * du nombre d'étoiles
        */

        System.out.println();

        Ranker<Establishment> moEtGuillaut = new Ranker<>();

        moEtGuillaut.addEvaluation(r3, 3, "Not so bad");
        moEtGuillaut.addEvaluation(r3, 5, "Amazing");
        moEtGuillaut.addEvaluation(r3, 1, "So popular");
        moEtGuillaut.addEvaluation(r3, 2);

        moEtGuillaut.addEvaluation(b1, 4, "Nice and simple");
        moEtGuillaut.addEvaluation(b1, 0, "Crappy");

        moEtGuillaut.addEvaluation(b2, 1, "Expensive!");
        moEtGuillaut.addEvaluation(b2, 4, "Fashionable and funny");

        System.out.println(moEtGuillaut.averageScoreFor(r3));
        System.out.println(moEtGuillaut.averageScoreFor(r1));

        System.out.println();

        moEtGuillaut.printCommentsForItemWithScoreBetterOrEqualsThan(3);
    }

    /*public static void printEstablishmentsWithGivenStarNumber(List<Establishment> list, int stars) {
        System.out.println("List of establishments with" + stars + "stars");
        list.forEach((est) -> {
            if(stars == est.stars())
                System.out.println(est);
        });
    }*/

    public static void printEstablishmentsWithGivenStarNumber(List<Establishment> list, int stars) {
        System.out.println("List of establishments with " + stars + " stars");
        list.stream()
            .filter((est) -> stars == est.stars())
            .forEach(System.out::println);
    }
}
