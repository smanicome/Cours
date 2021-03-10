package fr.upem.concurrence.td08;

import java.util.concurrent.ArrayBlockingQueue;

public class Codex {
    public static void main(String[] args) {
        var codeQueue = new ArrayBlockingQueue<String>(10);
        var decodeQueue = new ArrayBlockingQueue<String>(10);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        codeQueue.put(CodeAPI.receive());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
               while (true) {
                   try {
                       var code = codeQueue.take();
                       System.out.println(code);
                       decodeQueue.put(CodeAPI.decode(code));
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   } catch (IllegalArgumentException e) {
                   }
               }
            }).start();
        }

        new Thread(() -> {
            try {
                CodeAPI.archive(decodeQueue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
