import java.util.Objects;

public class ArrayShoppingCart {
    private final Book[] books;
    private int nbBooks;

    public ArrayShoppingCart(int size) {
        this.books = new Book[size];
    }

    public boolean add(Book book) {    
            
        if (this.nbBooks >= this.books.length) {
            return false;
        }

        this.books[this.nbBooks] = Objects.requireNonNull(book);
        return true;
    }

    public void displayCart() {
        System.out.println("There are" + this.books.length  + "books in the cart");

        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    /**
     * returns null if less or more than 1 book has the longest title
     */
    public String longestTitle() {
        String longest = "";
        boolean unique = false;

        for (Book book : books) {
            if (book == null) {
                continue;
            }

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

// 1. Stocker le nombre maximal de livres dans un champ statique est inutile car sa valeur est variable
//    pour chaque instance de la classe ArrayShoppingCart
//
// 2. Si on ne vérifie pas dans la méthode add de la classe ArrayShoppingCart que le caddie est remplie,
//    en essayant d'ajouter plus de livre que possible l'exception IndexOutOfBoundException est jetée.
//    Pour éviter cela il suffit de stocker le nombre de livre ajouté dans un champ puis vérifier dans la méthode
//    add que cette valeur n'est pas déjà à son maximum, autrement dit égale à la taille du tableau.