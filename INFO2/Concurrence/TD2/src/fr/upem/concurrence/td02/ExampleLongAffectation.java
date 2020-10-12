package fr.upem.concurrence.td02;

class ExampleLongAffectation {
    long l = -1L;

    public static void main(String[] args) {
        var e = new ExampleLongAffectation();
        new Thread(() -> {
            System.out.println("l = " + e.l);
        }).start();
        e.l = 0;
    }
}

// Le principe est le même, la création du thread prend du temps, donc e.l est modifié avant.
// L'affichage est donc constamment 0
