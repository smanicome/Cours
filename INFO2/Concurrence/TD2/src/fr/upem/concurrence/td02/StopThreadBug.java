package fr.upem.concurrence.td02;

public class StopThreadBug implements Runnable {
    private boolean stop = false;

    public void stop() {
        stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
           // System.out.println("Up");
        }
        System.out.print("Done");
    }

    public static void main(String[] args) throws InterruptedException {
        var stopThreadBug = new StopThreadBug();
        new Thread(stopThreadBug::run).start();
        Thread.sleep(5_000);
        System.out.println("Trying to tell the thread to stop");
        stopThreadBug.stop();
    }
}

// Le code est supposé affiché "Up" dans un thread pendant 5 secondes puis signaler à ce thread de s'arrêter
// En retirant l'affichage de la boucle du thread, le programme ne s'arrête plus

// Il ne s'agit pas du retrait par le compilateur d'une boucle vide, car dans ce cas le progremme s'arrêterai
// Il est plus probable que le test présent dans la boucle while ne se base pas sur la bonne valeur. Il est possible
// que le compilateur remplace le champs par une valeur temporaire non-modifiée dû à la boucle vide. Ou alors il est
// possible que cela soit dû au cache qui n'est pas rafraichit.

// Le code avec affichage devrais toujours s'arrêter car System.out.println contient un bloc synchronize, ce qui devrai laisser
// le temps de rafraîchir le cache
