import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class FreeShoppingCart {
    private final ArrayList<Book> books;

    public FreeShoppingCart() {
        this.books = new ArrayList<Book>();
    }

    public void add(Book book) {
        books.add(Objects.requireNonNull(book));
    }

    /* public boolean removeFirstOccurence(Book book) {
        return this.books.remove(book);
    } */

    public boolean removeFirstOccurence(Book book) {
        Book b;

        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            b = iterator.next();

            if (b.equals(book)) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }
    
    /**
     * returns null if less or more than 1 book has the longest title
     */
    /* public String longestTitle() {
        String longest = "";
        boolean unique = false;
        Book book;

        for (int i = 0; i < this.books.size(); i++) {
            book = books.get(i);

            if (longest.length() < book.getTitle().length()) {
                longest = book.getTitle();
                unique = true;
            } else if (longest.length() == book.getTitle().length()) {
                unique = false;
            }
        }

        // returns null if less or more than 1 book has the longest title
        if (!unique || longest == "") {
            return null;
        }
        return longest;
    } */

    /* public String longestTitle() {
        String longest = "";
        boolean unique = false;
        Book book;

        Iterator<Book> iterator = books.iterator();

        while (iterator.hasNext()) {
            book = iterator.next();

            if (longest.length() < book.getTitle().length()) {
                longest = book.getTitle();
                unique = true;
            } else if (longest.length() == book.getTitle().length()) {
                unique = false;
            }
        }

        // returns null if less or more than 1 book has the longest title
        if (!unique || longest == "") {
            return null;
        }
        return longest;
    } */

    public String longestTitle() {
        String longest = "";
        boolean unique = false;

        for (Book book : books) {
            if (longest.length() < book.getTitle().length()) {
                longest = book.getTitle();
                unique = true;
            } else if (longest.length() == book.getTitle().length()) {
                unique = false;
            }
        }

        // returns null if less or more than 1 book has the longest title
        if (!unique || longest == "") {
            return null;
        }
        return longest;
    }
}

// 1. Pour enlever les warning on précise le type du contenu de la liste
//
// 4. Sur une collection, le compilateur se sert de l'iterateur fourni par la classe
//    Avec un array c'est plus lent car il faut que le compilateur adapte la structure pour implémenter
//    l'iterateur.
//
// 5. Dans le pire des cas la complexite est O(n)
//
// 6. L'utilisation de l'iterator rend l'execution du code plus rapide, car la méthode remove de la classe
//    ArrayList utilise une simple "for" avec index.
//    La complexite est toujours O(n) dans le pire des cas
//
// 7. On utilise "forEach" si on souhaite seulement parcourir la collection, et l'iterateur si on souhaite le
//    modifier