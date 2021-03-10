package fr.umlv.conc;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class COWList<E> {
  private E[] array;
  private static final Object[] EMPTY_ARRAY = new Object[0];

  @SuppressWarnings("unchecked")
  public COWList() {
    array = (E[]) EMPTY_ARRAY;
  }

  public void add(E element) {
    var array = this.array;
    var newArray = Arrays.copyOf(array, array.length + 1);
    newArray[array.length] = element;
    this.array = newArray;
  }

  public int size() {
    return array.length;
  }

  public static void main(String[] args) throws InterruptedException {
    var list = new COWList<Integer>();
    var threads = IntStream.range(0, 4)
        .mapToObj(__ -> new Thread(() -> {
          for(var i = 0; i < 5_000; i++) {
            list.add(i);
          }
        }))
        .collect(Collectors.toList());

    for(var thread: threads) {
      thread.start();
    }

    for(var thread: threads) {
      thread.join();
    }

    System.out.println("size " + list.size());
  }
}

// add n'est pas atomique donc il y aura un accès concurrent entre les thread
