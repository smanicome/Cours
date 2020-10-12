package fr.upem.concurrence.td02;

public class Counter {
    private int value;

    public void add10000() {
        for (var i = 0; i < 10_000; i++) {
            value++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var counter = new Counter();
        var thread1 = new Thread(counter::add10000);
        var thread2 = new Thread(counter::add10000);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(counter.value);
    }
}

// Deux threads appellent la méthode add10000 sur un unique Counter, ils travaillent donc sur la même ressource
// Sur ma machine, le programme n'atteint jamais 20000 et ne descends pas en dessous de 10000

// Le fait que nous n'obtenons pas 20000 à chaque execution car les deux threads s'exécutent en parallèles. A certaines occasions
// la méthode est appelée simultanément pas les threads et donc récupèrent la même valeur à modifier, en considérant que value++
// n'est pas une opération atomique.
// On obtient ceci:
//      thread1 récupére la valeur actuelle de value, disons 0, et la stocke dans une variable temporaire (tmp)
//      thread2 fait de même, value = 0
//      thread1 incrémente tmp pour ensuite le réaffecter à value, value = 0 + 1
//      thread2 fait de même, value = 0 + 1
// Au final value contient 1 après deux incrémentations depuis 0, thread2 ayant un léger décalage avec thread1
// (l'inverse est aussi valable)

// Cependant, cela implique que la valeur ne pourra pas descendre en dessous de 0, car il ne peut y avoir qu'un thread "en retard"