package fr.umlv.conc;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringList {
  private static final VarHandle HEAD_REF;
  static {
    MethodHandles.Lookup lookup = MethodHandles.lookup();
    try {
      HEAD_REF = lookup.findVarHandle(
        StringList.class, // classe contenant le champ
        "head", // nom du champ
        Entry.class);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  static final class Entry {
    final String element;
    Entry next;

    Entry(String element) {
      this.element = element;
    }
  }

  private final Entry head;
  // private Entry tail;

  public StringList() {
    /* tail = */ head = new Entry(null); // fake first entry
  }

  public void addLast(String element) {
    var entry = new Entry(element);
    for (;;) {
      var last = head;
      for (;;) {
        last = last.next;
        if (last == null) {
          break;
        }
      }

      if(HEAD_REF.compareAndSet(this, last, entry)) {

      }
    }
  }

  public int size() {
    var count = 0;
    for (var e = head.next; e != null; e = e.next) {
      count++;
    }
    return count;
  }

  private static Runnable createRunnable(StringList list, int id) {
    return () -> {
      for (var i = 0; i < 10_000; i++) {
        list.addLast(id + " " + i);
      }
    };
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    var threadCount = 5;
    var list = new StringList();
    var tasks = IntStream.range(0, threadCount)
        .mapToObj(id -> createRunnable(list, id))
        .map(Executors::callable)
        .collect(Collectors.toList());
    var executor = Executors.newFixedThreadPool(threadCount);
    var futures = executor.invokeAll(tasks);
    executor.shutdown();
    for(var future : futures) {
      future.get();
    }
    System.out.println(list.size());
  }
}
