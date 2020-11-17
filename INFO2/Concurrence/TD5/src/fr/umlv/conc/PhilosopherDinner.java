package fr.umlv.conc;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PhilosopherDinner {
  private final Object[] forks;

  public PhilosopherDinner(int forkCount) {
    Object[] forks = new Object[forkCount];
    Arrays.setAll(forks, i -> new Object());
    this.forks = forks;
  }

  public void eat(int index) {
    Object fork1, fork2;

    if(index == forks.length - 1) {
      fork2 = forks[index];
      fork1 = forks[(index + 1) % forks.length];
    } else {
      fork1 = forks[index];
      fork2 = forks[(index + 1) % forks.length];
    }

    synchronized (fork1) {
      synchronized (fork2) {
        System.out.println("philosopher " + index + " eat");
      }
    }
  }

  public static void main(String[] args) {
    var dinner = new PhilosopherDinner(5);
    IntStream.range(0, 5).forEach(i -> {
      new Thread(() -> {
        for (;;) {
          dinner.eat(i);
        }
      }).start();
    });
  }
}
