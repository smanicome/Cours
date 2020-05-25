import fr.umlv.data.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> llist = new LinkedList();

        llist.add(13);
        llist.add(144);
        llist.add(123456);

        System.out.println(llist);
        System.out.println(llist.get(3));

        LinkedList<String> llist2 = new LinkedList();

        llist2.add("Premier");
        llist2.add("Deuxième");
        llist2.add("Troisième");

        System.out.println(llist2);
        System.out.println("Size of third element : "+llist2.get(3).length() );
        System.out.println("Is 13 in llist ? : "+ llist.contains(13));
    }
}
