package fr.umlv.movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("");

        try(Stream<String> stream = Files.lines(path)) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Reading went wrong");
            e.printStackTrace();
        }
    }
}
