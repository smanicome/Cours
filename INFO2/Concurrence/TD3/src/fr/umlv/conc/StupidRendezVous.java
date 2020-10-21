package fr.umlv.conc;

import java.util.Objects;

/**
 * Note: this code does several stupid things !
 */
public class StupidRendezVous<V> {
  private V value;
  
  public void set(V value) {
    Objects.requireNonNull(value);
    this.value = value;
  }
  
  public V get() throws InterruptedException {
    while(value == null) {
        // Thread.sleep(1);  // then comment this line !
    }
    return value;
  }
  
  public static void main(String[] args) throws InterruptedException {
    StupidRendezVous<String> rendezVous = new StupidRendezVous<>();
    new Thread(() -> {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        throw new AssertionError(e);
      }
      rendezVous.set("hello");
    }).start();
    
    System.out.println(rendezVous.get());
  }
}


// Lorsqu'on exécute ce code, un thread est créé dans le but de changer le champ value de l'objet rendezVous après 5 secondes.
// La méthode get boucle jusqu'à ce que value soit différent de null.
// Donc ce programme affiche "hello" après 5 secondes

// En commentant Thread.sleep(1) dans la méthode get l'exécution ne s'interromp plus.
// Cela est dû au fait que Thread.sleep(1) invalide le cache est permet donc de récuperer les données. Sans cela la condition
// de la boucle while est inchangée, étant vrai initialement la boucle while est infinie et get ne se termine jamais.
