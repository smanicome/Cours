import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var book = new Book("Livre", "Auteur");
        // Le code ne fonctionner plus car Book à désormais un constructeur nécessitant deux paramêtres
        
        System.out.println(book.getTitle() + ' ' + book.getAuthor());
        // title et author sont privées à la classe Book, il faut utiliser des accesseurs
        var b1 = new Book("Da Java Code", "Duke Brown");
        var b2 = b1;
        var b3 = new Book("Da Java Code", "Duke Brown");

        System.out.println(b1 == b2);
        System.out.println(b1 == b3);
        // b1 == b2 ==> true
        // b1 == b3 ==> false
        // L'opération == compare les instances d'objets et non leurs valeurs

        var list = new ArrayList();
        list.add(b1);
        System.out.println(list.indexOf(b2));
        System.out.println(list.indexOf(b3));

        var b4 = new Book("Da Java Code");

        System.out.println(b1.toString());
        System.out.println(b4.toString());

        // La méthode indexOf de la classe ArrayList renvoie l'index du premier objet dans la liste
        // égal au paramêtre passé, s'il n'est pas dans la liste renvoie -1

        // b3, qui à les mêmes valeurs que b1, n'est pas trouvé dans la liste.
        // La méthode de Book appellée dans indexOf est equals
    }
}