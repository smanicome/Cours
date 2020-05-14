public class ArrayShoppingCartTest {
    public static void main(String[] args) {
        Book a = new Book("Name", "Author");
        FreeShoppingCart fsc = new FreeShoppingCart();
        fsc.add(a);
        fsc.removeFirstOccurence(a);

    }
}