package fr.umlv.conc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KillThemAll {
    public static void main(String[] args) {
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            int id = i;
            threads[i] = new Thread(() -> {
                int count = 0;
                while (!Thread.interrupted()) {
                    count++;
                    System.out.println("Thread " + id + ": " + count);
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            });
            threads[i].start();
        }

        System.out.println("enter a thread id:");
        try (var input = new InputStreamReader(System.in);
             var reader = new BufferedReader(input)) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    var threadId = Integer.parseInt(line);
                    if(threadId < 0 || threadId > 3) {
                        System.out.println("Thread id must be an integer between 0 and 3 included");
                    } else {
                        threads[threadId].interrupt();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Thread id must be an integer between 0 and 3 included");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
