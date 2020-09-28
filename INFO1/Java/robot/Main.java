import fr.umlv.fight.*;

public class Main {
    public static void main(String[] args) {
        var john = new Fighter("John", 1);
        var jane = new Fighter("Jane", 2);
        System.out.println(Arena.fight(john, jane) + " wins");
    }
}

/* 
    1. Un générateur pseudo-aléatoire permet d'obtenir une valeur calculée sur la base d'une valeur appelée seed,
       si ce dernier n'est pas défini ou reste fixe, chaque éxecution identique générera la même valeur.

    3. Un champ autre que private ou package signifie que le champ peut être modifié depuis l'extérieur à n'importe quel
       moment, ne garantissant plus la fiabilité des données.

    5. Copier - coller le code se retrouve très rapidement inefficace, a chaque modification il faut penser à répercuter.
    
    6. La méthode rollDice() n'a pas d'utilité depuis l'extérieur, mais nous ne pouvons pas la considérer comme private
       car Fighter est un réécriture de cette méthode et doit donc y avoir accès.
       La visibilité doit donc être interne au package, avec l'utilisation du mot clé protected.

    9. Le sous-typage consiste en une classe qui hérite des propriété d'une autre.
       ex: Fighter est un sous-type de Robot
       Le polymorphisme est le fait de réécrire une méthode hérité.
       ex: La méthode rollDice de la classe Fighter est une réécriture de la méthode homonyme de la class Robot     
*/