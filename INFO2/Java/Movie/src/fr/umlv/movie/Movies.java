package fr.umlv.movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Movies {
	static List<Movie> movies(Path path) {
		Objects.requireNonNull(path);
		
		List<Movie> movies = new ArrayList<Movie>();

        try(Stream<String> stream = Files.lines(path)) {
            movies = stream.map(Movies::fromFile).collect(Collectors.toUnmodifiableList());
        } catch (IOException e) {
            System.out.println("Reading went wrong");
            e.printStackTrace();
        }
		
		return movies;
	}
	
	static Movie fromFile(String line) {
		String[] data = line.split(";");
		ArrayList<String> actors = new ArrayList<String>();
		
		for(var i = 1; i < data.length; i++) {
			actors.add(data[i]);
		}
		
		return new Movie(data[0], actors);
	}
	
	static Map movieMap(List<Movie> movies) {
		return movies.stream()
				.collect(Collectors.toMap(Movie::title, movie -> movie));
	}
}
