public class Book {
    private final String title;
    private final String author;
    private boolean receivedAuthor = true;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Book(String ptitle, String pauthor) {
        this.title = java.util.Objects.requireNonNull(ptitle);
        this.author = java.util.Objects.requireNonNull(pauthor);
    }

    public Book(String ptitle) {
        this(ptitle, "<no-author>");
        receivedAuthor = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Book)) {
            return false;
        }
        Book book = (Book) obj;
        return this.author.equals(book.author) && this.title.equals(book.title);
    }

    @Override
    public String toString() {
        String text = title;
        if (receivedAuthor) {
            text += " by " + author;
        }
        return text;
    }
    
    

    // public static void main(String[] args) {
    //     var book = new Book();
	//     System.out.println(book.title + ' ' + book.author);
    // }
    // Les variables title et author ne sont pas initialisées, donc valent null.

    // Les 4 visibilités sont: public, private, protected, default.
    // Les champs doivent être privée car autrement ils seront accessibles à tous.

    // Un accesseur est une méthode d'une classe permettant de récupérer ou modifier ses champs depuis 
    // l'extérieur sans y laisser un accès direct.
    // Puisque nous n'allons pas modifier les champs de la class Book, nous avons seulement besoin de getters.

    // Mettre les champs final permet d'éviter les modifications après initialisation.

    // Si les paramêtres ont les mêmes noms que les champs, on préfixe les champs avec this.
    // pour référencer l'objet.

    // Le compilateur utilise le constructeur qui répond à la signature demandé.
    // Donc la méthode de nom Book, avec le bon nombre d'arguments et les bons types.

    // Pour appeller le premier constructeur depuis le second on appelle this(ptitle, pauthor)

    // @Override permet d'écraser dans un classe une méthode de même signature définie dans la classe dont la
    // première hérite.

    // java.util.Objects.requireNonNull permet de vérifier qu'une valeur passée en argument n'est pas null,
    // le cas échéant jete NullPointerException

    // On peut utiliser Override sur la méthode toString, car celle-ci est hérité de la classe Object
}