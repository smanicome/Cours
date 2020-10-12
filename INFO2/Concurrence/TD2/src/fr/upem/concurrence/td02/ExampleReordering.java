package fr.upem.concurrence.td02;

class ExampleReordering {
    int a = 0;
    int b = 0;

    public static void main(String[] args) {
        var e = new ExampleReordering();
        new Thread(() -> { System.out.println("a = " + e.a + "  b = " + e.b); }).start();
        e.a = 1;
        e.b = 2;
    }
}

// Les affichages possibles sont les suivants
//   a = 1  b = 0
//   a = 0  b = 2
//   a = 1  b = 2
//   a = 0  b = 0
// La création et le lancement du thread prend du temps donc il est plus probable de voir
//   a = 1  b = 2

