package fr.umlv.movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Movies {
	static List<Movie> movies(Path path) {
		Objects.requireNonNull(path);
		
		List<Movie> movies = new ArrayList<>();

        try(Stream<String> stream = Files.lines(path)) {
            movies = stream.map(Movies::fromFile).collect(Collectors.toUnmodifiableList());
        } catch (IOException e) {
            System.out.println("Reading went wrong");
            e.printStackTrace();
        }
		
		return movies;
	}
	
	static Movie fromFile(String line) {
		Objects.requireNonNull(line);
		String[] data = line.split(";");

		ArrayList<String> actors = new ArrayList<>(Arrays.asList(data).subList(1, data.length));
		
		return new Movie(data[0], actors);
	}
	
	static Map<String, Movie> movieMap(List<Movie> movies) {
		Objects.requireNonNull(movies);
		return movies.stream()
				.collect(Collectors.toMap(Movie::title, Function.identity()));
	}

	static long numberOfUniqueActors(List<Movie> movies) {
		Objects.requireNonNull(movies);
		/*movies.stream()
			.map(Movie::actors)
			.flatMap(List::stream)
			.limit(50)
			.forEach(System.out::println);*/

		/*var actorsCount = movies.stream()
			.map(Movie::actors)
			.flatMap(List::stream)
			.count();
		 System.out.println(actorsCount);*/

		/*var actorsCount = movies.stream()
				.map(Movie::actors)
				.flatMap(List::stream)
				.collect(Collectors.toSet())
				.size();
		System.out.println(actorsCount);*/

		return movies.stream()
			.map(Movie::actors)
			.flatMap(List::stream)
			.distinct()
			.count();
	}

	static Map<String, Long> numberOfMoviesByActor(List<Movie> movies) {
		Objects.requireNonNull(movies);
		return movies.stream()
			.map(Movie::actors)
			.flatMap(List::stream)
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			//.collect(Collectors.groupingBy(e -> e, Collectors.counting()));
	}

	public static Optional<Map.Entry<String, Long>> actorInMostMovies(Map<String, Long> numberOfMoviesByActor) {
		Objects.requireNonNull(numberOfMoviesByActor);
		return numberOfMoviesByActor.entrySet().stream()
			.collect(Collectors.maxBy(Map.Entry.comparingByValue()));
	}
}
