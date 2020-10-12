package fr.umlv.conc;

/**
 * Expliquer pourquoi la classe HonorBoard n'est pas thread-safe.
   * Cette classe n'est pas thread-safe car set et toString ne sont pas atomiques.
   *
   * Voici un exemple pour set:
   *  t1 modifie firstname (John)
   *  t2 modifie firstname et lastname (Jane Odd)
   *  t1 modifie lastname (Jane Doe)
   *
   * La même chose s'applique pour toString:
   *  t1 (John Doe): tmp = firstname + " "
   *  t2 (John Doe): set(Jane, Odd)
   *  t1 (Jane Odd): tmp2 = tmp + lastname
   *
   * Au final toString nous renvoie John Odd dans cet exemple
 * Maintenant que votre classe est thread-safe, peut-on remplacer la ligne :
 *  System.out.println(board);
 * par la ligne :
 *  System.out.println(board.getFirstName() + ' ' + board.getLastName());
 *
 *  Nous nous ne pouvons pas le faire car la synchronisation ne s'applique plus
 */
public class HonorBoard {
  private final Object lock = new Object();
  private String firstName;
  private String lastName;

  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }

  public void set(String firstName, String lastName) {
    synchronized (lock) {
      this.firstName = firstName;
      this.lastName = lastName;
    }
  }
  
  @Override
  public String toString() {
    synchronized (lock) {
      return firstName + ' ' + lastName;
    }
  }
  
  public static void main(String[] args) {
    HonorBoard board = new HonorBoard();
    new Thread(() -> {
      for(;;) {
        board.set("John", "Doe");
      }
    }).start();
    
    new Thread(() -> {
      for(;;) {
        board.set("Jane", "Odd");
      }
    }).start();
    
    new Thread(() -> {
      for(;;) {
        System.out.println(board);
      }
    }).start();
  }
}
